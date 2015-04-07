package com.eagerlogic.cubee.client.events;

import com.google.gwt.user.client.Event;

/**
 *
 * @author dipacs
 */
public final class MouseDownEventArgs extends EventArgs {

    private final int screenX;
    private final int screenY;
    private final int x;
    private final int y;
    private final boolean altPressed;
    private final boolean ctrlPressed;
    private final boolean shiftPressed;
    private final boolean metaPressed;
    private final int button;
    private final com.google.gwt.user.client.Event nativeEvent;

    public MouseDownEventArgs(int screenX, int screenY, int x, int y, boolean altPressed, boolean ctrlPressed, boolean shiftPressed, boolean metaPressed, int button, com.google.gwt.user.client.Event nativeEvent, Object sender) {
        super(sender);
        this.screenX = screenX;
        this.screenY = screenY;
        this.x = x;
        this.y = y;
        this.altPressed = altPressed;
        this.ctrlPressed = ctrlPressed;
        this.shiftPressed = shiftPressed;
        this.metaPressed = metaPressed;
        this.button = button;
        this.nativeEvent = nativeEvent;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    public int getButton() {
        return button;
    }

    public Event getNativeEvent() {
        return nativeEvent;
    }

}
