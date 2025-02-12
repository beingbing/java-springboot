package be.springboot.pp.designpattern.behavioral.command.texteditor;

public class InsertTextCommand implements Command {
    private final TextEditor editor;
    private final String textToInsert;

    public InsertTextCommand(TextEditor editor, String text) {
        this.editor = editor;
        this.textToInsert = text;
    }

    @Override
    public void execute() {
        editor.appendText(textToInsert);
    }

    @Override
    public void undo() {
        editor.deleteText(textToInsert.length());
    }
}
