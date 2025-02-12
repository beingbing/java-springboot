package be.springboot.pp.designpattern.behavioral.command.texteditor;

public class TextEditor {
    private final StringBuilder text = new StringBuilder();

    public void appendText(String newText) {
        text.append(newText);
        System.out.println("📄 Current Text: " + text);
    }

    public void deleteText(int length) {
        if (length > text.length()) {
            text.setLength(0);
        } else {
            text.setLength(text.length() - length);
        }
        System.out.println("📄 Current Text: " + text);
    }

    public String getText() {
        return text.toString();
    }
}
