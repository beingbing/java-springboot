package be.springboot.pp.designpattern.behavioral.command.tagstore.commands;

import be.springboot.pp.designpattern.behavioral.command.tagstore.services.TagService;

public class PerfectMatchDeleteCommand implements Command {
    private final String name;
    private final TagService tagService;

    public PerfectMatchDeleteCommand(String name, TagService tagService) {
        this.name = name;
        this.tagService = tagService;
    }

    @Override
    public void execute() {
        this.tagService.delete(this.name);
    }

    @Override
    public void undo() {

    }
}
