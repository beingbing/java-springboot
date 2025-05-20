package be.springboot.pp.inmemorymysql.command.impl;

import be.springboot.pp.inmemorymysql.Database;
import be.springboot.pp.inmemorymysql.table.Table;
import be.springboot.pp.inmemorymysql.command.SqlCommand;
import be.springboot.pp.inmemorymysql.enums.ConstraintType;

public class DropTableCommand implements SqlCommand {
    private final String tableName;

    public DropTableCommand(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public void execute() {
        Database db = Database.getInstance();
        Table tableToBeDeleted = db.getTable(tableName);
        if (!tableToBeDeleted.getConstraintsByType(ConstraintType.CHILD_FOREIGN_KEY).isEmpty())
            throw new IllegalArgumentException("Cannot drop table with child foreign key constraints.");

        for (Table table : db.getTables()) {
            table.removeConstraint(ConstraintType.CHILD_FOREIGN_KEY, tableToBeDeleted);
        }

        db.dropTable(tableName);
        System.out.println("Successfully dropped Table: " + tableName);
    }
}
