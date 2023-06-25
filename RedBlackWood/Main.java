package RedBlackWood;

import java.util.Date;

class HashMap{
    private class Entity{
        int key;
        int value;
    }
    private class Basket{ // СЃРїРёСЃРѕРє
        private Node head;
        static class Node{
            Entity entity;
            Node next;
        }

        public Integer find(int key){ // O(N) -> O(1)
            Node node = head;
            while(node != null){
                if(node.entity.key == key){
                    return node.entity.value;
                }
                node = node.next;
            }
            return null;
        }

        public boolean push(int key, int value){
            Node node = new Node();
            node.entity = new Entity();
            node.entity.key = key;
            node.entity.value = value;

            if(head == null){
                head = node;
            }else {
                Node cur = head;

                while (cur.next != null) {
                    if (cur.entity.key == key) {
                        return false;
                    }
                    cur = cur.next;
                }
                cur.next = node;
            }
            return true;
        }
    }

    final static int INIT_SIZE = 16;
    private  Basket[] baskets;
    public HashMap(int size){
        baskets = new Basket[size];
    }
    public HashMap(){
        this(INIT_SIZE);
    }

    public int getIndex(int key){ // O(1)
        return key % baskets.length; // [0, baskets.length - 1]
    }

    public Integer find(int key){
        int index = getIndex(key);
        Basket basket = baskets[index];
        if(basket == null)
            return null;
        return basket.find(key);
    }

    public boolean push(int key, int value){
        int index = getIndex(key);
        Basket basket = baskets[index];
        if(basket == null){
            Basket b = new Basket();
            boolean res = b.push(key, value);
            baskets[index] = b;
            return res;
        }else{
            BinaryTree tree = new BinaryTree();
            for (Basket.Node node = basket.head; node != null; node = node.next) {
                tree.insert(node.entity.key);
            }
            boolean res = tree.find(key);
            if (!res) {
                tree.insert(key);
                basket.head = null;
                BinaryTree.Node root = tree.root;
                while (root.left != null) {
                    root = root.left;
                }
                Basket.Node node = new Basket.Node();
                node.entity = new Entity();
                node.entity.key = root.value;
                node.entity.value = find(root.value);
                basket.head = node;
                root = root.right;
                while (root != null) {
                    Basket.Node nextNode = new Basket.Node();
                    nextNode.entity = new Entity();
                    nextNode.entity.key = root.value;
                    nextNode.entity.value = find(root.value);
                    node.next = nextNode;
                    node = nextNode;
                    root = root.right;
                }
            }
            return res;
        }
    }
}
class BinaryTree{
    Node root;
    class Node{
        int value;
        Node left;
        Node right;
    }
    public boolean find(int value){ // O(log N)
        Node cur = root;
        while(cur != null){
            if(cur.value == value){
                return true;
            }
            if(cur.value < value){
                cur = cur.right;
            }else{
                cur = cur.left;
            }
        }
        return false;
    }


    public void print(){
        print(root);
    }

    private void print(Node node){
        if(node == null)
            return;
        System.out.println(node.value);

        if(node.left != null)
            System.out.println("L:" + node.left.value);

        if(node.right != null)
            System.out.println("R:" + node.right.value);

        print(node.left);
        print(node.right);
    }
    public void insert(int value) {
        root = insert(root, value);
    }

    private Node insert(Node node, int value) {
        if (node == null) {
            Node newNode = new Node();
            newNode.value = value;
            return newNode;
        }
        if (value < node.value) {
            node.left = insert(node.left, value);
        } else if (value > node.value) {
            node.right = insert(node.right, value);
        } else {
            return node; // элемент уже есть в дереве
        }

        int balance = getBalance(node);
        if (balance > 1 && value < node.left.value) {
            return rotateRight(node);
        }
        if (balance < -1 && value > node.right.value) {
            return rotateLeft(node);
        }
        if (balance > 1 && value > node.left.value) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balance < -1 && value < node.right.value) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    private Node rotateLeft(Node node) {
        Node right = node.right;
        Node leftOfRight = right.left;
        right.left = node;
        node.right = leftOfRight;
        return right;
    }

    private Node rotateRight(Node node) {
        Node left = node.left;
        Node rightOfLeft = left.right;
        left.right = node;
        node.left = rightOfLeft;
        return left;
    }
}
public class Main {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        for(int i=1; i<=5; i++){
            map.push(i, i);
        }

        System.out.println(map.find(3));
        System.out.println(map.find(6));
    }
}