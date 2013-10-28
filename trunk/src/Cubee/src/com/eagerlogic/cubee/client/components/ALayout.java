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
			if (child.isNeedsLayout()) {
				child.layout();
			}
		}
		this.onLayout();
		this.measure();
	}
	
	/**
	 * Called by the layout after the children of this layout are measured but before this layout measured. You need to 
	 * set the size of this layout if needed and set the positions of the children if needed.
	 */
	protected abstract void onLayout();

}
