package be.springboot.pp.dsalgo.greedy;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class S004_lc_1383 {
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        // Pair efficiency and speed, and sort by efficiency in descending order
        List<Engineer> engineers = new ArrayList<>();
        for (int i = 0; i < n; i++) engineers.add(new Engineer(efficiency[i], speed[i]));
        engineers.sort((a, b) -> b.efficiency - a.efficiency);

        PriorityQueue<Integer> speedHeap = new PriorityQueue<>(); // Use a min-heap to maintain the top-k speeds
        long maxPerformance = 0, speedSum = 0;

        for (Engineer engineer : engineers) {
            // Add current speed to the heap and update speed sum
            speedHeap.add(engineer.speed);
            speedSum += engineer.speed;

            // If the heap exceeds k, remove the smallest speed
            if (speedHeap.size() > k) speedSum -= speedHeap.poll();

            // Calculate performance with the current efficiency as the minimum efficiency
            maxPerformance = Math.max(maxPerformance, speedSum * engineer.efficiency);
        }

        return (int) (maxPerformance % (1_000_000_007)); // Return the maximum performance modulo 10^9 + 7
    }
}

class Engineer {
    int efficiency;
    int speed;

    public Engineer(int ef, int sp) {
        this.efficiency = ef;
        this.speed = sp;
    }
}
