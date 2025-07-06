package be.springboot.pp.concurrency;

import java.util.Arrays;

// 12
class SampleWaiter implements Runnable {
    private final Object monitor;

    public SampleWaiter(Object monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        synchronized(monitor) {
            System.out.println("SampleWaiter: run: " + Thread.currentThread().getName() + " starts");
            try {
                monitor.wait();
                System.out.println("SampleWaiter: run: " + Thread.currentThread().getName() + " waiting");
                monitor.wait(2000);
            } catch (InterruptedException ex) {
                System.out.println("SampleWaiter: ex: " + ex.getMessage());
            }
            System.out.println("SampleWaiter: run: " + Thread.currentThread().getName() + " ends");
        }
    }
}

class SampleNotifier implements Runnable {
    private final Object monitor;

    public SampleNotifier(Object monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        synchronized(monitor) {
            System.out.println("SampleNotifier: run: " + Thread.currentThread().getName() + " starts");
            monitor.notifyAll();
            System.out.println("SampleNotifier: run: " + Thread.currentThread().getName() + " ends");
        }
    }

}

public class SampleMonitor {
    public static void main(String[] args) {
        System.out.println("SampleMonitor: main: args: " + Arrays.toString(args) + " " + Thread.currentThread().getName());

        try {
            Object monitor = new Object();
            Thread waiter1 = new Thread(new SampleWaiter(monitor));
            Thread waiter2 = new Thread(new SampleWaiter(monitor));
            Thread notifier = new Thread(new SampleNotifier(monitor));
            waiter1.start();
            waiter2.start();
            Thread.sleep(2000);
            notifier.start();
        } catch (InterruptedException ex) {
            System.out.println("SampleMonitor: main: ex: " + ex.getMessage());
        }

        System.out.println("SampleMonitor: main: ends " + Thread.currentThread().getName());
    }
}
