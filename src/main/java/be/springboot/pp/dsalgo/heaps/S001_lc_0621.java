package be.springboot.pp.dsalgo.heaps;

public class S001_lc_0621 {
    public int leastInterval(char[] tasks, int n) {
        // Step 1: Count the frequency of each task
        int[] taskCounts = new int[26];
        for (char task : tasks) taskCounts[task - 'A']++;

        // Step 2: Find the maximum frequency
        int maxFreq = 0;
        int maxFreqCount = 0;
        for (int freq : taskCounts) {
            if (freq > maxFreq) {
                maxFreq = freq;
                maxFreqCount = 1;
            } else if (freq == maxFreq) maxFreqCount++;
        }

        // Step 3: Calculate the minimum intervals
        int partCount = maxFreq - 1; // The number of parts created by maxFreq tasks
        int partLength = n - (maxFreqCount - 1); // Remaining spaces in each part
        int emptySlots = partCount * partLength; // Total empty slots
        int availableTasks = tasks.length - maxFreq * maxFreqCount;
        int idles = Math.max(0, emptySlots - availableTasks);

        return tasks.length + idles;
    }
}
