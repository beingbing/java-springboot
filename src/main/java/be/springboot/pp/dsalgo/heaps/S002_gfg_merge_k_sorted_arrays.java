package be.springboot.pp.dsalgo.heaps;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class S002_gfg_merge_k_sorted_arrays {
    public static ArrayList<Integer> mergeKArrays(int[][] a, int k) {
        PriorityQueue<Sortedness> minHeap = new PriorityQueue<>(Comparator.comparingInt(p -> p.value));
        for (int i = 0; i < k; i++) minHeap.add(new Sortedness(a[i][0], i, 0));
        ArrayList<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            Sortedness element = minHeap.poll();
            result.add(element.value);
            if (element.column + 1 < k)
                minHeap.add(new Sortedness(a[element.row][element.column + 1], element.row, element.column + 1));
        }
        return result;
    }
}

class Sortedness {
    int value;
    int row;
    int column;

    public Sortedness(int v, int r, int c) {
        this.value = v;
        this.row = r;
        this.column = c;
    }
}
