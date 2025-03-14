package be.springboot.pp.dsalgo.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S004_cf_231c {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            a[i] = Integer.parseInt(st.nextToken());

        int[] ans = maxFrequency(a, k);
        out.println(ans[0] + " " + ans[1]);
        out.flush();
        out.close();
    }

    public static int[] maxFrequency(int[] a, int k) {
        Arrays.sort(a); // Sort to efficiently process frequency
        int n = a.length, left = 0;
        long sum = 0, maxFreq = 0, bestNum = a[0];

        for (int right = 0; right < n; right++) {
            sum += a[right];

            while ((long) a[right] * (right - left + 1) - sum > k)
                sum -= a[left++]; // Shrink window if exceeding k ops

            int currentFrequency = right - left + 1;
            if (currentFrequency > maxFreq) {
                maxFreq = currentFrequency;
                bestNum = a[right];
            }
        }
        return new int[]{(int) maxFreq, (int) bestNum};
    }
}
