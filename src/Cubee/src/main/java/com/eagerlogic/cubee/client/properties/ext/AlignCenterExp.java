/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eagerlogic.cubee.client.properties.ext;

import com.eagerlogic.cubee.client.components.AComponent;
import com.eagerlogic.cubee.client.properties.AExpression;
import com.eagerlogic.cubee.client.properties.IntegerProperty;

/**
 *
 * @author dipacs
 */
public class AlignCenterExp extends AExpression<Integer> {
    
    private final AComponent parent;
    private final AComponent child;
    private final int translateX;
    private final IntegerProperty translateXProperty;

    public AlignCenterExp(AComponent parent, AComponent child) {
        this(parent, child, 0);
    }
    
    public AlignCenterExp(AComponent parent, AComponent child, int translateX) {
        this.parent = parent;
        this.child = child;
        this.translateX = translateX;
        this.translateXProperty = null;
        this.bind(parent.clientWidthProperty(), child.boundsWidthProperty());
    }
    
    public AlignCenterExp(AComponent parent, AComponent child, IntegerProperty translateXProperty) {
        this.parent = parent;
        this.child = child;
        this.translateX = 0;
        this.translateXProperty = translateXProperty;
        this.bind(parent.clientWidthProperty(), child.boundsWidthProperty(), translateXProperty);
    }

    @Override
    public Integer calculate() {
        int tx = translateX;
        if (translateXProperty != null) {
            if (translateXProperty.get() == null) {
                tx = 0;
            } else {
                tx = translateXProperty.get();
            }
        }
        return ((parent.clientWidthProperty().get() - child.boundsWidthProperty().get()) / 2) + tx;
    }
    
}
