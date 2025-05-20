package be.springboot.pp.inmemorymysql.constraint.impl;

import be.springboot.pp.inmemorymysql.row.Row;
import be.springboot.pp.inmemorymysql.table.Table;
import be.springboot.pp.inmemorymysql.constraint.Constraint;
import be.springboot.pp.inmemorymysql.column.ColumnMapping;
import be.springboot.pp.inmemorymysql.enums.ConstraintType;

import java.util.List;

public class ChildForeignKeyConstraint implements Constraint {
    private final Table childTable;
    private final List<ColumnMapping> columnMappings;

    public ChildForeignKeyConstraint(Table childTable, List<ColumnMapping> columnMappings) {
        this.childTable = childTable;
        this.columnMappings = columnMappings;
    }

    @Override
    public void applyOnInsertRow(Row rowToBeInserted) {
        // no action needed
    }

    @Override
    public void applyOnUpdateRow(Row rowToBeUpdated) {
        // no action needed
    }

    @Override
    public void applyOnDeleteRow(Row rowToBeDeleted) {
        for (Row row : childTable.getRows()) {
            boolean allMatch = true;
            for (ColumnMapping mapping : columnMappings) {
                if (!row.get(mapping.foreignTableColumn()).equals(rowToBeDeleted.get(mapping.currentTableColumn())))
                    allMatch = false;
            }
            if (allMatch) throw new IllegalArgumentException("Child Foreign key constraint violation.");
        }
    }

    @Override
    public ConstraintType getType() {
        return ConstraintType.CHILD_FOREIGN_KEY;
    }

    @Override
    public boolean isRelated(Table table) {
        return table.getName().equals(childTable.getName());
    }
}
