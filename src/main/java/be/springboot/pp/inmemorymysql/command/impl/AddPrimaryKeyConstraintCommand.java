package be.springboot.pp.inmemorymysql.command.impl;

import be.springboot.pp.inmemorymysql.Database;
import be.springboot.pp.inmemorymysql.column.Column;
import be.springboot.pp.inmemorymysql.command.SqlCommand;
import be.springboot.pp.inmemorymysql.constraint.Constraint;
import be.springboot.pp.inmemorymysql.constraint.impl.PrimaryKeyConstraint;
import be.springboot.pp.inmemorymysql.enums.ConstraintType;
import be.springboot.pp.inmemorymysql.table.Table;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddPrimaryKeyConstraintCommand implements SqlCommand {
    private final String tableName;
    private final Set<String> columnNames;

    public AddPrimaryKeyConstraintCommand(String tableName, Set<String> columnNames) {
        this.tableName = tableName;
        this.columnNames = columnNames;
    }

    @Override
    public void execute() {
        Table table = Database.getInstance().getTable(tableName);
        Set<Column> columns = new HashSet<>();
        for (String columnName : columnNames)
            columns.add(table.getColumn(columnName));
        List<Constraint> constraints = table.getConstraintsByType(ConstraintType.PRIMARY_KEY);
        if (!constraints.isEmpty()) throw new IllegalArgumentException("Table already has a primary key.");
        // TODO: iterate over rows and verify uniqueness for current PK
        table.addConstraint(new PrimaryKeyConstraint(table, columns));
        System.out.println("Successfully added primary key constraint.");
    }
}
