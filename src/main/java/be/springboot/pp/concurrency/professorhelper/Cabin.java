package be.springboot.pp.concurrency.professorhelper;

import java.util.ArrayDeque;
import java.util.Queue;

public class Cabin {
    private final int capacity;
    private final Queue<Student> curStudents;
    private Batch curBatch, prvBatch;
    private int consCount;
    private final Queue<Student> servedStudents;

    public Cabin(int capacity) {
        this.capacity = capacity;
        this.curStudents = new ArrayDeque<>();
        this.servedStudents = new ArrayDeque<>();
        this.curBatch = null;
        this.prvBatch = null;
        this.consCount = 0;
    }

    public synchronized void enter(Student student) {
        while (!canEnter(student)) {
            System.out.println("cabin is full, " + student.getId() + " waiting for students to leave...");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            curStudents.add(student);
            if (curStudents.size() == 1) {
                curBatch = student.getBatch();
                if (curBatch != prvBatch) {
                    consCount = 1;
                } else {
                    consCount++;
                }
            } else {
                consCount++;
            }
            notifyAll();
            System.out.println(student.getId() + " entered the cabin");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void exit() {
        Student student = null;
        try {
            student = curStudents.poll();
            System.out.println(student.getId() + " left the cabin");
            this.servedStudents.add(student);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (curStudents.size() == 0) {
            prvBatch = curBatch;
            curBatch = null;
        }
        System.out.println(student.getId() + " left the cabin");
        notifyAll();
    }

    public synchronized Student getStudent() throws InterruptedException {
        while (curStudents.isEmpty()) {
            wait();
        }

        Student student = curStudents.peek();
        System.out.println(student.getId() + " is served");
//        notifyAll();
        return student;
    }

    public synchronized void log() {
        System.out.println("Cabin: " + curStudents.size() + " students, batch: " + curBatch);
        for (Student s : curStudents) {
            System.out.println(s.getId() + " " + s.getBatch());
        }
        System.out.println("consCount: " + consCount);
        System.out.println("served students: " + servedStudents.size());
        for (Student s : servedStudents) {
            System.out.println(s.getId() + " " + s.getBatch());
        }
    }

    private boolean canEnter(Student student) {
        if (curStudents.size() == capacity) return false;
        if (curBatch != null) {
            if (!student.getBatch().equals(curBatch)) return false;
            if (consCount == 5) return false;
            return true;
        } else {
            if (prvBatch == null) return true;
            else {
                if (!student.getBatch().equals(prvBatch)) return true;
                else {
                    if (consCount < 5) return true;
                    return false;
                }
            }
        }
    }

//    public boolean NoStudentWaiting() {
//        System.out.println("waiting size: " + curStudents.size());
//        return curStudents.isEmpty();
//    }
}
