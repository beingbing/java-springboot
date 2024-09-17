package be.springboot.pp.concurrency;

class SampleWaiter implements Runnable {

    private final Object monitor;

    public SampleWaiter(Object monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        synchronized(monitor) {
            System.out.println("Waiter: run: " + Thread.currentThread().getName() + " starts");
            try {
                monitor.wait();
            } catch (InterruptedException ex) {
                System.out.println("Waiter: ex: " + ex.getMessage());
            }
            System.out.println("Waiter: run: " + Thread.currentThread().getName() + " ends");
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
            System.out.println("Notifier: run: " + Thread.currentThread().getName() + " starts");
            monitor.notifyAll();
            System.out.println("Notifier: run: " + Thread.currentThread().getName() + " ends");
        }
    }

}

public class SampleMonitor {

    public static void main(String[] args) {
        System.out.println("SampleMonitor: main: args: " + args + " " + Thread.currentThread().getName());

        try {
            Object monitor = new Object();
            Thread waiter1 = new Thread(new SampleWaiter(monitor));
            Thread waiter2 = new Thread(new SampleWaiter(monitor));
            Thread notifier = new Thread(new SampleNotifier(monitor));
            waiter1.start();
            waiter2.start();
            Thread.sleep(1000);
            notifier.start();
        } catch (InterruptedException ex) {
            System.out.println("SampleMonitor: main: ex: " + ex.getMessage());
        }

        System.out.println("SampleMonitor: main: ends " + Thread.currentThread().getName());
    }
}
