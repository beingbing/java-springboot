package be.springboot.pp.dsalgo.binarytrees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class S001_lc_0508 {
    Map<Integer, Integer> sumFrequency = new HashMap<>();
    int maxFrequency = Integer.MIN_VALUE;

    private int calculateSubtreeSums(Node node) {
        if (node == null) return 0;
        int leftSum = calculateSubtreeSums(node.left);
        int rightSum = calculateSubtreeSums(node.right);
        int subtreeSum = node.data + leftSum + rightSum;
        sumFrequency.put(subtreeSum, sumFrequency.getOrDefault(subtreeSum, 0) + 1);
        maxFrequency = Math.max(maxFrequency, sumFrequency.get(subtreeSum));
        return subtreeSum;
    }

    public int[] findFrequentTreeSum(Node root) {
        calculateSubtreeSums(root);

        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : sumFrequency.entrySet())
            if (entry.getValue() == maxFrequency) result.add(entry.getKey());

        return result.stream().mapToInt(i -> i).toArray();
    }
}
