package be.springboot.pp.dsalgo.stacks;

import java.util.Scanner;
import java.util.Stack;

public class S001_sp_stpar {
    private String canArrangeParade(int[] trucks, int n) {
        Stack<Integer> stack = new Stack<>();
        int expected = 1; // The next truck number expected in the parade

        for (int truck : trucks) {
            if (truck == expected) expected++; // Truck can directly move to the parade
            else {
                // Push into the stack if it cannot go directly
                while (!stack.isEmpty() && stack.peek() == expected) {
                    stack.pop();
                    expected++;
                }
                stack.push(truck);
            }
        }

        // Check remaining stack
        while (!stack.isEmpty() && stack.peek() == expected) {
            stack.pop();
            expected++;
        }

        // If all trucks are arranged correctly
        return stack.isEmpty() ? "yes" : "no";
    }
}
