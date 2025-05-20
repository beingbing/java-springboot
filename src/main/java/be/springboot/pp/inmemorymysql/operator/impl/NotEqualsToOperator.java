package be.springboot.pp.inmemorymysql.operator.impl;

import be.springboot.pp.inmemorymysql.operator.Operator;

public class NotEqualsToOperator implements Operator {
    @Override
    public Boolean apply(String curVal, String expectedVal) {
        return !curVal.equals(expectedVal);
    }
}
