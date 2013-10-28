package com.eagerlogic.cubee.client.properties;

/**
 *
 * @author dipacs
 */
public final class Interpolators {
	
	public static final IInterpolator LINEAR = new IInterpolator() {

		@Override
		public double interpolate(double value) {
			return value;
		}
	};
	public static final IInterpolator EASE_IN = new IInterpolator() {

		@Override
		public double interpolate(double value) {
			return value * value;
		}
	};
	public static final IInterpolator EASE_OUT = new IInterpolator() {

		@Override
		public double interpolate(double value) {
			return Math.sqrt(value);
		}
	};
	
	private Interpolators() {}

}
