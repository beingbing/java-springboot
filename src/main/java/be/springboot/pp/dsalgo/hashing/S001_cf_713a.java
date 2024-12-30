package be.springboot.pp.dsalgo.hashing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class S001_cf_713a {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder result = new StringBuilder();

        int queryCount = Integer.parseInt(br.readLine().trim());
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();

        while (queryCount-- > 0) {
            String[] input = br.readLine().trim().split("\\s+");
            String operation = input[0]; // "+", "-", or "?"
            String number = input[1];  // The number as a string

            int transformedNumber = computeBinaryParity(number); // Convert the number to its transformed integer representation

            if (operation.equals("+")) frequencyMap.put(transformedNumber, frequencyMap.getOrDefault(transformedNumber, 0) + 1); // Increment the frequency of the transformed number
            else if (operation.equals("-")) {
                frequencyMap.put(transformedNumber, frequencyMap.getOrDefault(transformedNumber, 0) - 1); // Decrement the frequency of the transformed number
                if (frequencyMap.get(transformedNumber) == 0) frequencyMap.remove(transformedNumber); // Remove entry if the count reaches zero
            } else if (operation.equals("?")) result.append(frequencyMap.getOrDefault(transformedNumber, 0)).append("\n");
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int computeBinaryParity(String number) {
        int len = number.length();
        int parityValue = 0;
        int bitPosition = 1; // Represents the current bit's weight (1, 2, 4, ...)

        for (int i = len - 1; i >= 0; i--) { // Process digits from right to left
            int digit = number.charAt(i) - '0';
            if (digit % 2 == 1) { // If the digit is odd, sum up its bit weight
                parityValue += bitPosition;
            }
            bitPosition *= 2; // Move to the next bit position
        }

        return parityValue; // bit weight of given number
    }
}
