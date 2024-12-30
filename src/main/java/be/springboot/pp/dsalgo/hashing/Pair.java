package be.springboot.pp.dsalgo.hashing;

import java.util.Comparator;

public class Pair<F extends Comparable<F>, S extends Comparable<S>> {
    private final F first;
    private final S second;

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    public static <F extends Comparable<F>, S extends Comparable<S>> Comparator<Pair<F, S>> ascendingFirstThenSecondComparator() {
        return (p1, p2) -> {
            int cmp = p1.first.compareTo(p2.first);
            if (cmp == 0) {
                return p1.second.compareTo(p2.second);
            }
            return cmp;
        };
    }

    public static <F extends Comparable<F>, S extends Comparable<S>> Comparator<Pair<F, S>> ascendingSecondThenFirstComparator() {
        return (p1, p2) -> {
            int cmp = p1.second.compareTo(p2.second);
            if (cmp == 0) {
                return p1.first.compareTo(p2.first);
            }
            return cmp;
        };
    }
}