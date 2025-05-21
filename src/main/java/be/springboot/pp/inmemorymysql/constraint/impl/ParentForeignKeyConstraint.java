package be.springboot.pp.inmemorymysql.constraint.impl;

import be.springboot.pp.inmemorymysql.row.Row;
import be.springboot.pp.inmemorymysql.table.Table;
import be.springboot.pp.inmemorymysql.constraint.Constraint;
import be.springboot.pp.inmemorymysql.column.ColumnMapping;
import be.springboot.pp.inmemorymysql.enums.ConstraintType;

import java.util.List;

public class ParentForeignKeyConstraint implements Constraint {
    private final Table parentTable;
    private final List<ColumnMapping> columnMappings;

    public ParentForeignKeyConstraint(Table parentTable, List<ColumnMapping> columnMappings) {
        this.parentTable = parentTable;
        this.columnMappings = columnMappings;
    }

    @Override
    public void applyOnInsertRow(Row rowToBeInserted) {
        for (Row row : parentTable.getRows()) { // iterate over each row
            boolean allMatch = true;
            for (ColumnMapping mapping : columnMappings) { // check presence of row in parent for FK column
                String parentValue = row.get(mapping.foreignTableColumn());
                String newChildValue = rowToBeInserted.get(mapping.currentTableColumn());
                if (!parentValue.equals(newChildValue)) allMatch = false;
            }
            if (allMatch) return; // match found, constraint satisfied
        }
        throw new IllegalArgumentException("Parent Foreign key constraint violation.");
    }

    @Override
    public void applyOnUpdateRow(Row rowToBeUpdated) {
        // TODO: need action
    }

    @Override
    public void applyOnDeleteRow(Row rowToBeDeleted) {
        // no action needed
    }

    @Override
    public ConstraintType getType() {
        return ConstraintType.PARENT_FOREIGN_KEY;
    }

    @Override
    public boolean isRelated(Table table) {
        return table.getName().equals(parentTable.getName());
    }
}
