package com.eagerlogic.cubee.client.events;

/**
 *
 * @author dipacs
 */
public class ClickEventArgs extends EventArgs {

    private final int screenX;
    private final int screenY;
    private final int x;
    private final int y;
    private final boolean altPressed;
    private final boolean ctrlPressed;
    private final boolean shiftPressed;
    private final boolean metaPressed;
    private final int button;

    public ClickEventArgs(int screenX, int screenY, int x, int y, boolean altPressed, boolean ctrlPressed, boolean shiftPressed, boolean metaPressed, int button, Object sender) {
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

}
