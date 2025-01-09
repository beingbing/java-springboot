package be.springboot.pp.dsalgo.greedy;

import java.util.Arrays;

public class S002_fractional_knapsack {
    public double getMaxValue(int[] val, int[] wt, int capacity) {
        int n = val.length;

        // Step 1: Create an array of items with value, weight, and ratio
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) items[i] = new Item(val[i], wt[i]);

        // Step 2: Sort items by their value-to-weight ratio in descending order
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        // Step 3: Iterate through sorted items and fill the knapsack
        double totalValue = 0.0;
        for (Item item : items) {
            if (capacity == 0) break; // If knapsack is full, stop

            // If the current item fits completely in the remaining capacity
            if (item.weight <= capacity) {
                totalValue += item.value; // Add its full value
                capacity -= item.weight; // Reduce the remaining capacity
            } else { // Take the fraction of the item that fits
                totalValue += item.ratio * capacity;
                break; // Knapsack is now full
            }
        }

        return Math.round(totalValue * 1e6) / 1e6; // Round to 6 decimal places
    }
}

class Item {
    int value, weight;
    double ratio; // value-to-weight ratio

    Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
        this.ratio = (double) value / weight;
    }
}
