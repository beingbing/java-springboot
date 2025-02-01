package be.springboot.pp.designpattern.behavioral.strategy.numberstore.sorter;

import java.util.List;

public class QuickSort implements Sorter {
    @Override
    public void sort(List<Integer> array) {
        quickSort(array, 0, array.size() - 1);
        System.out.println("Sorted using Quick Sort");
    }

    private void quickSort(List<Integer> array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private int partition(List<Integer> array, int low, int high) {
        int pivot = array.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array.get(j) < pivot) {
                i++;
                int temp = array.get(i);
                array.set(i, array.get(j));
                array.set(j, temp);
            }
        }
        int temp = array.get(i + 1);
        array.set(i + 1, array.get(high));
        array.set(high, temp);
        return i + 1;
    }
}
