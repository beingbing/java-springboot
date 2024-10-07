### Tree-based Data Structures in Java

Tree-based data structures are used to store hierarchical data and enable efficient searching, sorting, and traversal. Java provides several implementations of tree-based data structures through its **Collections Framework**.

---

### **Objective:**
Learn about tree-based data structures, focusing on:
1. **General Tree Concepts and Traversals.**
2. **Binary Trees (Binary Search Tree, AVL Tree, Red-Black Tree).**
3. **Java's Tree-based Implementations (TreeSet, TreeMap).**

---

### 1. **General Tree Concepts**

A **tree** is a hierarchical data structure made up of nodes, where each node has a value and references to its child nodes. Trees are widely used in situations where data needs to be represented in a structured or hierarchical way (e.g., file systems, organizational charts).

#### **Key Characteristics of Trees:**
- **Root node**: The topmost node in a tree.
- **Parent-child relationship**: Each node, except the root, has exactly one parent. A node can have zero or more children.
- **Leaf node**: A node with no children.
- **Depth and height**: The depth of a node is the number of edges from the root to that node. The height of a tree is the maximum depth of any node.

#### **Tree Traversal Techniques**:
Tree traversal refers to the process of visiting all the nodes in a tree in a specific order. The most common types of traversal are:
1. **Pre-order traversal**: Visit the root node, then traverse the left subtree, followed by the right subtree.
2. **In-order traversal**: Traverse the left subtree, visit the root node, then traverse the right subtree. (Used in binary search trees to retrieve elements in sorted order).
3. **Post-order traversal**: Traverse the left subtree, traverse the right subtree, and then visit the root node.
4. **Level-order traversal**: Visit the nodes level by level, starting from the root.

---

### 2. **Binary Trees**

A **binary tree** is a tree in which each node has at most two children (referred to as the left child and the right child). Binary trees are the foundation for several advanced tree-based data structures.

#### **Binary Search Tree (BST)**:
A **Binary Search Tree** is a binary tree where the following properties hold:
- The left subtree contains only nodes with values less than the root.
- The right subtree contains only nodes with values greater than the root.

This structure allows for efficient searching, insertion, and deletion operations with an average time complexity of **O(log n)**.

**Example of a BST**:
```java
class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value) {
        this.value = value;
        left = right = null;
    }
}

public class BinarySearchTree {
    TreeNode root;

    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    private TreeNode insertRecursive(TreeNode root, int value) {
        if (root == null) {
            root = new TreeNode(value);
            return root;
        }

        if (value < root.value) {
            root.left = insertRecursive(root.left, value);
        } else if (value > root.value) {
            root.right = insertRecursive(root.right, value);
        }

        return root;
    }

    public void inOrderTraversal(TreeNode root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.print(root.value + " ");
            inOrderTraversal(root.right);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        bst.inOrderTraversal(bst.root);  // Output: 20 30 40 50 60 70 80
    }
}
```

#### **Self-balancing Binary Search Trees**:
Binary Search Trees can become **unbalanced**, where one subtree becomes significantly larger than the other. This can lead to inefficient operations with time complexity degenerating to **O(n)** in the worst case.

To address this issue, self-balancing trees automatically adjust their structure during insertions and deletions to maintain **O(log n)** performance. Examples include:

1. **AVL Tree**: A self-balancing BST where the difference in heights between the left and right subtrees (called the **balance factor**) is at most 1.
2. **Red-Black Tree**: Another self-balancing BST where nodes are colored either red or black, and the tree is balanced based on specific color-related properties.

In Java, **`TreeMap`** and **`TreeSet`** use **Red-Black Trees** to maintain sorted data and ensure balanced search times.

---

### 3. **Java's Tree-based Implementations**

#### **a. TreeSet**

`TreeSet` is a **SortedSet** implementation backed by a Red-Black Tree. It ensures that elements are stored in a sorted and balanced manner.

Key operations:
- **add(E e)**: Inserts the specified element into the set if it's not already present.
- **remove(Object o)**: Removes the specified element from the set.
- **contains(Object o)**: Checks if the set contains the specified element.

**Example of TreeSet**:
```java
import java.util.TreeSet;

public class TreeSetExample {
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(10);
        set.add(5);
        set.add(20);

        System.out.println("TreeSet: " + set);  // Output: [5, 10, 20]
    }
}
```

Key features of `TreeSet`:
- Elements are **sorted** in natural order (or based on a comparator).
- It does **not allow duplicates**.
- `TreeSet` provides logarithmic time complexity for basic operations like `add`, `remove`, and `contains`.

#### **b. TreeMap**

`TreeMap` is a **SortedMap** implementation backed by a Red-Black Tree. It stores key-value pairs in sorted order according to the natural ordering of keys or a custom comparator.

Key operations:
- **put(K key, V value)**: Associates the specified value with the specified key.
- **get(Object key)**: Returns the value to which the specified key is mapped.
- **remove(Object key)**: Removes the mapping for a key if it exists.

**Example of TreeMap**:
```java
import java.util.TreeMap;

public class TreeMapExample {
    public static void main(String[] args) {
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("Apple", 1);
        map.put("Banana", 2);
        map.put("Cherry", 3);

        System.out.println("TreeMap: " + map);  // Output: {Apple=1, Banana=2, Cherry=3}
    }
}
```

Key features of `TreeMap`:
- Entries are sorted by key.
- It allows **null values**, but does **not allow null keys**.
- Provides logarithmic time complexity for `get`, `put`, and `remove`.

---

### 4. **Applications of Tree-based Structures**

#### **a. Searching and Sorting**
- Trees provide efficient searching and sorting mechanisms. **Binary Search Trees** and **Self-balancing Trees** like Red-Black Trees ensure that elements remain sorted and can be accessed in logarithmic time.

#### **b. Priority Queues**
- Priority queues can be implemented using **binary heap** trees, which allow efficient insertion and retrieval of the minimum or maximum element.

#### **c. Decision Trees**
- **Decision Trees** are used in machine learning and other areas where decisions need to be made based on certain conditions, represented as tree nodes.

#### **d. Expression Evaluation**
- Trees can represent and evaluate expressions (e.g., mathematical or logical expressions) by arranging operands and operators in a hierarchical structure.

---

### Summary of Tree-based Data Structures in Java

- Tree-based data structures provide hierarchical organization and efficient search, insertion, and deletion operations.
- **Binary Search Trees** form the foundation, but **self-balancing trees** (e.g., AVL, Red-Black) ensure optimal performance.
- **TreeSet** and **TreeMap** in Java use Red-Black Trees to maintain sorted order and provide efficient operations.
- Applications range from sorting and searching to priority queues and expression evaluation.

Next, we will dive into **Graph-based Data Structures**. Let me know when you're ready to continue!