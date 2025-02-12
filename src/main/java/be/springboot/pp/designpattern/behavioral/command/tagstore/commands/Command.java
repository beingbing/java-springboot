package be.springboot.pp.designpattern.behavioral.command.tagstore.commands;

public interface Command {
    void execute();
    void undo();
}
