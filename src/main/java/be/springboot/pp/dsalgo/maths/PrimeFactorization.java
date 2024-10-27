package be.springboot.pp.dsalgo.maths;

public class PrimeFactorization {

    public static void primeFactorization(int n) {
        // Step 1: Handle the factor 2
        while (n % 2 == 0) {
            System.out.print(2 + " ");
            n /= 2;
        }

        // Step 2: Handle odd factors from 3 to √n
        for (int i = 3; i * i <= n; i += 2) {
            while (n % i == 0) {
                System.out.print(i + " ");
                n /= i;
            }
        }

        // Step 3: If remaining n is a prime number greater than 2
        if (n > 2) System.out.print(n);
    }
}
