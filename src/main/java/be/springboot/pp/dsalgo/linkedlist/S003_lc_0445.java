package be.springboot.pp.dsalgo.linkedlist;

import java.util.Stack;

public class S003_lc_0445 {
    public Node addTwoNumbers(Node l1, Node l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        // Push all values from l1 into stack1
        while (l1 != null) {
            stack1.push(l1.data);
            l1 = l1.next;
        }

        // Push all values from l2 into stack2
        while (l2 != null) {
            stack2.push(l2.data);
            l2 = l2.next;
        }

        Node head = null;
        int carry = 0;

        // Process both stacks until both are empty
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            int x = stack1.isEmpty() ? 0 : stack1.pop();
            int y = stack2.isEmpty() ? 0 : stack2.pop();

            int sum = x + y + carry;
            carry = sum / 10;

            // Prepend the new node to the result list
            Node newNode = new Node(sum % 10);
            newNode.next = head;
            head = newNode;
        }

        return head;
    }
}
