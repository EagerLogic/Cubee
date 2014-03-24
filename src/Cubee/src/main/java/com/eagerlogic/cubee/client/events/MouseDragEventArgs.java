package com.eagerlogic.cubee.client.events;

/**
 *
 * @author dipacs
 */
public final class MouseDragEventArgs extends EventArgs {

    private final int screenX;
    private final int screenY;
    private final int deltaX;
    private final int deltaY;
    private final boolean altPressed;
    private final boolean ctrlPressed;
    private final boolean shiftPressed;
    private final boolean metaPressed;

    public MouseDragEventArgs(int screenX, int screenY, int deltaX, int deltaY, boolean altPressed, boolean ctrlPressed, boolean shiftPressed, boolean metaPressed, Object sender) {
        super(sender);
        this.screenX = screenX;
        this.screenY = screenY;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.altPressed = altPressed;
        this.ctrlPressed = ctrlPressed;
        this.shiftPressed = shiftPressed;
        this.metaPressed = metaPressed;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
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

}
