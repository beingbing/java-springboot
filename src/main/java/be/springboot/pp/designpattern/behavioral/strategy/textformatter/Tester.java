package be.springboot.pp.designpattern.behavioral.strategy.textformatter;

import be.springboot.pp.designpattern.behavioral.strategy.textformatter.formats.LowerCaseFormatter;
import be.springboot.pp.designpattern.behavioral.strategy.textformatter.formats.TitleCaseFormatter;
import be.springboot.pp.designpattern.behavioral.strategy.textformatter.formats.UpperCaseFormatter;

public class Tester {

    public static void main(String[] args) {
        TextEditor editor = new TextEditor();

        String sampleText = "hello world! welcome to the strategy pattern.";

        // Apply Uppercase Formatting
        editor.setFormatter(new UpperCaseFormatter());
        editor.displayFormattedText(sampleText);

        // Apply Lowercase Formatting
        editor.setFormatter(new LowerCaseFormatter());
        editor.displayFormattedText(sampleText);

        // Apply Title Case Formatting
        editor.setFormatter(new TitleCaseFormatter());
        editor.displayFormattedText(sampleText);
    }
}
