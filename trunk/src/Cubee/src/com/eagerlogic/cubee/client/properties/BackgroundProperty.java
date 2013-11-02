package com.eagerlogic.cubee.client.properties;

import com.eagerlogic.cubee.client.styles.ABackground;

/**
 *
 * @author dipacs
 */
public class BackgroundProperty extends Property<ABackground> {

	public BackgroundProperty(ABackground defaultValue, boolean nullable, boolean readonly) {
		super(defaultValue, nullable, readonly);
	}

	@Override
	public ABackground animate(double pos, ABackground startValue, ABackground endValue) {
		// TODO implementálni
		return super.animate(pos, startValue, endValue);
	}

}
