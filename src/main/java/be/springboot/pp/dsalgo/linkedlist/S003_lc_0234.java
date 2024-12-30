package be.springboot.pp.dsalgo.linkedlist;

public class S003_lc_0234 {
    public boolean isPalindrome(Node head) {
        if (head == null || head.next == null) return true; // Single node or empty list is always a palindrome

        // Step 1: Find the middle of the list
        Node mid = middleNode(head);

        // Step 2: Reverse the second half of the list
        Node secondHalf = reverseList(mid);

        // Step 3: Compare the two halves
        Node firstHalf = head;
//        TreeNode temp = secondHalf; // Temporary pointer to restore the list later
        while (secondHalf != null) {
            if (firstHalf.data != secondHalf.data) {
//                reverseList(temp); // Restore the list before returning
                return false; // Mismatch found
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        // Step 4: Restore the original list structure
//        reverseList(temp);

        return true; // Palindrome
    }

    public Node middleNode(Node head) {
        Node slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;       // Move slow pointer one step
            fast = fast.next.next; // Move fast pointer two steps
        }

        return slow;
    }

    private Node reverseList(Node head) {
        Node prev = null;
        while (head != null) {
            Node next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}
