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
		this.measure();
	}
	
	protected abstract void onLayout();

}
