package xyz.kumaraswamy.slimey.processor;

import xyz.kumaraswamy.slimey.processor.node.Node;

public class Executor {
    public static double process(Node node) {
        if (node == null) {
            throw new IllegalArgumentException();
        }

        Node head = node;
        int len = 0;
        do {
            head = head.getLeft();
            len++;
        } while (head != null && head.hasNextLeft());

        double result =
                (double) (head == null ?
                        node.getValue() :
                        head.getValue());

        for (int i = len - 1; i >= 0; i--) {
            int count = 0;

            Node lastLeftNode = node;
            while (count++ < i) {
                lastLeftNode = lastLeftNode.getLeft();
            }

            final Node rightNode = lastLeftNode.getRight();

            if (rightNode == null) {
                continue;
            }

            final Object object = rightNode.getValue();
            double right =
                    object instanceof Double
                            ? (double) object
                            : process(rightNode);

            switch ((char) lastLeftNode.getValue()) {
                case '+':
                   result = result + right;
                   break;
                case '-':
                    result = result - right;
                    break;
                case '*':
                    result = result * right;
                    break;
                case '/':
                    result = result / right;
                    break;
            }
        }
        return result;
    }
}
