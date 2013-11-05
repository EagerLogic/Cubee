package com.eagerlogic.cubee.client.events;

/**
 *
 * @author dipacs
 */
public final class PointerEventArgs extends EventArgs {
	
	public static final int TYPE_MOUSE_DOWN = com.google.gwt.user.client.Event.ONMOUSEDOWN;
	public static final int TYPE_MOUSE_MOVE = com.google.gwt.user.client.Event.ONMOUSEMOVE;
	public static final int TYPE_MOUSE_UP = com.google.gwt.user.client.Event.ONMOUSEUP;
	public static final int TYPE_MOUSE_ENTER = com.google.gwt.user.client.Event.ONMOUSEOVER;
	public static final int TYPE_MOUSE_LEAVE = com.google.gwt.user.client.Event.ONMOUSEOUT;
	public static final int TYPE_MOUSE_WHEEL = com.google.gwt.user.client.Event.ONMOUSEWHEEL;
	
	private final int type;
	private final int x;
	private final int y;
	private final int screenX;
	private final int screenY;
	private final boolean altPressed;
	private final boolean ctrlPressed;
	private final boolean shiftPressed;
	private final boolean metaPressed;
	private final int wheelRotation;

	public PointerEventArgs(int type, int x, int y, int screenX, int screenY, boolean altPressed, boolean ctrlPressed, boolean shiftPressed, boolean metaPressed, int wheelRotation, Object sender) {
		super(sender);
		this.type = type;
		this.x = x;
		this.y = y;
		this.screenX = screenX;
		this.screenY = screenY;
		this.altPressed = altPressed;
		this.ctrlPressed = ctrlPressed;
		this.shiftPressed = shiftPressed;
		this.metaPressed = metaPressed;
		this.wheelRotation = wheelRotation;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getScreenX() {
		return screenX;
	}

	public int getScreenY() {
		return screenY;
	}

	public boolean isAltPressed() {
		return altPressed;
	}

	public boolean isCtrlPressed() {
		return ctrlPressed;
	}

	public boolean isShiftPressed() {
		return shiftPressed;
	}

	public boolean isMetaPressed() {
		return metaPressed;
	}

	public int getWheelRotation() {
		return wheelRotation;
	}

	public int getType() {
		return type;
	}

}
