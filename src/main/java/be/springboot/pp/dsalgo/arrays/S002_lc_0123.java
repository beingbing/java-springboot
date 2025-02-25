package be.springboot.pp.dsalgo.arrays;

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

        int t2FutureMaxSellPrice = prices[n-1];
        int[] t2SellDayProfit = new int[n];
        t2SellDayProfit[n-1] = 0;
        for (var price = n-2; price >= 0; price--) {
            t2FutureMaxSellPrice = Math.max(t2FutureMaxSellPrice, prices[price]);
            t2SellDayProfit[price] = Math.max(t2SellDayProfit[price+1], t2FutureMaxSellPrice - prices[price]);
        }

        int maxProfit = 0;
        for (int i = 1; i <= n-1; i++) {
            maxProfit = Math.max(maxProfit, t1SellDayProfit[i-1] + t2SellDayProfit[i]);
        }

        return maxProfit;
    }
}
