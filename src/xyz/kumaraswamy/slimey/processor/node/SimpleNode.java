package xyz.kumaraswamy.slimey.processor.node;

import java.util.WeakHashMap;

public class SimpleNode extends Node {

    private static final WeakHashMap<Object, SimpleNode> caches = new WeakHashMap<>();

    public static SimpleNode getInstance(Object value) {
        final SimpleNode cacheNode = caches.get(value);
        if (cacheNode == null) {
            final SimpleNode node = new SimpleNode(value);
            caches.put(value, node);
            return node;
        }
        return cacheNode;
    }

    private final Object number;

    public SimpleNode(Object value) {
        super(value);
        this.number = value;
    }

    public String toString() {
        return "[val=" + this.number + ']';
    }
}