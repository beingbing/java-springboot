package be.springboot.pp.dsalgo.twopointers;

import java.util.HashMap;
import java.util.Map;

public class S003_lc_0992 {

    public static int subarraysWithKDistinct(int[] nums, int k) {
        return subarraysWithAtMostKDistinct(nums, k) - subarraysWithAtMostKDistinct(nums, k - 1);
    }

    private static int subarraysWithAtMostKDistinct(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int start = 0, count = 0;

        for (int end = 0; end < nums.length; end++) {
            // Add current number to the frequency map
            freqMap.put(nums[end], freqMap.getOrDefault(nums[end], 0) + 1);

            // If there are more than k distinct integers, shrink the window
            while (freqMap.size() > k) {
                freqMap.put(nums[start], freqMap.get(nums[start]) - 1);
                if (freqMap.get(nums[start]) == 0) freqMap.remove(nums[start]);
                start++;
            }

            // Count all subarrays ending at 'end'
            count += end - start + 1;
        }

        return count;
    }
}
