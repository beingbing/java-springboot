package be.springboot.pp.designpattern.behavioral.strategy.numberstore.inserter;

import java.util.Collections;
import java.util.List;

public class OrderedInserter implements Inserter {
    @Override
    public void insert(Integer num, List<Integer> list) { // insertion sort
        list.add(num);
        int k = list.size() - 1;
        while (k > 0 && list.get(k) < list.get(k-1)) {
            Collections.swap(list, k-1, k);
            k--;
        }
    }
}
