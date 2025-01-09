package be.springboot.pp.dsalgo.graphs;

public class DisjointSetUnion {
    private final int[] parent;
    private final int[] rank;

    // Constructor to initialize the DSU with n elements
    public DisjointSetUnion(int n) {
        parent = new int[n];
        rank = new int[n];
        // Each element is initially its own parent (self-representative)
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    // Find operation with path compression
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    // Union operation by rank TC = O(e log v)
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    // Check if two elements are in the same set
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    // Utility to print parent array (for debugging)
    public void printParents() {
        System.out.print("Parents: ");
        for (int i : parent) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // Utility to print rank array (for debugging)
    public void printRanks() {
        System.out.print("Ranks: ");
        for (int i : rank) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Example usage
        int n = 5; // Number of elements (0 to 4)
        DisjointSetUnion dsu = new DisjointSetUnion(n);

        dsu.union(0, 1);
        dsu.union(1, 2);
        dsu.union(3, 4);

        System.out.println("Connected (0, 2): " + dsu.isConnected(0, 2)); // true
        System.out.println("Connected (0, 4): " + dsu.isConnected(0, 4)); // false

        dsu.union(2, 3);
        System.out.println("Connected (0, 4): " + dsu.isConnected(0, 4)); // true
    }
}