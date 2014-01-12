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
public class VCenterExp extends AExpression<Integer> {
    
    private final AComponent parent;
    private final AComponent child;

    public VCenterExp(AComponent parent, AComponent child) {
        this.parent = parent;
        this.child = child;
        this.bind(parent.clientHeightProperty(), child.boundsHeightProperty());
    }

    @Override
    public Integer calculate() {
        return (parent.clientHeightProperty().get() - child.boundsHeightProperty().get()) / 2;
    }
    
}
