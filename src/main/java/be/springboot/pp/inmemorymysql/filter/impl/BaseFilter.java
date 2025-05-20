package be.springboot.pp.inmemorymysql.filter.impl;

import be.springboot.pp.inmemorymysql.column.Column;
import be.springboot.pp.inmemorymysql.filter.Filter;
import be.springboot.pp.inmemorymysql.operator.Operator;
import be.springboot.pp.inmemorymysql.row.Row;
import be.springboot.pp.inmemorymysql.table.Table;

public class BaseFilter implements Filter {
    private final Column column;
    private final Operator operator;
    private final String value;

    public BaseFilter(Column column, Operator operator, String value) {
        this.column = column;
        this.operator = operator;
        this.value = value;
    }

    @Override
    public Table filter(Table table) {
        Table filteredTable = new Table("temp", table.getColumns());
        for (Row row : table.getRows()) {
            if (operator.apply(row.get(column), value)) {
                filteredTable.insertRow(row);
            }
        }
        return filteredTable;
    }
}
