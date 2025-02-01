package be.springboot.pp.designpattern.behavioral.strategy.numberstore;

import be.springboot.pp.designpattern.behavioral.strategy.numberstore.inserter.UnorderedInserter;
import be.springboot.pp.designpattern.behavioral.strategy.numberstore.searcher.BinarySearch;
import be.springboot.pp.designpattern.behavioral.strategy.numberstore.sorter.BubbleSort;

public class Tester {

    public static void main(String[] args) {
        NumberStore store = new NumberStore(new UnorderedInserter(), new BinarySearch(), new BubbleSort());
        store.insert(4);
        store.insert(3);
        store.insert(9);
        store.insert(0);
        store.sort();
        System.out.println(store.search(9));
    }
}
