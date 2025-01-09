package be.springboot.pp.dsalgo.greedy;

import java.util.Arrays;

public class S001_job_sequencing {
    public static int[] jobScheduling(int[] id, int[] deadline, int[] profit) {
        int n = id.length;
        Job[] jobs = new Job[n];
        // List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < n; i++) jobs[i] = new Job(id[i], deadline[i], profit[i]);

        // Sort by profit (descending)
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);
        // jobs.sort((a, b) -> b.profit - a.profit);

        int maxDeadline = Arrays.stream(deadline).max().getAsInt();
        boolean[] timeSlots = new boolean[maxDeadline + 1]; // Create an array to track free slots
        int totalProfit = 0, jobCount = 0;

        // Schedule jobs
        for (Job job : jobs) {
            for (int t = Math.min(maxDeadline, job.deadline); t > 0; t--) {
                if (!timeSlots[t]) {
                    timeSlots[t] = true;
                    totalProfit += job.profit;
                    jobCount++;
                    break;
                }
            }
        }

        return new int[]{jobCount, totalProfit};
    }
}

class Job {
    int id, deadline, profit;
    Job(int id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}
