package List;

public class LinkedList {

    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static void printList(Node head) {
        Node currNode = head;

        System.out.print("LinkedList: ");

        while (currNode != null) {
            System.out.print(currNode.data + " ");
            currNode = currNode.next;
        }
        System.out.println("");
    }

    static Node reverseList(Node head) {
        Node prevNode = null;
        Node currNode = head;
        Node nextNode = null;

        while (currNode != null) {
            nextNode = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = nextNode;
        }

        head = prevNode;

        return head;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);
        Node fourth = new Node(4);
        Node fifth = new Node(5);
        Node sixth = new Node(6);
        Node seventh = new Node(7);
        Node eighth = new Node(8);
        Node ninth = new Node(9);
        Node tenth = new Node(10);

        head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = sixth;
        sixth.next = seventh;
        seventh.next = eighth;
        eighth.next = ninth;
        ninth.next = tenth;

        System.out.println("Список до разворота:");
        printList(head);

        head = reverseList(head);

        System.out.println("Список после разворота:");
        printList(head);
    }
}