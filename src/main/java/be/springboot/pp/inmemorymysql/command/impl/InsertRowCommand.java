package be.springboot.pp.inmemorymysql.command.impl;

import be.springboot.pp.inmemorymysql.Database;
import be.springboot.pp.inmemorymysql.command.SqlCommand;
import be.springboot.pp.inmemorymysql.row.KeyValuePair;
import be.springboot.pp.inmemorymysql.row.Row;
import be.springboot.pp.inmemorymysql.table.Table;

import java.util.List;

public class InsertRowCommand implements SqlCommand {
    private final String tableName;
    private final List<KeyValuePair> keyValuePairs;

    public InsertRowCommand(String tableName, List<KeyValuePair> keyValuePairs) {
        this.tableName = tableName;
        this.keyValuePairs = keyValuePairs;
    }

    @Override
    public void execute() {
        Table table = Database.getInstance().getTable(tableName);
        Row row = new Row(table.getColumns());
        for (KeyValuePair keyValuePair : keyValuePairs) {
            row.put(table.getColumn(keyValuePair.key()), keyValuePair.value());
        }
        table.insertRow(row);
        System.out.println("Successfully inserted Row: " + row);
    }
}
