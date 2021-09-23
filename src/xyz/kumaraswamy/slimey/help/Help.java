package xyz.kumaraswamy.slimey.help;

import static xyz.kumaraswamy.slimey.Slimey.operators;

public class Help {
    public static boolean isNumber(char aChar) {
        return aChar == '.' || ('0' <= aChar && aChar <= '9');
    }

    public static boolean isNumber(String token) {
        if (token.length() == 1 && token.charAt(0) == '.') {
            return false;
        }
        for (char aChar : token.toCharArray()) {
            if (!isNumber(aChar)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isOperator(char check) {
        for (char aChar : operators) {
            if (aChar == check) {
                return true;
            }
        }
        return false;
    }
}