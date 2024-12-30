package be.springboot.pp.dsalgo.binarytrees;

import java.util.LinkedList;
import java.util.Queue;

public class S001_gfg_bt_from_ll {
    public Node linkedListToBinaryTree(ListNode head) {
        if (head == null) return null;

        Node root = new Node(head.val);
        head = head.next;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (head != null) {
            Node current = queue.poll();

            Node leftChild = new Node(head.val);
            current.left = leftChild;
            queue.offer(leftChild);
            head = head.next;

            if (head != null) {
                Node rightChild = new Node(head.val);
                current.right = rightChild;
                queue.offer(rightChild);
                head = head.next;
            }
        }

        return root;
    }
}
