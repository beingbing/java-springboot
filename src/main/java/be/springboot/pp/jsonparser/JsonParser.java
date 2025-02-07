package be.springboot.pp.jsonparser;

public interface JsonParser {
    JSON parse(String text);
    String toString(JSON json);
}
