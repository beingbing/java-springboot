package be.springboot.pp.dsalgo.bitmanipulation;

import java.util.ArrayList;
import java.util.List;

public class S002_lc_0089 {
    public static List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        int totalNumbers = 1 << n; // Total numbers in the sequence is 2^n

        // Generate Gray code using the formula
        for (int i = 0; i < totalNumbers; i++)
            result.add(i ^ (i >> 1)); // Gray code formula

        return result;
    }

    public static void main(String[] args) {
        List<Integer> result;
        for (int i = 1; i <= 16; i++) {
            result = grayCode(i);
            System.out.print("for i: " + i + " : ");
            for (int ele : result) System.out.print(ele + " ");
            System.out.println();
        }
    }
}
