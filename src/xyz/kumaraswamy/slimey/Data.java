package xyz.kumaraswamy.slimey;

import xyz.kumaraswamy.slimey.parse.SimpleToken;
import xyz.kumaraswamy.slimey.parse.Token;

public class Data {
    public static final SimpleToken NODE = new SimpleToken(Token.Type.NODE);

    public static final Token LPAREN = new Token(Token.Type.OPERATOR, '(');
    public static final Token RPAREN = new Token(Token.Type.OPERATOR, ')');

    public static final Token emptyToken = new Token(Token.Type.NUMBER, 0D);
    public static final Token plusToken = new Token(Token.Type.OPERATOR, '+');
}
