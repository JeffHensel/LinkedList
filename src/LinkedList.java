/**
 * Simple non-thread safe implementation of a doubly LinkedList in java
 *
 * @author Jeffrey Hensel
 *
 * @param <T>
 */
public class LinkedList<T> {
    private LinkedNode<T> head = null;
    private LinkedNode<T> tail = null;
    private int listSize = 0;

    /**
     * Add a new node with value to the end of the list (uses addLast)
     * @param valValue of new node to add
     */
    public void add(T val) {
        addLast(val);
    }

    /**
     * Add a new node with value to the front of the list
     * @param valValue of new node to add
     */
    public void addFirst(T val) {
        LinkedNode<T> newNode = new LinkedNode<T>(val);
        if (head == null) {
            head = newNode;
            tail = head; // Tail is null if head is null
        }
        else {
          head.setPrev(newNode);
          newNode.setNext(head);
          head = newNode;
        }
        listSize++;
    }

    /**
     * Add a new node with value to the end of the list
     * @param val Value of new node to add
     */
    public void addLast(T val) {
        LinkedNode<T> newNode = new LinkedNode<T>(val);
        if (tail == null) {
            head = newNode;
            tail = head; // Tail is null if head is null
        }
        else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
        listSize++;
    }

    /**
     * Add an element at index
     * @param index Index of new element
     * @param val Value of new node to add
     */
    public void add(int index, T val) {
        int curIndex = index;
        if (index == 0) {
            addFirst(val);
            return;
        }
        if (index == listSize) {
            addLast(val);
            return;
        }
        
        LinkedNode<T> curNode = head;
        while (--curIndex > 0) {
            if (curNode.getNext() == null) {
                throw new IndexOutOfBoundsException("Invalid index for insert");
            }
            curNode = curNode.getNext();
        }
        LinkedNode<T> newNode = new LinkedNode<T>(val, curNode.getNext(), curNode);
        curNode.setNext(newNode);
        listSize++;
    }
    
    /**
     * Get the element at an index
     * @param index Index to get the element
     * @return T The element at the index
     */
    public T get(int index) {
        LinkedNode<T> curNode = getNodeAt(index);
        return curNode.getValue();
    }
    
    /**
     * Get the first element in the linked list
     * @return T The first element in the linked list
     */
    public T getFirst() {
        if (head == null) {
            throw new IndexOutOfBoundsException("Invalid index for insert");
        }
        return head.getValue();
    }
    
    /**
     * Get the last element in the linked list
     * @return
     */
    public T getLast() {
        if (tail == null) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return tail.getValue();
    }
  
    @Override
    public String toString() {
        if (head == null) {
            return "{}";
        }
        else {
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            LinkedNode<T> curNode = head;
            while (curNode != null) {
                sb.append(curNode.getValue().toString());
                if (curNode.getNext() != null) {
                    sb.append(", ");
                }
                curNode = curNode.getNext();
            }
            return sb.append("}").toString();
        }
    }
    
    /**
     * Is the linked list empty
     * @return Weather or not the list is empty
     */
    public boolean isEmpty() {
        return head == null;
    }
    
    /**
     * Remove all elements from the list
     */
    public void clear() {
        head = null;
        tail = null;
        listSize = 0;
    }

    /**
     * Check if the list contains the element
     * @return true if the list contains the element
     *         false if the list does NOT contain the element
     */
    public boolean contains(T element) {
        LinkedNode<T> curNode = head;
        while (curNode != null) {
            if (curNode.getValue().equals(element)) {
                return true;
            }
            curNode = curNode.getNext();
        }
        return false;
    }

    /**
     * Get the index of the element
     * @return The index of the element (-1 if not found)
     */
    public int indexOf(T element) {
        LinkedNode<T> curNode = head;
        int index = 0;
        while (curNode != null) {
            if (curNode.getValue().equals(element)) {
                return index;
            }
            index++;
            curNode = curNode.getNext();
        }
        return -1;
    }
    
    /**
     * Get the last index of an element
     * @return The last index of the element (-1 if not found)
     */
    public int lastIndexOf(T element) {
        LinkedNode<T> curNode = tail;
        int index = listSize - 1;
        while (curNode != null) {
            if (curNode.getValue().equals(element)) {
                return index;
            }
            index--;
            curNode = curNode.getNext();
        }
        return -1;
    }
    
    /**
     * Return the head element
     * @return Head
     */
    public T element() {
        return head.getValue();
    }
    
    /**
     * Return the head element
     * @return Head
     */
    public T peek() {
        return peekFirst();
    }
    
    /**
     * Return the head element
     * @return Head
     */
    public T peekFirst() {
        return head.getValue();
    }
    
    /**
     * Return the tail element
     * @return Tail
     */
    public T peekLast() {
        return tail.getValue();
    }
    
    /**
     * Return the size of the linked list
     * @return Number of elements in the list
     */
    public int size() {
        return listSize;
    }
    
    /**
     * Set the value of an element at the index and return the old value
     * @param index Index of element to be set
     * @param element New value at the index
     * @return Old value at the index
     */
    public T set(int index, T element) {
        // Since this is a true linked list and not based on an array you need to iterate
        LinkedNode<T> curNode = getNodeAt(index);

        T curElement = curNode.getValue();
        curNode.setValue(element);
        return curElement;
    }
    
    /**
     * Helper method which gets the node at a given index
     * @param index Index of node
     * @return The node at the index
     */
    private LinkedNode<T> getNodeAt(int index) {
        if (index >= listSize || index < 0) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        LinkedNode<T> curNode;
        // Minor enhancement to decide which way to iterate (head>tail or tail>head)
        if (index > 0 && listSize / index < 2) {
            // Start from tail
            curNode = tail;
            int curIndex = listSize - 1;
            while (curIndex != index && curIndex >= 0 && curNode != null) {
                curIndex--;
                curNode = curNode.getPrev();
            }
        }
        else {
            // Start from head
            curNode = head;
            int curIndex = 0;
            while (curIndex != index && curIndex < listSize && curNode != null) {
                curIndex++;
                curNode = curNode.getNext();
            }
        }

        if (curNode == null) {
            return null;
        }
        return curNode;
    }

    /**
     * Simple LinkedNode class
     * @author Jeffrey Hensel
     *
     * @param <T>
     */
    private class LinkedNode<T> {
        private T nodeVal;
        private LinkedNode<T> next = null;
        private LinkedNode<T> prev = null;

        /**
         * Create a new LinkedNode with next and prev
         * @param val Value of new node to add
         * @param next Next node in list
         * @param prev Previous node in list
         */
        LinkedNode(T val, LinkedNode<T> next, LinkedNode<T> prev) {
            setValue(val);
            this.next = next;
            this.prev = prev;
        }

        /**
         * Create a new LinkedNode with value
         * @param val Value of new node to add
         */
        LinkedNode(T val) {
            setValue(val);
        }

        /**
         * Get the next node in the list
         * @return LinkedNode<T> Next node from current node
         */
        public LinkedNode<T> getNext() {
            return next;
        }

        /**
         * Get the previous node in the list
         * @return LinkedNode<T> Previous node from current node
         */
        public LinkedNode<T> getPrev() {
            return prev;
        }

        /**
         * Set the next linked node
         * @param next Next linked node
         */
        public void setNext(LinkedNode<T> next) {
            this.next = next;
        }

        /**
         * Set the previous linked node
         * @param prev Previous linked node
         */
        public void setPrev(LinkedNode<T> prev) {
            this.prev = prev;
        }

        /**
         * Get the value of this node
         * @return T Value of node
         */
        public T getValue() {
            return nodeVal;
        }

        /**
         * Set the value of this node
         * @param nodeVal Value of node
         */
        public void setValue(T nodeVal) {
            this.nodeVal = nodeVal;
        }
    }
}