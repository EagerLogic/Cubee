/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.ui;

import com.eagerlogic.cubee.client.properties.Property;

/**
 *
 * @author dipacs
 */
public class PaintProperty extends Property<APaint> {

    public PaintProperty(APaint defaultValue, boolean nullable, boolean readonly) {
        super(defaultValue, nullable, readonly);
    }

    @Override
    public APaint animate(double pos, APaint startValue, APaint endValue) {
        // TODO implement paint animation
        return super.animate(pos, startValue, endValue); 
    }
    
    
    
}
