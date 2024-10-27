package be.springboot.pp.dsalgo.maths;

import java.util.Scanner;

public class CountDivisors {

    public static int countDivisors(int N) {
        int count = 0;

        // Iterate from 1 to sqrt(N)
        for (int i = 1; i * i <= N; i++)
            if (N % i == 0) // i is a divisor, and N/i is also a divisor
                if (i == N / i) count += 1; // If both divisors are the same, count only once
                else count += 2; // Otherwise, count both divisors

        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the value of N: ");
        int N = sc.nextInt();
        int result = countDivisors(N);
        System.out.println("The number of divisors of " + N + " is: " + result);
    }
}
