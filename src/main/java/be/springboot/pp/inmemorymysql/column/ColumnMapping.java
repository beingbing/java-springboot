package be.springboot.pp.inmemorymysql.column;

public record ColumnMapping(Column foreignTableColumn, Column currentTableColumn) {}
