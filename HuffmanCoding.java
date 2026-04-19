package g22_Code;

import java.util.*;
import java.io.*;

// Implements Huffman Encoding and Decoding Algorithm
public class HuffmanCoding {

    private Map<Character, String> huffmanCodes = new HashMap<>(); // Stores generated codes
    private HuffmanNode root; // Root of Huffman tree

    // Counts frequency of each character in the text
    public Map<Character, Integer> calculateFrequency(String text) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        return freqMap;
    }

    // Builds Huffman Tree using a PriorityQueue (Greedy Approach)
    public void buildTree(Map<Character, Integer> freqMap) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();

        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {

            pq.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Merge nodes until a single root remains
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode newNode = new HuffmanNode(left.freq + right.freq, left, right);
            pq.add(newNode);
        }

        root = pq.poll();
        generateCodes(root, "");
    }

    // Recursively generate prefix-free Huffman codes
    private void generateCodes(HuffmanNode node, String code) {
        if (node == null) return;

        if (node.isLeaf()) {
            huffmanCodes.put(node.ch, code);
        }

        generateCodes(node.left, code + "0");
        generateCodes(node.right, code + "1");
    }

    // Encode the input text using generated Huffman codes
    public String encode(String text) {
        StringBuilder encoded = new StringBuilder();
        for (char c : text.toCharArray()) {
            encoded.append(huffmanCodes.get(c));
        }
        return encoded.toString();
    }

    // Decode encoded bit string back into original text
    public String decode(String encodedText) {
        StringBuilder decoded = new StringBuilder();
        HuffmanNode current = root;

        for (char bit : encodedText.toCharArray()) {
            current = (bit == '0') ? current.left : current.right;

            if (current.isLeaf()) {
                decoded.append(current.ch);
                current = root;
            }
        }
        return decoded.toString();
    }

    public Map<Character, String> getCodes() {
        return huffmanCodes;
    }
}
