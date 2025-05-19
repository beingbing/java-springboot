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

    public boolean isLeaf() {
        return (this.keyValue.size() == 1)
                && (this.keyValue.entrySet().iterator().next().getValue() == null);
    }
}
