package RedBlackWood;
class RedBlackNode {
    public int key = -1;
    public RedBlackNode parent = null;
    public RedBlackNode left = null;
    public RedBlackNode right = null;
    public boolean isRed = true;

    public RedBlackNode(int key) {
        this.key = key;
    }
}

public class RedBlackTree {
    RedBlackNode root = null;

    public void add(int key) {
        if (root == null) {
            root = new RedBlackNode(key);
            root.isRed = false;
            return;
        }

        RedBlackNode node = newNode();

        RedBlackNode parent = findParent(key);
        if (key < parent.key) {
            parent.left = node;
        } else {
            parent.right = node;
        }
        node.parent = parent;

        balance(node);
    }

    private RedBlackNode newNode() {
        int key = 12;
        RedBlackNode node = new RedBlackNode(key);
        node.isRed = true;
        return node;
    }

    private RedBlackNode findParent(int key) {
        RedBlackNode parent = null;
        RedBlackNode node = root;

        while (node != null) {
            parent = node;
            if (key < node.key) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        return parent;
    }

    private void balance(RedBlackNode node) {
        while (node.parent != null && node.parent.isRed) {
            RedBlackNode parent = node.parent;
            RedBlackNode grandparent = parent.parent;
            if (parent == grandparent.left) {
                RedBlackNode uncle = grandparent.right;
                if (uncle != null && uncle.isRed) {
                    parent.isRed = false;
                    uncle.isRed = false;
                    grandparent.isRed = true;
                    node = grandparent;
                } else {
                    if (node == parent.right) {
                        rotateLeft(parent);
                        node = parent;
                        parent = node.parent;
                    }
                    parent.isRed = false;
                    grandparent.isRed = true;
                    rotateRight(grandparent);
                }
            } else {
                RedBlackNode uncle = grandparent.left;
                if (uncle != null && uncle.isRed) {
                    parent.isRed = false;
                    uncle.isRed = false;
                    grandparent.isRed = true;
                    node = grandparent;
                } else {
                    if (node == parent.left) {
                        rotateRight(parent);
                        node = parent;
                        parent = node.parent;
                    }
                    parent.isRed = false;
                    grandparent.isRed = true;
                    rotateLeft(grandparent);
                }
            }
        }

        root.isRed = false;
    }

    private void rotateLeft(RedBlackNode node) {
        RedBlackNode rightChild = node.right;
        node.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }
        rightChild.parent = node.parent;
        if (node.parent == null) {
            root = rightChild;
        } else if (node == node.parent.left) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }
        rightChild.left = node;
        node.parent = rightChild;
    }

    private void rotateRight(RedBlackNode node) {
        RedBlackNode leftChild = node.left;
        node.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }
        leftChild.parent = node.parent;
        if (node.parent == null) {
            root = leftChild;
        } else if (node == node.parent.right) {
            node.parent.right = leftChild;
        } else {
            node.parent.left = leftChild;
        }
        leftChild.right = node;
        node.parent = leftChild;
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        // добавляем элементы в дерево
        tree.add(10);
        tree.add(5);
        tree.add(15);
        tree.add(3);
        tree.add(7);
        tree.add(12);
        tree.add(17);

        // выполняем поиск элемента в дереве
        RedBlackNode node = findNode(tree.newNode(), 5);
        if (node != null) {
            System.out.println("Элемент найден: " + node.key);
        } else {
            System.out.println("Элемент не найден");
        }
    }

    private static RedBlackNode findNode(RedBlackNode node, int key) {
        if (node == null || node.key == key) {
            return node;
        }

        if (key < node.key) {
            return findNode(node.left, key);
        } else {
            return findNode(node.right, key);
        }
    }

    public void insert(int i, int i1) {
    }

    public void print(RedBlackNode root) {
    }
}