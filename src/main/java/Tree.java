import java.util.logging.Level;
import java.util.logging.Logger;

public class Tree {
    private Node startNode;

    private static final Logger LOGGER = Logger.getLogger(Tree.class.getName());

    public Tree() {
        startNode = null;
    }

    public void insertNode(int value){
        if (startNode == null){
            startNode = new Node(value);
            LOGGER.log(Level.INFO, "New node was created " + startNode.getValue());
        } else {
            createNode(startNode, value);
        }
    }

    private void createNode(Node node, int value){
        if (value > node.getValue()){
            if (node.getRightNode() == null){
                node.setRightNode(new Node(value));
                node.getRightNode().setParent(node);
                LOGGER.log(Level.INFO, "New node was created " + node.getRightNode().getValue());
            } else {
                createNode(node.getRightNode(), value);
            }
        } else if (value < node.getValue()){
            if (node.getLeftNode() == null){
                node.setLeftNode(new Node(value));
                node.getLeftNode().setParent(node);
                LOGGER.log(Level.INFO, "New node was created " + node.getLeftNode().getValue());
            } else {
                createNode(node.getLeftNode(), value);
            }
        } else if (value == node.getValue()){
            LOGGER.log(Level.INFO, "The same value has been found " + value);
        }
    }

    public void showAllNodes(){
        if (startNode == null){
            LOGGER.log(Level.INFO, "There is nothing to be showed");
        } else {
            LOGGER.log(Level.INFO, "The start node is " + startNode.getValue());
            printAllNodes(startNode);
        }
    }

    private void printAllNodes(Node node) {
        if(node != null){
            LOGGER.log(Level.INFO, "The nodes value is " + node.getValue());
            printAllNodes(node.getRightNode());
            printAllNodes(node.getLeftNode());
        }
    }

    public Node searchNode(int value){
        if ((isThereANode(startNode, value))) {
            LOGGER.log(Level.INFO, "The node has been found " + value);
            return getFoundedNode(startNode, value);
        } else {
            LOGGER.log(Level.INFO, "The node has not been found " + value);
            return null;
        }
    }

    private boolean isThereANode(Node node, int value) {
        if (node == null){
            return false;
        } else {
            if (node.getValue() > value){
                return isThereANode(node.getLeftNode(), value);
            } else if (node.getValue() < value) {
                return isThereANode(node.getRightNode(), value);
            } else if (node.getValue() == value) {
                return true;
            }
            return false;
        }
    }

    private Node getFoundedNode(Node node, int value) {
        if (node.getValue() > value){
            return getFoundedNode(node.getLeftNode(), value);
        } else if (node.getValue() < value) {
            return getFoundedNode(node.getRightNode(), value);
        } else if (node.getValue() == value) {
            return node;
        }
        return null;
    }

    public void deleteNode(int value){
        // Optional: check first, if the value exists
        if (!isThereANode(startNode, value)) {
            LOGGER.log(Level.INFO, "There is no such node existing in the tree " + value);
            return;
        }

        startNode = deleteRecursive(startNode, value);

        // Root has no parent
        if (startNode != null) {
            startNode.setParent(null);
        }

        LOGGER.log(Level.INFO, "Node deleted: " + value);
    }

    private Node deleteRecursive(Node node, int value) {
        if (node == null) {
            return null;
        }

        if (value < node.getValue()) {
            // go to the left
            node.setLeftNode(deleteRecursive(node.getLeftNode(), value));
            if (node.getLeftNode() != null) {
                node.getLeftNode().setParent(node);
            }
        } else if (value > node.getValue()) {
            // go to the right
            node.setRightNode(deleteRecursive(node.getRightNode(), value));
            if (node.getRightNode() != null) {
                node.getRightNode().setParent(node);
            }
        } else {
            // value == node.getValue() → this node should be deleted

            // case 1: no child
            if (node.getLeftNode() == null && node.getRightNode() == null) {
                return null;
            }

            // case 2: only right child
            if (node.getLeftNode() == null) {
                Node child = node.getRightNode();
                return child;
            }

            // case 3: only left child
            if (node.getRightNode() == null) {
                Node child = node.getLeftNode();
                return child;
            }

            // case 4: 2 children → the smallest in the right part of tree
            Node successor = searchSmallestLeftNode(node.getRightNode());
            node.setValue(successor.getValue());

            // Successor has a left child → only right part is to be checked
            node.setRightNode(deleteRecursive(node.getRightNode(), successor.getValue()));
            if (node.getRightNode() != null) {
                node.getRightNode().setParent(node);
            }
        }

        return node;
    }

    private Node searchSmallestLeftNode(Node node) {
        if (node.getLeftNode() == null) {
            return node;
        } else {
            return searchSmallestLeftNode(node.getLeftNode());
        }
    }
}
