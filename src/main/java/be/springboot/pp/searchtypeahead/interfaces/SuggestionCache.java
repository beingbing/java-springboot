package be.springboot.pp.searchtypeahead.interfaces;

import be.springboot.pp.searchtypeahead.dtos.Suggestion;

import java.util.List;

public interface SuggestionCache {

    List<Suggestion> getCachedSuggestions(String query);
}
