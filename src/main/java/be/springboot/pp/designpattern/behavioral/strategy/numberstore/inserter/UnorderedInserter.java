package be.springboot.pp.designpattern.behavioral.strategy.numberstore.inserter;

import java.util.List;

public class UnorderedInserter implements Inserter {
    @Override
    public void insert(Integer num, List<Integer> list) {
        list.add(num);
    }
}
