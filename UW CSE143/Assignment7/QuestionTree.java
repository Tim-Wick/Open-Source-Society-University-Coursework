import java.io.*;
import java.util.*;

public class QuestionTree {
    // Constructing class variables
    QuestionNode overallRoot = new QuestionNode("", null, null);
    Scanner console = new Scanner(System.in);

    // Constructor for the class, creates a starter tree with just a leaf
    public QuestionTree() {
        this.overallRoot.data = "computer";
    }

    // Public method for reading file into a tree
    // Takes  a scanner
    // Passes the scanner of the input file to the private method to build the tree
    public void read(Scanner input) {
        overallRoot = buildTree(input.nextLine(), input, null);
    }

    // Private method to build a binary tree of the questions text file
    // Takes a string that denotes the node type and a scanner
    // Returns a new QuestionNode
    private QuestionNode buildTree(String nodeType, Scanner input, QuestionNode parent) {
        // Base case is if the node is an answer, we just return a leaf question node with the answer
        if (nodeType.equals("A:")) {
            return new QuestionNode(input.nextLine(), parent);
        // Else the node is implicitly a question
        // Preserves the data, and builds yes and no children nodes with the next lines
        } else {
            QuestionNode newNode = new QuestionNode(input.nextLine(), null, null);
            QuestionNode yes = buildTree(input.nextLine(), input, newNode);
            QuestionNode no = buildTree(input.nextLine(), input, newNode);
            newNode.yes = yes;
            newNode.no = no;
            return newNode;
        }
    }

    // Public method for writing the tree to a file
    // Takes a printStream to output the tree to
    // Passes the overallRoot and the output to the private method
    public void write(PrintStream output) {
        privateWrite(overallRoot, output);
    }

    // Private method for writing the tree to a file
    // Takes the root of the tree and an output file to write to
    private void privateWrite(QuestionNode root, PrintStream output) {
        if (root.yes == null && root.no == null) {
            output.println("A:");
            output.println(root.data);
        } else {
            output.println("Q:");
            output.println(root.data);
            privateWrite(root.yes, output);
            privateWrite(root.no, output);
        }
    }

    // Public method for asking questions
    public void askQuestions() {
        // Gets the final answer of the tree with the private method
        QuestionNode answerNode = getQuestion(overallRoot);
        // Tests if it got the right answer, if yes it wins!
        if (yesTo("Would your object happen to be " + answerNode.data)) {
            System.out.println("Great, I got it right!");
        // Else...
        } else {
            // Prompt the user for new values
            System.out.print("What is the name of you object? ");
            String rightObject = console.nextLine();
            System.out.print("Please give me a yes/no question that distinguishes between you object and mine--> ");
            String newQuestion = console.nextLine();
            boolean newAnswer = yesTo("And what is the answer for your object?");
            // Constructs a new node to add to the tree
            QuestionNode newQuestionNode = new QuestionNode(newQuestion, null, null);
            QuestionNode oldAnswerNode = new QuestionNode(answerNode.data, newQuestionNode);
            QuestionNode newAnswerNode = new QuestionNode(rightObject, newQuestionNode);
            // If the answer node belongs in the yes tree of the newQuestionNode
            if (newAnswer) {
                newQuestionNode.yes = newAnswerNode;
                newQuestionNode.no = oldAnswerNode;
            // If the answer node belongs in the no tree of the newQuestionNode
            } else {
                newQuestionNode.yes = oldAnswerNode;
                newQuestionNode.no = newAnswerNode;
            }
            // Replace the no child of the parent with the new questionNode
            // Tests if parent is overallRoot and if it is, makes it the new QuestionNode the overall root
            if (answerNode == overallRoot) {
                overallRoot = newQuestionNode;
            } else if (answerNode.parent.yes == answerNode){
                answerNode.parent.yes = newQuestionNode;
            } else {
                answerNode.parent.no = newQuestionNode;
            }
        }
    }

    // Private method for getting to the final node
    private QuestionNode getQuestion(QuestionNode root) {
        // Base case is the node being an answer node
        if (root.yes ==  null && root.no == null) {
            return root;
        // Otherwise asks the user the yes/no question and recursively passes nodes down the line back to the function
        } else if (yesTo(root.data)){
            return getQuestion(root.yes);
        } else {
            return getQuestion(root.no);
        }
    }

    // post: asks the user a question, forcing an answer of "y"or "n";
    //       returns true if the answer was yes, returns false otherwise
    public boolean yesTo(String prompt) {
        System.out.print(prompt + " (y/n)? ");
        String response = console.nextLine().trim().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Please answer y or n.");
            System.out.print(prompt + " (y/n)? ");
            response = console.nextLine().trim().toLowerCase();
        }
        return response.equals("y");
    }

}
