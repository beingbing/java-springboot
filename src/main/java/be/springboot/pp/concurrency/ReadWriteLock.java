package be.springboot.pp.concurrency;

// simultaneous reads are allowed
// when writing is done both reading and writing not allowed
// this feature can't be enforced by built-in tools, we always need to design a solution for our own.
public class ReadWriteLock {
    private int writers, readers;

    public ReadWriteLock() {
        this.writers = 0;
        this.readers = 0;
    }

    public synchronized void lockRead() throws InterruptedException {
        while (writers > 0) {
            wait();
        }

        readers++;
    }

    public synchronized void unlockRead() {
        readers--;
        notifyAll();
    }

    public synchronized void lockWrite() throws InterruptedException {
        while (readers > 0 || writers > 0) {
            wait();
        }
        writers++;
    }

    public synchronized void unlockWrite() {
        writers--;
        notifyAll();
    }
}
