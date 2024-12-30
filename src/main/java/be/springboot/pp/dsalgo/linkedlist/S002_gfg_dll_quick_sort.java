package be.springboot.pp.dsalgo.linkedlist;

public class S002_gfg_dll_quick_sort {
    Node head;

    Node quickSort(Node head) {
        Node tail = getLastNode(head);
        quickSortImpl(head, tail);
        return head;
    }

    Node getLastNode(Node node) {
        while (node != null && node.next != null)
            node = node.next;
        return node;
    }

    void quickSortImpl(Node low, Node high) {
        if (low != null && high != null && low != high && low != high.next) {
            Node pivot = partition(low, high);
            quickSortImpl(low, pivot.prev);  // Sort the left part
            quickSortImpl(pivot.next, high); // Sort the right part
        }
    }

    // Partition function for QuickSort
    Node partition(Node low, Node high) {
        int pivotValue = high.data;  // Choose the last node as the pivot
        Node i = low.prev;           // i starts before the low node

        for (Node j = low; j != high; j = j.next) {
            if (j.data < pivotValue) {
                i = (i == null) ? low : i.next;  // Move i forward
                // Swap i.data and j.data
                int temp = i.data;
                i.data = j.data;
                j.data = temp;
            }
        }
        // Move the pivot to its correct position
        i = (i == null) ? low : i.next;
        int temp = i.data;
        i.data = high.data;
        high.data = temp;

        return i;  // Return the pivot node
    }
}
