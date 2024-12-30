package be.springboot.pp.dsalgo.linkedlist;

public class S003_lc_0023 {
    public Node mergeKLists(Node[] lists) {
        if (lists == null || lists.length == 0) return null; // Edge case: empty input
        return mergeLists(lists, 0, lists.length - 1);
    }

    private Node mergeLists(Node[] lists, int start, int end) {
        if (start == end) return lists[start]; // Base case: single list

        int mid = start + (end - start) / 2;
        Node left = mergeLists(lists, start, mid);
        Node right = mergeLists(lists, mid + 1, end);
        return mergeTwoLists(left, right);
    }

    private Node mergeTwoLists(Node list1, Node list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        if (list1.data <= list2.data) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}
