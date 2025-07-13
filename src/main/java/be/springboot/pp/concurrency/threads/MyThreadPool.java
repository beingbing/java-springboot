package be.springboot.pp.concurrency.threads;

import be.springboot.pp.concurrency.PairCounter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
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
            if (i % 3 == 0) {
                int qty = 30000;
                List<Integer> nums = new ArrayList<>();
                for (int j = 0; j < qty; j++)
                    nums.add(random.nextInt(100, 200));
                int sum = random.nextInt(200, 400);
                res = threadPool.submit(new PairCounter(nums, sum));
            } else if (i % 3 == 1) res = threadPool.submit(new DummyCallback(k++));
            else res = threadPool.submit(new FatalTask());
            intFutureTaskMap.put(i, res);
        }

        for (int i = 0; i < 20; i++) {
            try {
                System.out.println("Simulator: Req #" + i + ": response: " + intFutureTaskMap.get(i).get());
            } catch (ExecutionException e) {
                System.out.println("Simulator: Req #" + i + ": exception: " + e.getMessage());
            }
        }
        threadPool.shutdown();
    }
}

class MyThreadPool {
    private final int poolSize;
    private final List<Thread> threads;
    private final BlockingQueue<Runnable> taskQueue;
    private final Set<Integer> deadThreadIdList;
    private boolean isShutdown = false;

    public MyThreadPool(int numThreads, int queueCapacity) {
        this.poolSize = numThreads;
        this.taskQueue = new LinkedBlockingQueue<>(queueCapacity);
        this.threads = new ArrayList<>();
        this.deadThreadIdList = new HashSet<>();

        for (int i = 0; i < numThreads; i++) {
            Thread thread = new Thread(new WorkerThread(i, taskQueue, deadThreadIdList));
            threads.add(thread);
            thread.start();
        }

        Thread bookKeeper = new Thread(new BookKeeper(deadThreadIdList, threads, taskQueue));
        bookKeeper.setDaemon(true);
        bookKeeper.start();
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
    private final Set<Integer> deadThreadIdList; // callback target

    public WorkerThread(int id, BlockingQueue<Runnable> taskQueue, Set<Integer> deadThreadIdList) {
        this.id = id;
        this.taskQueue = taskQueue;
        this.deadThreadIdList = deadThreadIdList;
    }

    @Override
    public void run() {
        System.out.println("WorkerThread-" + id + " started.");
        try {
            while (true) {
                Runnable task = taskQueue.take();
                task.run();
            }
        } catch (ShutdownException se) {
            System.out.println("WorkerThread-" + id + " shutting down.");
        } catch (Throwable t) {
            System.err.println("WorkerThread-" + id + " died unexpectedly: " + t);
            notifyDeath();
        }
    }

    private void notifyDeath() {
        synchronized (deadThreadIdList) {
            deadThreadIdList.add(id);
            deadThreadIdList.notifyAll();
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

class FatalTask implements Callable<Integer> {
    @Override
    public Integer call() {
        throw new RuntimeException("Simulated fatal task failure");
    }
}

class BookKeeper implements Runnable {
    private final Set<Integer> deadThreadIdList;
    private final List<Thread> threads;
    private final BlockingQueue<Runnable> taskQueue;

    public BookKeeper(Set<Integer> deadThreadIdList, List<Thread> threads, BlockingQueue<Runnable> taskQueue) {
        this.deadThreadIdList = deadThreadIdList;
        this.threads = threads;
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (deadThreadIdList) {
                while (deadThreadIdList.isEmpty()) {
                    try {
                        deadThreadIdList.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                for (Iterator<Integer> it = deadThreadIdList.iterator(); it.hasNext(); ) {
                    int id = it.next();
                    Thread t = new Thread(new WorkerThread(id, taskQueue, deadThreadIdList));
                    threads.set(id, t);
                    t.start();
                    System.out.println("BookKeeper: restarted WorkerThread at id: " + id);
                    it.remove(); // Safe removal while iterating
                }
            }
        }
    }
}
