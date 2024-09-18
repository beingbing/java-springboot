package be.springboot.pp.concurrency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class PairCounter implements Callable<Integer> {

    private final List<Integer> nums;

    private final int sum;

    public PairCounter(List<Integer> nums, int sum) {
        this.nums = nums;
        this.sum = sum;
    }

    @Override
    public Integer call() throws Exception {
        int cnt = 0;
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                if (nums.get(i) + nums.get(j) == sum) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}

class Server {

    public FutureTask<Integer> getPairCount(List<Integer> nums, int sum) {
        Callable<Integer> callable = new PairCounter(nums, sum);
        FutureTask<Integer> futureTask = new FutureTask<>(callable);

        /*
        * When we are working on a grand scale, then instead of spinning a thread manually and
        * managing a thread for each request (can scale to 1000-10k per second), we can use
        * ThreadPool. Launching a new Thread everytime will put system under load, as managing
        * this many objects will cause performance issues. That's why systems imposes a limit on
        * number of threads allowed to be created. Also, every Thread which is created, has an
        * associated cost of creation. And in our setup, after every request, we are destroying
        * thread object, which is a bad investment on the cost incurred in creating it. Hence,
        * it is better to keep a fixed count of threads running to entertain incoming requests
        * rather then creating and destroying a thread every time. ThreadPool interface handles
        * Threads in bulk and keep thread count to a said level without limiting or restricting
        * incoming requests. The maintained Threads are called Thread Pool. Incoming requests are
        * then put in a request queue, from which a thread from Thread Pool will consume, as soon
        * as it becomes available.
        * */
        new Thread(futureTask).start();
        return futureTask;
    }
}

class MultiThreadingSimulator {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Server server = new Server();
        Random random = new Random();
        Map<Integer, FutureTask<Integer>> intFutureTaskMap = new HashMap<>();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            int qty = 3000;
            List<Integer> nums = new ArrayList<>();

            for (int j = 0; j < qty; j++)
                nums.add(random.nextInt(100, 200));
            int sum = random.nextInt(200, 400);

            FutureTask<Integer> futureTask = server.getPairCount(nums, sum);
            System.out.println("Simulator: main: futureTask: " + futureTask);
            intFutureTaskMap.put(i, futureTask);
        }

        long end = System.currentTimeMillis();
        System.out.println("Simulator: main: time: " + (end - start));

        for (int i = 0; i < 5; i++) {
            System.out.println("Simulator: main: intFutureTaskMap: Req #" + i + ": " + intFutureTaskMap.get(i).get());
        }
    }
}

class SingleThreadedServer {

    public Integer getPairCount(List<Integer> nums, int sum) throws Exception {
       return new PairCounter(nums, sum).call();
    }
}

class SingleThreadedSimulator {

    public static void main(String[] args) throws Exception {
        SingleThreadedServer server = new SingleThreadedServer();
        Random random = new Random();

        long start = System.currentTimeMillis();

        for (int i = 0; i < 5; i++) {
            int qty = 3000;
            List<Integer> nums = new ArrayList<>();
            for (int j = 0; j < qty; j++)
                nums.add(random.nextInt(100, 200));
            int sum = random.nextInt(200, 400);
            int res = server.getPairCount(nums, sum);
            System.out.println("SingleThreadedSimulator: main: res: " + res);
        }

        long end = System.currentTimeMillis();
        System.out.println("SingleThreadedSimulator: main: time: " + (end - start));
    }
}
