/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.events;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author dipacs
 */
public final class Event<T extends EventArgs> {

    private final LinkedList<IEventListener<T>> listeners = new LinkedList<IEventListener<T>>();

    public Event() {
    }

    public void addListener(IEventListener<T> listener) {
        if (listener == null) {
            throw new NullPointerException("The listener parameter can not be null.");
        }

        if (hasListener(listener)) {
            return;
        }

        listeners.add(listener);
    }

    public void removeListener(IEventListener<T> listener) {
        Iterator<IEventListener<T>> iterator = listeners.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == listener) {
                iterator.remove();
                return;
            }
        }
    }

    public boolean hasListener(IEventListener<T> listener) {
        for (IEventListener<T> l : listeners) {
            if (l == listener) {
                return true;
            }
        }
        return false;
    }

    public void fireEvent(T args) {
        for (IEventListener<T> l : listeners) {
            l.onFired(args);
        }
    }

}
