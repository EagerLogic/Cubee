package com.eagerlogic.cubee.client.styles;

/**
 *
 * @author dipacs
 */
public final class ColorStop {
	
	private final float position;
	private final Color color;

	public ColorStop(float position, Color color) {
		this.position = position;
		this.color = color;
	}

	public float getPosition() {
		return position;
	}

	public Color getColor() {
		return color;
	}

}
