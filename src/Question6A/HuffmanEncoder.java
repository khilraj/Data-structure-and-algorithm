package src.Question6A;

import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;

public class HuffmanEncoder {
    private Map<Character, String> codes;

    public HuffmanEncoder() {
        codes = new HashMap<>();
    }

    public Map<Character, String> encode(String text) {
        // Count frequency of each character in the text
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        // Create a priority queue of nodes, where each node represents a character
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            pq.offer(new Node(entry.getValue(), entry.getKey()));
        }

        // Build the Huffman tree
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            pq.offer(new Node(left.freq + right.freq, left, right));
        }

        // Generate binary codes for each character using the Huffman tree
        generateCodes(pq.peek(), "");

        return codes;
    }

    private void generateCodes(Node node, String code) {
        if (node.charVal != null) {
            codes.put(node.charVal, code);
            return;
        }
        generateCodes(node.left, code + "0");
        generateCodes(node.right, code + "1");
    }

    private static class Node implements Comparable<Node> {
        private Integer freq;
        private Character charVal;
        private Node left;
        private Node right;

        public Node(Integer freq, Character charVal) {
            this.freq = freq;
            this.charVal = charVal;
        }

        public Node(Integer freq, Node left, Node right) {
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Node other) {
            return this.freq - other.freq;
        }
    }
}
