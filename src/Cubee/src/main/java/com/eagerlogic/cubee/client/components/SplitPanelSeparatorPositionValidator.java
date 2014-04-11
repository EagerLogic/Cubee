package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.shared.utils.AValidator;
import com.eagerlogic.cubee.shared.utils.ValidationException;

/**
 *
 * @author dipacs
 */
final class SplitPanelSeparatorPositionValidator extends AValidator<Double> {

    @Override
    public Double validate(Double value) throws ValidationException {
        if (value == null) {
            return 0.0;
        }
        if (value < 0.0) {
            return 0.0;
        }
        if (value > 1.0) {
            return 1.0;
        }
        return value;
    }

}
