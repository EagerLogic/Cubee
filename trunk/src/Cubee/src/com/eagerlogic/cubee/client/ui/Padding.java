package com.eagerlogic.cubee.client.ui;

/**
 *
 * @author dipacs
 */
public final class Padding {
	
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

}
