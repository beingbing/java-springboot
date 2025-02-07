package be.springboot.pp.jsonparser;

import java.util.List;

public interface Tokenizer {
    List<KeyValuePair> tokenize(String text);
}
