package be.springboot.pp.dsalgo.twopointers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class S002_gfg_equiv_sub_ar {
    public static int countDistinctSubarrays(int[] a) {
        int n = a.length;
        // Step 1: Find total distinct elements in the array
        HashSet<Integer> distinctSet = new HashSet<>();
        for (int num : a) distinctSet.add(num);
        int totalDistinct = distinctSet.size();

        // Step 2: Sliding window to count valid subarrays
        Map<Integer, Integer> freqMap = new HashMap<>();
        int start = 0, count = 0, distinctInWindow = 0;

        for (int end = 0; end < n; end++) {
            // Add current element to the frequency map
            freqMap.put(a[end], freqMap.getOrDefault(a[end], 0) + 1);

            // If the element is new to the window, increase distinct count
            if (freqMap.get(a[end]) == 1) distinctInWindow++;

            // Shrink the window until distinct count matches
            // count all the windows with starting index at start pointer
            // and ending index is in the range [end, n-1]
            while (distinctInWindow == totalDistinct) {
                count += n - end;
                freqMap.put(a[start], freqMap.get(a[start]) - 1);
                if (freqMap.get(a[start]) == 0) distinctInWindow--;
                start++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 1, 3, 4};
        System.out.println("Output: " + countDistinctSubarrays(arr1)); // Output: 2

        int[] arr2 = {1, 2, 1, 3};
        System.out.println("Output: " + countDistinctSubarrays(arr2)); // Output: 2

        int[] arr3 = {1, 2, 1, 2, 1};
        System.out.println("Output: " + countDistinctSubarrays(arr3)); // Output: 10

        int[] arr4 = {2, 1, 3, 2, 3};
        System.out.println("Output: " + countDistinctSubarrays(arr4)); // Output: 5

        int[] arr5 = {2, 4, 4, 2, 4};
        System.out.println("Output: " + countDistinctSubarrays(arr5)); // Output: 9
    }
}
