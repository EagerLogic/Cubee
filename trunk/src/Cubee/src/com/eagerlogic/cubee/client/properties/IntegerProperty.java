/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.properties;

/**
 *
 * @author dipacs
 */
public final class IntegerProperty extends Property<Integer> {

    public IntegerProperty(Integer defaultValue, boolean nullable, boolean readonly) {
        super(defaultValue, nullable, readonly);
    }

    @Override
    public Integer animate(double pos, Integer startValue, Integer endValue) {
        if (startValue == null) {
            startValue = 0;
        }

        if (endValue == null) {
            endValue = 0;
        }

        return (int) (startValue + ((endValue - startValue) * pos));
    }

}
