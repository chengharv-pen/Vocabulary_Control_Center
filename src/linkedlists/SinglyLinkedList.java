package linkedlists;

/**
 *
 * This class defines the SinglyLinkedList
 *
 * @param <E>
 */
public class SinglyLinkedList<E> {

    private class Node<E> {

        private Node<E> next;
        private E data;

        public Node(E e, Node<E> n) {
            this.data = e;
            this.next = n;
        }

        public Node(E e) {
            this(e, null);
        }

    } // end of Node inner class

    private Node<E> tail = null;
    private Node<E> head = null;
    private int size = 0;

    /**
     * This method checks if the SinglyLinkedList is empty
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * This method clears the SinglyLinkedList
     */
    public void clear() {
        head = null;
    }

    /**
     * This method prints the SinglyLinkedList by iterating through
     * each node
     */
    public void printList() {
        Node<E> current = head;
        while (current != null) {
            System.out.println("Node " + current.data);
            current = current.next;
        }
    }

    /**
     * This method takes in a parameter elem of type E, then adds
     * that parameter to the start of the SinglyLinkedList
     *
     * @param elem
     */
    public void addToStart(E elem) {
        head = new Node<E>(elem, head); // create the new node and insert it before first node
        if (tail == null) { // if the list was empty, head is also last node in the list
            tail = head;
        }
        size++; // increment size
    }

    /**
     * This method takes in a parameter elem of type E, then adds
     * that parameter to the end of the SinglyLinkedList
     *
     * @param elem
     */
    public void addToEnd(E elem) {
        Node<E> newNode = new Node<E>(elem); // create the new node
        if (this.isEmpty()) {
            addToStart(elem); // the new node becomes both head and tail node
        } else // the new node becomes the tail node
        {
            tail.next = newNode;
            tail = newNode;
            size = size + 1;
        }
    }

    /**
     * This method takes in a parameter data of type E, then searches
     * the SinglyLinkedList for a node's data that matches with data.
     * If there is a match, then the node's data becomes null.
     *
     * @param data
     */
    public void remove(E data) {
        Node<E> current = head;
        String word = (String) current.data;
        while (current != null) {
            if (word.compareTo((String) data) == 0)
                current.data = null;

            current = current.next;
        }
    }

    /**
     * This method iterates through the SinglyLinkedList to find words that
     * start with the parameter letter.
     * Then, it returns an array of String.
     *
     * @param letter
     * @return stringArray
     */
    public String[] findSameStartingLetter(String letter) {
        Node<E> current = head;
        String[] stringArray = new String[1000];
        int count = 0;

        while(current != null) {
            String word = (String) current.data;

            //I need to store it in the array rather than just printing it out..............
            if(word.startsWith(letter)) {
                stringArray[count] = word;
                count++;
            }

            current = current.next;
        }

        return stringArray;
    }

}
