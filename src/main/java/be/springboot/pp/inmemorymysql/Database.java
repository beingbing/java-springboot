package be.springboot.pp.inmemorymysql;

import be.springboot.pp.inmemorymysql.column.Column;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Database {
    private final Map<String, Table> tables;
    private static Database INSTANCE;

    private Database() {
        this.tables = new HashMap<>();
    }

    public static Database getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }

    public Table getTable(String name) {
        if (!tables.containsKey(name)) throw new IllegalArgumentException("Table not found.");
        return tables.get(name);
    }

    public void createTable(String name, Set<Column> columns) {
        if (tables.containsKey(name)) throw new IllegalArgumentException("Table already exists.");
        tables.put(name, new Table(name, columns));
    }

    public void dropTable(String name) {
        tables.remove(name);
    }

    public Collection<Table> getTables() {
        return tables.values();
    }
}
