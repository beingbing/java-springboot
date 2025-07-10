package be.springboot.pp.concurrency.threads;

import java.util.HashMap;
import java.util.Map;

public class MyReadWriteLock {
    private int writerReentrancyCount, writeReq;
    // Store threads entered as readers, maintain count if they reenter. Hence keeping a map
    private final Map<Thread, Integer> enteredReaders;
    private Thread enteredWriter;

    public MyReadWriteLock() {
        this.writerReentrancyCount = 0;
        this.writeReq = 0;
        this.enteredReaders = new HashMap<>();
        this.enteredWriter = null;
    }

    private boolean allowedReadAccess() {
        if (Thread.currentThread().equals(enteredWriter)) return true; // enough to switch from write to read
        if (writerReentrancyCount > 0) return false;
        if (enteredReaders.containsKey(Thread.currentThread())) return true;
        if (writeReq > 0) return false;
        return true;
    }

    public synchronized void lockRead() throws InterruptedException {
        while (!allowedReadAccess()) wait();
        System.out.println(Thread.currentThread().getName() + " acquired read-lock");
        Integer cnt = enteredReaders.getOrDefault(Thread.currentThread(), 0);
        enteredReaders.put(Thread.currentThread(), cnt+1);
    }

    public synchronized void unlockRead() {
        if (!enteredReaders.containsKey(Thread.currentThread()))
            throw new RuntimeException(Thread.currentThread().getName() + " don't have read lock, but still trying to unlock");
        Integer cnt = enteredReaders.get(Thread.currentThread());
        if (cnt > 1) enteredReaders.put(Thread.currentThread(), cnt-1);
        else {
            enteredReaders.remove(Thread.currentThread());
            notifyAll();
        }
        System.out.println(Thread.currentThread().getName() + " released read-lock");
    }

    private boolean allowWriteAccess() {
        // to allow write access to a single existing reader
        if (enteredReaders.size() == 1 && enteredReaders.get(Thread.currentThread()) != null) return true;
        if (!enteredReaders.isEmpty()) return false;
        if (enteredWriter == null) return true;
        if (Thread.currentThread().equals(enteredWriter)) return true;
        return false;
    }

    public synchronized void lockWrite() throws InterruptedException {
        writeReq++;
        while (!allowWriteAccess()) wait();
        writeReq--;
        enteredWriter = Thread.currentThread();
        writerReentrancyCount++;
    }

    public synchronized void unlockWrite() {
        if (!Thread.currentThread().equals(enteredWriter))
            throw new RuntimeException(Thread.currentThread().getName() + " don't have read lock, but still trying to unlock");
        writerReentrancyCount--;
        if (writerReentrancyCount == 0) {
            enteredWriter = null;
            notifyAll();
        }
        System.out.println(Thread.currentThread().getName() + " released write-lock");
    }
}

class Store {
    private volatile String name;
    private final MyReadWriteLock lock;

    public Store(MyReadWriteLock lock) {
        this.lock = lock;
        this.name = "bla";
    }

    public String read() throws InterruptedException {
        lock.lockRead();
        try {
            String val = name;
            Thread.sleep(1000); // getting into deadlock
            log(val); // read reentrancy
            return val;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlockRead();
        }
    }

    public void log(String name) throws InterruptedException {
        lock.lockRead();
        try {
            System.out.println("log printed: " + name);
        } catch (Exception e) {
            //
        } finally {
            lock.unlockRead();
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
        lock.lockWrite();
        System.out.println("action done after acquiring reentrancy write lock");
        lock.unlockWrite();
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
        System.out.println(Thread.currentThread().getName() + " writer wrote");
    }
}

class Tester {
    public static void main(String[] args) {
        MyReadWriteLock lock = new MyReadWriteLock();
        Store store = new Store(lock);
        Thread r1 = new Thread(new Reader(store));
        Thread w1 = new Thread(new Writer(store));
        Thread r2 = new Thread(new Reader(store));
        Thread w2 = new Thread(new Writer(store));
        r1.start();
        w1.start();
        r2.start();
        w2.start();
    }
}
