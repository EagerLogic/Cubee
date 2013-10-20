package com.eagerlogic.cubee.client.styles;

/**
 *
 * @author dipacs
 */
public enum EVAlign {
	
	TOP("top"),
	MIDDLE("middle"),
	BOTTOM("bottom");
	
	private final String cssValue;
	
	private EVAlign(String cssValue) {
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
