package be.springboot.pp.jsonparser;

public class Tester {

    public static void main(String[] args) {
        JsonParser jsonParser = new NaiveJsonParser(new NaiveTokenizer());

        String jsonText = """
                
                {
                  "$id": "https://jsoneditoronline.org/friends.schema.json",
                  "$schema": "https://json-schema.org/draft/2020-12/schema",
                  "title": "Friends",
                  "type": "array",
                  "items": {
                    "type": "object",
                    "properties": {
                      "name": {
                        "type": "string",
                        "description": "The friend's name."
                      },
                      "age": {
                        "description": "Age in years which must be equal to or greater than zero.",
                        "type": "integer",
                        "minimum": "0"
                      },
                      "email": {
                        "type": "string",
                        "format": "email",
                        "description": "Optional email address of the friend."
                      }
                    }
                  }
                }
                
                """;

        JSON json = jsonParser.parse(jsonText);
        System.out.println(jsonParser.toString(json.get("items").get("properties").get("name")));
    }
}
