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
public final class DoubleProperty extends Property<Double> {

    public DoubleProperty() {
    }

    public DoubleProperty(Double defaultValue) {
        super(defaultValue);
    }

    public DoubleProperty(Double defaultValue, boolean nullable) {
        super(defaultValue, nullable);
    }

    public DoubleProperty(Double defaultValue, AValidator<Double> validator) {
        super(defaultValue, validator);
    }

    public DoubleProperty(Double defaultValue, boolean nullable, boolean readonly) {
        super(defaultValue, nullable, readonly);
    }

    public DoubleProperty(Double defaultValue, boolean nullable, boolean readonly, AValidator<Double> validator) {
        super(defaultValue, nullable, readonly, validator);
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
