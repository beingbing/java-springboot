package be.springboot.pp.inmemorymysql.command.impl;

import be.springboot.pp.inmemorymysql.Database;
import be.springboot.pp.inmemorymysql.command.SqlCommand;
import be.springboot.pp.inmemorymysql.filter.Filter;
import be.springboot.pp.inmemorymysql.row.Row;
import be.springboot.pp.inmemorymysql.table.Table;

public class DeleteRowsCommand implements SqlCommand {
    private final String tableName;
    private final Filter filter;

    public DeleteRowsCommand(String tableName, Filter filter) {
        this.tableName = tableName;
        this.filter = filter;
    }

    @Override
    public void execute() {
        Table table = Database.getInstance().getTable(tableName);
        Table filteredTable = filter.filter(table);
        for (Row row : filteredTable.getRows()) {
            table.deleteRow(row);
        }
        System.out.println("Successfully deleted Rows: " + filteredTable);
    }
}
