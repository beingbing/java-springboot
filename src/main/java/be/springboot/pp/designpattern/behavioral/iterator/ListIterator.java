package be.springboot.pp.designpattern.behavioral.iterator;

import java.util.List;

public class ListIterator implements Iterator {
    private final List<Integer> list;
    private Integer current;
    private final Integer size;

    public ListIterator(List<Integer> list) {
        this.list = list;
        this.current = 0;
        this.size = list.size();
    }

    @Override
    public boolean hasNext() {
        return current < size;
    }

    @Override
    public int next() {
        int x = list.get(current);
        current++;
        return x;
    }
}
