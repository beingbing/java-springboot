package be.springboot.pp.dsalgo.queues;

public class S001_gfg_cir_tour {
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0, totalCost = 0;
        int start = 0, runningSum = 0;

        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
            runningSum += gas[i] - cost[i];

            // If running sum is negative, reset the start station
            if (runningSum < 0) {
                start = i + 1; // Set start to the next station
                runningSum = 0; // Reset running sum
            }
        }

        // Check if a solution exists
        return totalGas >= totalCost ? start : -1;
    }
}
