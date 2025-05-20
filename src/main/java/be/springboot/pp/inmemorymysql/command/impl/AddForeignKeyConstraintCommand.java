package be.springboot.pp.inmemorymysql.command.impl;

import be.springboot.pp.inmemorymysql.Database;
import be.springboot.pp.inmemorymysql.column.ColumnMapping;
import be.springboot.pp.inmemorymysql.column.ColumnNameMapping;
import be.springboot.pp.inmemorymysql.command.SqlCommand;
import be.springboot.pp.inmemorymysql.constraint.impl.ChildForeignKeyConstraint;
import be.springboot.pp.inmemorymysql.constraint.impl.ParentForeignKeyConstraint;
import be.springboot.pp.inmemorymysql.table.Table;

import java.util.ArrayList;
import java.util.List;

public class AddForeignKeyConstraintCommand implements SqlCommand {
    private final String parentTable;
    private final String childTable;
    private final List<ColumnNameMapping> columnNameMappingList;

    public AddForeignKeyConstraintCommand(String parentTable, String childTable, List<ColumnNameMapping> columnNameMappingList) {
        this.parentTable = parentTable;
        this.childTable = childTable;
        this.columnNameMappingList = columnNameMappingList;
    }

    @Override
    public void execute() {
        Database db = Database.getInstance();
        Table parent = db.getTable(parentTable);
        Table child = db.getTable(childTable);
        // TODO: 1. reference PK as FK
        // 2. cyclic FK should not be there
        List<ColumnMapping> parentColumnMappings = new ArrayList<>();
        List<ColumnMapping> childColumnMappings = new ArrayList<>();
        for (ColumnNameMapping columnNameMapping : columnNameMappingList) {
            parentColumnMappings.add(
                    new ColumnMapping(
                            parent.getColumn(columnNameMapping.parentColumnName()),
                            child.getColumn(columnNameMapping.childColumnName())));
            childColumnMappings.add(
                    new ColumnMapping(
                            child.getColumn(columnNameMapping.childColumnName()),
                            parent.getColumn(columnNameMapping.parentColumnName())));
        }
        child.addConstraint(new ParentForeignKeyConstraint(child, childColumnMappings));
        parent.addConstraint(new ChildForeignKeyConstraint(parent, parentColumnMappings));
        System.out.println("Successfully added foreign key constraint.");
    }
}
