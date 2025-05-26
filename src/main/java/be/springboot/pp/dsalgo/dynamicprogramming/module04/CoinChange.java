package be.springboot.pp.dsalgo.dynamicprogramming.module04;

import java.util.Arrays;

public class CoinChange {
    public static int minCoins(int[] coins, int amount) {
        int MAX = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, MAX);
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }

        return dp[amount] == MAX ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(minCoins(coins, amount)); // Output: 3
    }
}