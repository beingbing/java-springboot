package be.springboot.pp.designpattern.behavioral.strategy.textformatter;

import be.springboot.pp.designpattern.behavioral.strategy.textformatter.formats.TextFormatter;

public class TextEditor {
    private TextFormatter formatter;

    // Set the strategy dynamically
    public void setFormatter(TextFormatter formatter) {
        this.formatter = formatter;
    }

    // Apply the chosen strategy
    public void displayFormattedText(String text) {
        if (formatter == null) {
            throw new IllegalStateException("No text formatter is set.");
        }
        System.out.println(formatter.format(text));
    }
}
