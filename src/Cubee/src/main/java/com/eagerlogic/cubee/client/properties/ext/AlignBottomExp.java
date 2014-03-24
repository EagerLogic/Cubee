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
public class AlignBottomExp extends AExpression<Integer> {
    
    private final AComponent parent;
    private final AComponent child;
    private final int translateY;
    private final IntegerProperty translateYProperty;

    public AlignBottomExp(AComponent parent, AComponent child) {
        this(parent, child, 0);
    }
    
    public AlignBottomExp(AComponent parent, AComponent child, int translateY) {
        this.parent = parent;
        this.child = child;
        this.translateY = translateY;
        this.translateYProperty = null;
        this.bind(parent.clientHeightProperty(), child.boundsHeightProperty());
    }
    
    public AlignBottomExp(AComponent parent, AComponent child, IntegerProperty translateYProperty) {
        this.parent = parent;
        this.child = child;
        this.translateY = 0;
        this.translateYProperty = translateYProperty;
        this.bind(parent.clientHeightProperty(), child.boundsHeightProperty(), translateYProperty);
    }

    @Override
    public Integer calculate() {
        int ty = translateY;
        if (translateYProperty != null) {
            if (translateYProperty.get() == null) {
                ty = 0;
            } else {
                ty = translateYProperty.get();
            }
        }
        return ((parent.clientHeightProperty().get() - child.boundsHeightProperty().get())) - ty;
    }
    
}
