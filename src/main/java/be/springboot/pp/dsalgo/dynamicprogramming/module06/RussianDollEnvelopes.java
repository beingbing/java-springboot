package be.springboot.pp.dsalgo.dynamicprogramming.module06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RussianDollEnvelopes {
    public int maxEnvelopes(int[][] envs) {
        if (envs.length == 0) return 0;

        List<Envelope> envelopes = new ArrayList<>();
        for (int[] env : envs) envelopes.add(new Envelope(env[0], env[1]));

        Collections.sort(envelopes); // Sort envelopes by width (asc), height (desc if width is same)

        int[] lihs = new int[envelopes.size()]; // longest inc height subsequence
        Arrays.fill(lihs, 1);

        int len = 0;
        for (int i = 0; i < envelopes.size(); i++) {
            Envelope cur = envelopes.get(i);
            for (int j = 0; j < i; j++) {
                Envelope prev = envelopes.get(j);
                if (cur.height > prev.height)
                    lihs[i] = Math.max(lihs[i], lihs[j] + 1);
            }

            len = Math.max(len, lihs[i]);
        }
        return len;
    }

    public static void main(String[] args) {
        RussianDollEnvelopes solver = new RussianDollEnvelopes();
        int[][] envelopes1 = {{5,4},{6,4},{6,7},{2,3}};
        System.out.println(solver.maxEnvelopes(envelopes1)); // Output: 3

        int[][] envelopes2 = {{1,1},{1,1},{1,1}};
        System.out.println(solver.maxEnvelopes(envelopes2)); // Output: 1
    }
}

class Envelope implements Comparable<Envelope> {
    int width;
    int height;
    Envelope(int width, int height) {
        this.width = width;
        this.height = height;
    }
    @Override
    public int compareTo(Envelope e) {
        if (this.width == e.width) return e.height - this.height;
        return this.width - e.width;
    }
}
