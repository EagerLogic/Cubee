package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.events.Event;
import com.eagerlogic.cubee.client.events.EventArgs;
import com.eagerlogic.cubee.client.events.PointerEventArgs;
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
import com.google.gwt.dom.client.NativeEvent;
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
	private final BooleanProperty handlePointer = new BooleanProperty(true, false, false);
	private final BooleanProperty visible = new BooleanProperty(true, false, false);
	private final BooleanProperty enabled = new BooleanProperty(true, false, false);
	private final DoubleProperty alpha = new DoubleProperty(1.0, false, false);
	// TODO visible property
	// TODO enabled property
	
	private final Event<PointerEventArgs> onClick = new Event<PointerEventArgs>();
	private final Event<PointerEventArgs> onPointerDown = new Event<PointerEventArgs>();
	private final Event<PointerEventArgs> onPointerMove = new Event<PointerEventArgs>();
	private final Event<PointerEventArgs> onPointerUp = new Event<PointerEventArgs>();
	private final Event<EventArgs> onPointerEnter = new Event<EventArgs>();
	private final Event<EventArgs> onPointerLeave = new Event<EventArgs>();
	private final Event<PointerEventArgs> onMouseWheel = new Event<PointerEventArgs>();
	
	private int left = 0;
	private int top = 0;
	
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
		this.element.setAttribute("draggabe", "false");
		this.element.getStyle().setPosition(Style.Position.ABSOLUTE);
		this.element.getStyle().setProperty("pointerEvents", "none");
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
		enabled.addChangeListener(new IChangeListener() {

			@Override
			public void onChanged(Object sender) {
				if (!enabled.get()) {
					getElement().setAttribute("disabled", "true");
				} else {
					getElement().removeAttribute("disabled");
				}
			}
		});
		alpha.addChangeListener(new IChangeListener() {

			@Override
			public void onChanged(Object sender) {
				getElement().getStyle().setOpacity(alpha.get());
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
		this.needsLayout = true;
		if (this.parent != null) {
			this.parent.requestLayout();
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
	
	protected void setPosition(int left, int top) {
		getElement().getStyle().setLeft(left, Style.Unit.PX);
		getElement().getStyle().setTop(top, Style.Unit.PX);
		this.left = left;
		this.top = top;
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

	public final Event<PointerEventArgs> onClickEvent() {
		return onClick;
	}

	public final Event<PointerEventArgs> onPointerDownEvent() {
		return onPointerDown;
	}

	public final Event<PointerEventArgs> onPointerMoveEvent() {
		return onPointerMove;
	}

	public final Event<PointerEventArgs> onPointerUpEvent() {
		return onPointerUp;
	}

	public final Event<EventArgs> onPointerEnterEvent() {
		return onPointerEnter;
	}

	public final Event<EventArgs> onPointerLeaveEvent() {
		return onPointerLeave;
	}

	public final Event<PointerEventArgs> onMouseWheelEvent() {
		return onMouseWheel;
	}

	public final DoubleProperty alphaProperty() {
		return alpha;
	}

	public final BooleanProperty handlePointerProperty() {
		return handlePointer;
	}

	public final BooleanProperty enabledProperty() {
		return enabled;
	}

	public final int getLeft() {
		return left;
	}

	public final int getTop() {
		return top;
	}
	
	/**
	 * This method is called by the parent of this component when a pointer event is occured. The goal of this method is
	 * to decide if this component wants to handle the event or not, and delegate the event to child components if needed.
	 * 
	 * @param screenX
	 * The x coordinate of the pointer relative to the screen's top-left corner.
	 * @param screenY
	 * The y coordinate of the pointer relative to the screen's top-left corner.
	 * @param parentScreenX
	 * The x coordinate of the pointer relative to the parent's top-left corner.
	 * @param parentScreenY
	 * The y coordinate of the pointer relative to the parent's top-left corner.
	 * @param x
	 * The x coordinate of the pointer relative to this component's top-left corner.
	 * @param y
	 * The y coordinate of the pointer relative to this component's top-left corner.
	 * @param wheelVelocity
	 * The mouse wheel velocity value.
	 * @param type
	 * The type of the event. Valid values are listed in PointerEventArgs class.
	 * @param altPressed 
	 * Indicates if the alt key is pressed when the event occured or not.
	 * @param ctrlPressed 
	 * Indicates if the ctrl key is pressed when the event occured or not.
	 * @param shiftPressed 
	 * Indicates if the shift key is pressed when the event occured or not.
	 * @param metaPressed 
	 * Indicates if the meta key is pressed when the event occured or not.
	 * 
	 * @return 
	 * True if the event is fully handled and underlaying components can't handle this event, otherwise
	 * false if underlaying components can handle this event.
	 */
	boolean doPointerEventClimbingUp(int screenX, int screenY, int parentScreenX, int parentScreenY, 
			int x, int y, int wheelVelocity, boolean altPressed, boolean ctrlPressed, boolean shiftPressed, 
			boolean metaPressed, int type) {
		if (!handlePointer.get()) {
			return false;
		}
		if (pointerTransparent.get()) {
			return false;
		}
		if (!enabled.get()) {
			return true;
		}
		if (!visible.get()) {
			return false;
		}
		onPointerEventClimbingUp(screenX, screenY, parentScreenX, parentScreenY, x, y, wheelVelocity, altPressed, 
				ctrlPressed, shiftPressed, metaPressed, type);
		return onPointerEventFallingDown(screenX, screenY, parentScreenX, parentScreenY, x, y, wheelVelocity, altPressed, 
				ctrlPressed, shiftPressed, metaPressed, type);
	}
	
//	boolean doPointerEventFallingDown(int screenX, int screenY, int parentScreenX, int parentScreenY, 
//			int x, int y, int wheelVelocity, boolean altPressed, boolean ctrlPressed, boolean shiftPressed, 
//			boolean metaPressed, int type) {
//		return onPointerEventFallingDown(screenX, screenY, parentScreenX, parentScreenY, x, y, wheelVelocity, altPressed, 
//				ctrlPressed, shiftPressed, metaPressed, type);
//	}
	
	/**
	 * This method is called when a pointer event is climbing up on the component hierarchy. The goal of this method is 
	 * to decide if the event can reach child components or not. In the most of the cases you don't need to overwrite
	 * this method. The default implementation is returns true.
	 * 
	 * @param screenX
	 * The x coordinate of the pointer relative to the screen's top-left corner.
	 * @param screenY
	 * The y coordinate of the pointer relative to the screen's top-left corner.
	 * @param parentScreenX
	 * The x coordinate of the pointer relative to the parent's top-left corner.
	 * @param parentScreenY
	 * The y coordinate of the pointer relative to the parent's top-left corner.
	 * @param x
	 * The x coordinate of the pointer relative to this component's top-left corner.
	 * @param y
	 * The y coordinate of the pointer relative to this component's top-left corner.
	 * @param wheelVelocity
	 * The mouse wheel velocity value.
	 * @param type
	 * The type of the event. Valid values are listed in PointerEventArgs class.
	 * @param altPressed 
	 * Indicates if the alt key is pressed when the event occured or not.
	 * @param ctrlPressed 
	 * Indicates if the ctrl key is pressed when the event occured or not.
	 * @param shiftPressed 
	 * Indicates if the shift key is pressed when the event occured or not.
	 * @param metaPressed 
	 * Indicates if the meta key is pressed when the event occured or not.
	 * 
	 * @return 
	 * False if this event can't reach overlaying components, or
	 * true if overlaying components can also get the climbing up event.
	 */
	protected boolean onPointerEventClimbingUp(int screenX, int screenY, int parentScreenX, int parentScreenY, 
			int x, int y, int wheelVelocity, boolean altPressed, boolean ctrlPressed, boolean shiftPressed, 
			boolean metaPressed, int type) {
		return true;
	}
	
	/**
	 * This method is called when a pointer event is falling down on the component hierarchy. The goal of this method is to 
	 * fire events if needed, and in the result type define if the underlaying components can process this event too.
	 * The default implementation is fires the associated event, and returns true.
	 * 
	 * @param screenX
	 * The x coordinate of the pointer relative to the screen's top-left corner.
	 * @param screenY
	 * The y coordinate of the pointer relative to the screen's top-left corner.
	 * @param parentScreenX
	 * The x coordinate of the pointer relative to the parent's top-left corner.
	 * @param parentScreenY
	 * The y coordinate of the pointer relative to the parent's top-left corner.
	 * @param x
	 * The x coordinate of the pointer relative to this component's top-left corner.
	 * @param y
	 * The y coordinate of the pointer relative to this component's top-left corner.
	 * @param wheelVelocity
	 * The mouse wheel velocity value.
	 * @param type
	 * The type of the event. Valid values are listed in PointerEventArgs class.
	 * @param altPressed 
	 * Indicates if the alt key is pressed when the event occured or not.
	 * @param ctrlPressed 
	 * Indicates if the ctrl key is pressed when the event occured or not.
	 * @param shiftPressed 
	 * Indicates if the shift key is pressed when the event occured or not.
	 * @param metaPressed 
	 * Indicates if the meta key is pressed when the event occured or not.
	 * 
	 * @return 
	 * True if this event is fully processed, and underlaying components can't process this event, or
	 * false if underlaying components can also get the falling down event.
	 */
	protected boolean onPointerEventFallingDown(int screenX, int screenY, int parentScreenX, int parentScreenY, 
			int x, int y, int wheelVelocity, boolean altPressed, boolean ctrlPressed, boolean shiftPressed, 
			boolean metaPressed, int type) {
		PointerEventArgs args = new PointerEventArgs(type, x, y, screenX, screenY, altPressed, ctrlPressed, shiftPressed, 
				metaPressed, wheelVelocity, this);
		switch (type) {
			case PointerEventArgs.TYPE_MOUSE_DOWN:
				onPointerDown.fireEvent(args);
				break;
			case PointerEventArgs.TYPE_MOUSE_MOVE:
				onPointerMove.fireEvent(args);
				break;
			case PointerEventArgs.TYPE_MOUSE_UP:
				onPointerUp.fireEvent(args);
				onClick.fireEvent(args);
				break;
			case PointerEventArgs.TYPE_MOUSE_ENTER:
				onPointerEnter.fireEvent(args);
				break;
			case PointerEventArgs.TYPE_MOUSE_LEAVE:
				onPointerLeave.fireEvent(args);
				break;
			case PointerEventArgs.TYPE_MOUSE_WHEEL:
				onMouseWheel.fireEvent(args);
				break;
		}
		return true;
	}
	
	/**
	 * Indicates if this component is intersects the given point. The x and y coordinate is relative to the parent's
	 * top-left coordinate. 
	 * 
	 * @param x
	 * The x coordinate of the point.
	 * @param y
	 * The y coordinate of the point.
	 * 
	 * @return 
	 * True if this component is intersects the given point, otherwise false.
	 */
	boolean isIntersectsPoint(int x, int y) {
		// measured positions
		int x1 = left + translateX.get();
		int y1 = top + translateY.get();
		int x2 = x1 + measuredWidth.get();
		int y2 = y1;
		int x3 = x2;
		int y3 = y2 + measuredHeight.get();
		int x4 = x1;
		int y4 = y3;
		
		// scale points
		if (scaleX.get() != 1.0) {
			x1 = (int) (x1 - ((x2 - x1) * transformCenterX.get() * scaleX.get()));
			x2 = (int) (x1 + ((x2 - x1) *  (1 - transformCenterX.get()) * scaleX.get()));
			x3 = x2;
			x4 = x1;
		}
		if (scaleY.get() != 1.0) {
			y1 = (int) (y1 - ((y2 - y1) * transformCenterY.get() * scaleY.get()));
			y4 = (int) (y4 + ((y4 - y1) *  (1 - transformCenterY.get()) * scaleY.get()));
			y2 = y1;
			y3 = y4;
		}
		
		// rotatePoints
		if (rotate.get() != 1.0) {
			int rpx = (int) ((x2 - x1) * transformCenterX.get());
			int rpy = (int) ((y4 - y1) * transformCenterX.get());
			Point2D tl = rotatePoint(x1 - rpx, y1 - rpy, rotate.get());
			Point2D tr = rotatePoint(x2 - rpx, y2 - rpy, rotate.get());
			Point2D br = rotatePoint(x3 - rpx, y3 - rpy, rotate.get());
			Point2D bl = rotatePoint(x4 - rpx, y4 - rpy, rotate.get());
			x1 = tl.getX() + rpx;
			y1 = tl.getY() + rpy;
			x2 = tr.getX() + rpx;
			y2 = tr.getY() + rpy;
			x3 = br.getX() + rpx;
			y3 = br.getY() + rpy;
			x4 = bl.getX() + rpx;
			y4 = bl.getY() + rpy;
		}
		
		int cnt = 0;
		if (isPointIntersectsLine(x, y, x1, y1, x2, y2)) {
			cnt++;
		}
		if (isPointIntersectsLine(x, y, x2, y2, x3, y3)) {
			cnt++;
		}
		if (isPointIntersectsLine(x, y, x3, y3, x4, y4)) {
			cnt++;
		}
		if (isPointIntersectsLine(x, y, x4, y4, x1, y1)) {
			cnt++;
		}
		return cnt == 1 || cnt == 3;
	}
	
	private boolean isPointIntersectsLine(int px, int py, int lx1, int ly1, int lx2, int ly2) {
		/* ((poly[i][1] > y) != (poly[j][1] > y)) and \
                    (x < (poly[j][0] - poly[i][0]) * (y - poly[i][1]) / (poly[j][1] - poly[i][1]) + poly[i][0])
					*/
		return ((ly1 > py) != (ly2 > py)) && (px < (lx2 - lx1) * ((double)(py - ly1)) / (ly2 - ly1) + lx1);
	}
	
}
