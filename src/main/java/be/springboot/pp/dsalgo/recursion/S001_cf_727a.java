package be.springboot.pp.dsalgo.recursion;

import java.util.ArrayList;
import java.util.Scanner;

public class S001_cf_727a {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextLong();
        long b = scanner.nextLong();

        ArrayList<Long> path = new ArrayList<>();
        if (findPath(a, b, path)) {
            System.out.println("YES");
            System.out.println(path.size());
            for (long num : path) System.out.print(num + " ");
        } else System.out.println("NO");
    }

    private static boolean findPath(long a, long b, ArrayList<Long> path) {
        path.addFirst(b);
        if (a == b) return true;
        if (a > b) return false;

        // Recursively try reversing the operations
        if (b % 2 == 0 && findPath(a, b / 2, path)) return true;
        else if (b % 10 == 1 && findPath(a, b / 10, path)) return true;

        path.removeFirst();  // Backtrack if neither operation leads to solution
        return false;
    }
}
