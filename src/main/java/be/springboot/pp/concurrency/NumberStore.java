package be.springboot.pp.concurrency;
// 3, 8

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
             * after doing operation. This inconsistency leads to wrong value.
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

public class NumberStore {

    public synchronized void increment() {
        // critical section
        /*
         * its 3-step process, which is not atomic -
         * - fetch current value
         * - update value
         * - replaces old/stale value with new/updated value
         * */
        /*
         * It also highlights the need to have a setup which facilitates thread-safe increment operation on shared primitives.
         * Makers of Java realized this and hence created some special wrappers around primitive types for
         * thread-safe operations, and their naming convention follows the nomenclature of prefixing "Atomic" with primitive type name.
        * */
        // as all threads were given same instance of this class, hence there is only one 'this' variable across threads
//        synchronized(this) { // every thread encountering this line will get hold of 'this' variable and make it unavailable for other threads
        this.number++;
//        }   // this variable will be unavailable for other threads until here.
        // there is a short cut as well, to place 'synchronized' in definition itself.
    }
    // the above function is equivalent to declaring 'number' variable as below -
    // private AtomicInteger number = new AtomicInteger(0);

    // -------------------------------------------------------------------------------------

    /*
    * Although we didn't discuss it while going through race-condition cases,
    * but memory visibility issue can happen here as well.
    *
    * But as we mentioned, volatile is rarely used in production code, so
    * what is being done if not this to remedy memory visibility problem ?
    *
    * Synchronization is our saviour here as well. When a writer thread acquires
    * lock and make changes, it synchronized block makes sure that memory flush
    * happen, hence if in consecutive turns later reader threads comes in then
    * the value read will definitely be the correct and most recent value.
    *
    * This signifies that we can do away with volatile keyword if shared resource
    * is locked in under synchronized keyword for both reading and writing operation
    * as well.
    * */
//    private volatile int number;
    private int number;

    public NumberStore() {
        this.number = 0;
    }

    /*
    * This is the benefit of synchronized over other locking mechanisms,
    * it comes with in-build memory visibility problem resolution as well.
    * As locking mechanism ensures that once writer threads done updation,
    * next reader thread will get the updated value. Hence, synchroized
    * keyword ensures memory flushing which volatile keyword could not, as
    * it only prevents thread from making a local copy of RAM variable in
    * core memory space. As volatile was interfering in working methodology
    * of cores hence it is not used, instead synchronized is used to enforce
    * flushing of memory, which was the intentional requirement.
    * */
    public synchronized int getNumber() {
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
