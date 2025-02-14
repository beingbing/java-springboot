package be.springboot.pp.elevator.dtos;

import lombok.ToString;

// making class instead of enum because it is possible
// that i want to keep some extra information. Like which
// floor is not operational, etc.
@ToString
public class Floor {
    private final int number;
    private final String name;

    public Floor(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }
}
