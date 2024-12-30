package be.springboot.pp.dsalgo.bitmanipulation;

public class S001_gfg_ele_appearing_once {
    public static int findSingleNumber(int[] arr) {
        int result = 0;

        // XOR all elements in the array
        for (int num : arr) result ^= num;

        return result;
    }
}
