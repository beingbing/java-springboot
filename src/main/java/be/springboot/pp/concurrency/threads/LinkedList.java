package be.springboot.pp.concurrency.threads;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LinkedList {
    Node head;

    public void sortedInsert(int num) {
        Node cur = head, next = null;
        cur.getLock().lock();
        try {
            while (true) {
                next = cur.getNxt();
                next.getLock().lock();
                try {
                    if (next.getVal() <= num) {
                        Thread.sleep(1000);
                        Node newNode = new Node(num);
                        newNode.setPrv(cur);
                        newNode.setNxt(next);
                        cur.setNxt(newNode);
                        next.setPrv(newNode);
                        return;
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    cur.getLock().unlock();
                    cur = cur.getNxt();
                }
            }
        } finally {
            next.getLock().unlock();
        }
    }
}

@Getter
@Setter
class Node {
    private int val;
    private Node prv;
    private Node nxt;
    private final Lock lock;

    public Node(int val) {
        this.val = val;
        this.lock = new ReentrantLock();
    }
}
