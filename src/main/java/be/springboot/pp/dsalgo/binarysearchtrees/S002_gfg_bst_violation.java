package be.springboot.pp.dsalgo.binarysearchtrees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class SubtreeInfo {
    int minVal;      // Minimum value in the subtree
    int maxVal;      // Maximum value in the subtree
    int violations;  // Number of violations
    List<Integer> values; // List of values in the subtree

    SubtreeInfo(int minVal, int maxVal, int violations, List<Integer> values) {
        this.minVal = minVal;
        this.maxVal = maxVal;
        this.violations = violations;
        this.values = values;
    }
}

public class S002_gfg_bst_violation {

    public int pairsViolatingBST(int n, TreeNode root) {
        if (root == null) return 0; // Edge case: Empty tree
        List<Integer> jumbledValues = new ArrayList<>(); // Step 1: Perform an in-order traversal to collect node values in sorted order
        inorderTraversal(root, jumbledValues);
        int[] values = new int[n];
        for (int i = 0; i < n; i++) values[i] = jumbledValues.get(i);
        return mergeSortAndCount(values, 0, n - 1);
    }

    private void inorderTraversal(TreeNode root, List<Integer> values) {
        if (root == null) return;
        inorderTraversal(root.left, values);
        values.add(root.data);
        inorderTraversal(root.right, values);
    }

    private int mergeSortAndCount(int[] arr, int left, int right) {
        int count = 0;
        if (left == right) return count;
        int mid = left + (right - left) / 2;
        count += mergeSortAndCount(arr, left, mid);
        count += mergeSortAndCount(arr, mid + 1, right);
        count += mergeAndCount(arr, left, mid, right);
        return count;
    }

    private static int mergeAndCount(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];
        for (int i = 0; i < n1; i++) leftArr[i] = arr[left + i];
        for (int i = 0; i < n2; i++) rightArr[i] = arr[mid + 1 + i];
        int i = 0, j = 0, k = left;
        int count = 0;

        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) arr[k++] = leftArr[i++];
            else {
                arr[k++] = rightArr[j++];
                count += (n1 - i);
            }
        }
        while (i < n1) arr[k++] = leftArr[i++];
        while (j < n2) arr[k++] = rightArr[j++];
        return count;
    }

    // it worked, but it got timed out.
//    public int pairsViolatingBST(int n, TreeNode root) {
//        if (root == null) return 0;
//        return countViolations(root).violations;
//    }

    private SubtreeInfo countViolations(TreeNode root) {
        if (root == null) return new SubtreeInfo(Integer.MAX_VALUE, Integer.MIN_VALUE, 0, new ArrayList<>());
        SubtreeInfo left = countViolations(root.left);
        SubtreeInfo right = countViolations(root.right);

        int violations = left.violations + right.violations; // Calculate current node's violations with children

        for (int l : left.values)
            if (l >= root.data) violations++;

        for (int r : right.values)
            if (root.data >= r) violations++;

//        if (root.left != null && root.data <= root.left.data) violations++;
//        if (root.right != null && root.data >= root.right.data) violations++;

        // Count cross subtree violations
        for (int l : left.values)
            for (int r : right.values)
                if (l > r) violations++;

        // Merge current node values
        List<Integer> merged = new ArrayList<>(left.values);
        merged.add(root.data);
        merged.addAll(right.values);

        // Return updated subtree information
        return new SubtreeInfo(
                Math.min(root.data, left.minVal),  // Minimum value
                Math.max(root.data, right.maxVal), // Maximum value
                violations,                       // Total violations
                merged                            // Values in subtree
        );
    }
}

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t;
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {

            int n;
            n = Integer.parseInt(br.readLine());

            TreeNode root = TreeNode.inputTree(br);

            S002_gfg_bst_violation obj = new S002_gfg_bst_violation();
            printInorder(root);
            System.out.println();
            printPreorder(root);
            System.out.println();
            int res = obj.pairsViolatingBST(n, root);

            System.out.println(res);
        }
    }

    private static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.data);
        System.out.print(" ");
        printInorder(root.right);
    }

    private static void printPreorder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.data);
        System.out.print(" ");
        printPreorder(root.left);
        printPreorder(root.right);
    }
}