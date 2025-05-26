package be.springboot.pp.dsalgo.dynamicprogramming.module11;

public class BurstBalloons {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] balloons = new int[n + 2];
        balloons[0] = 1;
        balloons[n + 1] = 1;

        for (int i = 0; i < n; i++) balloons[i + 1] = nums[i];

        Integer[][] memo = new Integer[n + 2][n + 2];
        return burst(balloons, 1, n, memo);
    }

    private int burst(int[] nums, int left, int right, Integer[][] memo) {
        if (left > right) return 0;

        if (memo[left][right] != null) return memo[left][right];

        int max = 0;
        for (int i = left; i <= right; i++) {
            int coins = nums[left - 1] * nums[i] * nums[right + 1]
                    + burst(nums, left, i - 1, memo)
                    + burst(nums, i + 1, right, memo);
            max = Math.max(max, coins);
        }

        memo[left][right] = max;
        return max;
    }

    public int maximumCoins(int[] nums) {
        int n = nums.length;
        int[] balloons = new int[n + 2];
        balloons[0] = 1;
        balloons[n + 1] = 1;

        for (int i = 0; i < n; i++) balloons[i + 1] = nums[i];

        int[][] dp = new int[n + 2][n + 2];

        // length is the window size
        for (int len = 1; len <= n; len++) {
            for (int left = 1; left <= n - len + 1; left++) {
                int right = left + len - 1;
                for (int i = left; i <= right; i++) {
                    int coins = balloons[left - 1] * balloons[i] * balloons[right + 1]
                            + dp[left][i - 1] + dp[i + 1][right];
                    dp[left][right] = Math.max(dp[left][right], coins);
                }
            }
        }

        return dp[1][n]; // max coins from bursting balloons 1..n
    }
}
