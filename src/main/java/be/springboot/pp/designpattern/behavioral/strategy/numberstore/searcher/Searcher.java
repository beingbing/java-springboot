package be.springboot.pp.designpattern.behavioral.strategy.numberstore.searcher;

import java.util.List;

public interface Searcher {
    Integer search(Integer key, List<Integer> list);
}
