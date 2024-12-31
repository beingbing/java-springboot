package be.springboot.pp.dsalgo.tries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class S002_gfg_phone_directory {

    public static ArrayList<ArrayList<String>> displayContacts(int n, String[] contact, String s) {
        ContactsTrie trie = new ContactsTrie();
        for (String c : contact) trie.insert(c);

        ArrayList<ArrayList<String>> results = new ArrayList<>();
        StringBuilder prefix = new StringBuilder();
        for (char c : s.toCharArray()) {
            prefix.append(c);
            ArrayList<String> matches = trie.searchPrefix(prefix.toString());
            if (matches.isEmpty()) results.add(new ArrayList<>(List.of("0")));
            else results.add(matches);
        }
        return results;
    }

    public static void main(String[] args) {
        int n = 3;
        String[] contact = {"geeikistest", "geeksforgeeks", "geeksfortest"};
        String s = "geeips";

        ArrayList<ArrayList<String>> result = displayContacts(n, contact, s);
        for (List<String> res : result) {
            System.out.println(String.join(" ", res));
        }
    }
}

class ContactsTrie {
    private final ContactsTrieNode root;

    ContactsTrie() {
        root = new ContactsTrieNode();
    }

    public void insert(String contact) {
        ContactsTrieNode current = root;
        for (char c : contact.toCharArray()) {
            current.children.putIfAbsent(c, new ContactsTrieNode());
            current = current.children.get(c);
            current.contacts.add(contact); // Add contact at this prefix
        }
    }

    // Search for a prefix in the Trie and return matching contacts
    public ArrayList<String> searchPrefix(String prefix) {
        ContactsTrieNode current = root;
        for (char c : prefix.toCharArray()) {
            if (!current.children.containsKey(c)) return new ArrayList<>(); // Prefix not found
            current = current.children.get(c);
        }
        return new ArrayList<>(current.contacts); // Return contacts in lexicographical order
    }
}

class ContactsTrieNode {
    Map<Character, ContactsTrieNode> children;
    TreeSet<String> contacts; // Store contacts in lexicographical order

    ContactsTrieNode() {
        children = new HashMap<>();
        contacts = new TreeSet<>();
    }
}
