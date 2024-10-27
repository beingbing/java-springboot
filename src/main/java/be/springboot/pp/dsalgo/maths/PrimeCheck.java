package be.springboot.pp.dsalgo.maths;

public class PrimeCheck {

    public boolean isPrime(int n) {
        // Step 1: Handle small numbers
        if (n <= 1) return false;  // 1 and numbers below are not prime
        if (n == 2) return true;  // 2 is the only even prime number
        if (n % 2 == 0) return false;  // All other even numbers are not prime

        // Step 2: Check divisibility up to √n (only odd numbers)
        for (int i = 3; i * i <= n; i += 2)
            if (n % i == 0) return false;  // If divisible by any number, n is not prime

        return true;
    }

    public void mainly(String[] args) {
        int n = 29;  // Example input
        if (isPrime(n)) {
            System.out.println(n + " is prime.");
        } else {
            System.out.println(n + " is not prime.");
        }
    }
}

