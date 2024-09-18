package be.springboot.pp.concurrency;

import java.util.ArrayList;
import java.util.HashMap;
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
        for (int i = 0; i < 20; i++) {
//            System.out.println("Simulator: main: i: " + i);
            FutureTask<Integer> res = null;
            if (i % 2 == 0) {
//                System.out.println("Simulator: main: i is even: " + i);
                int qty = 30000;
                List<Integer> nums = new ArrayList<>();
                for (int j = 0; j < qty; j++)
                    nums.add(random.nextInt(100, 200));
                int sum = random.nextInt(200, 400);
                res = threadPool.submit(new PairCounter(nums, sum));
                intFutureTaskMap.put(i, res);
            } else {
//                System.out.println("Simulator: main: i is odd: " + i);
                res = threadPool.submit(new DummyCallback(k++));
                intFutureTaskMap.put(i, res);
            }
        }

        for (int i = 0; i < 20; i++) {
            System.out.println("Simulator: main: intFutureTaskMap: Req #" + i + ": " + intFutureTaskMap.get(i).get());
        }
    }
}

public class ThreadPool {

    private final int poolCapacity;

    private final List<Thread> threads;

    private final BlockingQueue<Runnable> taskQueue;

//    private final Set<Integer> deadThreadIds;

//    private final Thread bookKeeper;

//    private boolean isShutDownInitiated;

//    private int yetToTerminalCount;

    public ThreadPool(int numThreads, int tasksPerThread) {
        this.poolCapacity = numThreads;
        this.taskQueue = new ArrayBlockingQueue<>(tasksPerThread);
        this.threads = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            Thread thread = new Thread(new WorkerThread(i, taskQueue));
            threads.add(thread);
        }
        for (Thread thread : threads) thread.start();
    }

    public FutureTask<Integer> submit(Callable<Integer> task) throws InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        this.taskQueue.put(futureTask);
        return futureTask;
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
        while (true) {
            Runnable task = null;
            try {
                task = taskQueue.take();
                task.run();
                System.out.println("WorkerThread: run: starts: " + id);
            } catch (InterruptedException e) {
                System.out.println("WorkerThread: run: ex: " + e.getMessage());
            }
            if (task == null) {
                System.out.println("WorkerThread: run: ends: " + id);
                break;
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