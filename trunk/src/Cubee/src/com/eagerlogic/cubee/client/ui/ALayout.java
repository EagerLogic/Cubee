/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.ui;

/**
 *
 * @author dipacs
 */
public abstract class ALayout extends AComponent {
    
    private final LayoutChildren children = new LayoutChildren(this);
    
    public abstract void onChildrenChanged();
    
}
