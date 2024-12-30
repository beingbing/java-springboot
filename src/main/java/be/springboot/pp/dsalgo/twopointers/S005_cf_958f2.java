package be.springboot.pp.dsalgo.twopointers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class S005_cf_958f2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().trim().split("\\s+");
        int jediCount = Integer.parseInt(input[0]); // Total number of Jedi
        int colorType = Integer.parseInt(input[1]); // Number of lightsaber colors

        int[] jediSaberColor = new int[jediCount];
        input = br.readLine().trim().split("\\s+");
        for (int i = 0; i < jediCount; i++) jediSaberColor[i] = Integer.parseInt(input[i]);

        int[] reqColorTypeCnt = new int[colorType + 1]; // Required counts for each color
        input = br.readLine().trim().split("\\s+");
        for (int i = 1; i <= colorType; i++) reqColorTypeCnt[i] = Integer.parseInt(input[i-1]);

        // Output the result
        bw.write(findDisappearedJediCount(colorType, reqColorTypeCnt, jediCount, jediSaberColor) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int findDisappearedJediCount(int colorType, int[] reqColorTypeCnt, int jediCount, int[] jediSaberColor) {
        int[] curColorTypeCnt = new int[colorType + 1]; // 1-based indexing for colors
        Arrays.fill(curColorTypeCnt, 0);

        int left = 0; // Start of the sliding window
        int validColors = 0; // Count of colors meeting the required count
        int validJediSubarray = Integer.MAX_VALUE; // To track the smallest valid jedi subarray
        int minRemovals = Integer.MAX_VALUE;
        int totalRequired = Arrays.stream(reqColorTypeCnt).sum(); // Total knights needed

        // Total relevant colors (non-zero requirements)
        int relevantColors = 0;
        for (int i = 1; i <= colorType; i++) if (reqColorTypeCnt[i] > 0) relevantColors++;

        for (int right = 0; right < jediCount; right++) {
            curColorTypeCnt[jediSaberColor[right]]++; // Expand the window

            // Increment validColors if this color meets its required count
            if (curColorTypeCnt[jediSaberColor[right]] == reqColorTypeCnt[jediSaberColor[right]]) validColors++;

            // Shrink the window from the left once the current window becomes valid to get min possible valid window
            while (validColors == relevantColors) {
                int currentWindowSize = right - left + 1;
                validJediSubarray = Math.min(validJediSubarray, currentWindowSize);
                int removedKnights = validJediSubarray - totalRequired;
                minRemovals = Math.min(minRemovals, removedKnights);

                // Shrink the window
                curColorTypeCnt[jediSaberColor[left]]--;
                if (curColorTypeCnt[jediSaberColor[left]] < reqColorTypeCnt[jediSaberColor[left]]) validColors--; // No longer valid
                left++;
            }
        }

        return minRemovals == Integer.MAX_VALUE ? -1 : minRemovals;
    }
}
