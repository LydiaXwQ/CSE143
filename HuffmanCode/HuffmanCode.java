/**
 * Lydia Xue
 * 30/5/2022
 * CSE 143 Section AB
 * TA: Duncan Du
 * Take-home Assessment #6
 * The HuffmanCode class can compress and decompress a given text
 */

import java.util.*;
import java.io.*;

public class HuffmanCode {

    //field
    private HuffmanNode overallNode;

    /**
     * construct an new instance of HuffmanCode, connecting characters
     * with the corresponding frequencies.
     * @param frequencies pass a list of integer frequencies corresponding to the characters
     * to the constructor
     */
    public HuffmanCode(int[] frequencies) {
        Queue<HuffmanNode> nodes = new PriorityQueue<>();
        for(int i = 0; i < frequencies.length; i++) {
            if(frequencies[i] >= 1) {
                nodes.add(new HuffmanNode(i, frequencies[i]));
            }
        }
        while(nodes.size() > 1) {
            HuffmanNode first = nodes.remove();
            HuffmanNode second = nodes.remove();
            HuffmanNode sum = new HuffmanNode(-1, first.frequency + second.frequency, first, second);
            nodes.add(sum);
        }
        overallNode = nodes.remove();
    }

    /**
     * construct a new instance of HuffmanCode with the content from a (.code) file.
     * The file is not null and contains data encoded in the standard format.
     * @param input pass in a scanner to the constructor.
     */
    HuffmanCode(Scanner input) {
        while(input.hasNextLine()) {
            int asciiValue = Integer.parseInt(input.nextLine());
            String code = input.nextLine();
            overallNode = add(code, asciiValue, overallNode);
        }
    }

    /**
     * a helper method that helps reconstruct the huffman tree.
     * It firstly builds up new node base on the indication of 0s or 1s
     * (from the second line), and then fill in the asciiValue (first line)
     * when it runs out of space (when indication ends).
     * @param code pass in the binary code into the method
     * @param asciiValue pass in the asciiValue into the value
     * @param node pass in a current node need to be checked on into the method
     * @return back the node we constructed 
     */
    private HuffmanNode add(String code, int asciiValue, HuffmanNode node) {
        if(node == null) {
            node = new HuffmanNode('0', 0);
        }

        if(code.equals("")) {
            //what's the difference using .character and create a new node putting value in it?
            node.character = asciiValue;
        } else if(code.charAt(0) == '0') {
            node.left = add(code.substring(1), asciiValue, node.left);
        } else if(code.charAt(0) == '1'){
            node.right = add(code.substring(1), asciiValue, node.right);
        }
        return node;
    }

    /**
     * store the current Huffman codes to the given (.code) file in a standard format
     * (first line is asciiValue, second line is the path)
     * @param output pass in a file that want to be outputted to the method.
     */
    public void save(PrintStream output) {
        save(output, overallNode, "");
    }

    /**
     * this helper method helps save the current binary tree into a standard format in a
     * specified output file.
     * @param output pass in a output file to the method
     * @param node pass in the node that we are currently checking into the method.
     * @param path pass in the path (left is zero, right is 1) we can access to a character
     * into the method.
     */
    private void save(PrintStream output, HuffmanNode node, String path) {
        if(node.left == null && node.right == null) {
            output.println(node.character);
            output.println(path);
        } else {
            save(output, node.left, path + "0");
            save(output, node.right, path + "1");

        }
    }

    /**
     * translate the given input from bits to a human-redable characters in a given file.
     * the input contains a legal encoding of characters.
     * @param input pass in a scanner to the method.
     * @param output pass in the output file to the method.
     */
    public void translate(Scanner input, PrintStream output) {

        while(input.hasNext()) {
            translate(input, output, overallNode);
        }
    }

    /**
     * this helper method reads in the bit and write the corresponding character
     * to the given file
     * @param input pass in a scanner to the method
     * @param output pass in the output file to the method
     * @param node pass in the node that currently checking into the method
     */
    private void translate(Scanner input, PrintStream output, HuffmanNode node) {
        if(node.left == null && node.right == null) {
            output.write(node.character);
        } else {
            if(input.next().charAt(0) == '0') {
                translate(input, output, node.left);
            } else {
                translate(input, output, node.right);
            }
        }
    }

    /**
     * the HuffmanNode class represents a single node of a huffman binary tree.
     */
    private static class HuffmanNode implements Comparable<HuffmanNode> {
        public int character;
        public int frequency;
        public HuffmanNode left;
        public HuffmanNode right;

        /**
         * construct a leaf node with the given character value and frequency.
         * @param character pass in the asciiValue of a character to the constructor
         * @param frequency pass in the corresponding frequency to the constructor
         */
        public HuffmanNode(int character, int frequency) {
            this(character, frequency, null, null);
        }

        /**
         * Constructs a leaf or branch node with given character, frequency, left
         * and right reference node.
         * @param character pass in the asciiValue of a character to the constructor
         * @param frequencypass in the corresponding frequency to the constructor
         * @param left pass in a left node to the constructor
         * @param right pass in a right node to the constructor
         */
        public HuffmanNode(int character, int frequency, HuffmanNode left, HuffmanNode right) {
            this.character = character;
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }

        /**
         * compare two Huffman Nodes.
         * return -1 if this huffmanNode is smaller than the other node, 0 when there's a tie
         * and 1 if this is bigger than the other.
         * @param other another huffman node need to be compared.
         */
        public int compareTo(HuffmanNode other) {
            return this.frequency - other.frequency;
        }
    }
}