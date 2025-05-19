package be.springboot.pp.dsalgo.dynamicprogramming.module2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Vacations {

    private static int minRestDays(int[] vacationSchedule, int numDays) {
        int[][] dp = new int[numDays + 1][3]; // dp[i][0] -> Rest, dp[i][1] -> Contest, dp[i][2] -> Gym

        for (int day = 1; day <= numDays; day++) {
            int activity = vacationSchedule[day - 1];

            // Option 1: Rest today (take min of any previous day's activity and add 1 rest day)
            dp[day][0] = Math.min(dp[day - 1][0], Math.min(dp[day - 1][1], dp[day - 1][2])) + 1; // Rest

            // Option 2: Do contest today (if possible)
            if (activity == 1 || activity == 3) {
                dp[day][1] = Math.min(dp[day - 1][0], dp[day - 1][2]); // Previous day must NOT be a contest
            } else {
                dp[day][1] = Integer.MAX_VALUE; // Not allowed
            }

            // Option 3: Go to the gym today (if possible)
            if (activity == 2 || activity == 3) {
                dp[day][2] = Math.min(dp[day - 1][0], dp[day - 1][1]); // Previous day must NOT be gym
            } else {
                dp[day][2] = Integer.MAX_VALUE; // Not allowed
            }
        }

        // Answer: Minimum rest days possible at the end
        return Math.min(dp[numDays][0], Math.min(dp[numDays][1], dp[numDays][2]));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int n = Integer.parseInt(st.nextToken());
        int[] days = new int[n];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < n; i++) days[i] = Integer.parseInt(st.nextToken());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(minRestDays(days, n)));
        bw.flush();
        bw.close();
        br.close();
    }
}
