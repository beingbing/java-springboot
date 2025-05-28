package be.springboot.pp.concurrency;

import java.util.HashMap;
import java.util.Map;

// simultaneous reads are allowed
// when writing is done both reading and writing not allowed
// this feature can't be enforced by built-in tools, we always need to design a solution for our own.
public class ReadWriteLock {
    private int writerEntryCount, writeReq;

    /*
    Store threads entered as readers, maintain count if they reenter. Hence keeping a map
    * */
    private final Map<Thread, Integer> readerEntracy;
    private Thread enteredWriter;

    public ReadWriteLock() {
        this.writerEntryCount = 0;
        this.writeReq = 0;
        this.readerEntracy = new HashMap<>();
        this.enteredWriter = null;
    }

    private boolean allowReadAccess() {
        if (Thread.currentThread().equals(enteredWriter)) return true; // enough to switch from write to read
        if (readerEntracy.containsKey(Thread.currentThread())) return true;
        if (writerEntryCount > 0 || writeReq > 0) return false;
        return true;
    }

    private boolean allowWriteAccess() {
        // to allow write access to a single existing reader
        if (readerEntracy.size() == 1 && readerEntracy.get(Thread.currentThread()) != null) return true;
        if (!readerEntracy.isEmpty()) return false;
        if (enteredWriter == null) return true;
        if (Thread.currentThread().equals(enteredWriter)) return true;
        return false;
    }

    public synchronized void lockRead() throws InterruptedException {
        while (!allowReadAccess()) {
            wait();
        }
        System.out.println(Thread.currentThread().getId() + " acquired read-lock");
//        readers++;
        Integer cnt = readerEntracy.getOrDefault(Thread.currentThread(), 0);
        readerEntracy.put(Thread.currentThread(), cnt+1);
    }

    public synchronized void unlockRead() {
        if (!readerEntracy.containsKey(Thread.currentThread())) return;
        Integer cnt = readerEntracy.get(Thread.currentThread());
        if (cnt > 1) readerEntracy.put(Thread.currentThread(), cnt-1);
        else {
            readerEntracy.remove(Thread.currentThread());
            notifyAll();
        }
        System.out.println(Thread.currentThread().getId() + " released read-lock");
    }

    public synchronized void lockWrite() throws InterruptedException {
        writeReq++;
        while (!allowWriteAccess()) wait();
        System.out.println(Thread.currentThread().getId() + " acquired write-lock");
        writeReq--;
        enteredWriter = Thread.currentThread();
        writerEntryCount++;
    }

    public synchronized void unlockWrite() {
        if (!Thread.currentThread().equals(enteredWriter)) return;
        writerEntryCount--;
        if (writerEntryCount == 0) {
            enteredWriter = null;
            notifyAll();
        }
        System.out.println(Thread.currentThread().getId() + " released write-lock");
    }
}

class Store {
    private volatile String name;
    private final ReadWriteLock lock;

    public Store(ReadWriteLock lock) {
        this.lock = lock;
        this.name = "bla";
    }

    public String read() throws InterruptedException {
        lock.lockRead();
        try {
            String val = name;
            Thread.sleep(1000); // getting into deadlock
            log(val);
            return val;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlockRead();
        }
    }

    public void log(String name) throws InterruptedException {
        lock.lockWrite();
        try {
            System.out.println("log printed: " + name);
        } catch (Exception e) {
            //
        } finally {
            lock.unlockWrite();
        }
    }

    public void write() throws InterruptedException {
        lock.lockWrite();
        try {
            name += "bla"; // changes are done outside synchronized block hence memory visibility issues may appear
            action();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlockWrite();
        }
    }

    public void action() throws InterruptedException {
        lock.lockRead();
        lock.unlockRead();
    }
}

class Reader implements Runnable {
    private final Store store;

    public Reader(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        String name = null;
        try {
            name = store.read();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Read " + name);
    }
}

class Writer implements Runnable {
    private final Store store;

    Writer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        try {
            store.write();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getId() + " writer wrote");
    }
}

//class Tester {
//    public static void main(String[] args) {
//        ReadWriteLock lock = new ReadWriteLock();
//        Store store = new Store(lock);
//        Thread r1 = new Thread(new Reader(store));
//        Thread r2 = new Thread(new Reader(store));
//        Thread r3 = new Thread(new Reader(store));
//        Thread r4 = new Thread(new Reader(store));
//        Thread r5 = new Thread(new Reader(store));
//        Thread r6 = new Thread(new Reader(store));
//        Thread w1 = new Thread(new Writer(store));
//        r1.start();
//        r2.start();
//        r3.start();
//        w1.start(); // writer is starving
//        r4.start();
//        r5.start();
//        r6.start();
//    }
//}

// reader reentering alone: fine
// reader reentring along with writer: fine
class Tester {
    public static void main(String[] args) {
        ReadWriteLock lock = new ReadWriteLock();
        Store store = new Store(lock);
        Thread r1 = new Thread(new Reader(store));
        Thread w1 = new Thread(new Writer(store));
//        Thread r2 = new Thread(new Reader(store));
//        Thread w2 = new Thread(new Writer(store));
        r1.start();
        w1.start();
//        r2.start();
//        w2.start();
    }
}
/*
* here deadlock is because -
* r1 entered
* w1 entered, blocked any further reader entry
* r1 trying to reacquire lock, won't leave unless it does so.
* So, we are in a deadlock. Reader attempting reentrance, writer blocked further readers.
* Solution: block new readers entry, reentry of existing reader should be allowed.
* */

/*
* If thread switches behavior from write -> read: downgraded
* If thread switches behavior from read -> write: upgraded
*
* We allow a reader to acquire a write lock iff it is the only reader
*
* In case of two readers requesting write access together, both of them can still stuck in deadlock.
* That's why in many languages whenever a reader requests write access, it is enforced that first
* read access is relinquished.
* */