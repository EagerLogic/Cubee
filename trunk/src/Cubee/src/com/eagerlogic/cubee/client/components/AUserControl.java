package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.properties.BackgroundProperty;
import com.eagerlogic.cubee.client.properties.IChangeListener;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.styles.Color;
import com.eagerlogic.cubee.client.styles.ColorBackground;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.DOM;

/**
 *
 * @author dipacs
 */
public abstract class AUserControl extends ALayout {

	private final IntegerProperty width = new IntegerProperty(null, true, false);
	private final IntegerProperty height = new IntegerProperty(null, true, false);
	private final BackgroundProperty background = new BackgroundProperty(new ColorBackground(Color.WHITE), true, false);

	public AUserControl() {
		super(DOM.createDiv());
		width.addChangeListener(new IChangeListener() {
			@Override
			public void onChanged(Object sender) {
				if (width.get() == null) {
					getElement().getStyle().clearWidth();
					getElement().getStyle().setOverflowX(Style.Overflow.VISIBLE);
				} else {
					getElement().getStyle().setWidth(width.get(), Style.Unit.PX);
					getElement().getStyle().setOverflowX(Style.Overflow.HIDDEN);
				}
				requestLayout();
			}
		});
		width.invalidate();
		height.addChangeListener(new IChangeListener() {
			@Override
			public void onChanged(Object sender) {
				if (height.get() == null) {
					getElement().getStyle().clearHeight();
					getElement().getStyle().setOverflowY(Style.Overflow.VISIBLE);
				} else {
					getElement().getStyle().setHeight(height.get(), Style.Unit.PX);
					getElement().getStyle().setOverflowY(Style.Overflow.HIDDEN);
				}
				requestLayout();
			}
		});
		height.invalidate();
		background.addChangeListener(new IChangeListener() {
			@Override
			public void onChanged(Object sender) {
				getElement().getStyle().clearBackgroundColor();
				getElement().getStyle().clearBackgroundImage();
				getElement().getStyle().setProperty("background", "none");
				if (background.get() != null) {
					background.get().apply(getElement());
				}
			}
		});
		background.invalidate();
	}

	protected IntegerProperty widthProperty() {
		return width;
	}

	protected IntegerProperty heightProperty() {
		return height;
	}

	protected BackgroundProperty backgroundProperty() {
		return background;
	}

	@Override
	protected final void onChildAdded(AComponent child) {
		if (child != null) {
			getElement().appendChild(child.getElement());
		}
	}

	@Override
	protected final void onChildRemoved(AComponent child, int index) {
		if (child != null) {
			getElement().removeChild(child.getElement());
		}
	}

	@Override
	protected final void onChildrenCleared() {
		Element root = getElement();
		Element e = getElement().getFirstChildElement();
		while (e != null) {
			root.removeChild(e);
			e = root.getFirstChildElement();
		}
	}

	@Override
	protected final void onLayout() {
		if (widthProperty().get() != null && heightProperty().get() != null) {
			setSize(widthProperty().get(), heightProperty().get());
		} else {
			int maxW = 0;
			int maxH = 0;
			for (AComponent component : getChildren()) {
				int cW = component.boundsWidthProperty().get() + component.boundsLeftProperty().get() + component.translateXProperty().get();
				int cH = component.boundsHeightProperty().get() + component.boundsTopProperty().get() + component.translateYProperty().get();

				if (cW > maxW) {
					maxW = cW;
				}

				if (cH > maxH) {
					maxH = cH;
				}
			}

			if (widthProperty().get() != null) {
				maxW = widthProperty().get();
			}

			if (heightProperty().get() != null) {
				maxH = heightProperty().get();
			}

			setSize(maxW, maxH);
		}
	}
}
