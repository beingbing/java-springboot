package be.springboot.pp.dsalgo.dynamicprogramming.module06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Box implements Comparable<Box> {
    int length, width, height;

    Box(int l, int w, int h) {
        this.length = l;
        this.width = w;
        this.height = h;
    }

    @Override
    public int compareTo(Box other) {
        return (other.length * other.width) - (this.length * this.width);
    }
}

public class BoxStacking {
    public static int maxHeight(int[] height, int[] width, int[] length) {
        int n = height.length;
        List<Box> boxes = new ArrayList<>();

        // Generate all rotations of boxes
        for (int i = 0; i < n; i++) {
            boxes.add(new Box(Math.max(length[i], width[i]), Math.min(length[i], width[i]), height[i]));
            boxes.add(new Box(Math.max(height[i], width[i]), Math.min(height[i], width[i]), length[i]));
            boxes.add(new Box(Math.max(length[i], height[i]), Math.min(length[i], height[i]), width[i]));
        }

        // Sort boxes by decreasing base area (l * w)
        Collections.sort(boxes);

        int size = boxes.size();
        int[] dp = new int[size];

        // Initialize DP array with the height of each box
        for (int i = 0; i < size; i++) dp[i] = boxes.get(i).height;

        int maxStackHeight = 0;

        // Compute max height using DP
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < i; j++)
                if (boxes.get(i).length < boxes.get(j).length && boxes.get(i).width < boxes.get(j).width)
                    dp[i] = Math.max(dp[i], dp[j] + boxes.get(i).height);

            maxStackHeight = Math.max(maxStackHeight, dp[i]);
        }

        return maxStackHeight;
    }

    public static void main(String[] args) {
        int[] height = {4, 1, 4, 10};
        int[] width = {6, 2, 5, 12};
        int[] length = {7, 3, 6, 32};

        System.out.println(maxHeight(height, width, length)); // Output: 60
    }
}