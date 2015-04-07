package com.eagerlogic.cubee.client.components;

import java.util.LinkedList;
import java.util.List;

import com.eagerlogic.cubee.client.style.styles.Padding;
import com.eagerlogic.cubee.client.utils.Point2D;
import com.google.gwt.dom.client.Element;

/**
 *
 * @author dipacs
 */
public abstract class ALayout extends AComponent {

    private final LayoutChildren children = new LayoutChildren(this);

    public ALayout(Element element) {
        super(element);

        this.applyDefaultStyle(ALayout.class);
    }

    protected LayoutChildren getChildren() {
        return children;
    }

    protected abstract void onChildAdded(AComponent child);

    protected abstract void onChildRemoved(AComponent child, int index);

    protected abstract void onChildrenCleared();

    @Override
    public final void layout() {
        this.needsLayout = false;
        for (AComponent child : getChildren()) {
            if (child != null) {
                if (child.isNeedsLayout()) {
                    child.layout();
                }
            }
        }
        this.onLayout();
        this.measure();
    }

    @Override
    final boolean doPointerEventClimbingUp(int screenX, int screenY, int x, int y, int wheelVelocity,
            boolean altPressed, boolean ctrlPressed, boolean shiftPressed, boolean metaPressed, int type, int button, com.google.gwt.user.client.Event nativeEvent) {
        if (!handlePointerProperty().get()) {
            return false;
        }
        if (!enabledProperty().get()) {
            return true;
        }
        if (!visibleProperty().get()) {
            return false;
        }
        if (onPointerEventClimbingUp(screenX, screenY, x, y, wheelVelocity, altPressed,
                ctrlPressed, shiftPressed, metaPressed, type, button)) {
            for (int i = getChildren().size() - 1; i >= 0; i--) {
                AComponent child = getChildren().get(i);
                if (child != null) {
                    int parentX = x + getElement().getScrollLeft();
                    int parentY = y + getElement().getScrollTop();
                    Padding p = this.paddingProperty().get();
                    if (p != null) {
                        parentX -= p.getLeftPadding();
                        parentY -= p.getTopPadding();
                    }
                    if (child.isIntersectsPoint(parentX, parentY)) {
                        int left = child.getLeft() + child.translateXProperty().get();
                        int top = child.getTop() + child.translateYProperty().get();
                        int tcx = (int) (left + child.measuredWidthProperty().get() * child.transformCenterXProperty().get());
                        int tcy = (int) (top + child.measuredHeightProperty().get() * child.transformCenterYProperty().get());
                        Point2D childPoint = rotatePoint(tcx, tcy, parentX, parentY, -child.rotateProperty().get());
                        int childX = childPoint.getX();
                        int childY =childPoint.getY();
                        childX = childX - left;
                        childY = childY - top;
                        // TODO scale back point
                        if (child.doPointerEventClimbingUp(screenX, screenY, childX, childY, wheelVelocity,
                                altPressed, ctrlPressed, shiftPressed, metaPressed, type, button, nativeEvent)) {
                            return true;
                        }
                    }
                }
            }
        }
        if (pointerTransparentProperty().get()) {
            return false;
        } else {
            return onPointerEventFallingDown(screenX, screenY, x, y, wheelVelocity, altPressed,
                    ctrlPressed, shiftPressed, metaPressed, type, button, nativeEvent);
        }

    }

    private Point2D rotatePoint(int cx, int cy, int x, int y, double angle) {
        angle = (angle * 360) * (Math.PI / 180);
        x = x - cx;
        y = y - cy;
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        int rx = (int) ((cos * x) - (sin * y));
        int ry = (int) ((sin * x) + (cos * y));
        rx = rx + cx;
        ry = ry + cy;

        return new Point2D(rx, ry);
    }

    /**
     * Called by the layout after the children of this layout are measured but before this layout measured. You need to
     * set the size of this layout if needed and set the positions of the children if needed.
     */
    protected abstract void onLayout();

    public final List<AComponent> getComponentsAtPosition(int x, int y) {
        LinkedList<AComponent> res = new LinkedList<AComponent>();
        getComponentsAtPosition(this, x, y, res);
        return res;
    }

    private void getComponentsAtPosition(ALayout root, int x, int y, LinkedList<AComponent> result) {
        if (x >= 0 && x <= root.boundsWidthProperty().get() && y >= 0 && y <= root.boundsHeightProperty().get()) {
            result.addFirst(root);
            for (AComponent component : root.getChildren()) {
                if (component == null) {
                    continue;
                }
                int tx = x - component.getLeft() - component.translateXProperty().get();
                int ty = y - component.getTop() - component.translateYProperty().get();
                if (component instanceof ALayout) {
                    getComponentsAtPosition((ALayout)component, tx, ty, result);
                } else {
                    if (tx >= 0 && tx <= component.boundsWidthProperty().get() && y >= 0 && y <= component.boundsHeightProperty().get()) {
                        result.addFirst(component);
                    }
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        children.clear(true);
    }

    protected void setChildLeft(AComponent child, int left) {
        child.setLeft(left);
    }

    protected void setChildTop(AComponent child, int top) {
        child.setTop(top);
    }

}
