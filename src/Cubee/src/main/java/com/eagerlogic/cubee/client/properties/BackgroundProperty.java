package com.eagerlogic.cubee.client.properties;

import com.eagerlogic.cubee.client.style.styles.ABackground;
import com.eagerlogic.cubee.client.style.styles.Color;
import com.eagerlogic.cubee.client.style.styles.ColorBackground;
import com.eagerlogic.cubee.shared.utils.AValidator;

/**
 *
 * @author dipacs
 */
public class BackgroundProperty extends Property<ABackground> {

    public BackgroundProperty() {
    }

    public BackgroundProperty(ABackground defaultValue) {
        super(defaultValue);
    }

    public BackgroundProperty(ABackground defaultValue, boolean nullable) {
        super(defaultValue, nullable);
    }

    public BackgroundProperty(ABackground defaultValue, AValidator<ABackground> validator) {
        super(defaultValue, validator);
    }

    public BackgroundProperty(ABackground defaultValue, boolean nullable, boolean readonly) {
        super(defaultValue, nullable, readonly);
    }

    public BackgroundProperty(ABackground defaultValue, boolean nullable, boolean readonly, AValidator<ABackground> validator) {
        super(defaultValue, nullable, readonly, validator);
    }

    @Override
    public ABackground animate(double pos, ABackground startValue, ABackground endValue) {
        if (startValue == null) {
            startValue = new ColorBackground(Color.TRANSPARENT);
        }
        if (endValue == null) {
            endValue = new ColorBackground(Color.TRANSPARENT);
        }

        if ((startValue instanceof ColorBackground) && (endValue instanceof ColorBackground)) {
            return new ColorBackground(Color.fadeColors(((ColorBackground) startValue).getColor(), ((ColorBackground) endValue).getColor(), pos));
        } else {
            // TODO implementálni
            return super.animate(pos, startValue, endValue);
        }
    }

}
