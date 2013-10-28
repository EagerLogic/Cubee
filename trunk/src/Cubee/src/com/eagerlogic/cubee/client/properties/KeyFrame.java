package com.eagerlogic.cubee.client.properties;

import com.eagerlogic.cubee.client.properties.Property;

/**
 *
 * @author dipacs
 */
public final class KeyFrame {
	
	private final long time;
	private final Property<?> property;
	private final Object endValue;
	private final IInterpolator interpolator;
	private final Runnable keyFrameReachedListener;

	
	
	
	public <T> KeyFrame(long time, Property<T> property, T endValue) {
		this(time, property, endValue, Interpolators.LINEAR, null);
	}
	
	public <T> KeyFrame(long time, Property<T> property, T endValue, IInterpolator interpolator) {
		this(time, property, endValue, interpolator, null);
	}
	
	public <T> KeyFrame(long time, Property<T> property, T endValue, Runnable keyFrameReachedListener) {
		this(time, property, endValue, Interpolators.LINEAR, keyFrameReachedListener);
	}

	public <T> KeyFrame(long time, Property<T> property, T endValue, IInterpolator interpolator, Runnable keyFrameReachedListener) {
		if (time < 0) {
			throw new IllegalArgumentException("The time parameter can not be smaller than zero.");
		}
		this.time = time;
		
		if (property == null) {
			throw new NullPointerException("The property parameter can not be null.");
		}
		if (property.isReadonly()) {
			throw new IllegalArgumentException("Can't animate a read-only property.");
		}
		this.property = property;
		
		if (endValue == null && !property.isNullable()) {
			throw new IllegalArgumentException("Can't set null value to a non nullable property.");
		}
		this.endValue = endValue;
		
		this.interpolator = interpolator;
		this.keyFrameReachedListener = keyFrameReachedListener;
	}

	
	
	
	public long getTime() {
		return time;
	}

	public <T> Property<T> getProperty() {
		return (Property<T>)property;
	}

	public <T> T getEndValue() {
		return (T)endValue;
	}

	public IInterpolator getInterpolator() {
		return interpolator;
	}

	public Runnable getKeyFrameReachedListener() {
		return keyFrameReachedListener;
	}

}
