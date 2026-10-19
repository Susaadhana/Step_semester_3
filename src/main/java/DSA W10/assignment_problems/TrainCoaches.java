import java.util.Scanner;

class Node {
    String name;
    Node prev, next;

    Node(String name) {
        this.name = name;
    }
}

public class TrainCoaches {
    Node head = null, tail = null;

    void addLast(String name) {
        Node newNode = new Node(name);

        if (head == null) {
            head = tail = newNode;
            return;
        }

        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
    }

    void remove(String name) {
        Node temp = head;

        while (temp != null && !temp.name.equals(name)) {
            temp = temp.next;
        }

        if (temp == null) {
            return;
        }

        if (temp == head) {
            head = temp.next;
        }

        if (temp == tail) {
            tail = temp.prev;
        }

        if (temp.prev != null) {
            temp.prev.next = temp.next;
        }

        if (temp.next != null) {
            temp.next.prev = temp.prev;
        }
    }

    void printForward() {
        Node temp = head;
        System.out.print("Forward: ");

        while (temp != null) {
            System.out.print(temp.name);
            if (temp.next != null) {
                System.out.print(" <-> ");
            }
            temp = temp.next;
        }

        System.out.println();
    }

    void printBackward() {
        Node temp = tail;
        System.out.print("Backward: ");

        while (temp != null) {
            System.out.print(temp.name);
            if (temp.prev != null) {
                System.out.print(" <-> ");
            }
            temp = temp.prev;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main train = new Main();

        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            train.addLast(sc.nextLine());
        }

        String name = sc.nextLine();

        train.remove(name);
        train.printForward();
        train.printBackward();

        sc.close();
    }
}
