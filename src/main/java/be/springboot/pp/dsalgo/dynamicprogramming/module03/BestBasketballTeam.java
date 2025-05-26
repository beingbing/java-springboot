package be.springboot.pp.dsalgo.dynamicprogramming.module03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BestBasketballTeam {
    public int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length;
        List<Player> players = new ArrayList<>(n); // List of (age, score) pairs sorted by age>

        for (int i = 0; i < n; i++) players.add(new Player(ages[i], scores[i]));
        Collections.sort(players);

        int[] dp = new int[n];
        int maxScore = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = players.get(i).score;
            for (int j = 0; j < i; j++)
                if (players.get(j).score <= players.get(i).score) // No conflict
                    dp[i] = Math.max(dp[i], dp[j] + players.get(i).score);
            maxScore = Math.max(maxScore, dp[i]);
        }

        return maxScore;
    }
}

class Player implements Comparable<Player> {
    int age;
    int score;

    public Player(int age, int score) {
        this.age = age;
        this.score = score;
    }

    @Override
    public int compareTo(Player other) {
        if (age == other.age) return score - other.score;
        return age - other.age;
    }
}
