package be.springboot.pp.designpattern.behavioral.strategy.textformatter.formats;

public class LowerCaseFormatter implements TextFormatter {
    @Override
    public String format(String text) {
        return text.toLowerCase();
    }
}
