package com.eagerlogic.cubee.client.animation;

/**
 *
 * @author dipacs
 */
public class LinearInterpolator implements IInterpolator {
	
	private static LinearInterpolator instance;
	
	public static LinearInterpolator getInstance() {
		if (instance == null) {
			instance = new LinearInterpolator();
		}
		return instance;
	}

	private LinearInterpolator() {
	}

	@Override
	public double interpolate(double value) {
		return value;
	}

}
