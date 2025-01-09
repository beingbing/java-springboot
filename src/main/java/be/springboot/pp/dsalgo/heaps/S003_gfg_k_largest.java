package be.springboot.pp.dsalgo.heaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class S003_gfg_k_largest {
    public static List<Integer> kLargest(int[] a, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int ele : a) {
            minHeap.offer(ele);
            if (minHeap.size() > k) minHeap.poll();
        }

        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) result.add(minHeap.poll());
        Collections.reverse(result);
        return result;
    }
}
