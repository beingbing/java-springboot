package be.springboot.pp.concurrency;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Timer {

    public static void main(String args[]) throws InterruptedException {
        SimpleThreadPool threadPool = new SimpleThreadPool(3, 10);

        Task t1 = new Task("I love concurrency", 1, 5);
        Task t2 = new Task("I love Samar", 6, 2);
        Task t3 = new Task("I love Maheen", 4, 1);

        threadPool.submit(t1);
        threadPool.submit(t2);
        threadPool.submit(t3);
    }
}



class SimpleThreadPool {

    private final int poolCapacity;

    private final List<Thread> threads;

    private final BlockingQueue<Task> taskQueue;

    public SimpleThreadPool(int numThreads, int tasksPerThread) {
        this.poolCapacity = numThreads;
        this.taskQueue = new PriorityBlockingQueue<>(tasksPerThread, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                long diff = o1.getFireTime() - o2.getFireTime();
                int ans = 0;
                if (diff > 0) ans = 1;
                if (diff < 0) ans = -1;
                return ans;
            }
        });
        this.threads = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            Thread thread = new Thread(new TaskWorker(i, taskQueue));
            threads.add(thread);
        }
        for (Thread thread : threads) thread.start();
    }

    public void submit(Task task) throws InterruptedException {
        this.taskQueue.put(task);
    }
}



class Task implements Runnable {

    private final String message;

    @Getter
    private final int initialGapInSec;

    @Getter
    private final int subsequentGapInSec;

    @Getter
    @Setter
    private long fireTime;

    public Task(String message, int initialGapInSec, int subsequentGapInSec) {
        this.message = message;
        this.initialGapInSec = initialGapInSec;
        this.subsequentGapInSec = subsequentGapInSec;
        this.fireTime = System.currentTimeMillis() + initialGapInSec * 1000L;
    }

    @Override
    public void run() {
        System.out.println("Task: run: " + message);
    }
}



class TaskWorker implements Runnable {

    private final int id;

    private final BlockingQueue<Task> taskQueue;


    public TaskWorker(int id, BlockingQueue<Task> taskQueue) {
        this.id = id;
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        while (true) {
            Task task = null;
            try {
                task = taskQueue.take();
                long cur = System.currentTimeMillis();
                if (cur >= task.getFireTime()) {
                    System.out.println("cur: " + cur + " | fire-time: " + task.getFireTime());
                    task.run();
                    if (task.getSubsequentGapInSec() > 0) {
                        task.setFireTime(System.currentTimeMillis() + task.getSubsequentGapInSec() * 1000L);
                        this.taskQueue.put(task);
                    }
                } else {
                    this.taskQueue.put(task);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

}