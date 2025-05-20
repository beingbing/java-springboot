package be.springboot.pp.inmemorymysql.command.impl;

import be.springboot.pp.inmemorymysql.Database;
import be.springboot.pp.inmemorymysql.column.Column;
import be.springboot.pp.inmemorymysql.command.SqlCommand;

import java.util.Set;

public class CreateTableCommand implements SqlCommand {
    private final String tableName;
    private final Set<Column> columns;

    public CreateTableCommand(String tableName, Set<Column> columns) {
        this.tableName = tableName;
        this.columns = columns;
    }

    @Override
    public void execute() {
        Database.getInstance().createTable(tableName, columns);
        System.out.println("Successfully created Table: " + tableName);
    }
}
