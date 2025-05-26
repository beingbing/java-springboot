package be.springboot.pp.dsalgo.dynamicprogramming.module03;

import java.util.Arrays;

public class RodLength {

    public int getMaxProfit(int[] pricePerLength) {
        int rodLen = pricePerLength.length;
        int[] maxProfitAtLen = new int[rodLen + 1];

        for (int curLen = 1; curLen <= rodLen; curLen++) {
            System.out.println("curLen: " + curLen);
            int curMaxProfit = 0;

            System.out.println("before curMaxProfit: " + curMaxProfit);

            // Try every cut size that is <= current length
            for (int cutSz = 1; cutSz <= curLen; cutSz++) {
                System.out.println("cutSz: " + cutSz);
                int priceOfCut = pricePerLength[cutSz - 1];
                System.out.println("priceOfCut: " + priceOfCut);
                int remLen = curLen - cutSz;
                System.out.println("remLen: " + remLen);
                int totalProfit = priceOfCut + maxProfitAtLen[remLen];
                System.out.println("totalProfit: " + totalProfit);
                curMaxProfit = Math.max(curMaxProfit, totalProfit);
                System.out.println("interim curMaxProfit: " + curMaxProfit);
            }

            System.out.println("after curMaxProfit: " + curMaxProfit);
            maxProfitAtLen[curLen] = curMaxProfit;
            System.out.println("maxProfitAtLen: " + Arrays.toString(maxProfitAtLen));
        }

        return maxProfitAtLen[rodLen];
    }

    public static void main(String[] args) {
        int[] pricePerLength = {1, 5, 8, 9};
        int maxProfit = new RodLength().getMaxProfit(pricePerLength);
        System.out.println("maxProfit: " + maxProfit);
    }

}
