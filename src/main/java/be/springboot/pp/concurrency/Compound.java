package be.springboot.pp.concurrency;

public class Compound {

    private int number;

    public Compound() {
        this.number = 0;
    }

    public synchronized int getNumber() {
        return number;
    }

    public synchronized void setNumber(int val) {
        number = val;
    }
}

class CompoundWorker implements  Runnable {
    private final Compound number;

    public CompoundWorker(Compound number) {
        this.number = number;
    }

    @Override
    public void run() {
        /*
        * We are doing compound action (an action composed of multiple different actions)
        * although both the actions are independently thread-safe, but that do not mean
        * that the compound action will be thread-safe too. hence, the situation is -
        * thread-safe(action1) + thread-safe(action2) != thread-safe(compoundAction)
        *
        * so, introducing more thread-safe actions in compound-action process won't resolve it,
        * instead if you want compound-action to be thread-safe then make it so.
        *
        * Take-away is to identify the compound-action which needs to be thread-safe.
        * */

        /*
        * Here, i also need to mention that, the thread is re-entering the same synchronized block.
        * first when increment process was to be done, second when getting/setting the value was done.
        * It is allowed, but what we need to be careful about is, the same number of times a lock
        * is acquired, it needs to be released the exact same number of times.
        * This process terms the locking and unlocking as reentrant in nature and is in a way a
        * unique capability of the lock, hence, the locks which have this ability are called
        * reentrant-locks. Reentrancy can happen any number of times.
        * */
        synchronized(this) {
            int oldVal = number.getNumber();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            number.setNumber(oldVal + 1);
        }
    }
}

class Run {

    public static void main(String[] args) {
        System.out.println("Run: main: args: " + args + " " + Thread.currentThread().getName());

        try {
            Compound number = new Compound();
            Thread t1 = new Thread(new CompoundWorker(number));
            Thread t2 = new Thread(new CompoundWorker(number));
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println("Run: main: number: " + number.getNumber());
        } catch (InterruptedException ex) {
            System.out.println("Run: main: ex: " + ex.getMessage());
        }
        System.out.println("Run: main: ends " + Thread.currentThread().getName());
    }
}