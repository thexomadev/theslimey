package xyz.kumaraswamy.slimey.lex;

import xyz.kumaraswamy.slimey.Stack;

import static java.lang.String.valueOf;
import static xyz.kumaraswamy.slimey.help.Help.isNumber;

public class Lex {
    public static Stack lex(final String text) {
        char[] chars = text.toCharArray();
        final int len = chars.length;

        final Stack stack = new Stack(len);
        final StringBuilder builder = new StringBuilder(len / 2);

        for (final char aChar : chars) {
            if (aChar == ' ') {
                continue;
            }
            if (isNumber(aChar)) {
                builder.append(aChar);
            } else {
                push(stack, builder);
                stack.pushToken(valueOf(aChar));
            }
        }
        push(stack, builder);
        return stack;
    }

    private static void push(Stack stack, StringBuilder builder) {
        final String build = builder.toString();
        if (!build.isEmpty())
            stack.pushToken(build);
        builder.setLength(0);
    }
}
