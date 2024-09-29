package be.springboot.pp.searchtypeahead.interfaces.impl;

import be.springboot.pp.searchtypeahead.constants.SuggestionConstants;
import be.springboot.pp.searchtypeahead.dtos.Suggestion;
import be.springboot.pp.searchtypeahead.entities.QueryFrequency;
import be.springboot.pp.searchtypeahead.interfaces.SuggestionDataStructure;
import be.springboot.pp.searchtypeahead.repositories.QueryFrequencyRepository;
import be.springboot.pp.searchtypeahead.internal.TrieNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TopKSuggestionsTrie implements SuggestionDataStructure {

    private TrieNode root;
    private int threshold;
    private final QueryFrequencyRepository queryFrequencyRepository;

    @Autowired
    public TopKSuggestionsTrie(QueryFrequencyRepository queryFrequencyRepository, Optional<Integer> maxSug) {
        this.queryFrequencyRepository = queryFrequencyRepository;
        this.root = new TrieNode();
        init(maxSug.orElseGet(() -> threshold));
    }

    @Override
    public void init(int maxSuggestions) {
        this.threshold = Math.max(threshold, maxSuggestions);
        constructTrie(root);
    }

    @Override
    public List<Suggestion> getTopSuggestions(String query) {
        TrieNode cur = root;
        int verticalTraveller = 0;
        while (true) {
            cur = cur.getPointers().get(query.charAt(verticalTraveller) - 'a');
            if (cur == null) return new ArrayList<>();
            verticalTraveller++;
            if (verticalTraveller == query.length()) return cur.getTopSuggestions();
        }
    }

    @Override
    public void reload() {
        TrieNode temp = new TrieNode();
        constructTrie(temp);
        root = temp;
    }

    public List<Suggestion> insert(String query, long frequency, int depth, TrieNode cur) {
        if (depth == query.length() || depth == SuggestionConstants.MAX_QUERY_SIZE) { // termination condition
            Set<Suggestion> suggestions = new HashSet<>(cur.getTopSuggestions());
            suggestions.add(new Suggestion(query, (int) frequency));
            return updateSuggestionAndGet(suggestions, cur);
        }
        if (cur.getPointers().get(query.charAt(depth) - 'a') == null) {
            cur.getPointers().set(query.charAt(depth) - 'a', new TrieNode());
        }
        TrieNode next = cur.getPointers().get(query.charAt(depth) - 'a');
        Set<Suggestion> allSuggestions = new HashSet<>();
        if (depth == query.length() - 1)
            next.setEOW(true);
        insert(query, frequency, depth + 1, next);
        allSuggestions.addAll(cur.getTopSuggestions());
        for (int i = 0; i < 26; i++) {
            TrieNode node = cur.getPointers().get(i);
            if (node != null)
                allSuggestions.addAll(node.getTopSuggestions());
        }
        return updateSuggestionAndGet(allSuggestions, cur);
    }

    private List<Suggestion> updateSuggestionAndGet(Set<Suggestion> sortedSuggestions, TrieNode cur) {
        List<Suggestion> allSuggestionList = new ArrayList<>();
        allSuggestionList.addAll(sortedSuggestions);
        allSuggestionList.sort((s1, s2) -> Integer.compare(s2.getFrequency(), s1.getFrequency()));
        cur.setTopSuggestions(allSuggestionList.subList(0, Math.min(allSuggestionList.size(), threshold)));
        return cur.getTopSuggestions();
    }

    public void constructTrie(TrieNode cur) {
        List<QueryFrequency> queryFrequencyList = queryFrequencyRepository.findAll();
        for (QueryFrequency entry : queryFrequencyList)
            this.insert(entry.getQuery(), entry.getFrequency(), 0, cur);
    }
}
