package linkedlists;

import vocabpackage.Vocab;

/**
 *
 * This class defined the DoublyLinkedList
 *
 * @param <E>
 */
public class DoublyLinkedList<E> {

    private class Node<E> {

        private Node<E> next;
        private Node<E> prev;
        private E data;

        public Node(E data, Node<E> next, Node<E> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        public Node(E data) {
            this(data, null, null);
        }

    } // end of Node inner class

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * This method checks if the DoublyLinkedList is empty
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * This method clears the DoublyLinkedList
     */
    public void clear() {
        head = null;
        tail = null;
    }

    /**
     * This method prints the DoublyLinkedList by iterating through
     * each node, from head to tail
     */
    public void printListFoward() {
        Node curr = head;
        while (curr != null) {
            System.out.println(curr.data + " ");
            curr = curr.next;
        }
    }

    /**
     * This method checks the size of the DoublyLinkedList, then
     * returns an integer.
     *
     * @return count
     */
    public int size() {
        int count = 0;
        Node<E> curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }

    /**
     * This method takes in a parameter data of type E, then adds
     * that parameter to the start of the DoublyLinkedList by changing
     * the head reference around.
     *
     * @param data
     */
    public void addAtStart(E data) {
        Node<E> newHead = new Node<E>(data); // create a new node using data
        size++;
        if (this.isEmpty()) { // check if empty. if so, make newHead the only node.
            head = newHead;
            tail = newHead;
        } else { // add newHead to the beginning of ’this’ non-empty list
            newHead.next = head;
            head.prev = newHead;
            head = newHead; // finally, newHead officially becomes the new head node.
        }
    }

    /**
     * This method takes in a parameter data of type E, then adds
     * that parameter to the end of the DoublyLinkedList by changing
     * the tail reference around.
     *
     * @param data
     */
    public void addToEnd(E data) {
        if (isEmpty()) {
            addAtStart(data); // code reuse avoids introducing potential errors;
        } else {
            tail.next = new Node<E>(data, null, tail);
            tail = tail.next;
        }
        size++;
    }

    /**
     * Removes a specified parameter data with type E from the list.
     * returns true if the data was removed, false otherwise.
     *
     *
     * @param data
     * @return boolean
     */
    public boolean remove(E data) {
        if (isEmpty())
            return false; // not found

        Node<E> target = head; // Locate the target node to be removed
        while (target != null && !data.equals(target.data)) {
            target = target.next;
        }

        if (target == null)
            return false; // not found

        Node<E> before = target.prev;
        Node<E> after = target.next;

        // link the nodes before and after target
        if (before == null)
            head = after;
        else
            before.next = after;

        if (after == null)
            tail = before;
        else
            after.prev = before;

        return true;
    }

    /**
     * This method iterates through the DoublyLinkedList, from head to tail
     * then inserts the non-null entries into a static array of Vocab objects.
     *
     *
     * @return vocabArray
     */
    public Vocab[] returnVocabList() {
        Node<E> current = head;
        Vocab[] vocabArray = new Vocab[1000];
        int count = 0;

        while(current != null) {
            Vocab word = (Vocab) current.data;
            vocabArray[count] = word;
            count++;
            current = current.next;
        }

        return vocabArray;
    }

    /**
     * This method iterates through the DoublyLinkedList, then
     * it returns true if there is a match with the parameter data
     * of type E. Otherwise, it returns false.
     *
     * @param data
     * @return boolean
     */
    public boolean dataExists(E data) {
        Node<E> current = head;
        while(current != null) {
            if (current.data.equals(data))
                return true;
            else
                current = current.next;
        }
        return false;
    }


}
