/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.properties;

/**
 *
 * @author dipacs
 */
public final class FloatProperty extends Property<Float> {

    public FloatProperty(Float defaultValue, boolean nullable, boolean readonly) {
        super(defaultValue, nullable, readonly);
    }

    @Override
    public Float animate(double pos, Float startValue, Float endValue) {
        if (startValue == null) {
            startValue = 0.0f;
        }
        
        if (endValue == null) {
            endValue = 0.0f;
        }
        
        return (float)(startValue + ((endValue - startValue) * pos));
    }
    
}
