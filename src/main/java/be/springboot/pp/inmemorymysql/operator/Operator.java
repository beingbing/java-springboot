package be.springboot.pp.inmemorymysql.operator;

public interface Operator {
    Boolean apply(String curVal, String expectedVal);
}
