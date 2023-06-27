package RedBlackWood;

public class RedBlackTree2 {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        public Node left;
        public Node right;
        int key;
        int value;
        boolean color;
        int size;

        public Node(int key, int value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.size = 1;
        }
    }

    private Node root;

    public void insert(int key, int value) {
        root = insert(root, key, value);
        root.color = BLACK;
    }

    private Node insert(Node node, int key, int value) {
        if (node == null) return new Node(key, value, RED);

        if (key < node.key) node.left = insert(node.left, key, value);
    else if (key > node.key) node.right = insert(node.right, key, value);
        else node.value = value;

        if (isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColors(node);

        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    private boolean isRed(Node node) {
        if (node == null) return false;
        return node.color == RED;
    }

    private Node rotateLeft(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        x.size = node.size;
        node.size = size(node.left) + size(node.right) + 1;
        return x;
    }

    private Node rotateRight(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        x.size = node.size;
        node.size = size(node.left) + size(node.right) + 1;
        return x;
    }

    private void flipColors(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }

    private int size(Node node) {
        if (node == null) return 0;

        return node.size;
    }

    // Метод для проверки работы дерева
    public static void main(String[] args) {
        RedBlackTree2 tree = new RedBlackTree2();

        for (int i = 1; i <= 20; i++) {
            tree.insert (i, i);
        }

        System.out.println("Построенное дерево:");
        System.out.println("--------------------");
        tree.print(tree.root);
    }

    private void print(Node node) {
        if (node == null) return;

        print(node.left);
        System.out.println("Ключ: " + node.key + ", Значение: " + node.value + ", Цвет: " + (node.color == RED ? "Красный" : "Черный"));
        print(node.right);
    }
}