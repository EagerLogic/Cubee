package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.utils.ARunOnce;
import com.google.gwt.dom.client.Style;

/**
 *
 * @author dipacs
 */
public final class CubeePanel extends AUserControl {
	
	private final ARunOnce layoutRunOnce = new ARunOnce() {

		@Override
		protected void onRun() {
			layout();
		}
	};

	public CubeePanel() {
		getElement().getStyle().setLeft(0, Style.Unit.PX);
		getElement().getStyle().setTop(0, Style.Unit.PX);
		getElement().getStyle().setRight(0, Style.Unit.PX);
		getElement().getStyle().setBottom(0, Style.Unit.PX);
		requestLayout();
	}

	@Override
	public void requestLayout() {
		layoutRunOnce.run();
	}

	@Override
	public LayoutChildren getChildren() {
		return super.getChildren();
	}

	@Override
	protected IntegerProperty widthProperty() {
		return super.widthProperty();
	}

	@Override
	protected IntegerProperty heightProperty() {
		return super.heightProperty();
	}

}
