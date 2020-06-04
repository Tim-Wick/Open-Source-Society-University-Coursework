public class HuffmanNode implements Comparable<HuffmanNode> {

    // Initializing data fields
    public int value;
    public int frequency;
    public HuffmanNode left;
    public HuffmanNode right;

    // Constructor for a leaf node
    public HuffmanNode(int value, int frequency) {
        this.value = value;
        this.frequency = frequency;
    }

    // Constructor for a branch node
    public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    // Comparable interface
    // If compared to node is greater, returns negative number, zero if equal, else a positive number
    public int compareTo(HuffmanNode h) {
        return this.frequency - h.frequency;
    }

}
