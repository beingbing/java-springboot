package be.springboot.pp.designpattern.behavioral.command.texteditor;

import java.util.Stack;

public class CommandManager {
    private final Stack<Command> commandHistory = new Stack<>();
    private final Stack<Command> redoStack = new Stack<>();

    public void executeCommand(Command command) {
        command.execute();
        commandHistory.push(command);
        redoStack.clear(); // Clear redo history after a new command
    }

    public void undo() {
        if (!commandHistory.isEmpty()) {
            Command command = commandHistory.pop();
            command.undo();
            redoStack.push(command);
        } else {
            System.out.println("❌ No actions to undo.");
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.execute();
            commandHistory.push(command);
        } else {
            System.out.println("❌ No actions to redo.");
        }
    }
}
