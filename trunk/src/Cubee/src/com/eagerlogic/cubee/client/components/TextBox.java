package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.properties.BackgroundProperty;
import com.eagerlogic.cubee.client.properties.BooleanProperty;
import com.eagerlogic.cubee.client.properties.BorderProperty;
import com.eagerlogic.cubee.client.properties.ColorProperty;
import com.eagerlogic.cubee.client.properties.IChangeListener;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.PaddingProperty;
import com.eagerlogic.cubee.client.properties.Property;
import com.eagerlogic.cubee.client.properties.StringProperty;
import com.eagerlogic.cubee.client.styles.Color;
import com.eagerlogic.cubee.client.styles.ColorBackground;
import com.eagerlogic.cubee.client.styles.ETextAlign;
import com.eagerlogic.cubee.client.styles.EVAlign;
import com.eagerlogic.cubee.client.styles.FontFamily;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.EventListener;

/**
 *
 * @author dipacs
 */
public class TextBox extends AComponent {
	
	private final IntegerProperty width = new IntegerProperty(null, true, false);
	private final IntegerProperty height = new IntegerProperty(null, true, false);
	private final StringProperty text = new StringProperty("", false, false);
	private final BackgroundProperty background = new BackgroundProperty(new ColorBackground(Color.WHITE), true, false);
	private final ColorProperty foreColor = new ColorProperty(Color.BLACK, true, false);
	private final Property<ETextAlign> textAlign = new Property<ETextAlign>(ETextAlign.LEFT, false, false);
	private final Property<EVAlign> verticalAlign = new Property<EVAlign>(EVAlign.TOP, false, false);
	private final BooleanProperty bold = new BooleanProperty(false, false, false);
	private final BooleanProperty italic = new BooleanProperty(false, false, false);
	private final BooleanProperty underline = new BooleanProperty(false, false, false);
	private final IntegerProperty fontSize = new IntegerProperty(12, false, false);
	private final Property<FontFamily> fontFamily = new Property<FontFamily>(FontFamily.Arial, false, false);

	public TextBox() {
		this(DOM.createInputText());
	}
	
	TextBox(Element e) {
		super(e);
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
		foreColor.addChangeListener(new IChangeListener() {

			@Override
			public void onChanged(Object sender) {
				if (foreColor.get() == null) {
					getElement().getStyle().setColor("rgba(0, 0, 0, 0.0)");
				} else {
					getElement().getStyle().setColor(foreColor.get().toCSS());
				}
			}
		});
		foreColor.invalidate();
		textAlign.addChangeListener(new IChangeListener() {

			@Override
			public void onChanged(Object sender) {
				textAlign.get().apply(getElement());
			}
		});
		textAlign.invalidate();
		verticalAlign.addChangeListener(new IChangeListener() {

			@Override
			public void onChanged(Object sender) {
				EVAlign ta = verticalAlign.get();
				if (ta == EVAlign.TOP) {
					getElement().getStyle().setVerticalAlign(Style.VerticalAlign.TOP);
				} else if (ta == EVAlign.MIDDLE) {
					getElement().getStyle().setVerticalAlign(Style.VerticalAlign.MIDDLE);
				} else if (ta == EVAlign.BOTTOM) {
					getElement().getStyle().setVerticalAlign(Style.VerticalAlign.BOTTOM);
				}
			}
		});
		verticalAlign.invalidate();
		underline.addChangeListener(new IChangeListener() {

			@Override
			public void onChanged(Object sender) {
				if (underline.get()) {
					getElement().getStyle().setTextDecoration(Style.TextDecoration.UNDERLINE);
				} else {
					getElement().getStyle().setTextDecoration(Style.TextDecoration.NONE);
				}
				requestLayout();
			}
		});
		underline.invalidate();
		bold.addChangeListener(new IChangeListener() {

			@Override
			public void onChanged(Object sender) {
				if (bold.get()) {
					getElement().getStyle().setFontWeight(Style.FontWeight.BOLD);
				} else {
					getElement().getStyle().setFontWeight(Style.FontWeight.NORMAL);
				}
				requestLayout();
			}
		});
		bold.invalidate();
		italic.addChangeListener(new IChangeListener() {

			@Override
			public void onChanged(Object sender) {
				if (italic.get()) {
					getElement().getStyle().setFontStyle(Style.FontStyle.ITALIC);
				} else {
					getElement().getStyle().setFontStyle(Style.FontStyle.NORMAL);
				}
				requestLayout();
			}
		});
		italic.invalidate();
		fontSize.addChangeListener(new IChangeListener() {

			@Override
			public void onChanged(Object sender) {
				getElement().getStyle().setFontSize(fontSize.get(), Style.Unit.PX);
				requestLayout();
			}
		});
		fontSize.invalidate();
		fontFamily.addChangeListener(new IChangeListener() {

			@Override
			public void onChanged(Object sender) {
				fontFamily.get().apply(getElement());
				requestLayout();
			}
		});
		fontFamily.invalidate();
		DOM.setEventListener((com.google.gwt.user.client.Element)getElement(), new EventListener() {

			@Override
			public void onBrowserEvent(com.google.gwt.user.client.Event event) {
				if (event.getTypeInt() == com.google.gwt.user.client.Event.ONCHANGE) {
					text.set(getElement().getAttribute("value"));
				}
			}
		});
	}

	public final IntegerProperty getWidth() {
		return width;
	}

	public final IntegerProperty getHeight() {
		return height;
	}

	@Override
	public final PaddingProperty getPadding() {
		return super.getPadding();
	}

	@Override
	public final BorderProperty getBorder() {
		return super.getBorder();
	}

	public final StringProperty getText() {
		return text;
	}

	public final BackgroundProperty getBackground() {
		return background;
	}

	public final ColorProperty getForeColor() {
		return foreColor;
	}

	public final Property<ETextAlign> getTextAlign() {
		return textAlign;
	}

	public final Property<EVAlign> getVerticalAlign() {
		return verticalAlign;
	}

	public final BooleanProperty getBold() {
		return bold;
	}

	public final BooleanProperty getItalic() {
		return italic;
	}

	public final BooleanProperty getUnderline() {
		return underline;
	}

	public final IntegerProperty getFontSize() {
		return fontSize;
	}

	public final Property<FontFamily> getFontFamily() {
		return fontFamily;
	}

}
