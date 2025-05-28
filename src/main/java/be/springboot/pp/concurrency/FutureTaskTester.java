package be.springboot.pp.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTester {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> ft = new FutureTask<>(new MyCallable());
        new Thread(ft).start();
        System.out.println("started ...");
        String ans = ft.get();
        System.out.println(ans);
    }
}

class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(2000);
        return "Hello World!!";
    }
}