package com.eagerlogic.cubee.client.properties;

import com.eagerlogic.cubee.client.style.styles.Padding;
import com.eagerlogic.cubee.shared.utils.AValidator;

/**
 *
 * @author dipacs
 */
public final class PaddingProperty extends Property<Padding> {

    public PaddingProperty() {
    }

    public PaddingProperty(Padding defaultValue) {
        super(defaultValue);
    }

    public PaddingProperty(Padding defaultValue, boolean nullable) {
        super(defaultValue, nullable);
    }

    public PaddingProperty(Padding defaultValue, AValidator<Padding> validator) {
        super(defaultValue, validator);
    }

    public PaddingProperty(Padding defaultValue, boolean nullable, boolean readonly) {
        super(defaultValue, nullable, readonly);
    }

    public PaddingProperty(Padding defaultValue, boolean nullable, boolean readonly, AValidator<Padding> validator) {
        super(defaultValue, nullable, readonly, validator);
    }

	// TODO implement fade
}
