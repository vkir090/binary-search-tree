public class BinarySearchTree {
    public static void main(String[] args){
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
