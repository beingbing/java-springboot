package be.springboot.pp.dsalgo.recursion;

import java.util.ArrayList;
import java.util.List;

public class S003_gfg_tower_of_hanoi {

    private static List<int[]> moves;

    public static void main(String[] args) {
        int N = 3, n = 4;
        int[] result = shiftPile(N, n);
        System.out.println(result[0] + " " + result[1]);
    }

    public static int[] shiftPile(int N, int n) {
        moves = new ArrayList<>();
        hanoi(N, 1, 3, 2, n);
        return moves.get(n - 1); // Since moves list is zero-indexed
    }

    private static void hanoi(int plates, int from, int to, int aux, int n) {
        if (plates == 0 || moves.size() >= n) { // moves.size() is extra constraint
            return;
        }

        // Step 1: Move N-1 plates from 'from' to 'aux'
        hanoi(plates - 1, from, aux, to, n);

        // Step 2: Move the Nth plate from 'from' to 'to'
        if (moves.size() < n) { // remove this constraint to convert it into a standard solution
            moves.add(new int[]{from, to});
        }

        // Step 3: Move the N-1 plates from 'aux' to 'to'
        hanoi(plates - 1, aux, to, from, n);
    }
}

/**
 1. **Recursive Approach**:
 - Use a helper function `hanoi()` to generate moves recursively.
 - For each plate `N`, break down the problem into three recursive calls:
 - Move `N-1` plates from `from` to `aux`.
 - Move the `N`th plate from `from` to `to`.
 - Move `N-1` plates from `aux` to `to`.
 */