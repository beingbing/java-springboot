package be.springboot.pp.concurrency;

/*
* When you use a String type object as a Monitor, then remember that Java keep a string pool, in which
* if a string is created once and multiple variables have that string as value, then instead of each
* String type variable getting a separate value, will be getting reference to the same string object.
*
* so, even if the reference variable are different string object will be same in that case, as is
* happening in the below case with Waiter and Notifier.
* */

/*
* We also need to tackle 'spurious wakeups', it happens when a thread wakes up without receiving any
* signal. In that case, while() instead of if() becomes handy.
* */
public class StringMutex {

    public static void main(String[] args) {
        System.out.println("StringMutex: main: args: " + args + " " + Thread.currentThread().getName());

        try {
            Thread waiter = new Thread(new MutexWaiter());
            Thread notifier = new Thread(new MutexNotifier());
            waiter.start();
            Thread.sleep(1000);
            notifier.start();
        } catch (InterruptedException ex) {
            System.out.println("StringMutex: main: ex: " + ex.getMessage());
        }

        System.out.println("StringMutex: main: ends " + Thread.currentThread().getName());
    }

}

class MutexWaiter implements Runnable {

    private final String str = "abc";

    @Override
    public void run() {
        synchronized(str) {
            try {
                str.wait();
            } catch (InterruptedException e) {
                System.out.println("Waiter: ex: " + e.getMessage());
            }
            System.out.println("Waiter: run: ends");
        }
    }

}

class MutexNotifier implements Runnable {

//    private final String string = "abcdef";
    private final String string = "abc"; // when string value changed, waiter was able to receive notifier's signal.

    @Override
    public void run() {
        synchronized(string) {
            System.out.println("Notifier: run: starts");
            string.notify();
            System.out.println("Notifier: run: ends");
        }
    }

}