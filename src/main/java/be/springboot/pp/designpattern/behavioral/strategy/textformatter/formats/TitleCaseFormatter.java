package be.springboot.pp.designpattern.behavioral.strategy.textformatter.formats;

public class TitleCaseFormatter implements TextFormatter {
    @Override
    public String format(String text) {
        String[] words = text.split("\\s");
        StringBuilder titleCase = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                titleCase.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return titleCase.toString().trim();
    }
}
