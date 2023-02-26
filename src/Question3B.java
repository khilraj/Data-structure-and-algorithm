package src;

public class Question3B {
    public static void main(String[] args) {
        String a1 = "tt", p1 = "@";
        String a2 = "ta", p2 = "t";
        String a3 = "ta", p3 = "t#";

        boolean result1 = matchesPattern(a1, p1);
        boolean result2 = matchesPattern(a2, p2);
        boolean result3 = matchesPattern(a3, p3);

        System.out.println(result1); // expected output: true
        System.out.println(result2); // expected output: false
        System.out.println(result3); // expected output: true
    }

    public static boolean matchesPattern(String str, String pattern) {
        if (pattern.equals("@")) {
            // if pattern is "@", it matches entire sequence of characters
            return str.equals(pattern);
        }
        if (str.length() != pattern.length()) {
            // if string and pattern have different lengths, they can't match
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char s = str.charAt(i);
            char p = pattern.charAt(i);
            if (p == '@') {
                // if pattern has "@", it matches entire sequence of characters
                return str.equals(pattern.replace("@", str));
            } else if (p == '#') {
                // if pattern has "#", it matches any single character within string
                continue;
            } else if (s != p) {
                // if characters don't match, pattern doesn't match string
                return false;
            }
        }
        return true;
    }

}
