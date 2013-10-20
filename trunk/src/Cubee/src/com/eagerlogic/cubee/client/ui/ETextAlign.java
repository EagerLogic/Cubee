package com.eagerlogic.cubee.client.ui;

/**
 *
 * @author dipacs
 */
public enum ETextAlign {
	
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

}
