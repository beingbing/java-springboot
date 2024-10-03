package be.springboot.pp.concurrency;

import java.util.Arrays;
import java.util.List;
// 6
class MainEvent {

    public static void main(String[] args) {
        System.out.println("MainEvent: main: args: " + args + " " + Thread.currentThread().getName());

        try {
            EventContainer eventContainer = new EventContainer(new EventTitle(), new EventSyllabus());
            System.out.println(eventContainer.get());
            Thread t = new Thread(new EventWorker(eventContainer));
            t.start();
            Thread.sleep(2000);
            System.out.println(eventContainer.get());
        } catch (InterruptedException ex) {
            System.out.println("MainEvent: main: ex: " + ex.getMessage());
        }
    }
}

public class EventContainer {

    private final EventTitle title;

    private final EventSyllabus syllabus;

    public EventContainer(EventTitle title, EventSyllabus syllabus) {
        this.title = title;
        this.syllabus = syllabus;
    }

    /*
    * As we learned in Compound.java, compound actions need to be synchronized,
    * making individual actions thread-safe is not enough.
    *
    * But here, something more is also happening. Thread t is getting context
    * switched and going out of scope without completing the whole process, in this
    * case making increment() thread-safe didn't work either, because other thread
    * is not trying to run increment(), but is calling get(), and, get is fetching
    * the status which is in interim state because title movement is done but syllabus
    * is still not shifted. This introduces us to a new paradigm of concurrency problem,
    * which is synchronizing getters and setters.
    * */
    public synchronized void increment() {
        title.next();       // thread-safe
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        syllabus.next();    // thread-safe
    }

    /*
    * As EventContainer is responsible for updating both title and syllabus, so do not let
    * getter execute until setter is completed, hence getter needs to be locked by the same
    * lock which is implemented on setter. Hence, adding synchronized in its declaration as
    * well.
    * */
    public synchronized String get() {
        return title.get() + " | " + syllabus.get();
    }
}

class EventTitle {
    private static final List<String> titles = Arrays.asList("DSA", "LLD", "Concurrency", "HLD");

    private int counter = 0;

    public synchronized void next() {
        counter = (counter + 1) % titles.size();
    }

    public String get() {
        return titles.get(counter);
    }
}

class EventSyllabus {
    private static final List<String> syllabus = Arrays.asList("DSA", "LLD", "Concurrency", "HLD");

    private int counter = 0;

    public synchronized void next() {
        counter = (counter + 1) % syllabus.size();
    }

    public String get() {
        return syllabus.get(counter);
    }

}

class EventWorker implements Runnable {

    private final EventContainer eventContainer;

    public EventWorker(EventContainer eventContainer) {
        this.eventContainer = eventContainer;
    }

    @Override
    public void run() {
            eventContainer.increment();
    }
}