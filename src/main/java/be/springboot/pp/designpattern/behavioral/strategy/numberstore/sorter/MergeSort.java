package be.springboot.pp.designpattern.behavioral.strategy.numberstore.sorter;

import java.util.List;

class MergeSort implements Sorter {
    @Override
    public void sort(List<Integer> array) {
        mergeSort(array, 0, array.size() - 1);
        System.out.println("Sorted using Merge Sort");
    }

    private void mergeSort(List<Integer> array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private void merge(List<Integer> array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++) leftArray[i] = array.get(left + i);
        for (int j = 0; j < n2; j++) rightArray[j] = array.get(mid + 1 + j);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            array.set(k++, (leftArray[i] <= rightArray[j]) ? leftArray[i++] : rightArray[j++]);
        }

        while (i < n1) array.set(k++, leftArray[i++]);
        while (j < n2) array.set(k++, rightArray[j++]);
    }
}
