/**
 * Lydia Xue
 * 10/4/2022
 * CSE 143 Section AB
 * TA: Duncan Du
 * Take-home Assessment #1
 * The LetterInventory class keeps track of how many letters (from A to Z) are there in a given String.
 * And can also add and subtract number of counts with one inventory to another
 */

public class LetterInventory {
    public static final int ALPHABET = 26;
    private int[] elementData;
    private int size;
    
    /**
     * constructs an empty inventory
     */
    public LetterInventory() {
        this("");
    }
    
    /**
     * constructs an inventory with initial size, and increment the size
     * according to letter (case-insensitive) in the given String
     * @param data pass a String to the constructor
     */
    public LetterInventory(String data) {
        elementData = new int[ALPHABET];
        size = 0;

        for (int i = 0; i < data.length(); i++) {
            if(Character.isLetter(data.charAt(i))) {
                elementData[inventoryIndex(data.charAt(i))] ++;
                size ++;
            }
        }
    }

    /**
     * return the size of an inventory
     * @return the total letter counts in the inventory
     */
    public int size() {
        return size;
    }

    /**
     * check if an inventory is empty
     * @return true if the inventory is empty
     * otherwise, return false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * get the count of a letter in the inventory
     * @param letter pass a letter to the get method
     * @return back the counts of a letter in the inventory
     * pre: letter passed in must be alphabetic 
     *      (throws IllegalArgumentException if not)
     * post: return back the counts of a letter in the inventory
     */
    public int get(char letter) {
        if (!Character.isLetter(letter)) {
            throw new IllegalArgumentException();
        } 
        
        return elementData[inventoryIndex(letter)];
    }

    /**
     * gives a String representation of the inventory with all letters in lower case
     * surrounded by square brackets
     * @return back the String representation of the inventory
     */
    public String toString() {

        String result = "";

        for (int i = 0; i < ALPHABET; i++) {
            for (int j = 0; j < elementData[i]; j++) {
                result += (char) (i + 'a');
            }
        }
        return "[" + result + "]";
    }

    /**
     * set a new number of counts to a specified letter
     * @param letter pass a specific letter into the set method
     * @param value pass a value of counts into the set method
     * pre: the letter passed in must be alphabetic, and
     *      the counts passed in must be a non-negative value
     *      (throws IllegalArgumentException if either of them are not)
     * post: the counts of the letter will be reset to the value passed in
     *       the total counts will be also varied based on the new counts
     */
    public void set(char letter, int value) {
        if (!Character.isLetter(letter) || value < 0 ) {
            throw new IllegalArgumentException();
        }

        int prev = elementData[inventoryIndex(letter)];
        elementData[inventoryIndex(letter)] = value;
        size = size - prev + value;
    }

    /**
     * add the total counts for this inventory to the other
     * @param other pass another letter inventory object to this add method
     * @return a new letter inventory object contains the sum of 
     * this inventory and other inventory
     */
    public LetterInventory add(LetterInventory other) {
        LetterInventory sumInventory = new LetterInventory();
        for (int i = 0; i < ALPHABET; i ++) {
            sumInventory.set((char) (i + 'a'), this.elementData[i] + other.elementData[i]);
        }
        return sumInventory;
    }

    /**
     * subtract the total counts between this letter inventory object 
     * and other letter inventory object
     * @param other pass in another letter inventory object to be subtracted from this inventory
     * @return a new letter inventory object contains the difference of counts between
     * this inventory and other inventory object. If any of the difference is negative,
     * it will return null instead.
     */
    public LetterInventory subtract(LetterInventory other) {
        LetterInventory differenceInventory = new LetterInventory();
        int subtraction = 0;

        for (int i = 0; i < ALPHABET; i ++) {
            subtraction = this.elementData[i] - other.elementData[i];
            if (subtraction < 0) {
                return null;
            } else {
                differenceInventory.set((char) (i + 'a'), subtraction);
            }
        }
        return differenceInventory;
    }

    /**
     * convert a letter to the index it has in the inventory
     * @param letter pass a alphabetic letter into the inventoryIndex method
     * @return back the index the letter located in the inventory
     */
    private int inventoryIndex(char letter) {
        return Character.toLowerCase(letter) - 'a';
    } 
    
}