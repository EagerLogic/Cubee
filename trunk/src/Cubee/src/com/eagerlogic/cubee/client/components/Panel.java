package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.properties.BackgroundProperty;
import com.eagerlogic.cubee.client.properties.BorderProperty;
import com.eagerlogic.cubee.client.styles.ECursor;
import com.eagerlogic.cubee.client.properties.PaddingProperty;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.Property;
import com.eagerlogic.cubee.client.styles.BoxShadow;

/**
 *
 * @author dipacs
 */
public final class Panel extends AUserControl {

	@Override
	public final IntegerProperty widthProperty() {
		return super.widthProperty();
	}

	@Override
	public final IntegerProperty heightProperty() {
		return super.heightProperty();
	}

	@Override
	public final PaddingProperty paddingProperty() {
		return super.paddingProperty();
	}

	@Override
	public final BorderProperty borderProperty() {
		return super.borderProperty();
	}

	@Override
	public LayoutChildren getChildren() {
		return super.getChildren();
	}

	@Override
	public BackgroundProperty backgroundProperty() {
		return super.backgroundProperty();
	}

	@Override
	public Property<BoxShadow> shadowProperty() {
		return super.shadowProperty();
	}

	@Override
	public IntegerProperty minWidthProperty() {
		return super.minWidthProperty();
	}

	@Override
	public IntegerProperty minHeightProperty() {
		return super.minHeightProperty();
	}

	@Override
	public IntegerProperty maxWidthProperty() {
		return super.maxWidthProperty();
	}

	@Override
	public IntegerProperty maxHeightProperty() {
		return super.maxHeightProperty();
	}

}
