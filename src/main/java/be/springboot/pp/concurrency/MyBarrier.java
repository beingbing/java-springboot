package be.springboot.pp.concurrency;

public class MyBarrier {
    private final int numThreads;
    private int count, exits;
    private final Runnable barrierTask;
    private boolean isReady; // to check if we are ready for fresh iteration

    public MyBarrier(int numThreads, Runnable barrierTask) {
        this.numThreads = numThreads;
        this.count = numThreads;
        this.barrierTask = barrierTask;
        this.exits = 0;
        this.isReady = true;
    }

    public synchronized void await() throws InterruptedException {
        while (!isReady) wait(); // to prevent threads to call await again.
        count--; // tracking how many threads arrived till now
        if (count > 0) { // n-1 threads will enter
            while (count > 0) wait();
        }
        else { // nth thread will go in else
            // we can only notify here that all threads reached
            // we cannot reset the count variable yet
            // because it won't let other threads break-free from while-loop inside if
            barrierTask.run();
            isReady = false;
            notifyAll();
        }
        exits++; // so we introduce exits, to separately track threads breaking-free from loop
        // then resetting count to reactivate the while loop hurdle.
        if (exits == numThreads) {
            count = numThreads; // barrier getting reset after each round, hence cyclic
            exits = 0;
            isReady = true;
            notifyAll();
        }
        // still the problem is not solved, what if nth thread being last in first iteration
        // got again scheduled and is first thread of second iteration.
        // keep in mind, no other thread is woken up yet to exit while-loop but nth thread
        // entered again to further reduce count from 0 to -1. thi will be a problem.
        // To resolve this we need an indicator to prevent threads which exited to get rescheduled
        // before all the threads had a chance to exit.
    }
}

// Problem statement:
// given 4 threads, each is a god. So, every thread is capable of making a man or a woman.
// They decided to make a population of 100 people. Each god given responsibility of 25 people.
// We represent man by 1 and woman by 0. Every god fills a particular chunk with 0 or 1 randomly.
// Overall, if the count of 0 and 1 is not found to be equal, then we will run the iteration again.
