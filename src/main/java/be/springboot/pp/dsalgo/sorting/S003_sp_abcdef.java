package be.springboot.pp.dsalgo.sorting;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class S003_sp_abcdef {
    public static int countSextuples(int[] S, int n) {
        Map<Integer, Integer> lhsMap = new HashMap<>();
        Map<Integer, Integer> rhsMap = new HashMap<>();

        // Step 1: Calculate all possible LHS values and their frequencies
        for (int a : S)
            for (int b : S)
                for (int c : S) {
                    int lhs = a * b + c;
                    lhsMap.put(lhs, lhsMap.getOrDefault(lhs, 0) + 1);
                }

        // Step 2: Calculate all possible RHS values and their frequencies
        for (int d : S) {
            if (d == 0) continue; // Skip cases where d == 0
            for (int e : S)
                for (int f : S) {
                    int rhs = d * (e + f);
                    rhsMap.put(rhs, rhsMap.getOrDefault(rhs, 0) + 1);
                }
        }

        // Step 3: Match LHS and RHS values to count sextuples
        long count = 0;
        for (Map.Entry<Integer, Integer> entry : lhsMap.entrySet()) {
            int lhs = entry.getKey();
            int lhsFreq = entry.getValue();
            int rhsFreq = rhsMap.getOrDefault(lhs, 0);
            count = (count + (long) lhsFreq * rhsFreq);
        }

        return (int) count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); // Number of test cases

        while (T-- > 0) {
            int N = sc.nextInt();
            int[] S = new int[N];
            for (int i = 0; i < N; i++) {
                S[i] = sc.nextInt();
            }

            System.out.println(countSextuples(S, N));
        }
        sc.close();
    }
}

