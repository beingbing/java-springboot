package be.springboot.pp.designpattern.behavioral.command.texteditor;

public class DeleteTextCommand implements Command {
    private final TextEditor editor;
    private String deletedText = "";

    public DeleteTextCommand(TextEditor editor, int length) {
        this.editor = editor;
        if (length > editor.getText().length()) {
            this.deletedText = editor.getText();
        } else {
            this.deletedText = editor.getText().substring(editor.getText().length() - length);
        }
    }

    @Override
    public void execute() {
        editor.deleteText(deletedText.length());
    }

    @Override
    public void undo() {
        editor.appendText(deletedText);
    }
}
