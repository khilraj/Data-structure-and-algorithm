package src;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

    public class HuffmanEncoding_6A {
        public static void main(String[] args) {
            String input = "print!";
            HuffmanEncoding_6A.Node root = buildHuffmanTree(input);
            String encoded = encode(input, root);

            String decoded = decode(encoded, root);
            System.out.println("Original input: " + input);
            System.out.println("Encoded output: " + encoded);
            System.out.println("Decoded output: " + decoded);
        }

        // Node class for building the Huffman tree
        private static class Node implements Comparable<Node> {
            char ch;
            int frequency;
            Node left, right;

            Node(char ch, int frequency, Node left, Node right) {
                this.ch = ch;
                this.frequency = frequency;
                this.left = left;
                this.right = right;
            }

            public boolean isLeaf() {
                return left == null && right == null;
            }

            public int compareTo(Node other) {
                return Integer.compare(this.frequency, other.frequency);
            }
        }

        // Build the Huffman tree
        private static Node buildHuffmanTree(String input) {
            // Count the frequency of each character in the input
            Map<Character, Integer> frequencyMap = new HashMap<>();
            for (char ch : input.toCharArray()) {
                frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
            }

            // Build a priority queue of nodes, with the smallest frequency at the front
            PriorityQueue<Node> pq = new PriorityQueue<>();
            for (char ch : frequencyMap.keySet()) {
                pq.offer(new Node(ch, frequencyMap.get(ch), null, null));
            }

            // Combine the two nodes with the smallest frequency until only one node remains
            while (pq.size() > 1) {
                Node left = pq.poll();
                Node right = pq.poll();
                pq.offer(new Node('\0', left.frequency + right.frequency, left, right));
            }

            // The remaining node is the root of the Huffman tree
            return pq.poll();
        }

        // Encode the input using the Huffman tree
        private static String encode(String input, Node root) {
            Map<Character, String> encodingMap = buildEncodingMap(root);
            StringBuilder sb = new StringBuilder();
            for (char ch : input.toCharArray()) {
                sb.append(encodingMap.get(ch));
            }
            return sb.toString();
        }

        // Build a mapping of each character to its Huffman encoding
        private static Map<Character, String> buildEncodingMap(Node node) {
            Map<Character, String> encodingMap = new HashMap<>();
            buildEncodingMapHelper(node, "", encodingMap);
            return encodingMap;
        }

        private static void buildEncodingMapHelper(Node node, String encoding, Map<Character, String> encodingMap) {
            if (node.isLeaf()) {
                encodingMap.put(node.ch, encoding);
            } else {
                buildEncodingMapHelper(node.left, encoding + "0", encodingMap);
                buildEncodingMapHelper(node.right, encoding + "1", encodingMap);
            }
        }

        private static String decode(String input, Node root) {
            StringBuilder sb = new StringBuilder();
            Node curr = root;
            for (char bit : input.toCharArray()) {
                if (bit == '0') {
                    curr = curr.left;
                } else {
                    curr = curr.right;
                }
                if (curr.isLeaf()) {
                    sb.append(curr.ch);
                    curr = root;
                }
            }
            return sb.toString();
        }

}
