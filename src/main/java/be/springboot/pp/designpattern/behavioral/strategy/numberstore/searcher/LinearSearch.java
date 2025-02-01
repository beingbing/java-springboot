package be.springboot.pp.designpattern.behavioral.strategy.numberstore.searcher;

import java.util.List;
import java.util.Objects;

public class LinearSearch implements Searcher {
    @Override
    public Integer search(Integer key, List<Integer> list) {
        for (int i = 0; i < list.size(); i++) if (Objects.equals(list.get(i), key)) return i;
        return -1;
    }
}
