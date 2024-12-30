package be.springboot.pp.dsalgo.bitmanipulation;

public class S003_lc_1611 {
    public static int minimumOneBitOperations(int ele) {
        int ans = 0;
        while (ele > 0) {
            ans ^= ele;
            ele = ele >> 1;
        }
        return ans;
    }
}
