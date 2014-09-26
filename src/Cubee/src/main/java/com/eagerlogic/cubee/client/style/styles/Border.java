package com.eagerlogic.cubee.client.style.styles;

import com.google.gwt.dom.client.Element;

/**
 *
 * @author dipacs
 */
public final class Border implements IStyle {

    private final int leftBorderSize;
    private final int topBorderSize;
    private final int rightBorderSize;
    private final int bottomBorderSize;
    private final Color leftBorderColor;
    private final Color topBorderColor;
    private final Color rightBorderColor;
    private final Color bottomBorderColor;
    private final int topLeftBorderRadius;
    private final int topRightBorderRadius;
    private final int bottomRightBorderRadius;
    private final int bottomLeftBorderRadius;

    public Border(int size, Color color, int radius) {
        if (color == null) {
            throw new NullPointerException("The color property can not be null.");
        }
        this.leftBorderSize = size;
        this.topBorderSize = size;
        this.rightBorderSize = size;
        this.bottomBorderSize = size;
        this.leftBorderColor = color;
        this.topBorderColor = color;
        this.rightBorderColor = color;
        this.bottomBorderColor = color;
        this.topLeftBorderRadius = radius;
        this.topRightBorderRadius = radius;
        this.bottomRightBorderRadius = radius;
        this.bottomLeftBorderRadius = radius;
    }

    public Border(int leftBorderSize, int topBorderSize, int rightBorderSize, int bottomBorderSize,
            Color leftBorderColor, Color topBorderColor, Color rightBorderColor, Color bottomBorderColor,
            int topLeftBorderRadius, int topRightBorderRadius, int bottomRightBorderRadius, int bottomLeftBorderRadius) {
        if (leftBorderColor == null) {
            leftBorderColor = Color.TRANSPARENT;
        }
        if (topBorderColor == null) {
        	topBorderColor = Color.TRANSPARENT;
        }
        if (rightBorderColor == null) {
        	rightBorderColor = Color.TRANSPARENT;
        }
        if (bottomBorderColor == null) {
        	bottomBorderColor = Color.TRANSPARENT;
        }
        this.leftBorderSize = leftBorderSize;
        this.topBorderSize = topBorderSize;
        this.rightBorderSize = rightBorderSize;
        this.bottomBorderSize = bottomBorderSize;
        this.leftBorderColor = leftBorderColor;
        this.topBorderColor = topBorderColor;
        this.rightBorderColor = rightBorderColor;
        this.bottomBorderColor = bottomBorderColor;
        this.topLeftBorderRadius = topLeftBorderRadius;
        this.topRightBorderRadius = topRightBorderRadius;
        this.bottomRightBorderRadius = bottomRightBorderRadius;
        this.bottomLeftBorderRadius = bottomLeftBorderRadius;
    }

    public int getLeftBorderSize() {
        return leftBorderSize;
    }

    public int getTopBorderSize() {
        return topBorderSize;
    }

    public int getRightBorderSize() {
        return rightBorderSize;
    }

    public int getBottomBorderSize() {
        return bottomBorderSize;
    }

    public Color getLeftBorderColor() {
        return leftBorderColor;
    }

    public Color getTopBorderColor() {
        return topBorderColor;
    }

    public Color getRightBorderColor() {
        return rightBorderColor;
    }

    public Color getBottomBorderColor() {
        return bottomBorderColor;
    }

    public int getTopLeftBorderRadius() {
        return topLeftBorderRadius;
    }

    public int getTopRightBorderRadius() {
        return topRightBorderRadius;
    }

    public int getBottomRightBorderRadius() {
        return bottomRightBorderRadius;
    }

    public int getBottomLeftBorderRadius() {
        return bottomLeftBorderRadius;
    }

    @Override
    public void apply(Element element) {
        element.getStyle().setProperty("borderStyle", "solid");
        element.getStyle().setProperty("borderLeftColor", this.getLeftBorderColor().toCSS());
        element.getStyle().setProperty("borderLeftWidth", this.getLeftBorderSize() + "px");
        element.getStyle().setProperty("borderTopColor", this.getTopBorderColor().toCSS());
        element.getStyle().setProperty("borderTopWidth", this.getTopBorderSize() + "px");
        element.getStyle().setProperty("borderRightColor", this.getRightBorderColor().toCSS());
        element.getStyle().setProperty("borderRightWidth", this.getRightBorderSize() + "px");
        element.getStyle().setProperty("borderBottomColor", this.getBottomBorderColor().toCSS());
        element.getStyle().setProperty("borderBottomWidth", this.getBottomBorderSize() + "px");

        element.getStyle().setProperty("borderTopLeftRadius", this.getTopLeftBorderRadius() + "px");
        element.getStyle().setProperty("borderTopRightRadius", this.getTopRightBorderRadius() + "px");
        element.getStyle().setProperty("borderBottomLeftRadius", this.getBottomLeftBorderRadius() + "px");
        element.getStyle().setProperty("borderBottomRightRadius", this.getBottomRightBorderRadius() + "px");
    }
}
