package be.springboot.pp.dsalgo.sorting;

import java.util.Arrays;

public class S005_lc_0853 {
    // Analyzing closer cars first allows us to determine if a farther car will catch up,
    // simplifying fleet formation logic.
    // A car forms a new fleet only if it cannot catch up with the fleet ahead of it.
    // By processing one car at a time and deciding its fleet based on the above conditions,
    // the algorithm avoids unnecessary recalculations.
    public int carFleet(int target, int[] position, int[] speed) {
        // Step 1: Pair position and speed, and sort by position in descending order
        // This ensures cars closer to the target are processed first.
        int n = position.length;
        int[][] cars = new int[n][2];
        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }
        Arrays.sort(cars, (a, b) -> Integer.compare(b[0], a[0])); // Descending order by position

        // Step 2: Calculate time to reach target for each car
        double[] time = new double[n];
        for (int i = 0; i < n; i++) time[i] = (double) (target - cars[i][0]) / cars[i][1];

        // Step 3: Count fleets
        int fleets = 0;
        double lastTime = 0;
        for (int i = 0; i < n; i++) {
            // If the current car’s time is greater than the time of the last fleet, it forms a new fleet.
            if (time[i] > lastTime) {
                fleets++; // New fleet is formed
                lastTime = time[i]; // Update the time of the last fleet
            }
            // Otherwise, it joins the previous fleet.
        }

        return fleets;
    }
}
