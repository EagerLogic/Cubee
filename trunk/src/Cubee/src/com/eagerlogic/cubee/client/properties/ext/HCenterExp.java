/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eagerlogic.cubee.client.properties.ext;

import com.eagerlogic.cubee.client.components.AComponent;
import com.eagerlogic.cubee.client.properties.AExpression;

/**
 *
 * @author dipacs
 */
public class HCenterExp extends AExpression<Integer> {
    
    private final AComponent parent;
    private final AComponent child;

    public HCenterExp(AComponent parent, AComponent child) {
        this.parent = parent;
        this.child = child;
        this.bind(parent.clientWidthProperty(), child.boundsWidthProperty());
    }

    @Override
    public Integer calculate() {
        return (parent.clientWidthProperty().get() - child.boundsWidthProperty().get()) / 2;
    }
    
}
