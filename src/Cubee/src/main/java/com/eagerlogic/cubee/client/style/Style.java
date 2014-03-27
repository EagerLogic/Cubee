/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.style;

import com.eagerlogic.cubee.client.properties.Property;

/**
 *
 * @author dipacs
 */
public final class Style<T> {

    private StyleValue<T> value;
    private final boolean nullable;

    public Style(StyleValue<T> defaultValue, boolean nullable) {
        this.nullable = nullable;
        this.value = defaultValue;

        if (!nullable) {
            if (value != null) {
                if (value.getValue() == null) {
                    throw new NullPointerException("This style is not nullable, so you can't pass a StyleValue with null value in it.");
                }
            }
        }
    }

    public StyleValue<T> getValue() {
        return value;
    }

    public void setValue(StyleValue<T> value) {
        if (!nullable) {
            if (value != null) {
                if (value.getValue() == null) {
                    throw new NullPointerException("This style is not nullable, so you can't pass a StyleValue with null value in it.");
                }
            }
        }

        this.value = value;
    }

    public void apply(Property<T> property) {
        if (getValue() != null) {
            property.set(getValue().getValue());
        }
    }

}
