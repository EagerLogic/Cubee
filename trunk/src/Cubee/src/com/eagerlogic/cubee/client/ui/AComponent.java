package com.eagerlogic.cubee.client.ui;

import com.eagerlogic.cubee.client.properties.PaddingProperty;
import com.eagerlogic.cubee.client.properties.DoubleProperty;
import com.eagerlogic.cubee.client.properties.IChangeListener;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.utils.Point2D;
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
	private final IntegerProperty measuredWidth = new IntegerProperty(0, false, true);
	private final IntegerProperty measuredHeight = new IntegerProperty(0, false, true);
	private final IntegerProperty clientWidth = new IntegerProperty(0, false, true);
	private final IntegerProperty clientHeight = new IntegerProperty(0, false, true);
	private final IntegerProperty boundsWidth = new IntegerProperty(0, false, true);
	private final IntegerProperty boundsHeight = new IntegerProperty(0, false, true);
	private final IntegerProperty boundsLeft = new IntegerProperty(0, false, true);
	private final IntegerProperty boundsTop = new IntegerProperty(0, false, true);
	private final IntegerProperty measuredWidthSetter = new IntegerProperty(0, false, false);
	private final IntegerProperty measuredHeightSetter = new IntegerProperty(0, false, false);
	private final IntegerProperty clientWidthSetter = new IntegerProperty(0, false, false);
	private final IntegerProperty clientHeightSetter = new IntegerProperty(0, false, false);
	private final IntegerProperty boundsWidthSetter = new IntegerProperty(0, false, false);
	private final IntegerProperty boundsHeightSetter = new IntegerProperty(0, false, false);
	private final IntegerProperty boundsLeftSetter = new IntegerProperty(0, false, false);
	private final IntegerProperty boundsTopSetter = new IntegerProperty(0, false, false);
	
	private final Element element;
	private ALayout parent;
	
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
		measuredWidth.initReadonlyBind(measuredWidthSetter);
		measuredHeight.initReadonlyBind(measuredHeightSetter);
		clientWidth.initReadonlyBind(clientWidthSetter);
		clientHeight.initReadonlyBind(clientHeightSetter);
		boundsWidth.initReadonlyBind(boundsWidthSetter);
		boundsHeight.initReadonlyBind(boundsHeightSetter);
		boundsLeft.initReadonlyBind(boundsLeftSetter);
		boundsTop.initReadonlyBind(boundsTopSetter);
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
	
	private final void onMeasure() {
		// calculating client bounds
		int cw = element.getClientWidth();
		int ch = element.getClientHeight();
		Padding p = padding.get();
		if (p != null) {
			cw = cw - p.getLeftPadding() - p.getRightPadding();
			ch = ch - p.getTopPadding() - p.getBottomPadding();
		}
		clientWidthSetter.set(cw);
		clientHeightSetter.set(ch);
		
		// calculating measured bounds
		int mw = element.getOffsetWidth();
		int mh = element.getOffsetHeight();
		measuredWidthSetter.set(mw);
		measuredHeightSetter.set(mh);
		
		// calculating parent bounds
		double tcx = transformCenterX.get();
		double tcy = transformCenterY.get();
		
		double sx = scaleX.get();
		double sy = scaleY.get();
		
		int bw = (int) (mw * sx);
		int bh = (int) (mh * sy);
		int bx = (int) (0 - ((bw - mw) * tcx));
		int by = (int) (0 - ((bh - mh) * tcy));
		double rot = rotate.get();
		if (rot != 0.0) {
			rot = rot * 360;
			Point2D tr = rotatePoint(bw, 0, rot);
			Point2D br = rotatePoint(bw, bh, rot);
			Point2D bl = rotatePoint(0, bh, rot);
			int minX = Math.min(Math.min(0, tr.getX()), Math.min(br.getX(), bl.getX()));
			int minY = Math.min(Math.min(0, tr.getY()), Math.min(br.getY(), bl.getY()));
			int maxX = Math.max(Math.max(0, tr.getX()), Math.max(br.getX(), bl.getX()));
			int maxY = Math.max(Math.max(0, tr.getY()), Math.max(br.getY(), bl.getY()));
			bw = maxX - minX;
			bh = maxY - minY;
			bx = minX;
			by = minY;
		}
		boundsLeftSetter.set(bx);
		boundsTopSetter.set(by);
		boundsWidthSetter.set(bw);
		boundsHeightSetter.set(bh);
	}
	
	private Point2D rotatePoint(int x, int y, double angle) {
		angle = ((angle/180)*Math.PI);
		double cosAngle = Math.cos(angle);
		double sinAngle = Math.sin(angle);

		int resX = (int) ((x * cosAngle) - (y * sinAngle));
		int resY = (int) ((x * sinAngle) + (y * cosAngle));
		return new Point2D(resX, resY);
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

	public IntegerProperty getMeasuredWidth() {
		return measuredWidth;
	}

	public IntegerProperty getMeasuredHeight() {
		return measuredHeight;
	}

	public IntegerProperty getClientWidth() {
		return clientWidth;
	}

	public IntegerProperty getClientHeight() {
		return clientHeight;
	}

	public IntegerProperty getBoundsWidth() {
		return boundsWidth;
	}

	public IntegerProperty getBoundsHeight() {
		return boundsHeight;
	}

	public IntegerProperty getBoundsLeft() {
		return boundsLeft;
	}

	public IntegerProperty getBoundsTop() {
		return boundsTop;
	}
	
}
