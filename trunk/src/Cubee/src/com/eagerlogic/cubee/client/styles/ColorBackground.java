package com.eagerlogic.cubee.client.styles;

import com.google.gwt.dom.client.Element;

/**
 *
 * @author dipacs
 */
public class ColorBackground extends ABackGround {
	
	private final Color color;

	public ColorBackground(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public void apply(Element element) {
		if (color == null) {
			element.getStyle().clearBackgroundColor();
		} else {
			element.getStyle().setBackgroundColor(color.toCSS());
		}
	}

}
