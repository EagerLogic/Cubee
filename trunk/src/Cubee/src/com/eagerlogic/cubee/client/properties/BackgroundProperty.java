package com.eagerlogic.cubee.client.properties;

import com.eagerlogic.cubee.client.styles.ABackGround;

/**
 *
 * @author dipacs
 */
public class BackgroundProperty extends Property<ABackGround> {

	public BackgroundProperty(ABackGround defaultValue, boolean nullable, boolean readonly) {
		super(defaultValue, nullable, readonly);
	}

	@Override
	public ABackGround animate(double pos, ABackGround startValue, ABackGround endValue) {
		// TODO implementálni
		return super.animate(pos, startValue, endValue);
	}

}
