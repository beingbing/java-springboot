package be.springboot.pp.dsalgo.linkedlist;

class NodeRandom {
    int val;
    NodeRandom next;
    NodeRandom random;

    NodeRandom(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class S001_lc_0138 {
    public NodeRandom copyRandomList(NodeRandom head) {
        if (head == null) return null;

        // Step 1: Clone nodes and interweave them with the original list
        NodeRandom current = head;
        while (current != null) {
            NodeRandom newNode = new NodeRandom(current.val);
            newNode.next = current.next;
            current.next = newNode;
            current = newNode.next;
        }

        // Step 2: Assign random pointers to the cloned nodes
        current = head;
        while (current != null) {
            if (current.random != null) current.next.random = current.random.next;
            current = current.next.next;
        }

        // Step 3: Separate the original and copied lists
        NodeRandom original = head;
        NodeRandom copyHead = head.next;
        NodeRandom copyCurrent = copyHead;

        while (original != null) {
            original.next = original.next.next;
            if (copyCurrent.next != null) copyCurrent.next = copyCurrent.next.next;
            original = original.next;
            copyCurrent = copyCurrent.next;
        }

        return copyHead;
    }

    // Helper function to print the list for testing
    public void printList(NodeRandom head) {
        NodeRandom current = head;
        while (current != null) {
            System.out.print("[" + current.val + ", ");
            System.out.print((current.random != null ? current.random.val : "null") + "] ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        S001_lc_0138 solution = new S001_lc_0138();

        // Example: Create the list [[7,null],[13,0],[11,4],[10,2],[1,0]]
        NodeRandom head = new NodeRandom(7);
        head.next = new NodeRandom(13);
        head.next.next = new NodeRandom(11);
        head.next.next.next = new NodeRandom(10);
        head.next.next.next.next = new NodeRandom(1);

        head.random = null;
        head.next.random = head;
        head.next.next.random = head.next.next.next.next;
        head.next.next.next.random = head.next.next;
        head.next.next.next.next.random = head;

        System.out.println("Original list:");
        solution.printList(head);

        NodeRandom copiedHead = solution.copyRandomList(head);

        System.out.println("Copied list:");
        solution.printList(copiedHead);
    }
}
