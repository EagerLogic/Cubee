package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.EventQueue;
import com.eagerlogic.cubee.client.events.EventArgs;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.utils.ARunOnce;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Window;

/**
 *
 * @author dipacs
 */
public final class CubeePanel extends ALayout {

	private ARunOnce layoutRunOnce;
	
	private AComponent rootComponent;

	public CubeePanel() {
		super(DOM.createDiv());
		getElement().getStyle().setLeft(0, Style.Unit.PX);
		getElement().getStyle().setTop(0, Style.Unit.PX);
		getElement().getStyle().setRight(0, Style.Unit.PX);
		getElement().getStyle().setBottom(0, Style.Unit.PX);
		getElement().getStyle().setBackgroundColor("#f0f0f0");
		getElement().getStyle().setProperty("pointerEvents", "all");
		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				requestLayout();
			}
		});
		
		DOM.setEventListener((com.google.gwt.user.client.Element) getElement(), new EventListener() {

			@Override
			public void onBrowserEvent(com.google.gwt.user.client.Event event) {
				switch (event.getTypeInt()) {
					case com.google.gwt.user.client.Event.ONMOUSEDOWN:
					case com.google.gwt.user.client.Event.ONMOUSEMOVE:
					case com.google.gwt.user.client.Event.ONMOUSEUP:
					case com.google.gwt.user.client.Event.ONMOUSEOVER:
					case com.google.gwt.user.client.Event.ONMOUSEOUT:
					case com.google.gwt.user.client.Event.ONMOUSEWHEEL:
						int x = event.getClientX();
						int y = event.getClientY();
						int wheelVelocity = event.getMouseWheelVelocityY();
						doPointerEventClimbingUp(x, y, 0, 0, x, y, wheelVelocity, 
								event.getAltKey(), event.getCtrlKey(), event.getShiftKey(), event.getMetaKey(), 
								event.getTypeInt());
						break;
				}
			}
		});
		// sinking all the events
		DOM.sinkEvents((com.google.gwt.user.client.Element) getElement(), -1);
		
		requestLayout();
	}

	@Override
	public void requestLayout() {
		if (layoutRunOnce == null) {
			layoutRunOnce = new ARunOnce() {
				@Override
				protected void onRun() {
					// TODO remove sout
					System.out.println("!!! LAYING OUT !!!");
					layout();
				}
			};
		}
		layoutRunOnce.run();
	}

	public AComponent getRootComponent() {
		return rootComponent;
	}

	public void setRootComponent(AComponent rootComponent) {
		getChildren().clear();
		if (rootComponent != null) {
			getChildren().add(rootComponent);
		}
		this.rootComponent = rootComponent;
	}

	@Override
	protected final void onChildAdded(AComponent child) {
		if (child != null) {
			getElement().appendChild(child.getElement());
		}
	}

	@Override
	protected final void onChildRemoved(AComponent child, int index) {
		if (child != null) {
			getElement().removeChild(child.getElement());
		}
	}

	@Override
	protected final void onChildrenCleared() {
		Element root = getElement();
		Element e = getElement().getFirstChildElement();
		while (e != null) {
			root.removeChild(e);
			e = root.getFirstChildElement();
		}
	}

	@Override
	protected void onLayout() {
		// nothing to do here
	}
}
