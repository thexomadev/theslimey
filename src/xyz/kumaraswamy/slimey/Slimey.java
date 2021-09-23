package xyz.kumaraswamy.slimey;

import xyz.kumaraswamy.slimey.lex.Lex;
import xyz.kumaraswamy.slimey.processor.Executor;
import xyz.kumaraswamy.slimey.processor.Processor;
import xyz.kumaraswamy.slimey.processor.node.Node;

public class Slimey {

    public static char[] operators = {'(', ')', '+', '-', '*', '/'};
    public static char[] precedenceOperators = {'*', '/'};

    public static double exec(final String text) {
        Node headNode = Processor.processNode(Lex.lex(text).getObjects());
        return Executor.process(headNode);
    }
}
