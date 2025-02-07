package be.springboot.pp.jsonparser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static be.springboot.pp.jsonparser.Constants.CLOSING_PARENTHESIS;
import static be.springboot.pp.jsonparser.Constants.COLON;
import static be.springboot.pp.jsonparser.Constants.COMMA;
import static be.springboot.pp.jsonparser.Constants.DOUBLE_QUOTES;
import static be.springboot.pp.jsonparser.Constants.EMPTY_STRING;
import static be.springboot.pp.jsonparser.Constants.STARTING_PARENTHESIS;

public class NaiveJsonParser implements JsonParser {
    private final Tokenizer tokenizer;

    public NaiveJsonParser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    @Override
    public JSON parse(String text) {
        if (text == null) throw new RuntimeException("Json text is null");

        if (!text.contains(STARTING_PARENTHESIS)) {
            text = text.trim().replaceAll(DOUBLE_QUOTES, EMPTY_STRING);
            Map<String, JSON> keyToValues = new HashMap<>();
            keyToValues.put(text, null);
            return new JSON(keyToValues);
        }

        List<KeyValuePair> keyValuePairs = this.tokenizer.tokenize(text);
        Map<String, JSON> keyToValues = new HashMap<>();
        for (KeyValuePair pair : keyValuePairs) keyToValues.put(pair.key(), parse(pair.value()));
        return new JSON(keyToValues);
    }

    @Override
    public String toString(JSON json) {
        if (json.isLeaf()) {
            return DOUBLE_QUOTES + json.getAllKeys().getFirst().trim() + DOUBLE_QUOTES;
        }
        StringBuilder text = new StringBuilder(STARTING_PARENTHESIS);
        List<String> keys = json.getAllKeys();
        for (String key : keys) {
            text.append(DOUBLE_QUOTES).append(key).append(DOUBLE_QUOTES);
            text.append(COLON);
            text.append(toString(json.get(key)));
            text.append(COMMA);
        }
        if (text.toString().endsWith(COMMA)) text = new StringBuilder(text.substring(0, text.length() - 1));

        text.append(CLOSING_PARENTHESIS);
        return text.toString();
    }
}
