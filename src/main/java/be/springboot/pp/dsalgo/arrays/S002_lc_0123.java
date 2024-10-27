package be.springboot.pp.dsalgo.arrays;

/*

You are given an array prices where prices[i] is the price of a given stock on the ith day. Find the maximum profit you can achieve. You may complete at most two transactions.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

Example 1:

Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.

Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.

Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.

Constraints:

1 <= prices.length <= 10^5
0 <= prices[i] <= 10^5

* */

// profit p = t1Profit + t2Profit
// t1Profit = h1SellPrice - h1BuyPrice
// t2Profit = h2SellPrice - h2BuyPrice
// we need to maximize p, for that we need to find
// t1Profit and t2Profit for all txns and then
// see combination for which index will give max profit
public class S002_lc_0123 {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int t1MinBuy =  prices[0];
        int[] t1SellDayProfit = new int[n];
        t1SellDayProfit[0] = 0;

        for (var price = 1; price < n; price++) {
            t1MinBuy = Math.min(t1MinBuy, prices[price]);
            t1SellDayProfit[price] = Math.max(t1SellDayProfit[price-1], prices[price] - t1MinBuy);
        }

        int sellPriceFor2ndTxn = prices[n-1];
        int[] t2SellDayProfit = new int[n];
        t2SellDayProfit[n-1] = 0;
        for (var price = n-2; price >= 0; price--) {
            sellPriceFor2ndTxn = Math.max(sellPriceFor2ndTxn, prices[price]);
            t2SellDayProfit[price] = Math.max(t2SellDayProfit[price+1], sellPriceFor2ndTxn - prices[price]);
        }

        int maxProfit = 0;
        for (int i = 1; i <= n-1; i++) {
            maxProfit = Math.max(maxProfit, t1SellDayProfit[i-1] + t2SellDayProfit[i]);
        }

        return maxProfit;
    }
}
