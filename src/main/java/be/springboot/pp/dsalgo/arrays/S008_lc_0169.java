package be.springboot.pp.dsalgo.arrays;

public class S008_lc_0169 {

    public int findMajorityElement(int[] arr) {
        int candidate = 0, count = 0;

        // Step 1: Find the candidate
        for (int num : arr) {
            if (count == 0) candidate = num;  // Set the new candidate
            count += (num == candidate) ? 1 : -1;  // Increment or decrement count
        }

        // Step 2: Verify the candidate
        count = 0;
        for (int num : arr) if (num == candidate) count++;

        // Return the candidate if it appears more than n/2 times
        if (count > arr.length / 2) return candidate;

        return -1;
    }
}
