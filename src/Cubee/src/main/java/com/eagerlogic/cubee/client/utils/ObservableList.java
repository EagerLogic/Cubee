/*
 * Copyright 2014 dipacs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.eagerlogic.cubee.client.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author dipacs
 */
public final class ObservableList<T> {
    
    private final List<IListChangeListener<T>> listeners = new LinkedList<IListChangeListener<T>>();
    
    private final List<T> list = new ArrayList<T>();
    
    public void addListener(IListChangeListener<T> listener) {
        if (hasListener(listener)) {
            throw new IllegalArgumentException("This listener already added.");
        }
        listeners.add(listener);
    }
    
    public void removeListener(IListChangeListener<T> listener) {
        listeners.remove(listener);
    }
    
    public boolean hasListener(IListChangeListener<T> listener) {
        if (listener == null) {
            throw new NullPointerException("The 'listener' parameter can not be null.");
        }
        return listeners.contains(listener);
    }
    
    private void fireItemAddedEvent(int index, T item) {
        for (IListChangeListener<T> listener : listeners) {
            listener.onItemAdded(index, item, this);
        }
    }
    
    private void fireItemRemovedEvent(int index, T item) {
        for (IListChangeListener<T> listener : listeners) {
            listener.onItemRemoved(index, item, this);
        }
    }
    
    private void fireItemUpdatedEvent(int index, T oldItem, T newItem) {
        for (IListChangeListener<T> listener : listeners) {
            listener.onItemUpdated(index, oldItem, newItem, this);
        }
    }
    
    private void fireClearedEvent() {
        for (IListChangeListener<T> listener : listeners) {
            listener.onCleared(this);
        }
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean contains(T o) {
        return list.contains(o);
    }

    public Iterator<T> iterator() {
        return list.iterator();
    }

    public Object[] toArray() {
        return list.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    public boolean add(T e) {
        boolean res = list.add(e);
        fireItemAddedEvent(list.size() - 1, e);
        return res;
    }

    public boolean remove(Object o) {
        int index = list.indexOf(o);
        if (index < 0) {
            return false;
        }
        list.remove(index);
        fireItemRemovedEvent(index, (T) o);
        return true;
    }

    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    public boolean addAll(Collection<? extends T> c) {
        int index = list.size();
        boolean res = list.addAll(c);
        for (T t : c) {
            fireItemAddedEvent(index, t);
            index++;
        }
        return res;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        int startIndex = index;
        boolean res = list.addAll(index, c);
        for (T t : c) {
            fireItemAddedEvent(startIndex, t);
            startIndex++;
        }
        return res;
    }

    public boolean removeAll(Collection<? extends T> c) {
        boolean res = false;
        for (T t : c) {
            res |= remove(c);
        }
        return res;
    }

    public void sort(Comparator<? super T> c) {
        list.sort(c);
        fireClearedEvent();
        for (int i = 0; i < list.size(); i++) {
            fireItemAddedEvent(i, list.get(i));
        }
    }

    public void clear() {
        list.clear();
        fireClearedEvent();
    }

    @Override
    public boolean equals(Object o) {
        return list.equals(o);
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

    public T get(int index) {
        return list.get(index);
    }

    public T set(int index, T element) {
        T res = list.set(index, element);
        fireItemUpdatedEvent(index, res, element);
        return res;
    }

    public void add(int index, T element) {
        list.add(index, element);
        fireItemAddedEvent(index, element);
    }

    public T remove(int index) {
        T res = list.remove(index);
        fireItemRemovedEvent(index, res);
        return res;
    }

    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    public ListIterator<T> listIterator() {
        return list.listIterator();
    }

    public ListIterator<T> listIterator(int index) {
        return list.listIterator(index);
    }
    
}
