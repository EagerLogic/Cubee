package com.eagerlogic.cubee.client.properties;

import com.eagerlogic.cubee.client.style.styles.Border;
import com.eagerlogic.cubee.shared.utils.AValidator;

/**
 *
 * @author dipacs
 */
public final class BorderProperty extends Property<Border> {

    public BorderProperty() {
    }

    public BorderProperty(Border defaultValue) {
        super(defaultValue);
    }

    public BorderProperty(Border defaultValue, boolean nullable) {
        super(defaultValue, nullable);
    }

    public BorderProperty(Border defaultValue, AValidator<Border> validator) {
        super(defaultValue, validator);
    }

    public BorderProperty(Border defaultValue, boolean nullable, boolean readonly) {
        super(defaultValue, nullable, readonly);
    }

    public BorderProperty(Border defaultValue, boolean nullable, boolean readonly, AValidator<Border> validator) {
        super(defaultValue, nullable, readonly, validator);
    }

	// TODO implement fade
}
