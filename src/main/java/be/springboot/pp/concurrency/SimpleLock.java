package be.springboot.pp.concurrency;

/*
 * synchronized keyword make lock to be acquired on current object hence both lock()
 * and unlock() will be synchronized against the same object.
 * */

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
            System.out.println(Thread.currentThread().getId() + " needs to wait");
            wait();
        }
        isLocked = true;
        lockedBy = Thread.currentThread();
        count++;
        System.out.println(Thread.currentThread().getId() + " acquired the lock");
    }

    // unlock can be acquired on same object by any other thread as well to release the lock. We need to fix this issue.✅
    public synchronized void unlock() {
        if (!isLocked) return; // if no lock is acquired then no unlocking is allowed
        if (!Thread.currentThread().equals(lockedBy)) return; // malicious thread trying to unlocking w/o locking
        count--;
        if (count == 0) {
            isLocked = false;
            lockedBy = null;
            notifyAll();
        }
        System.out.println(Thread.currentThread().getId() + " release the lock");
    }
}

// w/o acquiring any lock, it will be able to release a lock on simpleLock object acquired by anyone.
class MaliciousWorker implements Runnable {
    private final SimpleLock lock;

    MaliciousWorker(SimpleLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            lock.lock();
        } catch (Exception e) {
            //
        } finally {
            lock.unlock();
        }
    }
}

class SimpleWorker implements Runnable {
    private final SimpleLock lock;

    SimpleWorker(SimpleLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            Thread.sleep(1000);
            start();
            lock.unlock();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void start() {
        try {
            lock.lock();
            end();
        } catch (Exception e) {
            System.out.println("from start: " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public void end() {
        try {
            lock.lock();
        } catch (Exception e) {
            System.out.println("from end: " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }
}

class SimpleExecutor {
//    public static void main(String[] args) {
//        SimpleLock lock = new SimpleLock();
//        Thread t1 = new Thread(new SimpleWorker(lock));
//        Thread t2 = new Thread(new SimpleWorker(lock));
//        Thread t3 = new Thread(new SimpleWorker(lock));
//        t1.start();
//        t2.start();
//        t3.start();
//    }

    // t2 is releasing lock which was acquired by t1
    public static void main(String[] args) {
        SimpleLock lock = new SimpleLock();
        Thread t1 = new Thread(new SimpleWorker(lock));
        Thread t2 = new Thread(new MaliciousWorker(lock));
        t1.start();
        t2.start();
    }
}