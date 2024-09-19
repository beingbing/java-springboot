package be.springboot.pp.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class JavaConcurrencyApis {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<?> f1 = executorService.submit(new SampleWorker("I love Maheen"));
        Future<?> f2 = executorService.submit(new SampleWorker("I love Samar"));
        Future<?> f3 = executorService.submit(new SampleWorker("I love concurrency"));
        f1.get();
        f2.get();
        f3.get();
        executorService.shutdown();
    }
}

class SampleWorker implements Runnable {

    private final String message;

    public SampleWorker(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        System.out.println("message to print: " + message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("reaching out to you from: " + Thread.currentThread().getName());
    }
}
