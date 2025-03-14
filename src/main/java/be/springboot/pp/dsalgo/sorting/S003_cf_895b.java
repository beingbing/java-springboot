package be.springboot.pp.dsalgo.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S003_cf_895b {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[] a = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            a[i] = Long.parseLong(st.nextToken());

        out.println(countPairs(a, n, x, k));
        out.flush();
        out.close();
    }

    public static long countPairs(long[] a, int n, int x, int k) {
        Arrays.sort(a);
        long ans = 0;

        for (int i = 0; i < n; i++) { // fix i and look for j
            long m = (a[i] - 1) / x + k; // multiplier giving k multiples till a[j]

            int leftIndex = lowerBound(a, Math.max(m * x, a[i]));
            int rightIndex = lowerBound(a, (m + 1) * x);

            ans += (rightIndex - leftIndex);
        }

        return ans;
    }

    static int lowerBound(long[] a, long key) {
        int left = 0, right = a.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (a[mid] > key) right = mid-1;
            else if (a[mid] < key) left = mid+1;
            else {
                if (mid == 0 || a[mid-1] != key) return mid;
                else right = mid-1;
            }
        }
        return left;
    }
}
