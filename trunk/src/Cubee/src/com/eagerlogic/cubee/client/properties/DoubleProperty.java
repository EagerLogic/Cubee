/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.properties;

/**
 *
 * @author dipacs
 */
public final class DoubleProperty extends Property<Double> {

    public DoubleProperty(Double defaultValue, boolean nullable, boolean readonly) {
        super(defaultValue, nullable, readonly);
    }

    @Override
    public Double animate(double pos, Double startValue, Double endValue) {
        if (startValue == null) {
            startValue = 0.0;
        }
        
        if (endValue == null) {
            endValue = 0.0;
        }
        
        return (startValue + ((endValue - startValue) * pos));
    }
    
}
