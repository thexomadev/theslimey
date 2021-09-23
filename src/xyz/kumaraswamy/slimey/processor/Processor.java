package xyz.kumaraswamy.slimey.processor;

import xyz.kumaraswamy.slimey.Data;
import xyz.kumaraswamy.slimey.parse.Token;
import xyz.kumaraswamy.slimey.processor.node.Node;
import xyz.kumaraswamy.slimey.processor.node.SimpleNode;

import java.util.ArrayList;

import static xyz.kumaraswamy.slimey.Data.LPAREN;
import static xyz.kumaraswamy.slimey.Data.RPAREN;
import static xyz.kumaraswamy.slimey.Slimey.precedenceOperators;

public class Processor {

    public static Node processNode(Object[] objects) {
        return process(optimize(objects));
    }

    private static Node process(Object[] objects) {
        final ArrayList<Object> tokensFormatted =
                processNodes(objects, false);

        if (tokensFormatted.size() != 0 && ((Token)
                tokensFormatted.get(0)).getType() == Token.Type.NODE) {
            // provide the least start values
            tokensFormatted.add(0, Data.emptyToken);
            tokensFormatted.add(1, Data.plusToken);
        }

        Object operator = null, lastValue = null;
        Node headNode = null;

        int pos;
        for (pos = 0; pos < tokensFormatted.size(); pos++) {
            Token token = (Token) tokensFormatted.get(pos);
            Object value = token.getValue();
            Token.Type type = token.getType();

            if (value == null) {
                value = type;
            }

            if (type == Token.Type.OPERATOR) {
                operator = value;
            } else {
                boolean isNode = type == Token.Type.NODE;

                if (isNode || operator != null && lastValue != null) {
                    Node leftNode = headNode == null ?
                            SimpleNode.getInstance(lastValue)
                            : headNode;
                    headNode = new Node(operator).setLeft(
                            leftNode
                    ).setRight(
                            isNode
                                    ? process((Object[]) token.getValue())
                                    : SimpleNode.getInstance(value)
                    );
                }
                lastValue = value;
            }
        }
        if (headNode == null && lastValue != null &&
                pos == 1) {
            return SimpleNode.getInstance(lastValue);
        }
        return headNode;
    }

    public static Object[] optimize(Object[] objects) {
        if (objects.length == 3) {
            return objects;
        }
        ArrayList<Object> tokens = processNodes(objects, true);

        int pos = 0;
        while (pos < tokens.size()) {
            final Token token = (Token) tokens.get(pos);
            final int next = pos + 2;

            if (next < tokens.size() && isValue(token)) {
                final Token operator = (Token) tokens.get(pos + 1),
                        nextValue = (Token) tokens.get(pos + 2);

                if (isValue(operator) || !isValue(nextValue)) {
                    throw new IllegalArgumentException();
                }
                if (isHighPrecedence(operator)) {
                    for (int i = 0; i < 3; i++) {
                        tokens.remove(pos);
                    }
                    tokens.add(pos, new Token(Token.Type.NODE,
                            new Object[] {
                                    LPAREN, token, operator, nextValue, RPAREN
                            })
                    );
                    continue;
                }
            }
            pos++;
        }
        return tokens.toArray();
    }

    private static ArrayList<Object> processNodes(Object[] objects, boolean recursive) {
        final ArrayList<Object> tokens = new ArrayList<>(),
                tempTokens = new ArrayList<>();

        boolean inside = false;
        int openBraces = 0;
        for (Object object : objects) {
            Token token = (Token) object;
            Object value = token.getValue();

            if (value instanceof Character) {
                char aChar = (char) value;
                if (aChar == '(' && openBraces++ == 0) {
                    inside = true;
                    continue;
                } else if (aChar == ')' && openBraces-- == 1) {
                    inside = false;
                    Object[] tempTokens1 = tempTokens.toArray();
                    if (recursive) {
                        tempTokens1 = optimize(tempTokens1);
                    }
                    tokens.add(new Token(Token.Type.NODE, tempTokens1));
                    tempTokens.clear();
                    continue;
                }
            }
            if (inside) {
                tempTokens.add(token);
            } else if (openBraces == 0) {
                tokens.add(token);
            }
        }
        return tokens;
    }

    private static boolean isHighPrecedence(Token token) {
        final char aChar = (char) token.getValue();
        for (char ch : precedenceOperators) {
            if (aChar == ch) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValue(Token token) {
        final Token.Type type = token.getType();
        return type == Token.Type.NUMBER
                || type == Token.Type.NODE;
    }
}
