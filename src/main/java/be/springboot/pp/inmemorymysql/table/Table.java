package be.springboot.pp.inmemorymysql.table;

import be.springboot.pp.inmemorymysql.row.Row;
import be.springboot.pp.inmemorymysql.constraint.Constraint;
import be.springboot.pp.inmemorymysql.column.Column;
import be.springboot.pp.inmemorymysql.enums.ConstraintType;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class Table {
    private final String name;
    private final Set<Column> columns;
    private final List<Row> rows;
    private final List<Constraint> constraints;

    public Table(String name, Set<Column> columns) {
        this.name = name;
        this.columns = columns;
        this.rows = new ArrayList<>();
        this.constraints = new ArrayList<>();
    }

    public void addColumn(Column column) {
        if (columns.contains(column)) throw new IllegalArgumentException("Column already exists.");
        columns.add(column);
        for (Row row : rows) {
            row.put(column, null);
        }
    }

    public void removeColumn(Column column) {
        columns.remove(column);
        for (Row row : rows) {
            row.remove(column);
        }
    }

    public Column getColumn(String colName) {
        for (Column column : columns) {
            if (column.name().equals(colName)) return column;
        }
        throw new IllegalArgumentException("Column does not exist.");
    }

    public void insertRow(Row newRow) {
        for (Constraint constraint : constraints) constraint.applyOnInsertRow(newRow);
        rows.add(newRow);
    }

    public void deleteRow(Row row) {
        if (!this.rows.contains(row)) throw new IllegalArgumentException("Row does not exist.");
        for (Constraint constraint : constraints) constraint.applyOnDeleteRow(row);
        rows.remove(row);
    }

    public void addConstraint(Constraint constraint) {
        constraints.add(constraint);
    }

    public Set<Column> getColumns() {
        return new HashSet<>(columns);
    }

    public List<Constraint> getConstraintsByType(ConstraintType type) {
        List<Constraint> result = new ArrayList<>();
        for (Constraint constraint : constraints) {
            if (constraint.getType() == type) {
                result.add(constraint);
            }
        }
        return result;
    }

    public void removeConstraint(ConstraintType type, Table relatedTable) {
        constraints.removeIf(constraint -> constraint.getType() == type && constraint.isRelated(relatedTable));
    }
}
