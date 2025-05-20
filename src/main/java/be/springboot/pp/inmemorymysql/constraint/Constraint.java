package be.springboot.pp.inmemorymysql.constraint;

import be.springboot.pp.inmemorymysql.row.Row;
import be.springboot.pp.inmemorymysql.table.Table;
import be.springboot.pp.inmemorymysql.enums.ConstraintType;

public interface Constraint {
    void applyOnInsertRow(Row rowToBeInserted);
    void applyOnUpdateRow(Row rowToBeUpdated);
    void applyOnDeleteRow(Row rowToBeDeleted);
    ConstraintType getType();
    boolean isRelated(Table table);
}
