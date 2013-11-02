package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.events.EventArgs;
import com.eagerlogic.cubee.client.styles.Color;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

/**
 *
 * @author dipacs
 */
public class ADialog {

	private final boolean modal;
	private final boolean autoHide;
	private final Color glassColor;
	private AComponent rootComponent;
	private Element glass;
	private boolean visible = false;

	public ADialog(boolean modal, boolean autoHide) {
		this(modal, autoHide, Color.getArgbColor(0x80000000));
	}

	public ADialog(final boolean modal, final boolean autoHide, Color glassColor) {
		this.modal = modal;
		this.autoHide = autoHide;
		this.glassColor = glassColor;

		glass = DOM.createDiv();
		glass.getStyle().setPosition(Style.Position.FIXED);
		glass.getStyle().setLeft(0, Style.Unit.PX);
		glass.getStyle().setTop(0, Style.Unit.PX);
		glass.getStyle().setRight(0, Style.Unit.PX);
		glass.getStyle().setBottom(0, Style.Unit.PX);
		if (glassColor != null) {
			glass.getStyle().setBackgroundColor(glassColor.toCSS());
		}

		DOM.setEventListener((com.google.gwt.user.client.Element) glass, new EventListener() {
			@Override
			public void onBrowserEvent(com.google.gwt.user.client.Event event) {
				switch (event.getTypeInt()) {
					case com.google.gwt.user.client.Event.ONCLICK:
						if (modal) {
							event.cancelBubble(true);
						}
						if (autoHide) {
							close();
						}
						break;
				}
			}
		});
		// sinking all the events
		DOM.sinkEvents((com.google.gwt.user.client.Element) glass, Event.ONCLICK);
		
		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				centerRoot();
			}
		});
	}
	
	private void centerRoot() {
		if (this.rootComponent != null && this.visible) {
			int ww = Window.getClientWidth();
			int wh = Window.getClientHeight();
			int rw = rootComponent.boundsWidthProperty().get();
			int rh = rootComponent.boundsHeightProperty().get();
			rootComponent.setPosition((ww - rw) / 2, (wh - rh) / 2);
		}
	}

	protected void show() {
		if (visible) {
			throw new IllegalStateException("This dialog is already shown.");
		}
		RootPanel.get().getElement().appendChild(glass);
		visible = true;
		centerRoot();
	}

	protected void close() {
		if (!visible) {
			throw new IllegalStateException("This dialog isn't shown.");
		}
		RootPanel.get().getElement().removeChild(glass);
		visible = false;
	}

	protected final AComponent getRootComponent() {
		return rootComponent;
	}

	protected final void setRootComponent(AComponent rootComponent) {
		if (this.rootComponent != null) {
			glass.removeChild(this.rootComponent.getElement());
		}
		if (rootComponent != null) {
			glass.appendChild(rootComponent.getElement());
			rootComponent.layout();
		}
		this.rootComponent = rootComponent;
		centerRoot();
	}

	public boolean isModal() {
		return modal;
	}

	public boolean isAutoHide() {
		return autoHide;
	}

	public Color getGlassColor() {
		return glassColor;
	}

	public boolean isVisible() {
		return visible;
	}
}
