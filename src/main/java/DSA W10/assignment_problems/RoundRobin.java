import java.util.Scanner;

class Node {
    String name;
    Node next;

    Node(String name) {
        this.name = name;
    }

}

public class RoundRobin {
    Node tail = null;

    void addLast(String name) {
        Node newNode = new Node(name);

        if (tail == null) {
            tail = newNode;
            tail.next = tail;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
            tail = newNode;
        }
    }

    void printTurns(int turns) {
        Node current = tail.next;

        for (int i = 0; i < turns; i++) {
            System.out.print(current.name);

            if (i < turns - 1) {
                System.out.print(" ");
            }

            current = current.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main list = new Main();

        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            list.addLast(sc.nextLine());
        }

        int turns = sc.nextInt();

        list.printTurns(turns);
        sc.close();
    }
}
