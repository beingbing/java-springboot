package be.springboot.pp.inmemorymysql.constraint.impl;

import be.springboot.pp.inmemorymysql.column.Column;
import be.springboot.pp.inmemorymysql.row.Row;
import be.springboot.pp.inmemorymysql.table.Table;
import be.springboot.pp.inmemorymysql.constraint.Constraint;
import be.springboot.pp.inmemorymysql.enums.ConstraintType;
import org.springframework.util.ObjectUtils;

import java.util.Set;

public class PrimaryKeyConstraint implements Constraint {
    private final Table table;
    private final Set<Column> primaryKeys;

    public PrimaryKeyConstraint(Table table, Set<Column> primaryKeys) {
        this.table = table;
        this.primaryKeys = primaryKeys;
    }

    @Override
    public void applyOnInsertRow(Row rowToBeInserted) {
        for (Row row : table.getRows()) {
            boolean allMatch = true;
            for (Column primaryKey : primaryKeys) {
                if (!ObjectUtils.isEmpty(rowToBeInserted.get(primaryKey))
                        && !row.get(primaryKey).equals(rowToBeInserted.get(primaryKey)))
                    allMatch = false;
            }
            if (allMatch) throw new IllegalArgumentException("Duplicate primary key.");
        }
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
        return ConstraintType.PRIMARY_KEY;
    }

    @Override
    public boolean isRelated(Table table) {
        return false;
    }
}
