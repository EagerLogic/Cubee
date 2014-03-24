package com.eagerlogic.cubee.client.style.styles;

/**
 *
 * @author dipacs
 */
public final class ColorStop {

    private final double position;
    private final Color color;

    public ColorStop(double position, Color color) {
        this.position = position;
        this.color = color;
    }

    public double getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

}
