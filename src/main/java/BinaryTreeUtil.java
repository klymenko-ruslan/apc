import java.util.*;

public class BinaryTreeUtil<T extends Comparable<T>> {

    private BinaryTreeNode<T> root;

    /**
     * Method to check is given list of edges can form a binary tree.
     * Order matters!!! Edges should pass in order of insertion to the tree.
     * @param edges
     * @return
     */
    public synchronized boolean isBinaryTree(List<Pair<T, T>> edges) {
        if(edges == null || edges.isEmpty()) return false;
        root = null;
        for(Pair<T, T> currentNode: edges) {
            if(!addNode(currentNode.getA(), currentNode.getB())) return false;
        }
        return true;
    }

    private boolean addNode(T src, T dest) {
        if(src.equals(dest)) {
            return false;
        } else if(root == null) {
            root = new BinaryTreeNode<>(src);
            root.setLeft(new BinaryTreeNode(dest));
        } else {
            if(findNode(root, dest) != null) return false;
            BinaryTreeNode srcNode = findNode(root, src);
            if(srcNode == null || srcNode.getLeft() != null && srcNode.getRight() != null) return false;
            if(srcNode.getLeft() == null) srcNode.setLeft(new BinaryTreeNode(dest));
            else srcNode.setRight(new BinaryTreeNode(dest));
        }
        return true;
    }

    private BinaryTreeNode<T> findNode(BinaryTreeNode current, T value) {
        if(current == null) return null;
        if(current.getValue().equals(value)) return current;
        BinaryTreeNode node = findNode(current.getLeft(), value);
        return node == null ? findNode(current.getRight(), value) : node;
    }
}

class BinaryTreeNode<T extends Comparable<T>> {
    private T value;
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;

    public BinaryTreeNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    public BinaryTreeNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryTreeNode<?> binaryTreeNode = (BinaryTreeNode<?>) o;
        return Objects.equals(value, binaryTreeNode.value) &&
                Objects.equals(left, binaryTreeNode.left) &&
                Objects.equals(right, binaryTreeNode.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, left, right);
    }
}
class Pair<K, V> {
    private K a;
    private K b;

    public Pair(K a, K b) {
        this.a = a;
        this.b = b;
    }

    public K getA() {
        return a;
    }

    public void setA(K a) {
        this.a = a;
    }

    public K getB() {
        return b;
    }

    public void setB(K b) {
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(a, pair.a) &&
                Objects.equals(b, pair.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}