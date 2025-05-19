package be.springboot.pp.jsonparser;

import java.util.ArrayList;
import java.util.List;

import static be.springboot.pp.jsonparser.Constants.CLOSING_PARENTHESIS;
import static be.springboot.pp.jsonparser.Constants.COLON;
import static be.springboot.pp.jsonparser.Constants.COMMA;
import static be.springboot.pp.jsonparser.Constants.DOUBLE_QUOTES;
import static be.springboot.pp.jsonparser.Constants.EMPTY_STRING;
import static be.springboot.pp.jsonparser.Constants.STARTING_PARENTHESIS;
import static be.springboot.pp.jsonparser.Constants.WHITE_SPACE;

public class NaiveTokenizer implements Tokenizer {

    @Override
    public List<KeyValuePair> tokenize(String text) {
        text = preProcess(text);
        int startIdx = 0, endIdx; // key-value pair indices
        List<String> keyValuePairs = new ArrayList<>();
        while (!text.isEmpty()) {
            // 1. split key-value pairs
            endIdx = getEndIndex(text);
            keyValuePairs.add(text.substring(startIdx, endIdx));
            if (endIdx >= text.length()) break;
            text = text.substring(endIdx+1);
        }

        List<KeyValuePair> keyValueTokens = new ArrayList<>();
        // 2. split key and value by colon
        for (String keyValuePair : keyValuePairs) {
            int idx = keyValuePair.indexOf(COLON);
            String key = keyValuePair.substring(0, idx).trim().replaceAll(DOUBLE_QUOTES, EMPTY_STRING);
            String val = keyValuePair.substring(idx + 1);
            keyValueTokens.add(new KeyValuePair(key, val));
        }
        return keyValueTokens;
    }

    private int getEndIndex(String text) { // to parse value component
        int idx = text.indexOf(COLON); // location of colon
        int endIdx = idx + 1; // starting point of our value
        while (String.valueOf(text.charAt(endIdx)).equals(WHITE_SPACE)) endIdx++; // skip blanks

        if (String.valueOf(text.charAt(endIdx)).equals(DOUBLE_QUOTES)) { // if normal string find its end
            endIdx++;
            while (text.length() > endIdx && !String.valueOf(text.charAt(endIdx)).equals(DOUBLE_QUOTES)) endIdx++;
            endIdx++; // go 1 beyond double-quotes
        } else if (String.valueOf(text.charAt(endIdx)).equals(STARTING_PARENTHESIS)) { // if json, find end-pair
            int cnt = 1;
            endIdx++; // go 1 beyond current opening parentheses
            while (cnt != 0 && text.length() > endIdx) {
                if (String.valueOf(text.charAt(endIdx)).equals(CLOSING_PARENTHESIS)) cnt--;
                else if (String.valueOf(text.charAt(endIdx)).equals(STARTING_PARENTHESIS)) cnt++;
                endIdx++;
            }
        } else throw new RuntimeException("Illegal Json");
        // skp blanks after matching end parentheses, till we reach next comma
        while (endIdx < text.length() && !String.valueOf(text.charAt(endIdx)).equals(COMMA)) endIdx++;
        return endIdx; // index of next comma
    }

    private String preProcess(String text) {
        try {
            text = text.trim();
            text = text.substring(1, text.length() - 1);
            text = text.trim();
        } catch (Exception e) {
            throw new RuntimeException("Invalid text");
        }
        return text;
    }
}
