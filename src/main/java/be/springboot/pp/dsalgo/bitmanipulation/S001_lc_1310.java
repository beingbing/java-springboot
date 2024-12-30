package be.springboot.pp.dsalgo.bitmanipulation;

public class S001_lc_1310 {
    public int[] xorQueries(int[] a, int[][] queries) {
        int n = a.length;
        int[] prefixXOR = new int[n];

        prefixXOR[0] = a[0];
        for (int i = 1; i < n; i++)
            prefixXOR[i] = prefixXOR[i - 1] ^ a[i];

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1];

            if (left == 0) result[i] = prefixXOR[right];
            else result[i] = prefixXOR[right] ^ prefixXOR[left - 1];
        }

        return result;
    }
}
