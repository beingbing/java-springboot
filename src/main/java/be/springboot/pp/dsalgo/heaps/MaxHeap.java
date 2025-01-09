package be.springboot.pp.dsalgo.heaps;

import java.util.ArrayList;

public class MaxHeap {
    private final ArrayList<Integer> heap;

    public MaxHeap() {
        heap = new ArrayList<>();
        heap.add(0); // Ignoring index 0
    }

    public void insert(int k) {
        heap.add(k);
        int current = heap.size() - 1;
        while (current > 1 && heap.get(current) > heap.get(current / 2)) { // Max-Heap
            swap(current, current / 2);
            current /= 2;
        }
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public void delete(int pos) {
        if (pos <= 0 || pos >= heap.size()) return;
        heap.set(pos, heap.getLast());
        heap.removeLast();
        heapifyDown(pos);
    }

    private void heapifyDown(int pos) {
        int largest = pos;
        int left = 2 * pos;
        int right = 2 * pos + 1;

        if (left < heap.size() && heap.get(left) > heap.get(largest)) {
            largest = left;
        }
        if (right < heap.size() && heap.get(right) > heap.get(largest)) {
            largest = right;
        }
        if (largest != pos) {
            swap(pos, largest);
            heapifyDown(largest);
        }
    }

    public int getMax() {
        if (heap.size() > 1) return heap.get(1);
        return -1; // Or throw an exception
    }

    public MaxHeap(int[] arr) {
        heap = new ArrayList<>();
        heap.add(0); // Ignoring index 0
        for (int val : arr) heap.add(val);

        for (int i = (heap.size() - 1) / 2; i >= 1; i--) heapifyDown(i);
    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap();
        maxHeap.insert(10);
        maxHeap.insert(20);
        maxHeap.insert(5);
        System.out.println("Max: " + maxHeap.getMax()); // Output: 20
        maxHeap.delete(1);
        System.out.println("Max: " + maxHeap.getMax()); // Output: 10
    }
}
