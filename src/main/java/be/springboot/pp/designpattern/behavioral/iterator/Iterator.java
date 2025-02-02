package be.springboot.pp.designpattern.behavioral.iterator;

public interface Iterator {
    boolean hasNext();
    int next(); // returns int because our example collection contains only int values
}
