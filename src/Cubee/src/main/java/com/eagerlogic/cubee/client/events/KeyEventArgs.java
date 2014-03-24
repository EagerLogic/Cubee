package com.eagerlogic.cubee.client.events;

/**
 *
 * @author dipacs
 */
public final class KeyEventArgs extends EventArgs {

    private final int keyCode;
    private final boolean altPressed;
    private final boolean ctrlPressed;
    private final boolean shiftPressed;
    private final boolean metaPressed;

    public KeyEventArgs(int keyCode, boolean altPressed, boolean ctrlPressed, boolean shiftPressed, boolean metaPressed, Object sender) {
        super(sender);
        this.keyCode = keyCode;
        this.altPressed = altPressed;
        this.ctrlPressed = ctrlPressed;
        this.shiftPressed = shiftPressed;
        this.metaPressed = metaPressed;
    }

    public int getKeyCode() {
        return keyCode;
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
