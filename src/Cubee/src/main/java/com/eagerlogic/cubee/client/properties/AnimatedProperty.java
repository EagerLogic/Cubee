package com.eagerlogic.cubee.client.properties;

public class AnimatedProperty<T> {
	
	private final Timeline tlToTrue;
	private final Timeline tlToFalse;

	public AnimatedProperty(final Property<T> property, final IProperty<Boolean> condition, T trueValue, T falseValue, long duration) {
		super();
		
		if ((Boolean)condition.getObjectValue()) {
			property.set(trueValue);
		} else {
			property.set(falseValue);
		}
		
		tlToTrue = new Timeline(new KeyFrame(0l, property, falseValue), new KeyFrame(duration, property, trueValue));
		tlToFalse = new Timeline(new KeyFrame(0l, property, trueValue), new KeyFrame(duration, property, falseValue));
		
		condition.addChangeListener(new IChangeListener() {
			
			@Override
			public void onChanged(Object sender) {
				tlToTrue.stop();
				tlToFalse.stop();
				Boolean value = (Boolean) condition.getObjectValue();
				if (value == null || value == false) {
					tlToFalse.start();
				} else {
					tlToTrue.start();
				}
			}
		});
	}

}
