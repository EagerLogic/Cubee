/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.events.ParentChangedEventArgs;
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
        if (component != null) {
            if (component.getParent() != null) {
                throw new IllegalStateException("The component is already a child of a layout.");
            }
            component.setParent(parent);
            component.onParentChangedEvent().fireEvent(new ParentChangedEventArgs(parent, component));
        }

        children.add(component);
        parent.onChildAdded(component);
    }

    public void remove(AComponent component) {
        int idx = children.indexOf(component);
        if (idx < 0) {
            throw new IllegalArgumentException("The given component isn't a child of this layout.");
        }
        remove(idx);
    }

    /**
     * Removes the index'th element from this container. This method does NOT removes the childs' binds automatically.
     * 
     * @param index 
     * The index of the omponent which you want to remove.
     */
    public void remove(int index) {
        remove(index, false);
    }
    
    public void remove(int index, boolean destroy) {
        AComponent removedComponent = children.remove(index);
        if (removedComponent != null) {
            removedComponent.setParent(null);
            removedComponent.onParentChangedEvent().fireEvent(new ParentChangedEventArgs(null, removedComponent));
            if (destroy) {
                removedComponent.destroy();
            }
        }
        parent.onChildRemoved(removedComponent, index);
    }

    /**
     * Clears all the childs of this container. This method automatically removes the binds of the child components.
     */
    public void clear() {
//        clear(true);
        clear(false);
    }
    
    public void clear(boolean destroy) {
        for (AComponent component : children) {
            if (component != null) {
                component.setParent(null);
                component.onParentChangedEvent().fireEvent(new ParentChangedEventArgs(null, component));
                if (destroy) {
                    component.destroy();
                }
            }
        }
        children.clear();
        parent.onChildrenCleared();
    }

    public AComponent get(int index) {
        return children.get(index);
    }

    public int indexOf(AComponent component) {
        return children.indexOf(component);
    }

    public int size() {
        return children.size();
    }

    @Override
    public Iterator<AComponent> iterator() {
        // TODO create iterator which notifies parent when element is removed.
        return children.iterator();
    }
}
