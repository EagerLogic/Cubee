package com.eagerlogic.cubee.client.styles;

import com.google.gwt.dom.client.Element;

/**
 *
 * @author dipacs
 */
public enum ETextAlign implements IStyle {

    LEFT("left"),
    CENTER("center"),
    RIGHT("right"),
    JUSTIFY("justify");

    private final String cssValue;

    private ETextAlign(String cssValue) {
        this.cssValue = cssValue;
    }

    public String getCssValue() {
        return cssValue;
    }

    @Override
    public String toString() {
        return cssValue;
    }

    @Override
    public void apply(Element element) {
        element.getStyle().setProperty("textAlign", cssValue);
    }

}
