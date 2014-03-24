package com.eagerlogic.cubee.client.properties;

import com.eagerlogic.cubee.client.style.styles.Color;

/**
 *
 * @author dipacs
 */
public final class ColorProperty extends Property<Color> {

    public ColorProperty(Color defaultValue, boolean nullable, boolean readonly) {
        super(defaultValue, nullable, readonly);
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
