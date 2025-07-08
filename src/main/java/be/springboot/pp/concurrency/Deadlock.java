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

Whenever there is a cycle dependency in terms of resource acquisition across threads, deadlock occur.
Solution is to acquire resources in a definite order.

If object on which locks to be acquired are not our native objects and also do not have any unique identification
parameter, then we can use hashcode of objects to establish an order.
The issue with this is there can come a scenario where 2 objects can have same hash-code. This is very rare.
But in such scenarios, we acquire lock on some third object called as tiebreaker lock, then on the intended
two locks. This way, both of them wont go in deadlock. because tiebreaker will be exclusive among those two.

Alien method calls leading to deadlocks -
It is advised that if you are invoking a user-defined method not known to you then relinquish all the locks
before doing so. Because it may contain further locking on some other object and you get into nested locking
scenario unintentionally. Which can lead to deadlock with some other thread.

Nested Monitor lockout -
Object1 acquiring lock1, then lock2 and then going to sleep after releasing lock2.
Object2 needs to wake object1, but for that it needs to acquire lock1.
This scenario is not a proper deadlock, as lock2 which is needed to wake up obj1 is still not
held by anyone, so if it is notified by any third object, obj1 will wake up and everything will
get sorted out.
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
