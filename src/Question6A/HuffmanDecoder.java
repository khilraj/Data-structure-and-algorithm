package src.Question6A;

import java.util.Map;

public class HuffmanDecoder {
    public String decode(Map<Character, String> codes, String encoded) {
        StringBuilder sb = new StringBuilder();
        StringBuilder currentCode = new StringBuilder();
        for (int i = 0; i < encoded.length(); i++) {
            currentCode.append(encoded.charAt(i));
            for (Map.Entry<Character, String> entry : codes.entrySet()) {
                if (entry.getValue().equals(currentCode.toString())) {
                    sb.append(entry.getKey());
                    currentCode = new StringBuilder();
                    break;
                }
            }
        }
        return sb.toString();
    }

    private static class Node {
        private Character charVal;
        private Node left;
        private Node right;

        public Node(Character charVal, Node left, Node right) {
            this.charVal = charVal;
            this.left = left;
            this.right = right;
        }
    }
}
