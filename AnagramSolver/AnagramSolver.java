/**
 * Lydia Xue
 * 15/5/2022
 * CSE 143 Section AB
 * TA: Duncan Du
 * Take-home Assessment #4
 * The AnagramSolver class can generate anagrams according to the input text
 */

import java.util.*;

public class AnagramSolver {

    //field
    private Map<String, LetterInventory> letterCounts;
    private List<String> dict;
    
    /**
     * construct a new Anagram solver which use the given dictionary
     * to generate anagrams
     * @param dictionary pass a dictionary of words to the AnagramSolver class
     */
    public AnagramSolver(List<String> dictionary) {
        dict = dictionary;
        letterCounts = new HashMap<>();
        for(String word : dict) {
            letterCounts.put(word, new LetterInventory(word));
        }
    }

    /**
     * print all anagrams to the given text, which means combination of words
     * that have the same letters as the given text. All combinations of words
     * that includes at most max words will be generated. When max is 0, meaning
     * there's no limit for the words included.
     * @param text pass a String text to the print method
     * @param max pass the maximum number of words the combination can include
     * to the print method
     * pre: the maximum number of words can included must be positive or equal to
     *      zero
     *      (throws IllegalArgumentException if the max is negative)
     */
    public void print(String text, int max) {
        if(max < 0) {
            throw new IllegalArgumentException();
        }

        LetterInventory input = new LetterInventory(text);
        List<String> prunedDictionary = new ArrayList<String>();
    
        for(String word : dict) {
            if(input.subtract(letterCounts.get(word)) != null) {
                prunedDictionary.add(word);
            }
        }
        explore(prunedDictionary, input, new Stack<String>(), max);
    }

    /**
     * this private method recursively build up all possible anagram combinations
     * and print out the final combinations
     * @param prunedDictionary pass a pruned dictionary only contains words relevant to
     * the given string to the method
     * @param input pass a letter inventory of the inputted String to the method
     * @param anagram pass a stack used to store generated anagrams to the method
     * @param max pass the maximum number of words included to the method
     */
    private void explore(List<String> prunedDictionary, LetterInventory input,
                         Stack<String> anagram, int max) {

        if(input.isEmpty()) {
            System.out.println(anagram);
        } else if(max == 0 || anagram.size() < max) {
            for(String word : prunedDictionary) {
                LetterInventory currWord = letterCounts.get(word);
                LetterInventory subtractInventory = input.subtract(currWord);
                if(subtractInventory != null) {
                    anagram.push(word);
                    explore(prunedDictionary, subtractInventory, anagram, max);
                    anagram.pop();
                }
            }
        }
    }
}
