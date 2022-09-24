/**
 * Lydia Xue
 * 3/5/2022
 * CSE 143 Section AB
 * TA: Duncan Du
 * Take-home Assessment #4
 * The GrammarSolver class represents a sentence generator. The user can use it to generate
 * "times" of random sentences according to their input and a specified set of grammar rules.
 */

import java.util.*;

public class GrammarSolver {

    //field
    //The map stores non-terminals(key), and its matching rules (values)
    private SortedMap<String, String[]> grammar;

    /**
     * constructs a new grammar over a given BNF rules
     * @param rules pass a list of BNF rules into the GrammarSolver
     * pre: the rules passed in must be non-empty
     *      (throw IllegalArgumentException if not)
     * pre: the non-terminal in rules must be non-duplicate
     *      (throw IllegalArgumentException if there are
     *      duplicate non-terminals in the rules)
     */
    public GrammarSolver(List<String> rules) {

        if(rules.isEmpty()) {
            throw new IllegalArgumentException();
        }

        grammar =  new TreeMap<>();

        for(String rule : rules) {
            String[] pieces = rule.split("::=");
            String nonTerminal = pieces[0];
            if(grammar.containsKey(nonTerminal)) {
                throw new IllegalArgumentException();
            }
            String remaining = pieces[1];
            String[] seperateRules = remaining.split("\\|");
            grammar.put(nonTerminal, seperateRules);
        } 
    }

    /**
     * check if the given symbol is a non-terminal in the grammar.
     * @param symbol pass a symbol to the grammarContain method
     * @return true if the symbol is a non-terminal or false otherwise.
     */
    public boolean grammarContains(String symbol) {
        return grammar.containsKey(symbol);
    }

    /**
     * the client can get a sorted list of all non-terminals in the grammar in a format of 
     * comma seperated, with square brackets at both ends.
     * @return a string of sorted non-terminals in the grammar with commas and square brackets.
     */
    public String getSymbols() {
        return grammar.keySet().toString();
    }

    /**
     * generate given times of random sentences base on the given symbol
     * @param symbol pass a symbol to the generate method
     * @param times pass how many times they want to generate sentences
     * into the generate method
     * @return "times" of random sentences back to the client base on the
     * symbol they give.
     * pre: the given times must be greater than zero
     *      (throw IllegalArgumentException if not)
     * pre: the grammar must contain the given symbol
     *      (throw IllegalArgumentException if not)
     */
    public String[] generate(String symbol, int times) {
        if(times < 0 || !grammarContains(symbol)) {
            throw new IllegalArgumentException();
        }

        String[] result = new String[times];
        for(int i = 0; i < times; i++) {
            result[i] = getRandom(symbol);
        }
        return result;
    }

    /**
     * this helper method is used to generate one random sentence
     * with rule randomly choosing from a non-terminal 
     * @param input pass a symbol input into this method
     * @return back one random sentence based on the given symbol
     */
    private String getRandom(String input) {
        if(!grammarContains(input)) {
            return input;
        } else {
            //get symbol associated rules
            String[] rule = grammar.get(input);

            //randomly choose the rules to explore
            Random rand = new Random();
            int randIndex = rand.nextInt(rule.length);

            //randomly choose a part (left or right to | ) and split up
            String[] subRule = rule[randIndex].split("\\s+");

            //for each part of the segment
            // recurse and build up sentences
            String result = "";
            for(int i = 0; i < subRule.length; i++) {
                result += getRandom(subRule[i]) + " ";
            }

            return result.trim();
        }
    }
}

