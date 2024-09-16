package be.springboot.pp.concurrency.synchronization;

public class NumberStore {

    private int number;

    public NumberStore() {
        this.number = 0;
    }

    public synchronized void increment() {
        // critical section
        /*
        * its 3-step process, which is not atomic -
        * - fetch current value
        * - update value
        * - replaces old/stale value with new/updated value
        * */
        // as all threads were given same instance of this class, hence there is only one 'this' variable across threads.
        // there is a short cut as well, to place 'synchronized' in definition itself.
//        synchronized(this) { // every thread encountering this line will get hold of 'this' variable and make it unavailable for other threads
            this.number++;
//        }   // this variable will be unavailable for other threads until here.
    }

    public int getNumber() {
        return this.number;
    }
}

class NumberWorker implements Runnable {

    private final NumberStore numberStore;

    public NumberWorker(NumberStore numberStore) {
        this.numberStore = numberStore;
    }

    @Override
    public void run() {
        System.out.println("NumberWorker: run: " + Thread.currentThread().getName());
        for (int i = 0; i < 1_000_000; i++) {
            numberStore.increment();
        }
        System.out.println("NumberWorker: run: ends " + Thread.currentThread().getName());
    }

}

class Main {

    public static void main(String[] args) {
        System.out.println("Main: main: args: " + args + " " + Thread.currentThread().getName());

        try {
            NumberStore numberStore = new NumberStore();
            /*
            * We initiated two threads to make change to shared resource NumberStore,
            * and we will wait both of them to finish before logging NumberStore value
            * to check the process completion value. Expected is 2_000_000;
            * */
            Thread t1 = new Thread(new NumberWorker(numberStore));
            Thread t2 = new Thread(new NumberWorker(numberStore));
            t1.start();
            t2.start();
            t1.join(); // wait for t1 to finish
            t2.join(); // wait for t2 to finish
            /*
            * When we logged the value, it is not what we expected. This was because NumberStore's increment()
            * is not a thread-safe operation, it had racing condition, in which one thread read the value to
            * increment, and another thread increment the value, before the first thread could write the value
            * after doing operation. This inconsistency leads to wrong value. It also highlights the need to
            * have a setup which facilitates thread-safe operation on shared resource. Makers of Java realized
            * this and hence created some special wrappers around primitive types for thread-safe operations, and
            * their naming convention follows the nomenclature of prefixing "Atomic" with primitive type name.
            *
            * Also, the result was different every time the code ran because thread scheduling and time-slice value
            * is not deterministic, hence thread execution is not predictable as well.
            * */
            System.out.println("Main: main: numberStore: " + numberStore.getNumber());
        } catch (InterruptedException ex) {
            System.out.println("Main: main: exception: " + ex.getMessage() + " " + Thread.currentThread().getName());
        }
        System.out.println("Main: main: ends " + Thread.currentThread().getName());
    }
}