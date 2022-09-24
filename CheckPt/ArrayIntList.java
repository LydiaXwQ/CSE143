// An ArrayIntList stores a list of integers

public class ArrayIntList {
    private int[] elementData;
    private int size = 0;

    // Doubles-up each element in this list
    public void doubleUp() {
        // TODO: Your code here
        for(int i = (size * 2) - 1; i >= 1; i -= 2 ) {
            elementData[i] = elementData[size - 1];
            size --;
            elementData[i - 1] = elementData[i];
        }
        size = size * 2;
        
       

    }

    public static void main(String[] args) {
        ArrayIntList list = new ArrayIntList();
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(7);
        System.out.println(list);
        list.doubleUp();
        System.out.println(list);
    }

    // Constructs an empty list
    public ArrayIntList() {
        elementData = new int[10];
        size = 0;
    }
	
	// Appends the given value to the end of the list
    public void add(int value) {
		elementData[size] = value;
        size += 1;
    }

    // pre : 0 <= index < size() (throws IndexOutOfBoundsException if not)
    // post: Removes value at the given index, shifting subsequent values left
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }
        for (int i = index; i < size - 1; i += 1) {
            elementData[i] = elementData[i + 1];
        }
        size -= 1;
    }

    // pre : 0 <= index < size() (throws IndexOutOfBoundsException if not)
    // post: Returns the integer at the given index in the list
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return elementData[index];
    }

    // Returns the current number of elements in the list
    public int size() {
        return size;
    }

    // Returns a comma-separated, bracketed version of the list
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            String result = "[" + elementData[0];
            for (int i = 1; i < size; i += 1) {
                result += ", " + elementData[i];
            }
            result += "]";
            return result;
        }
    }

    // Returns true if and only if o is an ArrayIntList with the same size and elements as this one
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof ArrayIntList)) {
            return false;
        } else {
            return this.toString().equals(o.toString());
        }
    }
}