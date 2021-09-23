package xyz.kumaraswamy.slimey.parse;

import xyz.kumaraswamy.slimey.Stack;

public class Token {
    private final Type type;
    private final Object value;

    public enum Type {
        NUMBER,
        OPERATOR,
        NODE
    }

    public Token(Type type, Object value) {
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        String string = value instanceof Stack ?
                null : value + "";
        String result =  "Token{type=" + type;
        return string == null ? result + "}" :
                result + ", value='" + string + "'}";
    }
}