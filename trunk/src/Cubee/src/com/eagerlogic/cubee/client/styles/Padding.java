package com.eagerlogic.cubee.client.styles;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;

/**
 *
 * @author dipacs
 */
public final class Padding implements IStyle {

	private final int leftPadding;
	private final int topPadding;
	private final int rightPadding;
	private final int bottomPadding;

	public Padding(int value) {
		this.leftPadding = value;
		this.topPadding = value;
		this.rightPadding = value;
		this.bottomPadding = value;
	}

	public Padding(int leftPadding, int topPadding, int rightPadding, int bottomPadding) {
		this.leftPadding = leftPadding;
		this.topPadding = topPadding;
		this.rightPadding = rightPadding;
		this.bottomPadding = bottomPadding;
	}

	public int getLeftPadding() {
		return leftPadding;
	}

	public int getTopPadding() {
		return topPadding;
	}

	public int getRightPadding() {
		return rightPadding;
	}

	public int getBottomPadding() {
		return bottomPadding;
	}

	@Override
	public void apply(Element element) {
		element.getStyle().setPaddingLeft(this.getLeftPadding(), Style.Unit.PX);
		element.getStyle().setPaddingTop(this.getTopPadding(), Style.Unit.PX);
		element.getStyle().setPaddingRight(this.getRightPadding(), Style.Unit.PX);
		element.getStyle().setPaddingBottom(this.getBottomPadding(), Style.Unit.PX);
	}
}
