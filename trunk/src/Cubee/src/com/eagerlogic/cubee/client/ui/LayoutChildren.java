/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.ui;

import java.util.LinkedList;

/**
 *
 * @author dipacs
 */
public final class LayoutChildren {
    
    private LinkedList<AComponent> children = new LinkedList<AComponent>();
    private final ALayout parent;

    LayoutChildren(ALayout parent) {
        this.parent = parent;
    }
    
}
