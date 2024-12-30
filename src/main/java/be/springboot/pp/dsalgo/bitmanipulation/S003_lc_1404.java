package be.springboot.pp.dsalgo.bitmanipulation;

public class S003_lc_1404 {
    public static int numberOfSteps(String s) {
        int steps = 0;
        StringBuilder binary = new StringBuilder(s);

        // Process until the binary number becomes "1"
        while (!binary.toString().equals("1")) {
            // If even, divide by 2 (remove last bit)
            if (binary.charAt(binary.length() - 1) == '0') binary.deleteCharAt(binary.length() - 1);
            else {
                // If odd, add 1 (binary addition)
                int i = binary.length() - 1;
                while (i >= 0 && binary.charAt(i) == '1') {
                    binary.setCharAt(i, '0'); // Carry propagation
                    i--;
                }
                if (i >= 0) binary.setCharAt(i, '1'); // Handle the increment
                else binary.insert(0, '1'); // Add new most significant bit
            }
            steps++; // Increment step count
        }

        return steps;
    }
}

class S003_lc_1404_2 {
    public int numberOfSteps(String binaryString) {
        int length = binaryString.length(); // Length of the binary string
        int carry = 0; // 0: no carry came from previous operation, 1: addition produced a carry forward bit
        int stepCount = 0; // To count the number of steps

        // Iterate from the least significant bit (rightmost) to the most significant bit
        for (int i = length - 1; i > 0; i--) {
            char currentBit = binaryString.charAt(i);

            if (currentBit == '1') {
                if (carry == 0) {
                    // For '1' with no carry, we add 2 steps:
                    // 1. Increment to make it even
                    // 2. Divide by 2
                    stepCount += 2;
                } else {
                    // For '1' with a carry, only 1 step is needed to divide by 2
                    stepCount++;
                }
                carry = 1; // A '1' causes a carry in binary addition
            } else {
                if (carry == 0) {
                    // For '0' with no carry, only 1 step is needed to divide by 2
                    stepCount++;
                } else {
                    // For '0' with a carry, we add 2 steps:
                    // 1. Increment to make it even (carry remains)
                    // 2. Divide by 2
                    stepCount += 2;
                }
            }
        }

        // Handle the most significant bit (leftmost bit)
        if (carry == 1) {
            // If there's a carry left, it takes one final step to add '1'
            stepCount++;
        }

        return stepCount;
    }
}
