package be.springboot.pp.dsalgo.heaps;

import java.util.PriorityQueue;

public class S002_gfg_heap_sort {
    public void heapSort(int[] a) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int elem : a) minHeap.offer(elem);
        for (int i = 0; i < a.length; i++) a[i] = minHeap.poll();
    }
}
