package be.springboot.pp.dsalgo.linkedlist;
// https://www.geeksforgeeks.org/xor-linked-list-a-memory-efficient-doubly-linked-list-set-2/
import java.util.ArrayList;

class XORNode {
    int data;
    XORNode npx;

    public XORNode(int data) {
        this.data = data;
        this.npx = null;
    }
}

class S003_gfg_ll_xor {
    // Insert at the beginning of the list
    public XORNode insert(XORNode head, int data) {
        XORNode newNode = new XORNode(data);
        newNode.npx = XOR(null, head); // npx of new node is XOR of null and current head
        if (head != null) head.npx = XOR(newNode, head.npx);
        head = newNode;
        return head;
    }

    public ArrayList<Integer> getList(XORNode head) {
        ArrayList<Integer> a = new ArrayList<>();
        XORNode cur = head, prv = null, nxt;
        while (cur != null){
            a.add(cur.data);
            nxt = XOR(prv, cur.npx);
            prv = cur;
            cur = nxt;
        }
        return a;
    }

    private static XORNode XOR(XORNode a, XORNode b) {
        return new XORNode(System.identityHashCode(a) ^ System.identityHashCode(b));
    }
}
