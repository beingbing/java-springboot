package be.springboot.pp.searchtypeahead.controllers;

import be.springboot.pp.searchtypeahead.dtos.Suggestion;
import be.springboot.pp.searchtypeahead.managers.SuggestionManager;
import be.springboot.pp.searchtypeahead.utils.SuggestionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/search")
public class SuggestionController {

    @Autowired
    private SuggestionManager suggestionManager;

    @GetMapping(value = "/suggestion")
    public ResponseEntity<List<Suggestion>> getSuggestion(@RequestParam("query") final String query) {
        log.info("SuggestionController: getSuggestion: query: {}", query);
        if (query == null || query.isEmpty() || !SuggestionUtils.isAllLowerCaseString(query)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        }

        List<Suggestion> suggestions = suggestionManager.getTopSuggestion(query);
        log.info("SuggestionController: getSuggestion: suggestions: {}", suggestions);
        return ResponseEntity.ok(suggestions);
    }

    @PostMapping(value = "/query")
    public ResponseEntity updateQueryFrequency(@RequestParam("query") final String query) {
        log.info("SuggestionController: updateQueryFrequency: query: {}", query);
        if (query == null || query.isEmpty() || !SuggestionUtils.isAllLowerCaseString(query)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        }

        suggestionManager.updateQueryFrequency(query);
        return ResponseEntity.ok().build();
    }
}
