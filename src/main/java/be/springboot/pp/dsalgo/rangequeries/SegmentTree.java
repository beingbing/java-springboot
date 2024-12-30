package be.springboot.pp.dsalgo.rangequeries;

public class SegmentTree {
    int[] tree;
    int n;

    public SegmentTree(int[] input) {
        n = input.length;
        tree = new int[4 * n];
        build(input, 0, 0, n - 1);
    }

    void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            build(arr, 2 * node + 1, start, mid);
            build(arr, 2 * node + 2, mid + 1, end);
            tree[node] = Math.min(tree[2 * node + 1], tree[2 * node + 2]);
        }
    }

    int query(int l, int r) {
        return queryUtil(0, 0, n - 1, l, r);
    }

    int queryUtil(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return Integer.MAX_VALUE;
        if (l <= start && end <= r) return tree[node];

        int mid = (start + end) / 2;
        int left = queryUtil(2 * node + 1, start, mid, l, r);
        int right = queryUtil(2 * node + 2, mid + 1, end, l, r);
        return Math.min(left, right);
    }
}
