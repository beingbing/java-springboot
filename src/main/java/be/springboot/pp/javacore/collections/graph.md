### Graph-based Data Structures in Java

Graphs are powerful data structures used to model a wide variety of real-world problems, such as networks (social networks, transportation systems, web crawlers), dependencies (task scheduling), and relationships between entities. A graph consists of **nodes (vertices)** connected by **edges**, where each edge may be directed or undirected, and it can also be weighted.

---

### **Objective:**
Learn about graph-based data structures, their representation in Java, and associated algorithms.

---

### Topics Covered:
1. **Introduction to Graphs: Definitions and Properties**
2. **Graph Representation in Java**
    - Adjacency Matrix
    - Adjacency List
3. **Traversing Graphs: Depth-First Search (DFS) and Breadth-First Search (BFS)**
4. **Directed and Undirected Graphs**
5. **Weighted Graphs and Dijkstra’s Algorithm**

---

### 1. **Introduction to Graphs**

A graph is a collection of vertices (also called **nodes**) and edges (connections between vertices). It is defined as **G = (V, E)**, where **V** is the set of vertices, and **E** is the set of edges.

#### **Types of Graphs**:
- **Directed Graph**: In a directed graph, edges have a direction, indicating a one-way relationship between two vertices.
- **Undirected Graph**: In an undirected graph, edges don’t have a direction, representing a two-way relationship between vertices.
- **Weighted Graph**: In a weighted graph, each edge has a weight or cost associated with it (e.g., distance, time, or cost).

#### **Properties**:
- **Degree**: The number of edges connected to a vertex. In a directed graph, vertices have **in-degree** and **out-degree**.
- **Path**: A sequence of edges that connects two vertices.
- **Cycle**: A path where the starting vertex and the ending vertex are the same.

---

### 2. **Graph Representation in Java**

Graphs can be represented in multiple ways in Java, depending on the efficiency needed for different operations.

#### **a. Adjacency Matrix Representation**

An **adjacency matrix** is a 2D array where rows and columns represent vertices, and the value in the cell represents the presence (and possibly the weight) of an edge between the vertices.

- **Adjacency Matrix**: For a graph with `n` vertices, an `n x n` matrix is used. A value of `1` (or the edge weight) indicates an edge, while `0` indicates no edge.

**Example of Adjacency Matrix**:

```java
public class GraphMatrix {
    private int[][] adjMatrix;
    private int vertices;

    public GraphMatrix(int vertices) {
        this.vertices = vertices;
        adjMatrix = new int[vertices][vertices];
    }

    public void addEdge(int src, int dest) {
        adjMatrix[src][dest] = 1; // For undirected graph, also add adjMatrix[dest][src] = 1;
    }

    public void printGraph() {
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        GraphMatrix graph = new GraphMatrix(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        graph.printGraph();
    }
}
```

This representation is simple but consumes **O(n²)** space, making it inefficient for sparse graphs (graphs with fewer edges compared to the number of vertices).

#### **b. Adjacency List Representation**

An **adjacency list** represents the graph as an array of lists. Each vertex has a list of its adjacent vertices (those connected by an edge).

**Example of Adjacency List**:

```java
import java.util.LinkedList;

public class GraphList {
    private LinkedList<Integer>[] adjList;
    private int vertices;

    public GraphList(int vertices) {
        this.vertices = vertices;
        adjList = new LinkedList[vertices];

        for (int i = 0; i < vertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int src, int dest) {
        adjList[src].add(dest);  // For undirected graph, also add adjList[dest].add(src);
    }

    public void printGraph() {
        for (int i = 0; i < vertices; i++) {
            System.out.print("Vertex " + i + ": ");
            for (Integer edge : adjList[i]) {
                System.out.print(edge + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        GraphList graph = new GraphList(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        graph.printGraph();
    }
}
```

This representation is more space-efficient for sparse graphs because only the edges are stored.

---

### 3. **Traversing Graphs: Depth-First Search (DFS) and Breadth-First Search (BFS)**

#### **a. Depth-First Search (DFS)**
DFS is a traversal algorithm that starts at a vertex, explores as far as possible along each branch before backtracking.

- DFS can be implemented using **recursion** or a **stack**.

**Example of DFS**:
```java
import java.util.LinkedList;

public class GraphDFS {
    private LinkedList<Integer>[] adjList;
    private boolean[] visited;

    public GraphDFS(int vertices) {
        adjList = new LinkedList[vertices];
        visited = new boolean[vertices];
        for (int i = 0; i < vertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int src, int dest) {
        adjList[src].add(dest);
    }

    public void dfs(int vertex) {
        visited[vertex] = true;
        System.out.print(vertex + " ");

        for (int adj : adjList[vertex]) {
            if (!visited[adj]) {
                dfs(adj);
            }
        }
    }

    public static void main(String[] args) {
        GraphDFS graph = new GraphDFS(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        System.out.println("DFS traversal starting from vertex 0:");
        graph.dfs(0);  // Output: 0 1 2 3
    }
}
```

#### **b. Breadth-First Search (BFS)**
BFS is a traversal algorithm that explores all neighbors of a vertex before moving to the next level. It uses a **queue** to keep track of vertices to explore.

**Example of BFS**:
```java
import java.util.LinkedList;
import java.util.Queue;

public class GraphBFS {
    private LinkedList<Integer>[] adjList;
    private boolean[] visited;

    public GraphBFS(int vertices) {
        adjList = new LinkedList[vertices];
        visited = new boolean[vertices];
        for (int i = 0; i < vertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int src, int dest) {
        adjList[src].add(dest);
    }

    public void bfs(int startVertex) {
        Queue<Integer> queue = new LinkedList<>();
        visited[startVertex] = true;
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            System.out.print(vertex + " ");

            for (int adj : adjList[vertex]) {
                if (!visited[adj]) {
                    visited[adj] = true;
                    queue.add(adj);
                }
            }
        }
    }

    public static void main(String[] args) {
        GraphBFS graph = new GraphBFS(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        System.out.println("BFS traversal starting from vertex 0:");
        graph.bfs(0);  // Output: 0 1 2 3
    }
}
```

---

### 4. **Directed and Undirected Graphs**

In a **directed graph**, each edge has a direction, meaning it goes from one vertex to another. The adjacency list of a directed graph only stores edges in one direction.

In an **undirected graph**, edges have no direction. The adjacency list of an undirected graph stores edges in both directions (i.e., `adjList[src].add(dest)` and `adjList[dest].add(src)`).

---

### 5. **Weighted Graphs and Dijkstra's Algorithm**

In a **weighted graph**, each edge has a weight associated with it. These graphs are used to represent scenarios like road maps, where distances between locations vary.

#### **Dijkstra's Algorithm**:
Dijkstra's Algorithm is used to find the **shortest path** from a starting vertex to all other vertices in a graph.

---

### Summary of Graph-based Data Structures in Java

- Graphs are versatile data structures used to model networks,

dependencies, and relationships.
- Java allows graph representation using adjacency matrices and adjacency lists.
- Traversal algorithms like DFS and BFS are essential for exploring graphs.
- Graphs can be directed, undirected, weighted, or unweighted.
