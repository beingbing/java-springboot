package be.springboot.pp.searchtypeahead.internal;

import be.springboot.pp.searchtypeahead.dtos.Suggestion;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class TrieNode {
    private boolean isEOW; // is the end of search query (which was done by other users) reached?
    private final List<TrieNode> pointers;
    private List<Suggestion> topSuggestions;

    public TrieNode() {
        this.isEOW = false;
        pointers = new ArrayList<>(Collections.nCopies(26, null));
        topSuggestions = new ArrayList<>();
    }
}
