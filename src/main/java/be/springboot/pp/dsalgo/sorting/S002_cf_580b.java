package be.springboot.pp.dsalgo.sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class S002_cf_580b {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of friends and minimum difference d
        int n = sc.nextInt();
        long d = sc.nextLong();

        // Array to store friends' money and friendship factor
        Friend[] friends = new Friend[n];

        for (int i = 0; i < n; i++) {
            long money = sc.nextLong();
            long friendship = sc.nextLong();
            friends[i] = new Friend(money, friendship);
        }

        System.out.println(getMaxFriendship(n, friends, d));
    }

    private static long getMaxFriendship(int n, Friend[] friends, long d) {
        Arrays.sort(friends, Comparator.comparingLong(f -> f.money)); // Sort friends by money

        long maxFriendship = 0, currentSum = 0;
        int start = 0;

        // Sliding window to maximize friendship factor sum
        for (int end = 0; end < n; end++) {
            currentSum += friends[end].friendship;

            // Shrink the window if money difference exceeds d
            while (friends[end].money - friends[start].money >= d) {
                currentSum -= friends[start].friendship;
                start++;
            }

            // Update the maximum friendship factor
            maxFriendship = Math.max(maxFriendship, currentSum);
        }
        return maxFriendship;
    }
}

class Friend {
    long money, friendship;

    Friend(long money, long friendship) {
        this.money = money;
        this.friendship = friendship;
    }
}
