/**
 * Lydia Xue
 * 26/4/2022
 * CSE 143 Section AB
 * TA: Duncan Du
 * Take-home Assessment #3
 * The Absurdle accepts the user's guessess for a lengthy word (word length at least one) and 
 * gives back the evaluation(pattern) for the guess. If the letter in the word guesses correctly 
 * and it's in the correct space as well, there will be a green tile in that space, 
 * if the gussing word has the correct letter but not in the correct place, there'll be
 * a yellow tile, Otherwise, there'll be grey in that place. 
 *
 */

import java.util.*;

public class AbsurdleManager {

    private Set<String> candidates;
    private int wordLength;

    /**
     * constructs a new game of Absurdle with all words from the dictionary have a 
     * length matches with the given length, eliminating duplicates.
     * @param dictionary pass a dictionary of words into the constructor
     * @param length pass a word length according to the given word into
     * the constructor
     * pre: the word length passed in must be greater than or equal to 1
     *      (throws IllegalArgumentException if not)
     * post: construct a new absurdle game contains words with a length corresponding
     *       to the given length
     */
    public AbsurdleManager(Collection<String> dictionary, int length){
        
        if(length < 1) {
            throw new IllegalArgumentException();
        }
        
        candidates = new TreeSet<>();
        wordLength = length;

        for(String word : dictionary){
            if(word.length() == wordLength){
                candidates.add(word);
            }
        }
    }

    /**
     * give the user access to the candidates(words with correct length) currently 
     * being considered by the manager
     * @return the candidates of words to the user
     */
    public Set<String> words(){
        return candidates;
    }

    /**
     * generate a pattern for the given word base on the given guess
     * @param word pass the target word to the patternFor method
     * @param guess pass the client guesses word into the method
     * @return back a pattern for the guessing word
     */
    public static String patternFor(String word, String guess){
        Map<Character, Integer> counts = new TreeMap<>();
        String[] pattern = new String[word.length()];

        for(int i = 0; i < word.length(); i++) {
            if(!counts.containsKey(word.charAt(i))) {
                counts.put(word.charAt(i), 0);
            }
            counts.put(word.charAt(i), counts.get(word.charAt(i)) + 1);
        }

        for(int i = 0; i < guess.length(); i++) {
            if(word.charAt(i) == guess.charAt(i)) {
                pattern[i] = "🟩";
                counts.put(word.charAt(i), counts.get(word.charAt(i)) - 1);
            } 
        }

        for(int i = 0; i < guess.length(); i++) {
            if(counts.getOrDefault(guess.charAt(i), 0) != 0 && 
                word.charAt(i) != guess.charAt(i)) {

                pattern[i] = "🟨";
                counts.put(guess.charAt(i), counts.get(guess.charAt(i)) - 1);
            }
        }

        String result = "";
        for(int i = 0; i < pattern.length; i++) {
            if(pattern[i] == null) {
                pattern[i] = "⬜";
            }
            result += pattern[i];
        }
        return result;
    }

    /**
     * record a guess and determine which set should be considered for the next round
     * return back the pattern associate with the largest bucket of 
     * target words
     * @param guess pass a user guess to the record method
     * @return back the pattern associate with the largest bucket of words
     * pre: the set of words must contain at least one word
     *      (throws IllegalStateException if not)
     * pre: the guess length must match the initial word length
     *      (throws IllegalArgumentException if not)
     * post: return the pattern associated with the most amounts of words
     */
    public String record(String guess) {
        
        if(candidates.isEmpty()) {
            throw new IllegalStateException();
        }

        if(guess.length() != wordLength) {
            throw new IllegalArgumentException();
        }

        Map<String, Set<String>> patternToWords = new TreeMap<>();

        for(String word : candidates) {
            String pattern = patternFor(word, guess);
            if(!patternToWords.containsKey(pattern)) {
                patternToWords.put(pattern, new TreeSet<>());
            }
            patternToWords.get(pattern).add(word);
        }

        int largestSize = 0;
        String maxPattern = "";

        for(String patternKey : patternToWords.keySet()) {

            if(patternToWords.get(patternKey).size() > largestSize) {
                largestSize = patternToWords.get(patternKey).size();
                candidates = patternToWords.get(patternKey);
                maxPattern = patternKey;
            } 
        }
        return maxPattern;
    }
}
