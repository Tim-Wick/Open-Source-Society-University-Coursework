// Class for a QuestionNode
public class QuestionNode {
    // Initializes data fields
    public String data;
    public QuestionNode yes;
    public QuestionNode no;
    public QuestionNode parent;

    // Constructor for a leaf node
    public QuestionNode(String data, QuestionNode parent) {
        this.data = data;
        this.parent = parent;
    }

    // Constructor for a branch node
    public QuestionNode(String data, QuestionNode yes, QuestionNode no) {
        this.data = data;
        this.yes = yes;
        this.no = no;
    }

}
