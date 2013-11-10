package com.eagerlogic.cubee.client.styles;

import com.google.gwt.dom.client.Element;

/**
 *
 * @author dipacs
 */
public class ColorBackground extends ABackground {

	private final Color color;
	private String cache = null;

	public ColorBackground(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public void apply(Element element) {
		if (cache != null) {
			element.getStyle().setBackgroundColor(cache);
		} else {
			if (color == null) {
				element.getStyle().clearBackgroundColor();
			} else {
				cache = color.toCSS();
				element.getStyle().setBackgroundColor(cache);
			}
		}
	}
}
