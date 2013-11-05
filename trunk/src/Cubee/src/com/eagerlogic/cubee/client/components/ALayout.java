package com.eagerlogic.cubee.client.components;

import com.google.gwt.dom.client.Element;

/**
 *
 * @author dipacs
 */
public abstract class ALayout extends AComponent {

	private final LayoutChildren children = new LayoutChildren(this);

	public ALayout(Element element) {
		super(element);
	}

	protected LayoutChildren getChildren() {
		return children;
	}

	protected abstract void onChildAdded(AComponent child);

	protected abstract void onChildRemoved(AComponent child, int index);

	protected abstract void onChildrenCleared();

	@Override
	public final void layout() {
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
	final boolean doPointerEventClimbingUp(int screenX, int screenY, int parentScreenX, int parentScreenY, int x, int y, int wheelVelocity, boolean altPressed, boolean ctrlPressed, boolean shiftPressed, boolean metaPressed, int type) {
		if (!handlePointerProperty().get()) {
			return false;
		}
		if (!enabledProperty().get()) {
			return true;
		}
		if (!visibleProperty().get()) {
			return false;
		}
		if (onPointerEventClimbingUp(screenX, screenY, parentScreenX, parentScreenY, x, y, wheelVelocity, altPressed,
				ctrlPressed, shiftPressed, metaPressed, type)) {
			for (int i = getChildren().size() - 1; i >= 0; i--) {
				AComponent child = getChildren().get(i);
				if (child != null) {
					int thisX = parentScreenX + getLeft() + translateXProperty().get();
					int thisY = parentScreenY + getTop() + translateYProperty().get();
					int childX = screenX - thisX - child.getLeft() - child.translateXProperty().get();
					int childY = screenY - thisY - child.getTop() - child.translateYProperty().get();
					// TODO rotate and scale child points
					// TODO rotate and scale this points
					if (child.isIntersectsPoint(x, y)) {
						// TODO convert points
						if (child.doPointerEventClimbingUp(screenX, screenY, thisX, thisY, childX, childY, wheelVelocity,
								altPressed, ctrlPressed, shiftPressed, metaPressed, type)) {
							return true;
						}
					}
				}
			}
		}
		return onPointerEventFallingDown(screenX, screenY, parentScreenX, parentScreenY, x, y, wheelVelocity, altPressed,
				ctrlPressed, shiftPressed, metaPressed, type);
	}

	/**
	 * Called by the layout after the children of this layout are measured but before this layout measured. You need to
	 * set the size of this layout if needed and set the positions of the children if needed.
	 */
	protected abstract void onLayout();
}
