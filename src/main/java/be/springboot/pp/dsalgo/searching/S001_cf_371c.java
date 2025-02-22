package be.springboot.pp.dsalgo.searching;

import java.util.Scanner;

public class S001_cf_371c {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read input values
        String recipe = scanner.next();
        long nb = scanner.nextLong(), ns = scanner.nextLong(), nc = scanner.nextLong();
        long pb = scanner.nextLong(), ps = scanner.nextLong(), pc = scanner.nextLong();
        long rubles = scanner.nextLong();
        scanner.close();

        // Count ingredients needed per hamburger from the recipe
        long requiredB = recipe.chars().filter(c -> c == 'B').count();
        long requiredS = recipe.chars().filter(c -> c == 'S').count();
        long requiredC = recipe.chars().filter(c -> c == 'C').count();

        // Binary search to find the maximum number of hamburgers
        long left = 0, right = (long) 1e13; // Safe high bound due to constraints

        while (left <= right) {
            long mid = left + (right - left) / 2;

            // Calculate additional ingredients needed
            long neededB = Math.max(0, requiredB * mid - nb);
            long neededS = Math.max(0, requiredS * mid - ns);
            long neededC = Math.max(0, requiredC * mid - nc);

            // Calculate total cost
            long totalCost = neededB * pb + neededS * ps + neededC * pc;

            // Check if we can afford this cost with available rubles
            if (totalCost <= rubles) left = mid + 1;
            else right = mid - 1; // Too costly, try fewer hamburgers
        }

        System.out.println(right); // Output the maximum hamburgers possible
        scanner.close();
    }
}
