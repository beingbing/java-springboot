package be.springboot.pp.inmemorymysql.row;

import be.springboot.pp.inmemorymysql.column.Column;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Row {
    private final Map<Column, String> data;

    public Row(Set<Column> newRow) {
        this.data = new HashMap<>();
        for (Column col : newRow) {
            this.data.put(col, null);
        }
    }

    public void put(Column col, String value) {
        if (!this.data.containsKey(col)) throw new IllegalArgumentException("Column does not exist.");
        this.data.put(col, value);
    }

    public String get(Column col) {
        if (!this.data.containsKey(col)) throw new IllegalArgumentException("Column does not exist.");
        return this.data.get(col);
    }

    public void remove(Column col) {
        if (!this.data.containsKey(col)) throw new IllegalArgumentException("Column does not exist.");
        this.data.remove(col);
    }

    public void print() {
        for (Map.Entry<Column, String> e : this.data.entrySet())
            System.out.println(e.getKey().name() + ": " + e.getValue());
    }
}
