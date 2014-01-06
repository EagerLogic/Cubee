package com.eagerlogic.cubee.client.styles;

/**
 *
 * @author dipacs
 */
public enum ECursor {

    /**
     * Default. The browser sets a cursor.
     */
    AUTO("auto"),
    /**
     * The cursor render as a crosshair.
     */
    CROSS_HAIR("crosshair"),
    /**
     * The default cursor.
     */
    DEFAULT("default"),
    /**
     * The cursor indicates that an edge of a box is to be moved right (east).
     */
    E_RESIZE("e-resize"),
    /**
     * The cursor indicates that help is available.
     */
    HELP("help"),
    /**
     * The cursor indicates something that should be moved.
     */
    MOVE("move"),
    /**
     * The cursor indicates that an edge of a box is to be moved up (north).
     */
    N_RESIZE("n-resize"),
    /**
     * The cursor indicates that an edge of a box is to be moved up and right (north/east).
     */
    NE_RESIZE("ne-resize"),
    /**
     * The cursor indicates that an edge of a box is to be moved up and left (north/west).
     */
    NW_RESIZE("nw-resize"),
    /**
     * The cursor render as a pointer.
     */
    POINTER("pointer"),
    /**
     * The cursor indicates that the program is busy (in progress).
     */
    PROGRESS("progress"),
    /**
     * The cursor indicates that an edge of a box is to be moved down (south).
     */
    S_RESIZE("s-resize"),
    /**
     * The cursor indicates that an edge of a box is to be moved down and right (south/east).
     */
    SE_RESIZE("se-resize"),
    /**
     * The cursor indicates that an edge of a box is to be moved down and left (south/west).
     */
    SW_RESIZE("sw-resize"),
    /**
     * The cursor indicates text.
     */
    TEXT("text"),
    /**
     * The cursor indicates that an edge of a box is to be moved left (west).
     */
    W_RESIZE("w-resize"),
    /**
     * The cursor indicates that the program is busy.
     */
    WAIT("wait"),
    /**
     * The cursor is inherited from the parent of the component.
     */
    INHERIT("inherit");

    private final String cssValue;

    private ECursor(String cssValue) {
        this.cssValue = cssValue;
    }

    public String getCssValue() {
        return cssValue;
    }

}
