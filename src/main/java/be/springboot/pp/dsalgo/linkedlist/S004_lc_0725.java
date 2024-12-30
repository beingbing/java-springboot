package be.springboot.pp.dsalgo.linkedlist;

public class S004_lc_0725 {
    public Node[] splitListToParts(Node head, int k) {
        // Count the total length of the list
        int length = 0;
        Node current = head;
        while (current != null) {
            length++;
            current = current.next;
        }

        // Calculate the base size and extra nodes
        int baseSize = length / k;
        int extra = length % k;

        // Create the result array
        Node[] result = new Node[k];
        current = head;

        // Split the list
        for (int i = 0; i < k; i++) {
            Node partHead = current; // Start of the current part
            Node prev = null;

            // Determine the size of the current part
            int partSize = baseSize + (i < extra ? 1 : 0);
            for (int j = 0; j < partSize; j++) {
                prev = current;
                if (current != null) {
                    current = current.next;
                }
            }

            // Break the link at the end of the current part
            if (prev != null) prev.next = null;

            // Assign the part to the result array
            result[i] = partHead;
        }

        return result;
    }
}
