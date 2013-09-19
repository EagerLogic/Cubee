/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.ui;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author dipacs
 */
public final class LayoutChildren implements Iterable<AComponent> {
    
    private final LinkedList<AComponent> children = new LinkedList<AComponent>();
    private final ALayout parent;

    LayoutChildren(ALayout parent) {
        this.parent = parent;
    }
    
    public void add(AComponent component) {
        if (component == null) {
            throw new NullPointerException("The component can not be null.");
        }
        
        if (component.getParent() != null || component.getCubeePanel() != null) {
            throw new IllegalStateException("The component is already a child of a layout.");
        }
        
        component.setParent(parent);
        children.add(component);
        parent.onChildrenChanged();
    }
    
    public void remove(AComponent component) {
        children.remove(component);
        parent.onChildrenChanged();
    }
    
    public void remove(int index) {
        children.remove(index);
        parent.onChildrenChanged();
    }
    
    public void get(int index) {
        children.get(index);
    }
    
    public int indexOf(AComponent component) {
        return children.indexOf(component);
    }
    
    public int size() {
        return children.size();
    }

    @Override
    public Iterator<AComponent> iterator() {
        return children.iterator();
    }
    
}
