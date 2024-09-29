package be.springboot.pp.searchtypeahead.interfaces;

import be.springboot.pp.searchtypeahead.dtos.Suggestion;

import java.util.List;

public interface SuggestionDataStructure {

    void init(int maxSuggestions);

    List<Suggestion> getTopSuggestions(String query);

    void reload(); // for replacing existing RAM trie with new trie constructed from DB
}
