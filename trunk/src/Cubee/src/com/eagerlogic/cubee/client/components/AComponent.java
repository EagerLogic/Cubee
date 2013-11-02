package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.events.Event;
import com.eagerlogic.cubee.client.events.EventArgs;
import com.eagerlogic.cubee.client.events.MouseEventArgs;
import com.eagerlogic.cubee.client.properties.BooleanProperty;
import com.eagerlogic.cubee.client.styles.Padding;
import com.eagerlogic.cubee.client.properties.BorderProperty;
import com.eagerlogic.cubee.client.styles.Border;
import com.eagerlogic.cubee.client.properties.PaddingProperty;
import com.eagerlogic.cubee.client.properties.DoubleProperty;
import com.eagerlogic.cubee.client.styles.ECursor;
import com.eagerlogic.cubee.client.properties.IChangeListener;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.Property;
import com.eagerlogic.cubee.client.utils.Point2D;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.EventListener;

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
	private final Property<ECursor> cursor = new Property<ECursor>(ECursor.AUTO, false, false);
	private final BooleanProperty pointerTransparent = new BooleanProperty(false, false, false);
	private final BooleanProperty visible = new BooleanProperty(false, false, false);
	// TODO visible property
	// TODO enabled property
	
	private final Event<MouseEventArgs> onClick = new Event<MouseEventArgs>();
	private final Event<MouseEventArgs> onMouseDown = new Event<MouseEventArgs>();
	private final Event<MouseEventArgs> onMouseMove = new Event<MouseEventArgs>();
	private final Event<MouseEventArgs> onMouseUp = new Event<MouseEventArgs>();
	private final Event<EventArgs> onMouseEnter = new Event<EventArgs>();
	private final Event<EventArgs> onMouseLeave = new Event<EventArgs>();
	private final Event<MouseEventArgs> onMouseWheel = new Event<MouseEventArgs>();
	
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
					p.apply(getElement());
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
					b.apply(getElement());
				}
				requestLayout();
			}
		});
		cursor.addChangeListener(new IChangeListener() {

			@Override
			public void onChanged(Object sender) {
				getElement().getStyle().setProperty("cursor", cursor.get().getCssValue());
			}
		});
		visible.addChangeListener(new IChangeListener() {

			@Override
			public void onChanged(Object sender) {
				if (visible.get()) {
					getElement().getStyle().setVisibility(Style.Visibility.HIDDEN);
				} else {
					getElement().getStyle().setVisibility(Style.Visibility.VISIBLE);
				}
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
		DOM.setEventListener((com.google.gwt.user.client.Element) getElement(), new EventListener() {

			@Override
			public void onBrowserEvent(com.google.gwt.user.client.Event event) {
				switch (event.getTypeInt()) {
					case com.google.gwt.user.client.Event.ONCLICK:
						if (!pointerTransparent.get()) {
							onClick.fireEvent(createMouseEventArgs(event));
						}
						break;
					case com.google.gwt.user.client.Event.ONMOUSEDOWN:
						if (!pointerTransparent.get()) {
							onMouseDown.fireEvent(createMouseEventArgs(event));
						}
						break;
					case com.google.gwt.user.client.Event.ONMOUSEMOVE:
						if (!pointerTransparent.get()) {
							onMouseMove.fireEvent(createMouseEventArgs(event));
						}
						break;
					case com.google.gwt.user.client.Event.ONMOUSEUP:
						if (!pointerTransparent.get()) {
							onMouseUp.fireEvent(createMouseEventArgs(event));
						}
						break;
					case com.google.gwt.user.client.Event.ONMOUSEOVER:
						if (!pointerTransparent.get()) {
							onMouseEnter.fireEvent(new EventArgs(AComponent.this));
						}
						break;
					case com.google.gwt.user.client.Event.ONMOUSEOUT:
						if (!pointerTransparent.get()) {
							onMouseLeave.fireEvent(new EventArgs(AComponent.this));
						}
						break;
					case com.google.gwt.user.client.Event.ONMOUSEWHEEL:
						if (!pointerTransparent.get()) {
							onMouseLeave.fireEvent(createMouseEventArgs(event));
						}
						break;
				}
			}
		});
		// sinking all the events
		DOM.sinkEvents((com.google.gwt.user.client.Element) getElement(), -1);
	}
	
	private MouseEventArgs createMouseEventArgs(com.google.gwt.user.client.Event event) {
		return new MouseEventArgs(
				event.getClientX(), 
				event.getClientY(), 
				event.getScreenX(), 
				event.getScreenY(), 
				event.getAltKey(), 
				event.getCtrlKey(), 
				event.getShiftKey(), 
				event.getMetaKey(), 
				event.getMouseWheelVelocityY(),
				this
				);
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
		} else {
			layout();
		}
	}
	
	public final void measure() {
		onMeasure();
		this.needsLayout = false;
	}
	
	private void onMeasure() {
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

	public final IntegerProperty translateXProperty() {
		return translateX;
	}

	public final IntegerProperty translateYProperty() {
		return translateY;
	}

	public final DoubleProperty rotateProperty() {
		return rotate;
	}

	public final DoubleProperty scaleXProperty() {
		return scaleX;
	}

	public final DoubleProperty scaleYProperty() {
		return scaleY;
	}

	public final DoubleProperty transformCenterXProperty() {
		return transformCenterX;
	}

	public final DoubleProperty transformCenterYProperty() {
		return transformCenterY;
	}

	protected PaddingProperty paddingProperty() {
		return padding;
	}

	protected BorderProperty borderProperty() {
		return border;
	}

	public IntegerProperty measuredWidthProperty() {
		return measuredWidth;
	}

	public IntegerProperty measuredHeightProperty() {
		return measuredHeight;
	}

	public IntegerProperty clientWidthProperty() {
		return clientWidth;
	}

	public IntegerProperty clientHeightProperty() {
		return clientHeight;
	}

	public IntegerProperty boundsWidthProperty() {
		return boundsWidth;
	}

	public IntegerProperty boundsHeightProperty() {
		return boundsHeight;
	}

	public IntegerProperty boundsLeftProperty() {
		return boundsLeft;
	}

	public IntegerProperty boundsTopProperty() {
		return boundsTop;
	}
	
	protected void setPosition(int x, int y) {
		getElement().getStyle().setLeft(x, Style.Unit.PX);
		getElement().getStyle().setTop(y, Style.Unit.PX);
	}
	
	protected void setSize(int width, int height) {
		getElement().getStyle().setWidth(width, Style.Unit.PX);
		getElement().getStyle().setHeight(height, Style.Unit.PX);
	}

	public final Property<ECursor> cursorProperty() {
		return cursor;
	}

	public final BooleanProperty pointerTransparentProperty() {
		return pointerTransparent;
	}

	public final BooleanProperty visibleProperty() {
		return visible;
	}

	public final Event<MouseEventArgs> onClickEvent() {
		return onClick;
	}

	public final Event<MouseEventArgs> onMouseDownEvent() {
		return onMouseDown;
	}

	public final Event<MouseEventArgs> onMouseMoveEvent() {
		return onMouseMove;
	}

	public final Event<MouseEventArgs> onMouseUpEvent() {
		return onMouseUp;
	}

	public final Event<EventArgs> onMouseEnterEvent() {
		return onMouseEnter;
	}

	public final Event<EventArgs> onMouseLeaveEvent() {
		return onMouseLeave;
	}

	public final Event<MouseEventArgs> onMouseWheelEvent() {
		return onMouseWheel;
	}
	
}
