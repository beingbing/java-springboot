package be.springboot.pp.inmemorymysql.operator.impl;

import be.springboot.pp.inmemorymysql.operator.Operator;

public class LessThanOperator implements Operator {
    @Override
    public Boolean apply(String curVal, String expectedVal) {
        try {
            Double curValDouble = Double.parseDouble(curVal);
            Double expectedValDouble = Double.parseDouble(expectedVal);
            return curValDouble < expectedValDouble;
        } catch (NumberFormatException e) {
            System.out.println("LessThanOperator: NumberFormatException: " + e.getMessage());
        }
        return curVal.compareTo(expectedVal) < 0;
    }
}
