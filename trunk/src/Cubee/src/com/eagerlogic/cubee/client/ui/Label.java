package com.eagerlogic.cubee.client.ui;

import com.eagerlogic.cubee.client.properties.BooleanProperty;
import com.eagerlogic.cubee.client.properties.ColorProperty;
import com.eagerlogic.cubee.client.properties.PaddingProperty;
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
	private final ColorProperty foreColor = new ColorProperty(Color.BLACK, true, false);
	private final Property<ETextAlign> textAlign = new Property<ETextAlign>(ETextAlign.LEFT, false, false);
	private final Property<EVAlign> verticalAlign = new Property<EVAlign>(EVAlign.TOP, false, false);
	private final BooleanProperty bold = new BooleanProperty(false, false, false);
	private final BooleanProperty italic = new BooleanProperty(false, false, false);
	private final BooleanProperty underline = new BooleanProperty(false, false, false);
	private final IntegerProperty fontSize = new IntegerProperty(12, false, false);
	private final Property<FontFamily> fontFamily = new Property<FontFamily>(FontFamily.Arial, false, false);
	
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
		textOverFlow.invalidate();
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
				ETextAlign ta = textAlign.get();
				if (ta == ETextAlign.LEFT) {
					getElement().getStyle().setTextAlign(Style.TextAlign.LEFT);
				} else if (ta == ETextAlign.CENTER) {
					getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
				} else if (ta == ETextAlign.RIGHT) {
					getElement().getStyle().setTextAlign(Style.TextAlign.RIGHT);
				} else if (ta == ETextAlign.JUSTIFY) {
					getElement().getStyle().setTextAlign(Style.TextAlign.JUSTIFY);
				}
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
			}
		});
		italic.invalidate();
		fontSize.addChangeListener(new IChangeListener() {

			@Override
			public void onChanged(Object sender) {
				getElement().getStyle().setFontSize(fontSize.get(), Style.Unit.PX);
			}
		});
		fontSize.invalidate();
		fontFamily.addChangeListener(new IChangeListener() {

			@Override
			public void onChanged(Object sender) {
				getElement().getStyle().setProperty("fontFamily", fontFamily.get().toCSS());
			}
		});
		fontFamily.invalidate();
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

	@Override
	protected PaddingProperty getPadding() {
		return super.getPadding();
	}

	@Override
	protected BorderProperty getBorder() {
		return super.getBorder();
	}

	public ColorProperty getForeColor() {
		return foreColor;
	}

	public Property<EVAlign> getVerticalAlign() {
		return verticalAlign;
	}

	public BooleanProperty getBold() {
		return bold;
	}

	public BooleanProperty getItalic() {
		return italic;
	}

	public BooleanProperty getUnderline() {
		return underline;
	}

	public Property<ETextAlign> getTextAlign() {
		return textAlign;
	}

	public IntegerProperty getFontSize() {
		return fontSize;
	}

}
