public class List {

    // Points to the first node in this list
    private Node first;

    // The number of elements in this list
    private int size;

    /** Constructs an empty list. */
    public List() {
        first = null;
        size = 0;
    }

    /** Returns the number of elements in this list. */
    public int getSize() {
        return size;
    }

    /** Returns the CharData of the first element in this list. */
    public CharData getFirst() {
        if (first == null) {
            return null; 
        }
        return first.cp;
    }

    /** GIVE Adds a CharData object with the given character to the beginning of this list. */
    public void addFirst(char chr) {
        CharData newCharData = new CharData(chr);
        Node newNode = new Node(newCharData, first);
        first = newNode;
        size++;
    }

    /** GIVE Textual representation of this list. */
    public String toString() {
        if (size == 0) return "()";
        StringBuilder sb = new StringBuilder("(");
        Node current = first;
        while (current != null) {
            sb.append(current.cp.toString()).append(" ");
            current = current.next;
        }
        // מחיקת הרווח האחרון וסגירת סוגריים
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 1);
        }
        sb.append(")");
        return sb.toString();
    }

    /** Returns the index of the first CharData object in this list
     * that has the same chr value as the given char,
     * or -1 if there is no such object in this list. */
    public int indexOf(char chr) {
        Node current = first;
        int index = 0;
        while (current != null) {
            // הנחה: ל-CharData יש מתודה equals שמשווה תווים
            if (current.cp.equals(chr)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    /** If the given character exists in one of the CharData objects in this list,
     * increments its counter. Otherwise, adds a new CharData object with the
     * given chr to the beginning of this list. */
    public void update(char chr) {
        Node current = first;
        while (current != null) {
            if (current.cp.equals(chr)) {
                // אם נמצא - נגדיל את המונה (הנחה: יש שדה או מתודה count)
                current.cp.count++;
                return;
            }
            current = current.next;
        }
        // אם סיימנו את הלולאה ולא מצאנו - נוסיף להתחלה
        addFirst(chr);
    }

    /** GIVE If the given character exists in one of the CharData objects
     * in this list, removes this CharData object from the list and returns
     * true. Otherwise, returns false. */
    public boolean remove(char chr) {
        Node prev = null;
        Node current = first;

        while (current != null) {
            if (current.cp.equals(chr)) {
                // מקרה 1: האיבר למחיקה הוא הראשון
                if (prev == null) {
                    first = first.next;
                } 
                // מקרה 2: האיבר למחיקה הוא באמצע או בסוף
                else {
                    prev.next = current.next;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    /** Returns the CharData object at the specified index in this list. 
     * If the index is negative or is greater than the size of this list, 
     * throws an IndexOutOfBoundsException. */
    public CharData get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.cp;
    }

    /** Returns an array of CharData objects, containing all the CharData objects in this list. */
    public CharData[] toArray() {
        CharData[] arr = new CharData[size];
        Node current = first;
        int i = 0;
        while (current != null) {
            arr[i++]  = current.cp;
            current = current.next;
        }
        return arr;
    }

    /** Returns an iterator over the elements in this list, starting at the given index. */
    public ListIterator listIterator(int index) {
        // If the list is empty, there is nothing to iterate   
        if (size == 0) return null;
        // Gets the element in position index of this list
        Node current = first;
        int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        // Returns an iterator that starts in that element
        return new ListIterator(current);
    }
}