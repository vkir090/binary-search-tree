# Binary Search Tree (Java)

This small project contains my own implementation of a **Binary Search Tree (BST)** in Java.  
Goal: practise classic data structures and recursion without using the Java Collections framework.

---

## Features

- Insert nodes (`insertNode(int value)`)
- Search for a value (`searchNode(int value)`)
- Delete a node (handles all cases: leaf, one child, two children)
- Simple tree traversal / printing (`showAllNodes()`)
- Parent references in each node (makes some operations easier)
- Logging via `java.util.logging` for debugging and learning

---

## Implementation Overview

### Core classes

- **`Node`**
  - Fields: `Integer value`, `Node leftNode`, `Node rightNode`, `Node parent`
  - Simple getters/setters
  - `parent` is updated whenever links in the tree change

- **`Tree`**
  - Field: `Node startNode` (root of the tree, `null` if the tree is empty)
  - Important methods:
    - `insertNode(int value)` – public entry for insertion
    - `createNode(Node node, int value)` – recursive insert helper
    - `showAllNodes()` / `printAllNodes(Node node)` – traversal and logging
    - `searchNode(int value)` – returns the `Node` or `null`
    - `isThereANode(Node node, int value)` – boolean search helper
    - `getFoundedNode(Node node, int value)` – returns the matching node
    - `deleteNode(int value)` – public delete method
    - `deleteRecursive(Node node, int value)` – recursive delete logic
    - `searchSmallestLeftNode(Node node)` – finds the in‑order successor
  - Deletion covers:
    - no children
    - only left child
    - only right child
    - two children (using the smallest node in the right subtree as successor)

- **`BinarySearchTree`**
  - Contains the `main` method.
  - Used to create a tree, insert some values, search and delete nodes.

---

## Time Complexity (Average Case)

- Insert: **O(log n)**
- Search: **O(log n)**
- Delete: **O(log n)**

In the worst case (highly unbalanced tree) operations degrade to **O(n)**.

---

## How to Run

1. Make sure you have **Java 17+** installed.
2. Put the three classes into the same package / directory:
   - `Tree.java`
   - `Node.java`
   - `BinarySearchTree.java`
3. Compile and run:

```bash
javac *.java
java BinarySearchTree
```

You should see log output in the console that shows inserted nodes, traversals and deletes.

---

## Example Usage (simplified)

```java
public class BinarySearchTree {
    public static void main(String[] args) {
        Tree tree = new Tree();

        tree.insertNode(5);
        tree.insertNode(3);
        tree.insertNode(7);
        tree.insertNode(1);
        tree.insertNode(9);

        tree.showAllNodes();      // prints the whole tree
        tree.searchNode(7);       // logs whether 7 exists
        tree.deleteNode(3);       // deletes node with value 3
        tree.showAllNodes();      // tree after deletion
    }
}
```

---

