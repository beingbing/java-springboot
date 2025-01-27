package be.springboot.pp.javacore.polymorphism;

public class SortingUtility {

    public static void sort(int[] a, Comparator comparator) {
        for (int i = 0; i < a.length; i++) {
            int j = i;
            while (j > 0 && !comparator.compare(a[j-1], a[j])) {
                int temp = a[j];
                a[j] = a[j-1];
                a[j-1] = temp;
                j--;
            }
        }
    }

}
