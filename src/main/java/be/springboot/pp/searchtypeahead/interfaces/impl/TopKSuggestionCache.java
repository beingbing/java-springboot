package be.springboot.pp.searchtypeahead.interfaces.impl;

import be.springboot.pp.searchtypeahead.dtos.Suggestion;
import be.springboot.pp.searchtypeahead.interfaces.SuggestionCache;
import be.springboot.pp.searchtypeahead.interfaces.SuggestionDataStructure;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopKSuggestionCache implements SuggestionCache {

    private final SuggestionDataStructure suggestionDataStructure;

    public TopKSuggestionCache(SuggestionDataStructure suggestionDataStructure) {
        this.suggestionDataStructure = suggestionDataStructure;
    }

    @Override
    public List<Suggestion> getCachedSuggestions(String query) {
        return this.suggestionDataStructure.getTopSuggestions(query);
    }
}
