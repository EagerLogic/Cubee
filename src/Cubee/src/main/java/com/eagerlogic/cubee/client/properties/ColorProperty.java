package com.eagerlogic.cubee.client.properties;

import com.eagerlogic.cubee.client.style.styles.Color;
import com.eagerlogic.cubee.shared.utils.AValidator;

/**
 *
 * @author dipacs
 */
public final class ColorProperty extends Property<Color> {

    public ColorProperty() {
    }

    public ColorProperty(Color defaultValue) {
        super(defaultValue);
    }

    public ColorProperty(Color defaultValue, boolean nullable) {
        super(defaultValue, nullable);
    }

    public ColorProperty(Color defaultValue, AValidator<Color> validator) {
        super(defaultValue, validator);
    }

    public ColorProperty(Color defaultValue, boolean nullable, boolean readonly) {
        super(defaultValue, nullable, readonly);
    }

    public ColorProperty(Color defaultValue, boolean nullable, boolean readonly, AValidator<Color> validator) {
        super(defaultValue, nullable, readonly, validator);
    }

    @Override
    public Color animate(double pos, Color startValue, Color endValue) {
        if (startValue == null) {
            startValue = new Color(0);
        }

        if (endValue == null) {
            endValue = new Color(0);
        }

        return startValue.fade(endValue, pos);
    }

}
