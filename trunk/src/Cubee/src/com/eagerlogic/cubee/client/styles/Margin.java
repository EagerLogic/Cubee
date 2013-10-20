package com.eagerlogic.cubee.client.styles;

import com.google.gwt.dom.client.Element;

/**
 *
 * @author dipacs
 */
public final class Margin implements IStyle {
	
	private final int leftMargin;
	private final int topMargin;
	private final int rightMargin;
	private final int bottomMargin;

	public Margin(int value) {
		this.leftMargin = value;
		this.topMargin = value;
		this.rightMargin = value;
		this.bottomMargin = value;
	}

	public Margin(int leftMargin, int topMargin, int rightMargin, int bottomMargin) {
		this.leftMargin = leftMargin;
		this.topMargin = topMargin;
		this.rightMargin = rightMargin;
		this.bottomMargin = bottomMargin;
	}

	public int getLeftMargin() {
		return leftMargin;
	}

	public int getTopMargin() {
		return topMargin;
	}

	public int getRightMargin() {
		return rightMargin;
	}

	public int getBottomMargin() {
		return bottomMargin;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 23 * hash + this.leftMargin;
		hash = 23 * hash + this.topMargin;
		hash = 23 * hash + this.rightMargin;
		hash = 23 * hash + this.bottomMargin;
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Margin other = (Margin) obj;
		if (this.leftMargin != other.leftMargin) {
			return false;
		}
		if (this.topMargin != other.topMargin) {
			return false;
		}
		if (this.rightMargin != other.rightMargin) {
			return false;
		}
		if (this.bottomMargin != other.bottomMargin) {
			return false;
		}
		return true;
	}

	@Override
	public void apply(Element element) {
		// TODO implementálni
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
