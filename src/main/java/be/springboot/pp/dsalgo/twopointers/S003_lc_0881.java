package be.springboot.pp.dsalgo.twopointers;

import java.util.Arrays;

public class S003_lc_0881 {
    public static int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);

        int left = 0; // Pointer to the lightest person
        int right = people.length - 1; // Pointer to the heaviest person
        int boatCount = 0; // Count of boats required

        // Step 2: Use two-pointer approach
        while (left <= right) {
            // If the lightest and heaviest can share a boat
            if (people[left] + people[right] <= limit) left++; // Move the left pointer
            // The heaviest person always takes a boat (alone or paired)
            right--; // Move the right pointer
            boatCount++; // Increment boat count
        }

        return boatCount; // Return total boats required
    }
}
