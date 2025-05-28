package be.springboot.pp.concurrency;

// simultaneous reads are allowed
// when writing is done both reading and writing not allowed
// this feature can't be enforced by built-in tools, we always need to design a solution for our own.
public class ReadWriteLock {
    private int writers, readers, writeReq;

    public ReadWriteLock() {
        this.writers = 0;
        this.readers = 0;
        this.writeReq = 0;
    }

    public synchronized void lockRead() throws InterruptedException {
        while (writers > 0 || writeReq > 0) {
            wait();
        }

        readers++;
    }

    public synchronized void unlockRead() {
        readers--;
        notifyAll();
    }

    public synchronized void lockWrite() throws InterruptedException {
        writeReq++;
        while (readers > 0 || writers > 0) {
            wait();
        }
        writeReq--;
        writers++;
    }

    public synchronized void unlockWrite() {
        writers--;
        notifyAll();
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
            return name;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlockRead();
        }
    }

    public void write() throws InterruptedException {
        lock.lockWrite();
        try {
            name += "bla"; // changes are done outside synchronized block hence memory visibility issues may appear
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlockWrite();
        }
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
        System.out.println("writer wrote");
    }
}

class Tester {
    public static void main(String[] args) {
        ReadWriteLock lock = new ReadWriteLock();
        Store store = new Store(lock);
        Thread r1 = new Thread(new Reader(store));
        Thread r2 = new Thread(new Reader(store));
        Thread r3 = new Thread(new Reader(store));
        Thread r4 = new Thread(new Reader(store));
        Thread r5 = new Thread(new Reader(store));
        Thread r6 = new Thread(new Reader(store));
        Thread w1 = new Thread(new Writer(store));
        r1.start();
        r2.start();
        r3.start();
        w1.start(); // writer is starving
        r4.start();
        r5.start();
        r6.start();
    }
}