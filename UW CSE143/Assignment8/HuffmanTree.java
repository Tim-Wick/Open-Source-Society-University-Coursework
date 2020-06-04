import java.util.*;
import java.io.*;

public class HuffmanTree {
    // Initializing class variables
    private Queue<HuffmanNode> huffmanQueue = new PriorityQueue<>();
    private HuffmanNode overallRoot;

    // Constructor for the class
    // Takes an array of of frequencies for letters
    HuffmanTree(int[] count) {
        // Creates branch nodes and puts them into the queue
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                huffmanQueue.add(new HuffmanNode(i, count[i]));
            }
        }
        // Adds in the eof
        huffmanQueue.add(new HuffmanNode(256, 1));
        // Calls the buildTree function with the built out queue
        while (huffmanQueue.size() > 1) {
            HuffmanNode firstNode = huffmanQueue.remove();
            HuffmanNode secondNode = huffmanQueue.remove();
            huffmanQueue.add(new HuffmanNode(firstNode.frequency + secondNode.frequency, firstNode, secondNode));
        }
    }

    // Constructor for reconstructing a tree from a file in standard format
    HuffmanTree(Scanner input) {
        // put values into queue with frequencies of 0
        while (input.hasNextLine()) {
            int value = Integer.parseInt(input.nextLine());
            String code = input.nextLine();
            overallRoot = treeBuilder(overallRoot, value, code);
        }

    }

    // Builder for the tree when given a scanner as input
    private HuffmanNode treeBuilder(HuffmanNode root, int value, String code) {
        if (root == null) {
            root = new HuffmanNode(value, 0);
        }
        if (code.length() == 1) {
            if (code.charAt(0) == '0') {
                root.left = new HuffmanNode(value, 0);
            } else {
                root.right = new HuffmanNode(value, 0);
            }
        } else {
            char codeValue = code.charAt(0);
            code = code.substring(1);
            if (codeValue == '0') {
                root.left = treeBuilder(root.left, value, code);
            } else {
                root.right = treeBuilder(root.right, value, code);
            }
        }
        return root;
    }

    // Writes the tree to an ouput file
    void write(PrintStream output) {
        if (!huffmanQueue.isEmpty()) {
            privateWrite(huffmanQueue.remove(), "", output);
        } else {
            privateWrite(overallRoot, "", output);
        }
    }

    private void privateWrite(HuffmanNode root, String code, PrintStream output) {
        // Base case is node not having any children making it a leaf node
        if (root.left == null && root.right == null) {
            output.println(root.value);
            output.println(code);
        // Recursive case is to pass the children to the function with the needed code additions
        // Checks against possible null pointer exceptions
        } else if (root.left != null && root.right != null){
            privateWrite(root.left, code + "0", output);
            privateWrite(root.right, code + "1", output);
        }
    }

    public void decode(BitInputStream input, PrintStream output, int eof) {
        decodePrivate(input, output, eof, overallRoot);
    }

    private void decodePrivate(BitInputStream input, PrintStream output, int eof, HuffmanNode root) {
        // Base case is a leaf node
        if (root.left == null && root.right == null) {
            if (root.value != eof) {
                output.print(root.value);
            }
        } else {
            if (input.readBit() == 0) {
                decodePrivate(input, output, eof, root.left);
            } else {
                decodePrivate(input, output, eof, root.right);
            }
        }
    }

}
