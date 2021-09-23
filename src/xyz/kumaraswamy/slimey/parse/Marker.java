package xyz.kumaraswamy.slimey.parse;

import xyz.kumaraswamy.slimey.help.Help;
import xyz.kumaraswamy.slimey.parse.Token.Type;

public class Marker {
    public static Token mark(String token) {
        Type type;
        Object value;

        if (Help.isNumber(token)) {
            type = Type.NUMBER;
            value = parseNumber(token);
        } else if (isOperator(token)) {
            type = Type.OPERATOR;
            value = token.charAt(0);
        } else {
            throw new IllegalArgumentException("Unexpected value '" + token + "'");
        }
        return new Token(type, value);
    }

    private static double parseNumber(String token) {
        return Double.parseDouble(token);
    }

    private static boolean isOperator(String token) {
        if (token.length() != 1) {
            return false;
        }
        return Help.isOperator(token.charAt(0));
    }
}