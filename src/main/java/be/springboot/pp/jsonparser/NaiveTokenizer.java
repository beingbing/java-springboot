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
        int startIdx = 0, endIdx;
        List<String> keyValuePairs = new ArrayList<>();
        while (!text.isEmpty()) {
            endIdx = getEndIndex(text);
            keyValuePairs.add(text.substring(startIdx, endIdx));
            if (endIdx >= text.length()) break;
            text = text.substring(endIdx+1);
        }

        List<KeyValuePair> keyValueTokens = new ArrayList<>();
        for (String keyValuePair : keyValuePairs) {
            int idx = keyValuePair.indexOf(COLON);
            String key = keyValuePair.substring(0, idx).trim().replaceAll(DOUBLE_QUOTES, EMPTY_STRING);
            String val = keyValuePair.substring(idx + 1);
            keyValueTokens.add(new KeyValuePair(key, val));
        }
        return keyValueTokens;
    }

    private int getEndIndex(String text) {
        int idx = text.indexOf(COLON);
        int endIdx = idx + 1;
        while (String.valueOf(text.charAt(endIdx)).equals(WHITE_SPACE)) endIdx++;

        if (String.valueOf(text.charAt(endIdx)).equals(DOUBLE_QUOTES)) {
            endIdx++;
            while (text.length() > endIdx && !String.valueOf(text.charAt(endIdx)).equals(DOUBLE_QUOTES)) endIdx++;
            endIdx++;
        } else if (String.valueOf(text.charAt(endIdx)).equals(STARTING_PARENTHESIS)) {
            int cnt = 1;
            endIdx++;
            while (cnt != 0 && text.length() > endIdx) {
                if (String.valueOf(text.charAt(endIdx)).equals(CLOSING_PARENTHESIS)) cnt--;
                else if (String.valueOf(text.charAt(endIdx)).equals(STARTING_PARENTHESIS)) cnt++;
                endIdx++;
            }
        } else throw new RuntimeException("Illegal Json");
        while (endIdx < text.length() && !String.valueOf(text.charAt(endIdx)).equals(COMMA)) endIdx++;
        return endIdx;
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
