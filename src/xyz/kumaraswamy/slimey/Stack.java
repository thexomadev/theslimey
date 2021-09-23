package xyz.kumaraswamy.slimey;

import xyz.kumaraswamy.slimey.parse.Marker;

import static java.lang.Math.min;
import static java.lang.System.arraycopy;

public class Stack {

    private Object[] objects;
    private int pos = 0;

    public Stack(int capacity) {
        objects = new Object[capacity];
    }

    public void pushToken(String element) {
        push(Marker.mark(element));
    }

    public void push(Object element) {
        if (pos == objects.length) {
            objects = resize(pos == 0 ? 1 : pos * 2);
        }
        objects[pos++] = element;
    }

    private Object[] resize(int to) {
        final Object[] copy = new Object[to];
        arraycopy(objects, 0, copy, 0, min(objects.length, to));
        return copy;
    }

    public Object[] getObjects() {
        return resize(pos);
    }
}
