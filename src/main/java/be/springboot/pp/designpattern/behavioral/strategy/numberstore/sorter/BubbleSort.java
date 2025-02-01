package be.springboot.pp.designpattern.behavioral.strategy.numberstore.sorter;

import java.util.List;

public class BubbleSort implements Sorter {
    @Override
    public void sort(List<Integer> array) {
        int n = array.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array.get(j) > array.get(j + 1)) {
                    int temp = array.get(j);
                    array.set(j, array.get(j + 1));
                    array.set(j + 1, temp);
                }
            }
        }
        System.out.println("Sorted using Bubble Sort");
    }
}
