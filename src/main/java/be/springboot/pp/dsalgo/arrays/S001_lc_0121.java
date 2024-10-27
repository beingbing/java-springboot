package be.springboot.pp.dsalgo.arrays;

public class S001_lc_0121 {

    public int maxProfit(int[] prices) {
        int profit = 0, minPrice = Integer.MAX_VALUE;

        for (var price : prices) {
            minPrice = Math.min(minPrice, price);
            profit = Math.max(profit, price - minPrice);
        }
        return profit;
    }
}
