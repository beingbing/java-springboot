package be.springboot.pp.designpattern.behavioral.command.tagstore.commands;

import be.springboot.pp.designpattern.behavioral.command.tagstore.services.TagService;

public class InsertTagCommand implements Command {
    private final String name;
    private final TagService tagService;

    public InsertTagCommand(String name, TagService tagService) {
        this.name = name;
        this.tagService = tagService;
    }

    @Override
    public void execute() {
        this.tagService.insert(name);
    }

    @Override
    public void undo() {

    }
}
