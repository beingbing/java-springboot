package be.springboot.pp.dsalgo.greedy;

import java.util.Arrays;

public class S003_job_scheduling_loss_minimization {
    public static int[] minimizeLoss(int[] L, int[] T) {
        int n = L.length;
        DelayJob[] jobs = new DelayJob[n];

        // Step 1: Create job objects with their indices, loss, time, and ratio
        for (int i = 0; i < n; i++) jobs[i] = new DelayJob(i + 1, L[i], T[i]);

        // Step 2: Sort jobs by descending ratio, with a tie-breaker on index
        Arrays.sort(jobs, (a, b) -> {
            if (Double.compare(b.ratio, a.ratio) == 0) return Integer.compare(a.index, b.index); // Lexicographical order
            return Double.compare(b.ratio, a.ratio); // Descending ratio
        });

        // Step 3: Extract the job order
        int[] result = new int[n];
        for (int i = 0; i < n; i++) result[i] = jobs[i].index;

        return result;
    }
}

class DelayJob {
    int index;
    int loss;
    int time;
    double ratio;

    DelayJob(int index, int loss, int time) {
        this.index = index;
        this.loss = loss;
        this.time = time;
        this.ratio = (double) loss / time;
    }
}
