package be.springboot.pp.dsalgo.graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class S003_gfg_shortest_prime_path {

    private boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }


    public int solve(int num1, int num2) {
        if (num1 == num2) return 0;

        // Generate all 4-digit primes
        Set<Integer> primes = new HashSet<>();
        for (int i = 1000; i <= 9999; i++) if (isPrime(i)) primes.add(i);

        // BFS initialization
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(num1);
        visited.add(num1);

        int steps = 0;

        // BFS loop
        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;

            for (int i = 0; i < size; i++) {
                int current = queue.poll();

                // Generate all neighbors by changing one digit
                for (int j = 0; j < 4; j++) {
                    char[] digits = String.valueOf(current).toCharArray();

                    for (char d = '0'; d <= '9'; d++) {
                        if (digits[j] == d) continue; // Skip same digit
                        digits[j] = d;

                        int neighbor = Integer.parseInt(new String(digits));

                        // Check if the neighbor is a valid 4-digit prime
                        if (primes.contains(neighbor) && !visited.contains(neighbor)) {
                            if (neighbor == num2) return steps; // Found the target
                            queue.add(neighbor);
                            visited.add(neighbor);
                        }
                    }
                }
            }
        }

        // If num2 is unreachable
        return -1;
    }
}
