package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.components.AComponent;

/**
 *
 * @author dipacs
 */
final class MouseDownEventLog {

    private final AComponent component;
    private final int screenX;
    private final int screenY;
    private final int x;
    private final int y;
    private final long timeStamp;

    public MouseDownEventLog(AComponent component, int screenX, int screenY, int x, int y) {
        this.component = component;
        this.screenX = screenX;
        this.screenY = screenY;
        this.x = x;
        this.y = y;
        this.timeStamp = System.currentTimeMillis();
    }

    public AComponent getComponent() {
        return component;
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

    public long getTimeStamp() {
        return timeStamp;
    }

}
