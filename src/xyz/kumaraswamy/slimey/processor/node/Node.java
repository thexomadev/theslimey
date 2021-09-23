package xyz.kumaraswamy.slimey.processor.node;

public class Node {
    private Node left;
    private Node right;
    private final Object value;

    public Node(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return this.value;
    }

    public boolean hasNextLeft() {
        return this.left != null;
    }

    public Node setLeft(Node left) {
        this.left = left;
        return this;
    }

    public Node setRight(Node right) {
        this.right = right;
        return this;
    }

    public Node getLeft() {
        return this.left;
    }

    public Node getRight() {
        return this.right;
    }

    public String toString() {
        return "[value=" + this.value + ", left=" + this.left + ", right=" + this.right + ']';
    }
}