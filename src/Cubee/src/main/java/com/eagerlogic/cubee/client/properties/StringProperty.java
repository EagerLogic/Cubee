package com.eagerlogic.cubee.client.properties;

import com.eagerlogic.cubee.shared.utils.AValidator;
import java.util.Random;

/**
 *
 * @author dipacs
 */
public class StringProperty extends Property<String> {

    public StringProperty() {
    }

    public StringProperty(String defaultValue) {
        super(defaultValue);
    }

    public StringProperty(String defaultValue, boolean nullable) {
        super(defaultValue, nullable);
    }

    public StringProperty(String defaultValue, AValidator<String> validator) {
        super(defaultValue, validator);
    }

    public StringProperty(String defaultValue, boolean nullable, boolean readonly) {
        super(defaultValue, nullable, readonly);
    }

    public StringProperty(String defaultValue, boolean nullable, boolean readonly, AValidator<String> validator) {
        super(defaultValue, nullable, readonly, validator);
    }

    @Override
    public String animate(double pos, String startValue, String endValue) {
        if (pos <= 0.0) {
            return startValue;
        }
        if (pos >= 1.0) {
            return endValue;
        }

        if (startValue == null) {
            startValue = "";
        }
        if (endValue == null) {
            endValue = "";
        }

        char[] sChars = startValue.toCharArray();
        char[] eChars = endValue.toCharArray();

        int randomCharsCount = (int) (eChars.length * (1.0 - pos));
        for (int i = eChars.length - 1; i >= eChars.length - randomCharsCount; i--) {
            eChars[i] = generateRandomChar();
        }

        return new String(eChars);
    }

    private char generateRandomChar() {
        int value = new Random().nextInt(48);
        boolean isBig = value > 23;
        if (isBig) {
            return (char) ('A' + value);
        } else {
            return (char) ('a' + value);
        }
    }

}
