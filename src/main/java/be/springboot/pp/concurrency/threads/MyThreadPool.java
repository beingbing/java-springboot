package be.springboot.pp.concurrency.threads;

import be.springboot.pp.concurrency.PairCounter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;

class Simulator {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        MyThreadPool threadPool = new MyThreadPool(3, 10);
        Map<Integer, Future<Integer>> intFutureTaskMap = new HashMap<>();
        Random random = new Random();
        int k = 0;
        for (int i = 0; i < 20; i++) {
            Future<Integer> res = null;
            if (i % 2 == 0) {
                int qty = 30000;
                List<Integer> nums = new ArrayList<>();
                for (int j = 0; j < qty; j++)
                    nums.add(random.nextInt(100, 200));
                int sum = random.nextInt(200, 400);
                res = threadPool.submit(new PairCounter(nums, sum));
            } else res = threadPool.submit(new DummyCallback(k++));
            intFutureTaskMap.put(i, res);
        }

        for (int i = 0; i < 20; i++)
            System.out.println("Simulator: main: intFutureTaskMap: Req #" + i + ": " + intFutureTaskMap.get(i).get());

        threadPool.shutdown();
    }
}

class MyThreadPool {
    private final int poolSize;
    private final List<Thread> threads;
    private final BlockingQueue<Runnable> taskQueue;
    private boolean isShutdown = false;

    public MyThreadPool(int numThreads, int queueCapacity) {
        this.poolSize = numThreads;
        this.taskQueue = new LinkedBlockingQueue<>(queueCapacity);
        this.threads = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            Thread thread = new Thread(new WorkerThread(i, taskQueue));
            threads.add(thread);
            thread.start();
        }
    }

    public synchronized Future<Integer> submit(Callable<Integer> task) throws InterruptedException {
        if (isShutdown) throw new RejectedExecutionException("ThreadPool has been shut down.");

        FutureTask<Integer> futureTask = new FutureTask<>(task);
        taskQueue.put(futureTask);
        return futureTask;
    }

    public synchronized void shutdown() throws InterruptedException {
        isShutdown = true;

        for (int i = 0; i < poolSize; i++)
            taskQueue.put(() -> {throw new ShutdownException();}); // Send poison pills equal to number of worker threads

        for (Thread thread : threads) thread.join(); // Wait for all threads to finish
        System.out.println("ThreadPool has been shut down.");
    }
}

class WorkerThread implements Runnable {
    private final int id;
    private final BlockingQueue<Runnable> taskQueue;

    public WorkerThread(int id, BlockingQueue<Runnable> taskQueue) {
        this.id = id;
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        System.out.println("WorkerThread-" + id + " started.");
        while (true) {
            try {
                Runnable task = taskQueue.take();
                task.run();
            } catch (ShutdownException se) {
                System.out.println("WorkerThread-" + id + " shutting down.");
                break;
            } catch (Exception e) {
                System.err.println("WorkerThread-" + id + ": Error in task - " + e.getMessage());
            }
        }
    }
}

class DummyCallback implements Callable<Integer> {
    private final int x;

    public DummyCallback(int x) {
        this.x = x;
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(2000);
        return 786;
    }
}

class ShutdownException extends RuntimeException {
    public ShutdownException() {
        super("Shutdown signal received");
    }
}
