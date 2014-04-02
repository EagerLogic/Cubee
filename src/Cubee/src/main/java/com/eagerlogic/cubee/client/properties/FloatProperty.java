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
public final class FloatProperty extends Property<Float> {

    public FloatProperty() {
    }

    public FloatProperty(Float defaultValue) {
        super(defaultValue);
    }

    public FloatProperty(Float defaultValue, boolean nullable) {
        super(defaultValue, nullable);
    }

    public FloatProperty(Float defaultValue, AValidator<Float> validator) {
        super(defaultValue, validator);
    }

    public FloatProperty(Float defaultValue, boolean nullable, boolean readonly) {
        super(defaultValue, nullable, readonly);
    }

    public FloatProperty(Float defaultValue, boolean nullable, boolean readonly, AValidator<Float> validator) {
        super(defaultValue, nullable, readonly, validator);
    }

    @Override
    public Float animate(double pos, Float startValue, Float endValue) {
        if (startValue == null) {
            startValue = 0.0f;
        }

        if (endValue == null) {
            endValue = 0.0f;
        }

        return (float) (startValue + ((endValue - startValue) * pos));
    }

}
