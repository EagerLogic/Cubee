package com.eagerlogic.cubee.client.ui;

import com.eagerlogic.cubee.client.properties.IChangeListener;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.Property;
import com.eagerlogic.cubee.client.properties.StringProperty;
import com.google.gwt.dom.client.Style;

/**
 *
 * @author dipacs
 */
public final class Label extends AUserControl {
	
	private final StringProperty text = new StringProperty("", false, false);
	private final Property<ETextOverflow> textOverFlow = new Property<ETextOverflow>(ETextOverflow.CLIP, false, false);
	// TODO Text align
	// TODO Vertical align
	
	public Label() {
		text.addChangeListener(new IChangeListener() {

			@Override
			public void onChanged(Object sender) {
				getElement().setInnerHTML(text.get());
				requestLayout();
			}
		});
		textOverFlow.addChangeListener(new IChangeListener() {

			@Override
			public void onChanged(Object sender) {
				if (textOverFlow.get() == ETextOverflow.CLIP) {
					getElement().getStyle().setTextOverflow(Style.TextOverflow.CLIP);
				} else if (textOverFlow.get() == ETextOverflow.WRAP) {
					getElement().getStyle().clearTextOverflow();
				} else {
					getElement().getStyle().setTextOverflow(Style.TextOverflow.ELLIPSIS);
				}
			}
		});
	}

	@Override
	public final IntegerProperty getWidth() {
		return super.getWidth();
	}

	@Override
	public final IntegerProperty getHeight() {
		return super.getHeight();
	}

	public final StringProperty getText() {
		return text;
	}

	public final Property<ETextOverflow> getTextOverFlow() {
		return textOverFlow;
	}
	

}
