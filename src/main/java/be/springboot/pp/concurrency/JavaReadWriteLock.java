package be.springboot.pp.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class JavaReadWriteLock {
    //
}
// HW: attempt to use tryLock to escape reader-reader access upgrade deadlock
class JavaStore {
    private volatile String name;
    private final ReadWriteLock lock;
    private final Lock readLock, writeLock;

    public JavaStore() {
        this.lock = new ReentrantReadWriteLock();
        this.readLock = lock.readLock();
        this.writeLock = this.lock.writeLock();
        this.name = "bla";
    }

    public String read() throws InterruptedException {
        readLock.lock();
        try {
            String val = name;
            Thread.sleep(1000); // getting into deadlock
            log(val);
            return val;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            readLock.unlock();
        }
    }

    public void log(String name) throws InterruptedException {
        writeLock.lock();
        try {
            System.out.println("log printed: " + name);
        } catch (Exception e) {
            //
        } finally {
            writeLock.unlock();
        }
    }

    public void write() throws InterruptedException {
        writeLock.lock();
        try {
            name += "bla"; // changes are done outside synchronized block hence memory visibility issues may appear
            action();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            writeLock.unlock();
        }
    }

    public void action() throws InterruptedException {
        readLock.lock();
        readLock.unlock();
    }
}