/**
 * Lydia Xue
 * 25/5/2022
 * CSE 143 Section AB
 * TA: Duncan Du
 * Take-home Assessment #5
 * The QuestionGame class represents a guessing game that allows the client
 * to think about an object and let the computer make guesses by asking a series
 * of yes/no questions. If the guess is correct, computer wins, otherwise, the client
 * wins.
 */

import java.util.*;
import java.io.*;

public class QuestionsGame {
    private QuestionNode overallRoot;
    private Scanner console;

    /**
     * construct a new QuestionGame and is initialized
     * with the String "computer"
     */
    public QuestionsGame() {
        overallRoot = new QuestionNode("computer");
        console = new Scanner(System.in);
    }

    /**
     * store and write out the current questions and answers to an
     * output file. Then the client can play another game with this
     * set of questions. The file is in standard format with first line
     * indicate the type (Question or Answer), and the second line has the
     * content corresponding to the type.
     * @param output pass a output file name want to be printed on to the method
     */
    public void write(PrintStream output) {
        write(output, overallRoot);
    }

    /**
     * This helper method help the write method print out the type and the content
     * of either a question or an answer to the output file in a standard format.
     * @param output pass the output file that the user want to be printed on to this method.
     * @param root pass in a root to be printed out or traversed from.
     */
    private void write(PrintStream output, QuestionNode root) {
        // when at leaf node, directly print it out
        if(root.left == null && root.right == null) {
            output.println("A:");
            output.println(root.data);
            // print out myself, then explore left and right
        } else {
            output.println("Q:");
            output.println(root.data);
            write(output, root.left);
            write(output, root.right);
        }
    }

    /**
     * replace the current set of questions and answers by reading another one
     * from a file.
     * @param input pass in a file name that want to be scanned to replace the current one.
     */
    public void read(Scanner input) {
        overallRoot = readHelper(input);
    }

    /**
     * This helper method help the read method replace the content of the current file with
     * new set of questions and answers from a new file.
     * @param input pass in an input file name that want to be used to replace the current one
     * @return return the node updated with the information in file.
     */
    private QuestionNode readHelper(Scanner input) {
        // base case:
            // takes in type and data
        String type = input.nextLine();
        String data = input.nextLine();
        //create a new node with the data
        QuestionNode root = new QuestionNode(data);
        if(type.equals("Q:")) {
            root.left = readHelper(input);
            root.right = readHelper(input);
        }
        return root;
    }

    /**
     * play one complete game with the user using the current set of questions
     * and answers. If the computer guess the correct answer, it will report a message
     * and say so. Otherwise, it will ask a series of questions to the user and update
     * its knowledge base accordingly for future games.
     */
    public void askQuestions() {
        overallRoot = askQuestions(overallRoot);
    }

    /**
     * this helper method helps the askQuestions method print out the winning message if
     * the computer wins, or prompts up the series of questions if it guesses incorrect.
     * Also, it will update its knowledge base according to the user's responds.
     * @param root pass in a root that need to be printed out or modified to this method.
     * @return the root that already be modified.
     */
    private QuestionNode askQuestions(QuestionNode root) {
        //we at a leave node
        if(root.left == null && root.right == null) {
            if(yesTo("Would your object happen to be " + root.data + "?")) {
                System.out.println("Great, I got it right!");
            } else {
                System.out.print("What is the name of your object? ");
                //car
                QuestionNode answer = new QuestionNode(console.nextLine());
                System.out.println("Please give me a yes/no question that");
                System.out.println("distinguishes between your object");
                System.out.print("and mine--> ");
                //prompt
                String question = console.nextLine();
                if(yesTo("And what is the answer for your object?")) {
                    root = new QuestionNode(question, answer , root);
                } else {
                    root = new QuestionNode(question, root, answer);
                }
            }
        } else {
            if(yesTo(root.data)) {
                root.left = askQuestions(root.left);
            } else {
                root.right = askQuestions(root.right);
            }
        }
        return root;
    }

    // Do not modify this method in any way
    // post: asks the user a question, forcing an answer of "y" or "n";
    //       returns true if the answer was yes, returns false otherwise
    private boolean yesTo(String prompt) {
        System.out.print(prompt + " (y/n)? ");
        String response = console.nextLine().trim().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Please answer y or n.");
            System.out.print(prompt + " (y/n)? ");
            response = console.nextLine().trim().toLowerCase();
        }
        return response.equals("y");
    }

    /**
     * the QuestionNode class represents a single node in a binary tree.
     */
    private static class QuestionNode {
        //field(left, right, data)
        public String data;
        public QuestionNode left;
        public QuestionNode right;

        /**
         * construct a leaf node with the given data
         * @param data pass in the data wanted to be stored in the leaf node to this method.
         */
        public QuestionNode(String data) {
            this(data, null, null);
        }

        /**
         * Constructs a leaf or branch node with given data and links
         * @param data pass in the data wanted to be stored in the leaf node to this method.
         * @param left pass in a reference of a left node to this method.
         * @param right pass in a reference of a right node to this method.
         */
        public QuestionNode(String data, QuestionNode left, QuestionNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
}

