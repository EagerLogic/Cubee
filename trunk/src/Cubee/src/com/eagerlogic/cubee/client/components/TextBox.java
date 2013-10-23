package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.properties.BorderProperty;
import com.eagerlogic.cubee.client.properties.IChangeListener;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.PaddingProperty;
import com.eagerlogic.cubee.client.properties.StringProperty;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.EventListener;

/**
 *
 * @author dipacs
 */
public final class TextBox extends AComponent {
	
	private final IntegerProperty width = new IntegerProperty(null, true, false);
	private final IntegerProperty height = new IntegerProperty(null, true, false);
	private final StringProperty text = new StringProperty("", false, false);

	public TextBox() {
		super(DOM.createInputText());
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
					getElement().getStyle().clearHeight();
					getElement().getStyle().setOverflowY(Style.Overflow.AUTO);
				} else {
					getElement().getStyle().setHeight(height.get(), Style.Unit.PX);
					getElement().getStyle().setOverflowY(Style.Overflow.HIDDEN);
				}
				requestLayout();
			}
		});
		text.addChangeListener(new IChangeListener() {

			@Override
			public void onChanged(Object sender) {
				getElement().setAttribute("value", text.get());
			}
		});
		DOM.setEventListener((com.google.gwt.user.client.Element)getElement(), new EventListener() {

			@Override
			public void onBrowserEvent(com.google.gwt.user.client.Event event) {
				if (event.getTypeInt() == com.google.gwt.user.client.Event.ONCHANGE) {
					text.set(getElement().getAttribute("value"));
				}
			}
		});
	}

	public IntegerProperty getWidth() {
		return width;
	}

	public IntegerProperty getHeight() {
		return height;
	}

	@Override
	public PaddingProperty getPadding() {
		return super.getPadding();
	}

	@Override
	public BorderProperty getBorder() {
		return super.getBorder();
	}

}
