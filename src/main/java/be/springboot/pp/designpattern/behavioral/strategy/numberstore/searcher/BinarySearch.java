package be.springboot.pp.designpattern.behavioral.strategy.numberstore.searcher;

import java.util.List;
import java.util.Objects;

public class BinarySearch implements Searcher {
    @Override
    public Integer search(Integer key, List<Integer> list) {
        int l = 0, h = list.size() - 1;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (Objects.equals(list.get(m), key)) return m;
            else if (list.get(m) < key) l = m+1;
            else h = m-1;
        }
        return -1;
    }
}
