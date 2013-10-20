package com.eagerlogic.cubee.client.ui;

/**
 *
 * @author dipacs
 */
public final class Border {
	
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

}
