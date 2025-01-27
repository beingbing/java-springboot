package be.springboot.pp.javacore.polymorphism;

public class SortingUtilityTester {

    public static void main(String[] args) {
        int[] a = {1, -2, 3, 1, 2, -9, 0, 5, -4};
        SortingUtility.sort(a, new AscendingComparator());
        for (int ele : a) {
            System.out.println(ele);
        }
        System.out.println("====");
        SortingUtility.sort(a, new DescendingComparator());
        for (int ele : a) {
            System.out.println(ele);
        }
        System.out.println("========");
        SortingUtility.sort(a, new Comparator() { // this is called an anonymous class, we cannot reuse it
            @Override
            public boolean compare(int a, int b) {
                return Math.abs(a) <= Math.abs(b);
            }
        });
        for (int ele : a) {
            System.out.println(ele);
        }
    }

}
