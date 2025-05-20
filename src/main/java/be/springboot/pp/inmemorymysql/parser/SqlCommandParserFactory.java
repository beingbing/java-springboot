package be.springboot.pp.inmemorymysql.parser;

import be.springboot.pp.inmemorymysql.parser.impl.CreateTableCommandParser;
import be.springboot.pp.inmemorymysql.parser.impl.SelectCommandParser;

public class SqlCommandParserFactory {

    private SqlCommandParserFactory() {}

    public static SqlCommandParser getParser(String sql) {
        if (sql.startsWith("CREATE TABLE")) {
            return new CreateTableCommandParser();
        } else if (sql.startsWith("SELECT")) {
            return new SelectCommandParser();
        }
        // ....

        return null;
    }
}
