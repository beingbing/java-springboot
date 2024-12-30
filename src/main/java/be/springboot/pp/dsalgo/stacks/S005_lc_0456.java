package be.springboot.pp.dsalgo.stacks;

import java.util.Stack;

public class S005_lc_0456 {
    public boolean find132pattern(int[] a) {
        int n = a.length;
        if (n < 3) return false;

        Stack<Integer> minStack = new Stack<>(); // put smaller or equal at the top
        int largestSmallerOnRight = Integer.MIN_VALUE;

        for (int p = n - 1; p >= 0; p--) {
//            System.out.println("a[p] " + a[p]);
            if (a[p] < largestSmallerOnRight) { // current element is treated as a[i]
//                System.out.println(a[p] + " " + nextGreater + " " + minStack.peek());
//                while (!minStack.isEmpty()) System.out.println(minStack.pop());
                return true;
            } // 4. check for a[i] < a[k]
            while (!minStack.isEmpty() && a[p] > minStack.peek()) { // 1. check for a[j] > a[k]; current element is a[j]
                largestSmallerOnRight = minStack.pop(); // 2. get the largest element from stack which is smaller than current element
//                System.out.println("next-greater: " + nextGreater);
            } // stack has right side of array sorted in monotonic decreasing order from bottom to top
            minStack.push(a[p]); // 3. if not a[j] candidate, then consider current element for a[k]
        }
        return false;
    }

    public static void main(String[] args) {
        int[] a = {2,  3  ,  5  ,6,  4  ,1,2,2,4};
        S005_lc_0456 solver = new S005_lc_0456();
        System.out.println("answer: " + solver.find132pattern(a));
    }
}

class Find132Pattern {
    public boolean find132Pattern(int[] nums) {
        int n = nums.length;
        if (n < 3) return false; // A 132 pattern requires at least three elements.

        // Step 1: Create an array to store the prefix minimum for each index.
        // This represents the smallest element to the left of the current index.
        int[] prefixMin = new int[n];
        prefixMin[0] = nums[0];
        for (int i = 1; i < n; i++) prefixMin[i] = Math.min(prefixMin[i - 1], nums[i]);

        // Step 2: Use a stack to track potential "nums[k]" values in the "132 pattern".
        Stack<Integer> stack = new Stack<>();

        // Step 3: Traverse the array from right to left and maintain elements for the right part of the pattern.
        for (int j = n - 1; j >= 0; j--) {
            while (!stack.isEmpty() && stack.peek() < nums[j]) {
                if (prefixMin[j] < stack.peek()) return true;
                stack.pop(); // too small for being nums[k]
            }
            stack.push(nums[j]); // no longer nums[j] candidate but can be nums[k] candidate
        }
        return false;
    }

    public static void main(String[] args) {
        Find132Pattern solver = new Find132Pattern();
        int[] nums = {3, 1, 4, 2};
        System.out.println("132 pattern exists: " + solver.find132Pattern(nums)); // Output: true
    }
}
