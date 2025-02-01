package be.springboot.pp.designpattern.behavioral.strategy.numberstore;

import be.springboot.pp.designpattern.behavioral.strategy.numberstore.inserter.Inserter;
import be.springboot.pp.designpattern.behavioral.strategy.numberstore.searcher.Searcher;
import be.springboot.pp.designpattern.behavioral.strategy.numberstore.sorter.Sorter;

import java.util.ArrayList;
import java.util.List;

public class NumberStore {
    private final List<Integer> nums;
    private final Inserter inserter;
    private final Searcher searcher;
    private final Sorter sorter;

    // accepting different insertion and search strategy.
    public NumberStore(Inserter inserter, Searcher searcher, Sorter sorter) {
        this.inserter = inserter;
        this.searcher = searcher;
        this.sorter = sorter;
        this.nums = new ArrayList<>();
    }

    public void insert(Integer num) {
        this.inserter.insert(num, nums);
    }

    public Integer search(Integer key) {
        return this.searcher.search(key, nums);
    }

    public void sort() {
        this.sorter.sort(nums);
    }
}
