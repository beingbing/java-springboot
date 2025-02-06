package be.springboot.pp.parkinglot.dtos;

import lombok.Getter;

@Getter
public class EntryPoint {
    private final String name;
    private final Boolean isOpen;

    public EntryPoint(String name, Boolean isOpen) {
        this.name = name;
        this.isOpen = isOpen;
    }
}
