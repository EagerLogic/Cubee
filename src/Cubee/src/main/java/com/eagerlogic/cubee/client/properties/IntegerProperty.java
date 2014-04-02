/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.properties;

import com.eagerlogic.cubee.shared.utils.AValidator;

/**
 *
 * @author dipacs
 */
public final class IntegerProperty extends Property<Integer> {

    public IntegerProperty() {
    }

    public IntegerProperty(Integer defaultValue) {
        super(defaultValue);
    }

    public IntegerProperty(Integer defaultValue, boolean nullable) {
        super(defaultValue, nullable);
    }

    public IntegerProperty(Integer defaultValue, AValidator<Integer> validator) {
        super(defaultValue, validator);
    }

    public IntegerProperty(Integer defaultValue, boolean nullable, boolean readonly) {
        super(defaultValue, nullable, readonly);
    }

    public IntegerProperty(Integer defaultValue, boolean nullable, boolean readonly, AValidator<Integer> validator) {
        super(defaultValue, nullable, readonly, validator);
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
