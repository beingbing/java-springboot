package be.springboot.pp.concurrency;

/*
* synchronized keyword make lock to be acquired on current object hence both lock()
* and unlock() will be synchronized against the same object.
* */

public class SimpleLock {
    private boolean isLocked;

    public SimpleLock() {
        this.isLocked = false;
    }

    public synchronized void lock() throws InterruptedException {
        while (isLocked) {
            System.out.println(Thread.currentThread().getId() + " needs to wait");
            wait();
        }
        isLocked = true;
        System.out.println(Thread.currentThread().getId() + " acquired the lock");
    }

    // unlock can be acquired on same object by any other thread as well to release the lock. We need to fix this issue.
    public synchronized void unlock() {
        isLocked = false;
        notifyAll();
        System.out.println(Thread.currentThread().getId() + " release the lock");
    }
}

class SimpleWorker implements Runnable {
    @Override
    public void run() {
        try {
            lock.lock();
            Thread.sleep(1000);
            lock.unlock();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private final SimpleLock lock;

    SimpleWorker(SimpleLock lock) {
        this.lock = lock;
    }
}

class SimpleExecutor {
    public static void main(String[] args) {
        SimpleLock lock = new SimpleLock();
        Thread t1 = new Thread(new SimpleWorker(lock));
        Thread t2 = new Thread(new SimpleWorker(lock));
        Thread t3 = new Thread(new SimpleWorker(lock));
        t1.start();
        t2.start();
        t3.start();
    }
}