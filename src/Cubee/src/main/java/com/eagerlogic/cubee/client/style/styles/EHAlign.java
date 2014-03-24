package com.eagerlogic.cubee.client.style.styles;

/**
 *
 * @author dipacs
 */
public enum EHAlign {

    LEFT("left"),
    CENTER("center"),
    RIGHT("right");

    private final String cssValue;

    private EHAlign(String cssValue) {
        this.cssValue = cssValue;
    }

    public String getCssValue() {
        return cssValue;
    }

    @Override
    public String toString() {
        return cssValue;
    }

}
