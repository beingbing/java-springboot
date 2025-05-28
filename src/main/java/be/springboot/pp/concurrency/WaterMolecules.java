package be.springboot.pp.concurrency;

/*
* Problem: print formula of water molecule (H2O). We wish to print multiple of them.
* Any number of threads are free to run at a time, printing in any sequence.
* Validation: every progressive string window of size 3 should contain a water molecule
* represented by exactly 2 H and 1 O. Their order doesn't matter.
* Validation window won't be slided, it moves on to every next 3 characters in progression.
*
* Solution: It looks like a semaphore implementation where at any given time there are
* two active hydrogen threads and 1 active oxygen thread.
* */

import java.util.ArrayList;
import java.util.List;

public class WaterMolecules {
    public static void main(String[] args) {
        H2OSemaphore semaphore = new H2OSemaphore();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) threads.add(new Thread(new Oxygen(semaphore)));
        for (int i = 0; i < 20; i++) threads.add(new Thread(new Hydrogen(semaphore)));
        for (int i = 0; i < 30; i++) threads.get(i).start();
    }
}

class Hydrogen implements Runnable {
    private final H2OSemaphore h20Semaphore;

    Hydrogen(H2OSemaphore h20Semaphore) {
        this.h20Semaphore = h20Semaphore;
    }

    public void run() {
        try {
            h20Semaphore.acquireH();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println("H");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            h20Semaphore.releaseH();
        }
    }
}

class Oxygen implements Runnable {
    private final H2OSemaphore h20Semaphore;

    Oxygen(H2OSemaphore h20Semaphore) {
        this.h20Semaphore = h20Semaphore;
    }

    public void run() {
        try {
            h20Semaphore.acquireO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println("O");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            h20Semaphore.releaseO();
        }
    }
}

class H2OSemaphore {
    private int hCount, oCount, releaseH, releaseO;
    public H2OSemaphore() {
        this.hCount = 2;
        this.oCount = 1;
        this.releaseH = 0;
        this.releaseO = 0;
    }

    public synchronized void acquireH() throws InterruptedException {
        while (hCount == 0) wait();
        hCount--;
    }

    public synchronized void acquireO() throws InterruptedException {
        while (oCount == 0) wait();
        oCount--;
    }

    public synchronized void releaseH() {
        releaseH++;
        if (releaseH == 2 && releaseO == 1) {
            this.hCount = 2;
            this.oCount = 1;
            releaseH = 0;
            releaseO = 0;
            notifyAll();
        }
    }

    public synchronized void releaseO() {
        releaseO++;
        if (releaseH == 2 && releaseO == 1) {
            this.hCount = 2;
            this.oCount = 1;
            releaseH = 0;
            releaseO = 0;
            notifyAll();
        }
    }
}