package be.springboot.pp.jsonparser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// it will be a recursive data class
public class JSON {
    private final Map<String, JSON> keyValue;

    public JSON(Map<String, JSON> keyValue) {
        this.keyValue = keyValue;
    }

    public JSON get(String key) {
        return this.keyValue.get(key);
    }

    public List<String> getAllKeys() {
        List<String> keys = new ArrayList<>();
        for (Map.Entry<String, JSON> jsonEntry : keyValue.entrySet()) keys.add(jsonEntry.getKey());
        return keys;
    }

    /*
    * If we create a getter to access map then its not a good design as we are exposing
    * internal data-structure details. Furthermore, client will need to figure out how to
    * handle this extra detail. Also, if we changed internal implementation from map to
    * something else then client need to understand handling of that as well.
    * */

    public boolean isLeaf() {
        return (this.keyValue.size() == 1)
                && (this.keyValue.entrySet().iterator().next().getValue() == null);
    }
}
