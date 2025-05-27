package be.springboot.pp.concurrency;

 /* Like nested loops we can have nested synchronized blocks as well. But
 Same lock when acquired in reverse by any other thread leads to deadlock.
 As now both threads are waiting for other to leave the remaining lock.

This is our third and final evil -
- Race condition
- memory visibility issues
- deadlock

To escape this cyclic dependency, every thread if require same set of locks
then should acquire in same order.

Deadlock is not a 2 thread thing. It can happen due to n numbers of threads,
all locking each other due to a cyclic dependency for some resource.

In general, acquisition of resources should be done in a globally defined manner.
 */

public class Deadlock {
    public static void main(String[] args) {
        MyLock lock1 = new MyLock(1);
        MyLock lock2 = new MyLock(2);
        MyLock lock3 = new MyLock(3);
        Thread t1 = new Thread(new Run1(lock1, lock2));
        Thread t2 = new Thread(new Run2(lock2, lock3));
        Thread t3 = new Thread(new Run3(lock1, lock3));
        t1.start();
        t2.start();
        t3.start();
    }
}

class MyLock {
    private final int id;

    MyLock(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

class Run1 implements Runnable {
    private final MyLock lock1, lock2;

    Run1(MyLock lock1, MyLock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        MyLock l1, l2;
        if (lock1.getId() < lock2.getId()) {
            l1 = lock1;
            l2 = lock2;
        } else {
            l1 = lock2;
            l2 = lock1;
        }
        synchronized(l1) {
            for (int x = 0; x < 10000000; x++) {}
            synchronized(l2) {
                System.out.println("Run1 printed");
            }
        }
    }
}

class Run2 implements Runnable {
    private final MyLock lock1, lock2;

    Run2(MyLock lock1, MyLock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        MyLock l1, l2;
        if (lock1.getId() < lock2.getId()) {
            l1 = lock1;
            l2 = lock2;
        } else {
            l1 = lock2;
            l2 = lock1;
        }
        synchronized(l1) {
            for (int x = 0; x < 10000000; x++) {}
            synchronized(l2) {
                System.out.println("Why Run2 Why");
            }
        }
    }
}

class Run3 implements Runnable {
    private final MyLock lock1, lock2;

    Run3(MyLock lock1, MyLock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        MyLock l1, l2;
        if (lock1.getId() < lock2.getId()) {
            l1 = lock1;
            l2 = lock2;
        } else {
            l1 = lock2;
            l2 = lock1;
        }
        synchronized(l1) {
            for (int x = 0; x < 10000000; x++) {}
            synchronized(l2) {
                System.out.println("Why Run3 Why!!!");
            }
        }
    }
}
