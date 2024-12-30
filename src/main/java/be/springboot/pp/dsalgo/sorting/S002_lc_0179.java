package be.springboot.pp.dsalgo.sorting;

import java.util.Arrays;

public class S002_lc_0179 {
    public String largestNumber(int[] nums) {
        // Step 1: Convert integers to strings
        String[] strNums = new String[nums.length];
        for (int i = 0; i < nums.length; i++) strNums[i] = String.valueOf(nums[i]);

        // Step 2: Sort using custom comparator
        Arrays.sort(strNums, (a, b) -> (b + a).compareTo(a + b));

        // Step 3: Check for edge case where all numbers are zero
        if (strNums[0].equals("0")) return "0";

        // Step 4: Concatenate sorted strings to form the result
        StringBuilder largestNumber = new StringBuilder();
        for (String num : strNums) largestNumber.append(num);

        return largestNumber.toString();
    }
}

