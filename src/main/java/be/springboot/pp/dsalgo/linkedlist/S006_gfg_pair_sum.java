package be.springboot.pp.dsalgo.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class S006_gfg_pair_sum {
    static boolean check_pair_sum(Node head, int sum) {
        Set<Integer> set = new HashSet<>();
        Node p = head;
        while (p != null) {
            int cur = p.data;
            if (set.contains(sum - cur)) return true;
            set.add(p.data);
            p = p.next;
        }
        return false;
    }

    public boolean pairSum(Node head, int k) {
        Set<Integer> seen = new HashSet<>();
        Node dmy = head;
        while (dmy != null) {
            int compliment = k - dmy.data;
            if (seen.contains(compliment)) return true;
            seen.add(dmy.data);
            dmy = dmy.next;
        }
        return false;
    }
}
