package be.springboot.pp.searchtypeahead.utils;

public class SuggestionUtils {

    public static boolean isAllLowerCaseString(String query) {
        if (query == null || query.isEmpty()) {
            return false;
        }

        for (char c : query.toCharArray()) {
            if (!Character.isLowerCase(c)) {
                return false;
            }
        }
        return true;
    }
}
