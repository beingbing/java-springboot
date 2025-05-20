package be.springboot.pp.inmemorymysql.command.impl;

import be.springboot.pp.inmemorymysql.Database;
import be.springboot.pp.inmemorymysql.command.SqlCommand;
import be.springboot.pp.inmemorymysql.enums.ConstraintType;
import be.springboot.pp.inmemorymysql.table.Table;

public class RemoveForeignKeyConstraintCommand implements SqlCommand {
    private final String parentTable;
    private final String childTable;

    public RemoveForeignKeyConstraintCommand(String parentTable, String childTable) {
        this.parentTable = parentTable;
        this.childTable = childTable;
    }

    @Override
    public void execute() {
        Database db = Database.getInstance();
        Table parent = db.getTable(parentTable);
        Table child = db.getTable(childTable);
        parent.removeConstraint(ConstraintType.CHILD_FOREIGN_KEY, child);
        child.removeConstraint(ConstraintType.PARENT_FOREIGN_KEY, parent);
        System.out.println("Successfully removed foreign key constraint.");
    }
}
