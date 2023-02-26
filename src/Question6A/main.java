package src.Question6A;
import java.util.Map;

public class main {
    public static void main(String[] args) {
        String text = "Hello, world!";
        HuffmanEncoder encoder = new HuffmanEncoder();
        Map<Character, String> codes = encoder.encode(text);
        String encoded = encode(text, codes);
        String decoded = decode(codes, encoded);
        System.out.println("Original text: " + text);
        System.out.println("Encoded text: " + encoded);
        System.out.println("Decoded text: " + decoded);
    }

    private static String encode(String text, Map<Character, String> codes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            sb.append(codes.get(c));
        }
        return sb.toString();
    }

    private static String decode(Map<Character, String> codes, String encoded) {
        HuffmanDecoder decoder = new HuffmanDecoder();
        return decoder.decode(codes, encoded);
    }
}


