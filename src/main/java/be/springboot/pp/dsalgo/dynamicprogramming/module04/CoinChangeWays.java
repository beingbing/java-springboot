package be.springboot.pp.dsalgo.dynamicprogramming.module04;

import java.util.Arrays;

public class CoinChangeWays {
    public int countWays(int[] coins, int amount) {
        int n = coins.length;
        int MOD = 1_000_000_007;
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins)
            for (int i = coin; i <= amount; i++)
                dp[i] = (dp[i] + dp[i - coin]) % MOD;

        return dp[amount];
    }

    private final int[][] memo;
    private final int[] coins;
    private final int amount;

    public int countWays(int index, int amount) {
        if (amount == 0) return 1;  // Found a valid combination
        if (index == coins.length) return 0;  // No more coins to use
        if (memo[index][amount] != -1) return memo[index][amount];

        int ways = 0;

        // Option 1: Skip the current coin
        ways += countWays(index + 1, amount);

        // Option 2: Pick the current coin (if possible)
        if (coins[index] <= amount) ways += countWays(index, amount - coins[index]);

        memo[index][amount] = ways;
        return ways;
    }

    public CoinChangeWays(int[] coins, int amount) {
        this.amount = amount;
        memo = new int[coins.length][amount + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        this.coins = coins;
    }

    public int countWays() {
        return countWays(0, amount);
    }
}
