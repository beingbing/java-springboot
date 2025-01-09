package be.springboot.pp.dsalgo.greedy;

import java.util.Arrays;

public class S002_trains_and_platforms {
    public static int findMinimumPlatforms(int[] arrivals, int[] departures) {
        int n = arrivals.length;

        Arrays.sort(arrivals);
        Arrays.sort(departures);

        int platformNeeded = 0;
        int maxPlatforms = 0;

        int i = 0; // Pointer for arrivals
        int j = 0; // Pointer for departures

        while (i < n && j < n) {
            if (arrivals[i] < departures[j]) { // A train has arrived before another has departed
                platformNeeded++;
                i++;
                maxPlatforms = Math.max(maxPlatforms, platformNeeded);
            } else { // A train has departed
                platformNeeded--;
                j++;
            }
        }

        return maxPlatforms;
    }
}
