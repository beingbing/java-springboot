package be.springboot.pp.concurrency.threads;

public class SimpleLock {
    private boolean isLocked;
    private Thread lockedBy;
    private int count;

    public SimpleLock() {
        this.isLocked = false;
        this.lockedBy = null;
        this.count = 0;
    }

    public synchronized void lock() throws InterruptedException {
        while (isLocked && !Thread.currentThread().equals(lockedBy)) {
            System.out.println(Thread.currentThread().getName() + " needs to wait");
            wait();
        }
        isLocked = true;
        lockedBy = Thread.currentThread();
        count++;
        System.out.println(Thread.currentThread().getName() + " acquired the lock");
    }

    public synchronized void unlock() {
        if (!isLocked) return; // if no lock is acquired then no unlocking is allowed
        if (!Thread.currentThread().equals(lockedBy)) return; // malicious thread won't be allowed to unlock w/o taking lock
        count--;
        if (count == 0) {
            isLocked = false;
            lockedBy = null;
            notifyAll();
        }
        System.out.println(Thread.currentThread().getName() + " release the lock");
    }
}
