### Trie-based Data Structures in Java

A **Trie** (pronounced "try") is a specialized tree-like data structure that is used to store a dynamic set of strings. It is especially useful for solving problems related to word searching, autocomplete, and prefix matching. Each node in a Trie represents a single character of a string, and all the descendants of a node share a common prefix with the string represented by that node.

---

### **Objective:**
Learn how Trie-based data structures work, their structure, and how to implement them in Java.

---

### Topics Covered:
1. **Introduction to Trie Data Structure**
2. **Structure of a Trie**
3. **Operations on Trie (Insert, Search, Delete)**
4. **Efficiency and Space Complexity**
5. **Java Implementation of Trie**
6. **Applications of Trie (Autocomplete, Spell Checker, Prefix Matching)**

---

### 1. **Introduction to Trie Data Structure**

A **Trie** (also known as a prefix tree or digital tree) is a tree structure where:
- Each **node** represents a character.
- The **root** node represents an empty string.
- A path from the root to a particular node represents a **prefix** of words in the set.
- The complete words are stored in leaf nodes or nodes with an explicit end-of-word marker.

#### **Key Properties**:
- Efficient for storing and searching for strings (especially when dealing with large datasets).
- Searches for a word or prefix in **O(m)** time, where **m** is the length of the word.
- Typically used for **autocomplete** and **spell checking** tasks.

---

### 2. **Structure of a Trie**

Each node in the Trie contains:
- **Children**: An array or map that holds references to the next character in the string.
- **End-of-Word Marker**: A flag indicating if the node represents the end of a valid word.

**Example**: Consider inserting the words "cat", "car", and "cap" into a Trie.

```
         (root)
          / 
         c
       / | \
      a  a  a
     /   |   \
    t    p    r
   (end) (end) (end)
```

The paths `c -> a -> t`, `c -> a -> p`, and `c -> a -> r` represent the words "cat", "cap", and "car", respectively.

---

### 3. **Operations on Trie**

#### **a. Insert Operation**:
The insertion process adds characters one by one into the Trie, creating new nodes if necessary. If the word is already in the Trie, we simply mark the end node.

**Insert Example**:
1. Start at the root node.
2. For each character in the string:
    - If a child node for that character exists, move to it.
    - Otherwise, create a new node for the character.
3. After the last character, mark the current node as the end of the word.

#### **b. Search Operation**:
To search for a word, start at the root and follow the nodes corresponding to each character in the word. If the word is found and the last node is marked as the end of the word, the search is successful.

**Search Example**:
1. Start at the root.
2. For each character in the string:
    - If a child node exists for the character, move to it.
    - Otherwise, return `false` (word not found).
3. If all characters are found and the last node is an end-of-word marker, return `true`.

#### **c. Delete Operation**:
Deleting a word from a Trie requires careful handling to avoid deleting nodes that are part of other words. The steps involve:
1. Traverse the Trie and find the end of the word.
2. If the word is found, unmark the end node.
3. Optionally, prune nodes that are no longer part of any other word.

---

### 4. **Efficiency and Space Complexity**

Trie structures are very efficient in terms of search time:
- **Time Complexity**:
    - **Insert**: O(m), where **m** is the length of the word.
    - **Search**: O(m), where **m** is the length of the word.
    - **Delete**: O(m), where **m** is the length of the word.

- **Space Complexity**:
  The space complexity is typically **O(n * m)**, where **n** is the number of words and **m** is the average length of the words. However, with shared prefixes, Tries can reduce redundancy, leading to memory savings.

---

### 5. **Java Implementation of Trie**

A Trie can be implemented using a class with nodes representing each character. The nodes can be stored in an array (for fixed-size alphabets) or in a `Map` for more flexibility.

**Java Code for a Trie**:

```java
import java.util.HashMap;
import java.util.Map;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord = false;
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Insert a word into the Trie
    public void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            current = current.children.computeIfAbsent(c, k -> new TrieNode());
        }
        current.isEndOfWord = true;
    }

    // Search for a word in the Trie
    public boolean search(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            current = current.children.get(c);
            if (current == null) {
                return false;
            }
        }
        return current.isEndOfWord;
    }

    // Delete a word from the Trie
    public boolean delete(String word) {
        return delete(root, word, 0);
    }

    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEndOfWord) {
                return false;  // word not found
            }
            current.isEndOfWord = false;  // unmark the end of word
            return current.children.isEmpty();  // delete the node if no children
        }

        char c = word.charAt(index);
        TrieNode node = current.children.get(c);
        if (node == null) {
            return false;  // word not found
        }

        boolean shouldDeleteCurrentNode = delete(node, word, index + 1);

        if (shouldDeleteCurrentNode) {
            current.children.remove(c);  // remove the mapping
            return current.children.isEmpty();  // delete this node if no children
        }

        return false;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("cat");
        trie.insert("car");
        trie.insert("cap");

        System.out.println("Search 'cat': " + trie.search("cat"));  // true
        System.out.println("Search 'dog': " + trie.search("dog"));  // false
        System.out.println("Delete 'cat': " + trie.delete("cat"));  // true
        System.out.println("Search 'cat' after delete: " + trie.search("cat"));  // false
    }
}
```

This implementation includes methods to:
- **Insert** a word into the Trie.
- **Search** for a word in the Trie.
- **Delete** a word from the Trie.

---

### 6. **Applications of Trie**

#### **a. Autocomplete**:
Tries are frequently used for building **autocomplete systems**. Given a prefix, the Trie can quickly return all words that start with the prefix.

**Example**: Typing "app" in a search bar suggests words like "apple", "appetite", etc.

#### **b. Spell Checker**:
Tries can also be used in **spell checkers**, where given a misspelled word, the Trie can find the closest valid word by comparing prefixes.

#### **c. Prefix Matching**:
In many applications, finding all words that share a common prefix (like in search engines) is important. A Trie can efficiently handle this operation.

---

### Summary of Trie-based Data Structures in Java

- **Tries** are tree-like structures for efficiently storing and searching strings.
- Tries are optimized for **prefix matching**, **autocomplete**, and **spell checking**.
- They provide efficient **O(m)** time complexity for insertion, search, and deletion, where **m** is the length of the word.
- **Java implementation** involves creating nodes with children for each character and supporting operations like insert, search, and delete.