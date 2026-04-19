package g22_Code;

// Represents a node in the Huffman tree
public class HuffmanNode implements Comparable<HuffmanNode> {
    char ch;              // Character stored in the node
    int freq;             // Frequency of the character
    HuffmanNode left;     // Left child (represents bit '0')
    HuffmanNode right;    // Right child (represents bit '1')

    // Constructor for leaf nodes
    public HuffmanNode(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
        this.left = null;
        this.right = null;
    }

    // Constructor for internal nodes (merge two nodes)
    public HuffmanNode(int freq, HuffmanNode left, HuffmanNode right) {
        this.ch = '\0';
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    // Sorting based on frequency for PriorityQueue
    @Override
    public int compareTo(HuffmanNode other) {
        return this.freq - other.freq;
    }

    // Check if node is a leaf (represents a character)
    public boolean isLeaf() {
        return left == null && right == null;
    }
}

