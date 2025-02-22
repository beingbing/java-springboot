package be.springboot.pp.dsalgo.searching;

public class FindTwoSum {
    public int[] findTwoSum(int[] a, int key) {
        int n = a.length;
        int left = 0, right = n - 1;

        // = removed to avoid using the same element for both pointers
        while (left < right) {
            int sum = a[left] + a[right];

            if (sum > key) right--;
            else if (sum < key) left++;
            else return new int[]{left + 1, right + 1};
        }

        // Since we are guaranteed a solution, we should never reach here
        return new int[]{-1, -1};
    }
}
