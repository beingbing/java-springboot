package be.springboot.pp.dsalgo.tries;

public class BinaryTrie {
    private final BinaryTrieNode root;

    public BinaryTrie() {
        root = new BinaryTrieNode();
    }

    public void insert(int num) {
        BinaryTrieNode current = root;
        for (int i = 31; i >= 0; i--) { // Process each bit from most significant to least significant
            int bit = (num >> i) & 1; // reduce bit to 0 or 1
            if (current.children[bit] == null) current.children[bit] = new BinaryTrieNode();
            current = current.children[bit];
            current.prefixCount++;
        }
    }

    // Finds the maximum XOR of provided number with numbers already in the Trie
    public int findMaxXOR(int num) {
        BinaryTrieNode current = root;
        int maxXOR = 0;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            int oppositeBit = 1 - bit; // Opposite bit for maximizing XOR
            if (current.children[oppositeBit] != null) { // if opposite bit exists
                maxXOR = (maxXOR << 1) | 1; // left shift and set LSB
                current = current.children[oppositeBit];
            } else {
                maxXOR = maxXOR << 1; // left shift but LSB will be unset
                current = current.children[bit];
            }
        }
        return maxXOR;
    }

    // Finds the minimum XOR for a given number
    public int findMinXOR(int num) {
        BinaryTrieNode current = root;
        int minXOR = 0;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (current.children[bit] != null) { // Take the same bit for minimizing XOR
                minXOR = minXOR << 1;
                current = current.children[bit];
            } else { // If same bit is not available, take the opposite bit
                minXOR = (minXOR << 1) | 1;
                current = current.children[1 - bit]; // chose opposite bit out of compulsion but traverse in same bit
            }
        }
        return minXOR;
    }

    // Count numbers in the Trie whose XOR with 'num' is less than 'limit'
    public int countLessThan(int num, int limit) {
        BinaryTrieNode current = root;
        int count = 0;
        for (int i = 31; i >= 0; i--) {
            if (current == null) break;
            int numBit = (num >> i) & 1;
            int limitBit = (limit >> i) & 1;

            if (limitBit == 1) { // If limit bit is 1, add count of numbers with opposite bit at this position
                if (current.children[numBit] != null) count += current.children[numBit].prefixCount;
                current = current.children[1 - numBit]; // Move to the other branch
            } else // if limit-bit is 0, the current bit can't contribute to our answer
                current = current.children[numBit]; // Move to the branch with the same bit as num
        }
        return count;
    }
}
