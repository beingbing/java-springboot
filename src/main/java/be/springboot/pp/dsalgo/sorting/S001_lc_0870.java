package be.springboot.pp.dsalgo.sorting;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;

public class S001_lc_0870 {

    public int[] advantageCount(int[] winner, int[] loser) {
        Arrays.sort(winner);

        int n = loser.length;
        List<Player> loserList = new ArrayList<>();
        for (int i = 0; i < n; i++)
            loserList.add(new Player(loser[i], i));

        loserList.sort(Comparator.comparingInt(player -> player.value));

        int[] winningOrder = new int[n];
        Deque<Integer> remaining = new ArrayDeque<>();
        int player = 0;

        for (int candVal : winner) {
            if (candVal > loserList.get(player).value)
                winningOrder[loserList.get(player++).index] = candVal; // Assign advantage value
            else remaining.add(candVal); // Store unused values
        }

        for (int i = 0; i < n; i++)
            if (winningOrder[i] == 0) winningOrder[i] = remaining.poll(); // Fill remaining slots

        return winningOrder;
    }
}

class Player {
    int value;
    int index;

    Player(int val, int idx) {
        this.value = val;
        this.index = idx;
    }
}
