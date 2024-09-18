package be.springboot.pp.concurrency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class Simulator {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadPool threadPool = new ThreadPool(3, 10);
        Map<Integer, FutureTask<Integer>> intFutureTaskMap = new HashMap<>();
        Random random = new Random();
        int k = 0;
        for (int i = 0; i < 21; i++) {
            System.out.println("Simulator: main: i: " + i);
            FutureTask<Integer> res = null;
            if (i % 3 == 0) {
                System.out.println("Simulator: main: i is divisible by 3: " + i);
                res = threadPool.submit(new DummyCallback(k++));
                intFutureTaskMap.put(i, res);
            } else if (i % 3 == 1) {
                System.out.println("Simulator: main: i gives 1 when divided by 3: " + i);
                int qty = 30000;
                List<Integer> nums = new ArrayList<>();
                for (int j = 0; j < qty; j++)
                    nums.add(random.nextInt(100, 200));
                int sum = random.nextInt(200, 400);
                res = threadPool.submit(new PairCounter(nums, sum));
                intFutureTaskMap.put(i, res);
            } else {
                System.out.println("Simulator: main: i gives 2 when divided by 3: " + i);
                threadPool.submit(new ThreadKill());
            }
        }
        threadPool.shutdown();

        for (int i = 0; i < 20; i++) {
            System.out.println(" ++++++ Simulator: Req #" + i + ": response: " + intFutureTaskMap.get(i).get());
        }
    }
}



public class ThreadPool {

    private final int poolCapacity;

    private final List<Thread> threads;

    private final BlockingQueue<Runnable> taskQueue;

    private final Set<Integer> deadThreadIdList;

    private final Thread bookKeeper;

    private boolean isShutDownInitiated;

//    private int yetToTerminalCount;

    public ThreadPool(int numThreads, int tasksPerThread) {
        this.poolCapacity = numThreads;
        this.taskQueue = new ArrayBlockingQueue<>(tasksPerThread);
        this.threads = new ArrayList<>();
        this.deadThreadIdList = new HashSet<>();
        for (int i = 0; i < numThreads; i++) {
            Thread thread = new Thread(new WorkerThread(i, taskQueue, deadThreadIdList));
            threads.add(thread);
        }
        for (Thread thread : threads) thread.start();
        this.isShutDownInitiated = false;
        this.bookKeeper = new Thread(new BookKeeper(deadThreadIdList, threads, taskQueue));
        this.bookKeeper.start();
    }

    public synchronized FutureTask<Integer> submit(Callable<Integer> task) throws InterruptedException {
        if (this.isShutDownInitiated) throw new InterruptedException("Thread pool is shut down");
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        this.taskQueue.put(futureTask);
        return futureTask;
    }

    public synchronized void submit(Runnable task) throws InterruptedException {
        if (this.isShutDownInitiated) throw new InterruptedException("Thread pool is shut down");
        this.taskQueue.put(task);
    }

    public synchronized void shutdown() throws InterruptedException {
        this.isShutDownInitiated = true;
        for (int i = 0; i < poolCapacity; i++)
            taskQueue.put(new ShutterDown());
    }

}



class WorkerThread implements Runnable {

    private final int id;

    private final BlockingQueue<Runnable> taskQueue;

    private final Set<Integer> deadThreadIdList;

    public WorkerThread(int id, BlockingQueue<Runnable> taskQueue, Set<Integer> deadThreadIdList) {
        this.id = id;
        this.taskQueue = taskQueue;
        this.deadThreadIdList = deadThreadIdList;
    }

    @Override
    public void run() {
        while (true) {
            Runnable task = null;
            try {
                task = taskQueue.take();
                task.run();
                System.out.println("WorkerThread: run: starts: " + id);
            } catch (InterruptedException e) {
                System.out.println("WorkerThread: run: ex: " + e.getMessage());
            } catch (RuntimeException e) {
                System.out.println("WorkerThread: run: ex: " + e.getMessage());
                if (e.getMessage().contains("Thread pool is shut down")) {
                    System.out.println("WorkerThread: run: ends: " + id);
                    break;
                }
                synchronized(deadThreadIdList) {
                    deadThreadIdList.add(id);
                    deadThreadIdList.notifyAll();
                }
                System.out.println("WorkerThread: id#" + id + " is dead");
                throw new RuntimeException(e.getMessage());
            }
        }
        System.out.println("WorkerThread: id#" + id + " exiting ...");
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



class ShutterDown implements Runnable {
    @Override
    public void run() {
        throw new RuntimeException("Thread pool is shut down");
    }
}



class ThreadKill implements Runnable {
    @Override
    public void run() {
        throw new RuntimeException("Thread killed ;)");
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
            synchronized(deadThreadIdList) {
                while (deadThreadIdList.isEmpty()) {
                    try {
                        deadThreadIdList.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                for (var i : deadThreadIdList) {
                    Thread t = new Thread(new WorkerThread(i, taskQueue, deadThreadIdList));
                    threads.set(i, t);
                    t.start();
                    deadThreadIdList.remove(i);
                    System.out.println("BookKeeper: new Thread at id: " + i + " is: " + t);
                }
            }
        }
    }
}