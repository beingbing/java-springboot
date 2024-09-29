package be.springboot.pp.searchtypeahead.managers;

import be.springboot.pp.searchtypeahead.constants.SuggestionConstants;
import be.springboot.pp.searchtypeahead.dtos.Suggestion;
import be.springboot.pp.searchtypeahead.entities.QueryFrequency;
import be.springboot.pp.searchtypeahead.interfaces.SuggestionCache;
import be.springboot.pp.searchtypeahead.repositories.QueryFrequencyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class SuggestionManager {

    private final SuggestionCache suggestionCache;

    private final QueryFrequencyRepository queryFrequencyRepository;

    @Autowired
    public SuggestionManager(SuggestionCache suggestionCache,
                             QueryFrequencyRepository queryFrequencyRepository) {
        this.suggestionCache = suggestionCache;
        this.queryFrequencyRepository = queryFrequencyRepository;
    }

    public List<Suggestion> getTopSuggestion(String query) {
        log.info("Fetching top suggestions for query: {}", query);
        if (query.length() > SuggestionConstants.MAX_ALLOWED_SUGGESTIONS)
            throw new RuntimeException("Query length should not exceed " + SuggestionConstants.MAX_ALLOWED_SUGGESTIONS);

        List<Suggestion> suggestions = suggestionCache.getCachedSuggestions(query);
        log.info("Returning {} suggestions for query: {}", suggestions.size(), query);
        return suggestions;
    }

    public void updateQueryFrequency(String query) {
        log.info("Updating query frequency for query: {}", query);
        QueryFrequency queryFrequency = queryFrequencyRepository.findByQuery(query);
        if (queryFrequency == null) {
            queryFrequency = new QueryFrequency(query, 1L);
        } else {
            queryFrequency.setFrequency(queryFrequency.getFrequency() + 1);
        }
        queryFrequencyRepository.save(queryFrequency);
    }
}
