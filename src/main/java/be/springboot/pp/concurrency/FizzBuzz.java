package be.springboot.pp.concurrency;

class FizzBuzzMaker {

    /* Problem:
    * given n = 15, print number from 1 to 15 in sorted manner,
    * further categorize these numbers in 4 buckets -
    * - Fizz: divisible by 3 but not divisible by 5
    * - Buzz: not divisible by 3 but divisible by 5
    * - FizzBuzz: divisible by both 3 and 5
    * - Plain: neither divisible by 3 nor by 5
    *
    * After printing numbers in sorted manner also print their bucket.
    * */
    public static final int n = 15;

//    public static volatile int i = 1; // adding volatile didn't fix it
    public static int i = 1;

    public static void main(String[] args) {
        System.out.println("FizzBuzzMaker: main: args: " + args + " " + Thread.currentThread().getName());
        Thread fizz = new Thread(new Fizz());
        Thread buzz = new Thread(new Buzz());
        Thread fizzbuzz = new Thread(new FizzBuzz());
        Thread plain = new Thread(new Plain());
        fizz.start();
        buzz.start();
        fizzbuzz.start();
        plain.start();

        System.out.println("FizzBuzzMaker: main: ends " + Thread.currentThread().getName());
    }
}

// 3, !5
class Fizz implements Runnable {
    @Override
    public void run() {
        System.out.println("Fizz: run: " + Thread.currentThread().getName());
        while (FizzBuzzMaker.i <= FizzBuzzMaker.n) {
            while (!(FizzBuzzMaker.i % 3 == 0 && FizzBuzzMaker.i % 5 != 0)) {
//                System.out.println("busy waiting inside fz: " + Thread.currentThread().getName());
            }
            System.out.println(FizzBuzzMaker.i + " Fizz");
            FizzBuzzMaker.i++;
        }
        System.out.println("Fizz: run: ends " + Thread.currentThread().getName());
    }
}

// !3, 5
class Buzz implements Runnable {
    @Override
    public void run() {
        System.out.println("Buzz: run: " + Thread.currentThread().getName());
        while (FizzBuzzMaker.i <= FizzBuzzMaker.n) {
            while (!(FizzBuzzMaker.i % 3 != 0 && FizzBuzzMaker.i % 5 == 0)) {
//                System.out.println("busy waiting inside bz: " + Thread.currentThread().getName());
            }
            System.out.println(FizzBuzzMaker.i + " Buzz");
            FizzBuzzMaker.i++;
        }
        System.out.println("Buzz: run: ends " + Thread.currentThread().getName());
    }
}

// 3, 5
public class FizzBuzz implements Runnable {
    @Override
    public void run() {
        System.out.println("FizzBuzz: run: " + Thread.currentThread().getName());
        while (FizzBuzzMaker.i <= FizzBuzzMaker.n) {
            while (!(FizzBuzzMaker.i % 3 == 0 && FizzBuzzMaker.i % 5 == 0)) {
//                System.out.println("busy waiting inside fb: " + Thread.currentThread().getName());
            }
            System.out.println(FizzBuzzMaker.i + " FizzBuzz");
            FizzBuzzMaker.i++;
        }
        System.out.println("FizzBuzz: run: ends " + Thread.currentThread().getName());
    }
}

// !3, !5
class Plain implements Runnable {
    @Override
    public void run() {
        System.out.println("Plain: run: " + Thread.currentThread().getName());
        while (FizzBuzzMaker.i <= FizzBuzzMaker.n) {
            while (!(FizzBuzzMaker.i % 3 != 0 && FizzBuzzMaker.i % 5 != 0)) {
//                System.out.println("busy waiting inside pl: " + Thread.currentThread().getName());
            }
            System.out.println(FizzBuzzMaker.i + " Plain");
            FizzBuzzMaker.i++;
        }
        System.out.println("Plain: run: ends " + Thread.currentThread().getName());
    }
}
