package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.properties.IChangeListener;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.Property;
import com.eagerlogic.cubee.client.styles.Color;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.DOM;

/**
 *
 * @author dipacs
 */
public final class Triangle extends AComponent {
	
	private final Property<Color> color = new Property<Color>(Color.BLACK, false, false);
	private final IntegerProperty width = new IntegerProperty(20, false, false);
	private final IntegerProperty height = new IntegerProperty(20, false, false);

	public Triangle() {
		super(DOM.createDiv());
		color.addChangeListener(new IChangeListener() {

			@Override
			public void onChanged(Object sender) {
				getElement().getStyle().setProperty("borderBottomColor", color.get().toCSS());
			}
		});
		color.invalidate();
		width.addChangeListener(new IChangeListener() {

			@Override
			public void onChanged(Object sender) {
				getElement().getStyle().setWidth(0, Style.Unit.PX);
				getElement().getStyle().setHeight(0, Style.Unit.PX);
				getElement().getStyle().setProperty("borderLeft", (width.get()/2) + "px solid transparent");
				getElement().getStyle().setProperty("borderRight", (width.get()/2) + "px solid transparent");
				getElement().getStyle().setProperty("borderBottomWidth", height.get() + "px");
				getElement().getStyle().setProperty("borderBottomStyle", height.get() + "solid");
			}
		});
		width.invalidate();
		height.addChangeListener(new IChangeListener() {

			@Override
			public void onChanged(Object sender) {
				getElement().getStyle().setWidth(0, Style.Unit.PX);
				getElement().getStyle().setHeight(0, Style.Unit.PX);
				getElement().getStyle().setProperty("borderLeft", (width.get()/2) + "px solid transparent");
				getElement().getStyle().setProperty("borderRight", (width.get()/2) + "px solid transparent");
				getElement().getStyle().setProperty("borderBottomWidth", height.get() + "px");
				getElement().getStyle().setProperty("borderBottomStyle", "solid");
			}
		});
		height.invalidate();
	}

	public Property<Color> colorProperty() {
		return color;
	}

	public IntegerProperty widthProperty() {
		return width;
	}

	public IntegerProperty heightProperty() {
		return height;
	}

}
