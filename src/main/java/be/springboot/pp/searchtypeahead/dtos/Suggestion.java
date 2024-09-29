package be.springboot.pp.searchtypeahead.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Suggestion {
    private final int frequency;
    private String suggestion;

    public Suggestion(String suggestionText, int frequency) {
        this.suggestion = suggestionText;
        this.frequency = frequency;
    }
}
