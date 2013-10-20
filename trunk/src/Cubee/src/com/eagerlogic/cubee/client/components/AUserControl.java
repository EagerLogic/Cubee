package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.properties.IChangeListener;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
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

	public AUserControl() {
		super(DOM.createDiv());
		width.addChangeListener(new IChangeListener() {
			@Override
			public void onChanged(Object sender) {
				if (width.get() == null) {
					getElement().getStyle().clearWidth();
					getElement().getStyle().setOverflowX(Style.Overflow.AUTO);
				} else {
					getElement().getStyle().setWidth(width.get(), Style.Unit.PX);
					getElement().getStyle().setOverflowX(Style.Overflow.HIDDEN);
				}
				requestLayout();
			}
		});
		height.addChangeListener(new IChangeListener() {
			@Override
			public void onChanged(Object sender) {
				if (height.get() == null) {
					getElement().getStyle().clearWidth();
					getElement().getStyle().setOverflowY(Style.Overflow.AUTO);
				} else {
					getElement().getStyle().setWidth(height.get(), Style.Unit.PX);
					getElement().getStyle().setOverflowY(Style.Overflow.HIDDEN);
				}
				requestLayout();
			}
		});
	}

	protected IntegerProperty getWidth() {
		return width;
	}

	protected IntegerProperty getHeight() {
		return height;
	}

	@Override
	protected final void onChildAdded(AComponent child) {
		if (child != null) {
			getElement().appendChild(child.getElement());
		}
	}

	@Override
	protected final void onChildRemoved(AComponent child) {
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
		// nothing to do here
	}
}
