package com.eagerlogic.cubee.client.properties;

import com.eagerlogic.cubee.client.properties.Property;
import com.eagerlogic.cubee.client.style.styles.Margin;

/**
 *
 * @author dipacs
 */
public final class MarginProperty extends Property<Margin> {

    public MarginProperty(Margin defaultValue, boolean nullable, boolean readonly) {
        super(defaultValue, nullable, readonly);
    }

    @Override
    public Margin animate(double pos, Margin startValue, Margin endValue) {
        startValue = createNullMarginIfNeeded(startValue);
        endValue = createNullMarginIfNeeded(endValue);
        if (startValue.equals(endValue)) {
            return startValue;
        }

        return new Margin(mixComponent(
                startValue.getLeftMargin(), endValue.getLeftMargin(), pos),
                mixComponent(startValue.getTopMargin(), endValue.getTopMargin(), pos),
                mixComponent(startValue.getRightMargin(), endValue.getRightMargin(), pos),
                mixComponent(startValue.getBottomMargin(), endValue.getBottomMargin(), pos)
        );
    }

    private Margin createNullMarginIfNeeded(Margin margin) {
        if (margin == null) {
            return new Margin(0);
        }
        return margin;
    }

    private int mixComponent(int value1, int value2, double pos) {
        int res = (int) (value1 + ((value2 - value1) * pos));
        if (res < 0) {
            return 0;
        }
        return res;
    }

}
