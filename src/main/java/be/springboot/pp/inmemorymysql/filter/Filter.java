package be.springboot.pp.inmemorymysql.filter;

import be.springboot.pp.inmemorymysql.table.Table;

public interface Filter {
    Table filter(Table table);
}
