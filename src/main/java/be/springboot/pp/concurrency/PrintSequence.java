package be.springboot.pp.concurrency;

/*
* as soon as t1.start() is invoked, we have 2 independent threads for running concurrently.
* as soon as t2.start() is invoked, we have 3 independent threads for running concurrently.
*
* all three of them will be concurrently running, CPU scheduler will be scheduling them
* in a time-slice manner.
*
* so, output cannot be predicted as CPU scheduler may scheduler all three threads differently
* every time the program is executed.
* */
public class PrintSequence {

    /*
    * main thread execution -
    * - print "PrintSequence: main: args: " + args
    * - create Object of type Thread and store its reference in t1
    * - create Object of type Thread and store its reference in t2
    * - call start() of Thread class on t1
    * - call start() of Thread class on t2
    * - start a for loop to print numbers from 100 to 109
    *   - print 100 + " " + Thread.currentThread().getName()
    *   - print 101 + " " + Thread.currentThread().getName()
    *   - print 102 + " " + Thread.currentThread().getName()
    *   - print 103 + " " + Thread.currentThread().getName()
    *   - print 104 + " " + Thread.currentThread().getName()
    *   - print 105 + " " + Thread.currentThread().getName()
    *   - print 106 + " " + Thread.currentThread().getName()
    *   - print 107 + " " + Thread.currentThread().getName()
    *   - print 108 + " " + Thread.currentThread().getName()
    *   - print 109 + " " + Thread.currentThread().getName()
    * -print "PrintSequence: main: ends"
    * */
    public static void main(String[] args) {
        System.out.println("PrintSequence: main: args: " + args);
        Thread t1 = new Thread(new Sequencer());
        Thread t2 = new Thread(new ReverseSequencer());
        t1.start();
        t2.start();
        for (int i = 100; i < 110; i++)
            System.out.println(i + " " + Thread.currentThread().getName());
        System.out.println("PrintSequence: main: ends");
    }
}

/*
* calling start() on t1 will schedule a separate task to be executed by the JVM.
* JVM will pick the task, see it as of type Runnable and invoke run() on it.
* - for loop starts to print numbers from 0 to 9
*   - print 0 + " " + Thread.currentThread().getName()
*   - print 1 + " " + Thread.currentThread().getName()
*   - print 2 + " " + Thread.currentThread().getName()
*   - print 3 + " " + Thread.currentThread().getName()
*   - print 4 + " " + Thread.currentThread().getName()
*   - print 5 + " " + Thread.currentThread().getName()
*   - print 6 + " " + Thread.currentThread().getName()
*   - print 7 + " " + Thread.currentThread().getName()
*   - print 8 + " " + Thread.currentThread().getName()
*   - print 9 + " " + Thread.currentThread().getName()
* */
class Sequencer implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++)
            System.out.println(i + " " + Thread.currentThread().getName());
    }
}

/*
 * calling start() on t2 will schedule a separate task to be executed by the JVM.
 * JVM will pick the task, see it as of type Runnable and invoke run() on it.
 * - for loop starts to print numbers from 50 to 41
 *   - print 50 + " " + Thread.currentThread().getName()
 *   - print 49 + " " + Thread.currentThread().getName()
 *   - print 48 + " " + Thread.currentThread().getName()
 *   - print 47 + " " + Thread.currentThread().getName()
 *   - print 46 + " " + Thread.currentThread().getName()
 *   - print 45 + " " + Thread.currentThread().getName()
 *   - print 44 + " " + Thread.currentThread().getName()
 *   - print 43 + " " + Thread.currentThread().getName()
 *   - print 42 + " " + Thread.currentThread().getName()
 *   - print 41 + " " + Thread.currentThread().getName()
 * */
class ReverseSequencer implements Runnable {
    @Override
    public void run() {
        for (int i = 50; i > 40; i--)
            System.out.println(i + " " + Thread.currentThread().getName());
    }
}
