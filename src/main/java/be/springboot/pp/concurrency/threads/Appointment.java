package be.springboot.pp.concurrency.threads;

import java.util.ArrayList;
import java.util.List;

class Customer implements Runnable {
    private final int appointmentId;
    private final TickingBoard tickingBoard;

    public Customer(int appointmentId, TickingBoard tickingBoard) {
        this.appointmentId = appointmentId;
        this.tickingBoard = tickingBoard;
    }

    @Override
    public void run() {
        synchronized (tickingBoard) {
            while (!tickingBoard.isMyTurn(appointmentId)) {
                System.out.println("Customer: appointmentId: " + appointmentId + " " + Thread.currentThread().getName() + " waiting");
                try {
                    tickingBoard.wait(); // wait until notified
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // preserve interrupt status
                    return;
                }
            }
            System.out.println("Customer: appointmentId: " + appointmentId + " " + Thread.currentThread().getName() + " entered ++++++");
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

    public synchronized boolean isMyTurn(int appointmentId) {
        if (cur == appointments.size()) throw new RuntimeException("All appointments are over");
        if (appointments.get(cur) != appointmentId) return false;
        cur++; // advance to next appointment
        notifyAll(); // notify all waiting threads after advancing
        return true;
    }
}

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
