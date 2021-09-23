package xyz.kumaraswamy.slimey.parse;

public class SimpleToken extends Token {
    public SimpleToken(Type type) {
        super(type, null);
    }

    @Override
    public String toString() {
        return "[" + getType() + "]";
    }
}
