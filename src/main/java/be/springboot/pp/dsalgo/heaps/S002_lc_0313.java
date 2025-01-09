package be.springboot.pp.dsalgo.heaps;

import java.util.Arrays;
import java.util.PriorityQueue;

public class S002_lc_0313 {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] ugly = new int[n];
        ugly[0] = 1; // First super ugly number is always 1
        PriorityQueue<SuperUgly> minHeap = new PriorityQueue<>((a, b) -> a.value - b.value);

        // Step 2: Add initial candidates (prime, prime * 1, index = 0) to the heap
        for (int prime : primes) minHeap.offer(new SuperUgly(prime, prime, 0));

        // Step 3: Generate the sequence of super ugly numbers
        for (int i = 1; i < n; i++) {
            // Extract the smallest element
            SuperUgly current = minHeap.poll();
            ugly[i] = current.value; // Add the smallest element to the result array
            while (!minHeap.isEmpty() && minHeap.peek().value == current.value) minHeap.poll(); // Avoid duplicate entries by skipping repeated values
            minHeap.offer(new SuperUgly(current.prime * ugly[current.index + 1], current.prime, current.index + 1)); // Push the next candidate for the current prime
        }

        return ugly[n - 1]; // Return the nth super ugly number
    }
}

class S002_lc_0313_2 {
    public int nthSuperUglyNumber(int n, int[] primes) {
        // Step 1: Initialize variables
        int[] ugly = new int[n];
        int[] indices = new int[primes.length]; // Pointers for each prime
        int[] values = Arrays.copyOf(primes, primes.length); // Next values for each prime
        ugly[0] = 1; // First super ugly number

        // Step 2: Generate n super ugly numbers
        for (int i = 1; i < n; i++) {
            // Find the smallest value among the current values
            int nextUgly = Integer.MAX_VALUE;
            for (int val : values) {
                nextUgly = Math.min(nextUgly, val);
            }
            ugly[i] = nextUgly;

            // Increment pointers for all primes that produced the smallest value
            for (int j = 0; j < primes.length; j++) {
                if (values[j] == nextUgly) {
                    indices[j]++;
                    values[j] = ugly[indices[j]] * primes[j];
                }
            }
        }

        // Step 3: Return the nth super ugly number
        return ugly[n - 1];
    }
}

class SuperUgly {
    int value;
    int prime;
    int index;

    public SuperUgly(int v, int p, int i) {
        this.value = v;
        this.prime = p;
        this.index = i;
    }
}
