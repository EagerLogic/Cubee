package com.eagerlogic.cubee.client.ui;

import com.eagerlogic.cubee.client.properties.DoubleProperty;
import com.eagerlogic.cubee.client.properties.IChangeListener;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;

/**
 *
 * @author dipacs
 */
public abstract class AComponent {

	private final IntegerProperty translateX = new IntegerProperty(0, false, false);
	private final IntegerProperty translateY = new IntegerProperty(0, false, false);
	private final DoubleProperty rotate = new DoubleProperty(0.0, false, false);
	private final DoubleProperty scaleX = new DoubleProperty(1.0, false, false);
	private final DoubleProperty scaleY = new DoubleProperty(1.0, false, false);
	private final DoubleProperty transformCenterX = new DoubleProperty(0.5, false, false);
	private final DoubleProperty transformCenterY = new DoubleProperty(0.5, false, false);
	private final PaddingProperty padding = new PaddingProperty(null, true, false);
	private final BorderProperty border = new BorderProperty(null, true, false);
	
	private final Element element;
	private ALayout parent;
	
	private int measuredLeft;
	private int measuredTop;
	private int measuredWidth;
	private int measuredHeight;
	
	private boolean needsLayout = true;
	
	private IChangeListener transformChangedListener = new IChangeListener() {

		@Override
		public void onChanged(Object sender) {
			updateTransform();
			requestLayout();
		}
	};
	
	
	

	public AComponent(Element rootElement) {
		this.element = rootElement;
		this.element.getStyle().setPosition(Style.Position.ABSOLUTE);
		translateX.addChangeListener(transformChangedListener);
		translateY.addChangeListener(transformChangedListener);
		rotate.addChangeListener(transformChangedListener);
		scaleX.addChangeListener(transformChangedListener);
		scaleY.addChangeListener(transformChangedListener);
		transformCenterX.addChangeListener(transformChangedListener);
		transformCenterY.addChangeListener(transformChangedListener);
		padding.addChangeListener(new IChangeListener() {

			@Override
			public void onChanged(Object sender) {
				Padding p = padding.get();
				if (p == null) {
					getElement().getStyle().setPadding(0.0, Style.Unit.PX);
				} else {
					getElement().getStyle().setPaddingLeft(p.getLeftPadding(), Style.Unit.PX);
					getElement().getStyle().setPaddingTop(p.getTopPadding(), Style.Unit.PX);
					getElement().getStyle().setPaddingRight(p.getRightPadding(), Style.Unit.PX);
					getElement().getStyle().setPaddingBottom(p.getBottomPadding(), Style.Unit.PX);
				}
				requestLayout();
			}
		});
		border.addChangeListener(new IChangeListener() {

			@Override
			public void onChanged(Object sender) {
				Border b = border.get();
				if (b == null) {
					getElement().getStyle().clearBorderStyle();
					getElement().getStyle().clearBorderColor();
					getElement().getStyle().clearBorderWidth();
				} else {
					getElement().getStyle().setProperty("borderStyle", "solid");
					getElement().getStyle().setProperty("borderLeftColor", b.getLeftBorderColor().toCSS());
					getElement().getStyle().setProperty("borderLeftWidth", b.getLeftBorderSize() + "px");
					getElement().getStyle().setProperty("borderTopColor", b.getTopBorderColor().toCSS());
					getElement().getStyle().setProperty("borderTopWidth", b.getTopBorderSize() + "px");
					getElement().getStyle().setProperty("borderRightColor", b.getRightBorderColor().toCSS());
					getElement().getStyle().setProperty("borderRightWidth", b.getRightBorderSize() + "px");
					getElement().getStyle().setProperty("borderBottomColor", b.getBottomBorderColor().toCSS());
					getElement().getStyle().setProperty("borderBottomWidth", b.getBottomBorderSize() + "px");
					
					getElement().getStyle().setProperty("borderTopLeftRadius", b.getTopLeftBorderRadius() + "px");
					getElement().getStyle().setProperty("borderTopRightRadius", b.getTopRightBorderRadius() + "px");
					getElement().getStyle().setProperty("borderBottomLeftRadius", b.getBottomLeftBorderRadius() + "px");
					getElement().getStyle().setProperty("borderBottomRightRadius", b.getBottomRightBorderRadius() + "px");
				}
				requestLayout();
			}
		});
	}

	private void updateTransform() {
		double angle = rotate.get();
		angle = angle - ((int) angle);
		angle = angle * 360;
		String angleStr = angle + "deg";

		String centerX = (transformCenterX.get() * 100) + "%";
		String centerY = (transformCenterY.get() * 100) + "%";

		String sX = scaleX.get().toString();
		String sY = scaleY.get().toString();

		element.getStyle().setProperty("transformOrigin", centerX + " " + centerY);
		element.getStyle().setProperty("transform", "translate(" + translateX.get() + "px, " + translateY.get() + "px) rotate(" + angleStr + ") scaleX( " + sX + ") scaleY(" + sY + ")");
		element.getStyle().setProperty("msTransformOrigin", centerX + " " + centerY);
		element.getStyle().setProperty("msTransform", "translate(" + translateX.get() + "px, " + translateY.get() + "px) rotate(" + angleStr + ") scaleX( " + sX + ") scaleY(" + sY + ")");
		element.getStyle().setProperty("webkitTransformOrigin", centerX + " " + centerY);
		element.getStyle().setProperty("webkitTransform", "translate(" + translateX.get() + "px, " + translateY.get() + "px) rotate(" + angleStr + ") scaleX( " + sX + ") scaleY(" + sY + ")");
	}
	
	public void requestLayout() {
		if (this.parent != null) {
			this.parent.requestLayout();
		}
	}
	
	public final void measure() {
		onMeasure();
		this.needsLayout = false;
	}
	
	protected void onMeasure() {
		int ow = element.getOffsetWidth();
		int oh = element.getOffsetHeight();
		
		double tcx = transformCenterX.get();
		double tcy = transformCenterY.get();
		
		double sx = scaleX.get();
		double sy = scaleY.get();
		
		int width = (int) (ow * sx);
		int height = (int) (oh * sy);
		
		int ox = (int) (0 - ((width - ow) * tcx));
		int oy = (int) (0 - ((height - oh) * tcy));
		
		// TODO calculate rotated bounds
		
		this.measuredLeft = ox;
		this.measuredTop = oy;
		this.measuredWidth = width;
		this.measuredHeight = height;
	}
	
	public final Element getElement() {
		return this.element;
	}

	public final ALayout getParent() {
		return parent;
	}

	final void setParent(ALayout parent) {
		this.parent = parent;
	}
	
	public void layout() {
		measure();
	}

	public final int getMeasuredLeft() {
		return measuredLeft;
	}

	public final int getMeasuredTop() {
		return measuredTop;
	}

	public final int getMeasuredWidth() {
		return measuredWidth;
	}

	public final int getMeasuredHeight() {
		return measuredHeight;
	}

	public boolean isNeedsLayout() {
		return needsLayout;
	}

	public final IntegerProperty getTranslateX() {
		return translateX;
	}

	public final IntegerProperty getTranslateY() {
		return translateY;
	}

	public final DoubleProperty getRotate() {
		return rotate;
	}

	public final DoubleProperty getScaleX() {
		return scaleX;
	}

	public final DoubleProperty getScaleY() {
		return scaleY;
	}

	public final DoubleProperty getTransformCenterX() {
		return transformCenterX;
	}

	public final DoubleProperty getTransformCenterY() {
		return transformCenterY;
	}

	protected PaddingProperty getPadding() {
		return padding;
	}

	protected BorderProperty getBorder() {
		return border;
	}
	
}
