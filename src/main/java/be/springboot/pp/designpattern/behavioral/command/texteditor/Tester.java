package be.springboot.pp.designpattern.behavioral.command.texteditor;

public class Tester {

    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        CommandManager commandManager = new CommandManager();

        // User types "Hello"
        Command insertHello = new InsertTextCommand(editor, "Hello "); // Command #1 - execution top
        commandManager.executeCommand(insertHello);                         // "Hello "

        // User types "World"
        Command insertWorld = new InsertTextCommand(editor, "World");  // Command #2 - execution top
        commandManager.executeCommand(insertWorld);                         // "Hello World"

        // User deletes last 5 characters ("World")
        Command deleteText = new DeleteTextCommand(editor, 5);       // Command #3 - execution top
        commandManager.executeCommand(deleteText);                          // "Hello "

        // Undo last operation (Restore "World")                            // Command #2 - execution top, Command #3 redo top
        commandManager.undo();                                              // "Hello World"

        // Undo previous operation (Remove "World" again)                   // Command #1 - execution top, Command #2 redo top
        commandManager.undo();                                              // "Hello "

        // Redo last undone operation (Restore "World")                     // Command #2 - execution top, Command #3 redo top
        commandManager.redo();                                              // "Hello World"

        // Redo another operation (Remove "Hello World")                    // Command #3 - execution top, empty redo top
        commandManager.redo();                                              // "Hello "
    }
}
