package be.springboot.pp.concurrency;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
// 11, 13

/*
* Problem statement: schedule appointment of a customer according to the number displayed on the screen.
* the catch here is every customer will be an independent thread.
* */
class Customer implements Runnable {

    private final int appointmentId;

    private final TickingBoard tickingBoard;

    public Customer(int appointmentId, TickingBoard tickingBoard) {
        this.appointmentId = appointmentId;
        this.tickingBoard = tickingBoard;
    }

    @Override
    public void run() {
        // ---------- 1
//        while (!tickingBoard.isMyTurn(appointmentId)) { // problem of busy waiting
//            System.out.println("Customer: appointmentId: " + appointmentId + " " + Thread.currentThread().getName() + " waiting");
//        }
//        System.out.println("Customer: appointmentId: " + appointmentId + " " + Thread.currentThread().getName() + " entered ++++++");

        // ---------- 2
        /*
        * This while loop is doing busy waiting which should not be done, ideally, Threads should wait
        * before again analyzing the condition. It is consuming CPU cycles and yet doing nothing.
        * So, where Thread does not busy-waiting, it should wait quietly so that no CPU cycles are
        * consumed, we can make thread go to sleep.
        * */
//        while (!tickingBoard.isMyTurn(appointmentId)) {
//            System.out.println("Customer: appointmentId: " + appointmentId + " " + Thread.currentThread().getName() + " waiting");
//            try {
//                Thread.sleep(1000); // to remedy busy waiting
//                // but what if the thread which went to sleep is still sleeping and it is its turn now?
//                // until thread whose turn is there doesn't wakes up, all thread will keep on waking and going to sleep relentlessly
//                // this is not a good design. As time elapsed in switching the appointment number may not
//                // always be equal to the sleep duration of thread whose turn is next.
//                // alternatively, if chaning of appointment number takes more time than sleep duration of thread
//                // then a thread might come back to enquire again and again, and we will fallback to case 1 of busy-waiting.
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        System.out.println("Customer: appointmentId: " + appointmentId + " " + Thread.currentThread().getName() + " entered ++++++");

        // ---------- 3
        /*
         * Instead, the evaluation should only be done by threads when they are made aware of the appointment-id
         * getting changes, so that they can review whether it is their turn or not. Hence, ticking board needs
         * to signal all threads to check whether it is their turn or not. And until that is done, threads should
         * wait, do not keep checking.
        * */
        synchronized(tickingBoard) {
            while (!tickingBoard.isMyTurn(appointmentId)) {
                System.out.println("Customer: appointmentId: " + appointmentId + " " + Thread.currentThread().getName() + " waiting");
                try {
                    tickingBoard.wait(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Customer: appointmentId: " + appointmentId + " " + Thread.currentThread().getName() + " entered ++++++");
            tickingBoard.notifyAll();
        }
    }
}

class TickingBoard {

    private final List<Integer> appointments;

    private int cur;

    public TickingBoard(List<Integer> appointments) {
        this.appointments = appointments;
        this.cur = 0;
    }

    // it is synchronized otherwise if while loop was to be synchronized the thread would acquire look and go to infinite loop
    // also it might happen that one thread reads cur to be n, goes context switched and other threads updates to n+1
    // this way when first thread again came in context will execute over stale value.
//    public synchronized boolean isMyTurn(int appointmentId) {
    public boolean isMyTurn(int appointmentId) { // as we have synchronized its parent process hence we can remove it from here
        if (cur == appointments.size()) throw new RuntimeException("all appointments are over");
        if (appointments.get(cur) != appointmentId) return false;
        cur++;
        return true;
    }
}

/*
* For the second part, where whenever the value is changed on a ticking board, this event of change in value is
* propagated to all waiting threads. So that they can check, if it is still not their turn can go to sleep.
*
* This can be achieved by using a Monitor. Monitor is an instrument to which all threads can subscribe to and go to
* sleep, anytime a signal comes to the Monitor, it interrupts all sleeping threads to wake up and check
* whose turn is now.
*
* So the process will be, every thread once scheduled will check the board, if it's not their turn, will
* go to sleep after subscribing to monitor. Once the thread whose turn is now enters, and value on board
* is updated, a signal will be sent on monitor to wake up all sleeping threads. Again all of them will check
* once, whose turn wasn't there will again go to sleep and whose turn was there will enter leading to
* getting the value updated. This will repeat until all appointments are over.
*
* Monitor also has two ways to notify -
* - notify(): will notify to any one of the subscribers randomly.
* - notifyAll(): will notify to all subscribers.
*
* so in total Monitor has 3 features to provide to us -
* - subscribe and wait
* - notify
* -notifyAll
*
* any random java object can be used for the purpose of Monitor, just as was with Lock.
*
* How does wait functions?
* When you call wait() on a monitor, the current thread will wait until another thread invokes notify()/notifyAll().
* method on the same object.
*
* How does notify/notifyAll function?
* Apart from what i already informed so far, once a thread is awakened, until the notifying thread relinquishes
* the ownership of monitor(just like Lock), and awakened thread acquires it, awakened thread won't be able to
* proceed. The awakened thread will compete in usual manner with other threads that might be actively competing
* to synchronize on the monitor.
*
* as you know, only one thread at a time can acquire Monitor (just like Lock).
*
* To become owner of Monitor, you need to acquire a lock on it.
*
* It simply means, the object used for locking has extra feature of wait/notify/notifyAll as well. Acquire lock
* by entering synchronized code block guarded by that lock, and you will have extra privilege to use these 3
* features as well.
*
* As soon as a thread who is owner of the lock invokes wait(), lock will be relinquished and the current thread
* will go to sleep.
*
* */
public class Appointment {

    public static void main(String[] args) {
        List<Customer> customers = new ArrayList<>();
        List<Integer> appointments = new ArrayList<>();

        for (int i = 0; i < 10; i++) appointments.add(i);

        TickingBoard tickingBoard = new TickingBoard(appointments);

        for (int i = 9; i>=0; i--) customers.add(new Customer(appointments.get(i), tickingBoard));

        for (Customer customer : customers) new Thread(customer).start();
    }
}
