package be.springboot.pp.designpattern.behavioral.command.tagstore.commands;

import be.springboot.pp.designpattern.behavioral.command.tagstore.services.TagService;

import java.util.regex.Pattern;

public class PartialMatchDeleteCommand implements Command {
    private final Pattern pattern;
    private final TagService tagService;

    public PartialMatchDeleteCommand(Pattern pattern, TagService tagService) {
        this.pattern = pattern;
        this.tagService = tagService;
    }

    @Override
    public void execute() {
        this.tagService.delete(pattern);
    }

    @Override
    public void undo() {

    }
}
