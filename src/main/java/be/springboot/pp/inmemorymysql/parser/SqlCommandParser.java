package be.springboot.pp.inmemorymysql.parser;

import be.springboot.pp.inmemorymysql.command.SqlCommand;

public interface SqlCommandParser {
    SqlCommand parse(String sql);
}
