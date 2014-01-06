package com.eagerlogic.cubee.client.events;

/**
 *
 * @author dipacs
 */
public class MouseWheelEventArgs extends EventArgs {

    private final int wheelVelocity;
    private final boolean altPressed;
    private final boolean ctrlPressed;
    private final boolean shiftPressed;
    private final boolean metaPressed;

    public MouseWheelEventArgs(int wheelVelocity, boolean altPressed, boolean ctrlPressed, boolean shiftPressed, boolean metaPressed, Object sender) {
        super(sender);
        this.wheelVelocity = wheelVelocity;
        this.altPressed = altPressed;
        this.ctrlPressed = ctrlPressed;
        this.shiftPressed = shiftPressed;
        this.metaPressed = metaPressed;
    }

    public int getWheelVelocity() {
        return wheelVelocity;
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
