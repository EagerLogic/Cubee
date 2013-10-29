package com.eagerlogic.cubee.client.events;

/**
 *
 * @author dipacs
 */
public final class MouseEventArgs extends EventArgs {
	
	private final int x;
	private final int y;
	private final int screenX;
	private final int screenY;
	private final boolean altPressed;
	private final boolean ctrlPressed;
	private final boolean shiftPressed;
	private final boolean metaPressed;
	private final int wheelRotation;

	public MouseEventArgs(int x, int y, int screenX, int screenY, boolean altPressed, boolean ctrlPressed, boolean shiftPressed, boolean metaPressed, int wheelRotation, Object sender) {
		super(sender);
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

}
