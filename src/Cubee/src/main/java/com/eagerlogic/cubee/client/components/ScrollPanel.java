package com.eagerlogic.cubee.client.components;

import java.util.Random;

import com.eagerlogic.cubee.client.properties.AExpression;
import com.eagerlogic.cubee.client.properties.IChangeListener;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.Property;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;

/**
 * 
 * @author dipacs
 */
public final class ScrollPanel extends AUserControl {

	private final IntegerProperty width = new IntegerProperty(100, false);
	private final IntegerProperty height = new IntegerProperty(100, false);
	private final Property<AComponent> content = new Property<AComponent>(null);
	private final Property<EScrollBarPolicy> hScrollPolicy = new Property<EScrollBarPolicy>(EScrollBarPolicy.AUTO, false);
	private final Property<EScrollBarPolicy> vScrollPolicy = new Property<EScrollBarPolicy>(EScrollBarPolicy.AUTO, false);
	private final IntegerProperty scrollWidth = new IntegerProperty(0, false, true);
	private final IntegerProperty scrollHeight = new IntegerProperty(0, false, true);
	private final IntegerProperty hScrollPos = new IntegerProperty(0, false, true);
	private final IntegerProperty vScrollPos = new IntegerProperty(0, false, true);
	private final IntegerProperty maxHScrollPos = new IntegerProperty(0, false, true);
	private final IntegerProperty maxVScrollPos = new IntegerProperty(0, false, true);
	private final IntegerProperty maxHScrollPosWriter = new IntegerProperty(0, false, false);
	private final IntegerProperty maxVScrollPosWriter = new IntegerProperty(0, false, false);

	private final AExpression<Integer> calculateScrollWidthExp = new AExpression<Integer>() {

		{
			bind(content);
		}

		@Override
		public Integer calculate() {
			if (content.get() == null) {
				return 0;
			}
			return content.get().boundsWidthProperty().get();
		}
	};
	private final AExpression<Integer> calculateScrollHeightExp = new AExpression<Integer>() {

		{
			bind(content);
		}

		@Override
		public Integer calculate() {
			if (content.get() == null) {
				return 0;
			}
			return content.get().boundsHeightProperty().get();
		}
	};

	public ScrollPanel() {
		super.widthProperty().bind(this.width);
		super.heightProperty().bind(this.height);
		getElement().getStyle().clearOverflow();
		getElement().setId("ScrollPanel" + new Random().nextInt());

		scrollWidth.initReadonlyBind(calculateScrollWidthExp);
		scrollHeight.initReadonlyBind(calculateScrollHeightExp);

		maxHScrollPos.initReadonlyBind(maxHScrollPosWriter);
		maxVScrollPos.initReadonlyBind(maxVScrollPosWriter);

		maxHScrollPosWriter.bind(new AExpression<Integer>() {

			{
				bind(clientWidthProperty(), scrollWidth);
			}

			@Override
			public Integer calculate() {
				return (scrollWidth.get() - clientWidthProperty().get());
			}
		});

		maxVScrollPosWriter.bind(new AExpression<Integer>() {

			{
				bind(clientHeightProperty(), scrollHeight);
			}

			@Override
			public Integer calculate() {
				return (scrollHeight.get() - clientHeightProperty().get());
			}
		});

		content.addChangeListener(new IChangeListener() {

			@Override
			public void onChanged(Object sender) {
				getChildren().clear();
				calculateScrollWidthExp.unbind();
				calculateScrollWidthExp.bind(content);
				if (content.get() != null) {
					calculateScrollWidthExp.bind(content.get()
							.boundsWidthProperty());
				}

				calculateScrollHeightExp.unbind();
				calculateScrollHeightExp.bind(content);
				if (content.get() != null) {
					calculateScrollHeightExp.bind(content.get()
							.boundsHeightProperty());
				}
				
				if (content.get() != null) {
					getChildren().add(content.get());
				}
			}
		});

		DOM.setEventListener(this.getElement(), new EventListener() {

			@Override
			public void onBrowserEvent(Event event) {
				if (event.equals(Event.ONSCROLL)) {
					hScrollPos.set(getElement().getScrollLeft());
					vScrollPos.set(getElement().getScrollTop());
				}
			}
		});

		hScrollPos.addChangeListener(new IChangeListener() {

			@Override
			public void onChanged(Object sender) {
				getElement().setScrollLeft(hScrollPos.get());
			}
		});
		vScrollPos.addChangeListener(new IChangeListener() {

			@Override
			public void onChanged(Object sender) {
				getElement().setScrollTop(vScrollPos.get());
			}
		});

		hScrollPolicy.addChangeListener(new IChangeListener() {

			@Override
			public void onChanged(Object sender) {
				switch (hScrollPolicy.get()) {
				case AUTO:
					getElement().getStyle().setOverflowX(Overflow.AUTO);
					break;
				case HIDDEN:
					getElement().getStyle().setOverflowX(Overflow.HIDDEN);
					break;
				case VISBLE:
					getElement().getStyle().setOverflowX(Overflow.SCROLL);
					break;
				}
			}
		});
		hScrollPolicy.invalidate();
		vScrollPolicy.addChangeListener(new IChangeListener() {

			@Override
			public void onChanged(Object sender) {
				switch (hScrollPolicy.get()) {
				case AUTO:
					getElement().getStyle().setOverflowY(Overflow.AUTO);
					break;
				case HIDDEN:
					getElement().getStyle().setOverflowY(Overflow.HIDDEN);
					break;
				case VISBLE:
					getElement().getStyle().setOverflowY(Overflow.SCROLL);
					break;
				}
			}
		});
		vScrollPolicy.invalidate();
	}

	@Override
	public IntegerProperty widthProperty() {
		return width;
	}

	@Override
	public IntegerProperty heightProperty() {
		return height;
	}

	public Property<AComponent> contentProperty() {
		return content;
	}

	public Property<EScrollBarPolicy> hScrollPolicyProperty() {
		return hScrollPolicy;
	}

	public Property<EScrollBarPolicy> vScrollPolicyProperty() {
		return vScrollPolicy;
	}

	public IntegerProperty scrollWidthProperty() {
		return scrollWidth;
	}

	public IntegerProperty scrollHeightProperty() {
		return scrollHeight;
	}

	public IntegerProperty hScrollPosProperty() {
		return hScrollPos;
	}

	public IntegerProperty vScrollPosProperty() {
		return vScrollPos;
	}

	public IntegerProperty maxHScrollPosProperty() {
		return maxHScrollPos;
	}

	public IntegerProperty maxVScrollPosProperty() {
		return maxVScrollPos;
	}

	// public static class StyleClass<T extends ScrollPanel> extends
	// AUserControl.StyleClass<T> {
	//
	// private final Style<EScrollBarPolicy> horizontalScrollBarPolicy = new
	// Style<EScrollBarPolicy>(null, false);
	// private final Style<EScrollBarPolicy> verticalScrollBarPolicy = new
	// Style<EScrollBarPolicy>(null, false);
	// private final Style<ABackground> scrollBarBackground = new
	// Style<ABackground>(null, false);
	//
	// @Override
	// public void apply(T component) {
	// super.apply(component);
	//
	// horizontalScrollBarPolicy.apply(component.horizontalScrollBarPolicyProperty());
	// verticalScrollBarPolicy.apply(component.verticalScrollBarPolicyProperty());
	// scrollBarBackground.apply(component.scrollBarBackgroundProperty());
	// }
	//
	// @Override
	// public Style<ABackground> getBackground() {
	// return super.getBackground();
	// }
	//
	// @Override
	// public Style<Integer> getHeight() {
	// return super.getHeight();
	// }
	//
	// @Override
	// public Style<Integer> getWidth() {
	// return super.getWidth();
	// }
	//
	// @Override
	// public Style<Integer> getMaxHeight() {
	// return super.getMaxHeight();
	// }
	//
	// @Override
	// public Style<Integer> getMaxWidth() {
	// return super.getMaxWidth();
	// }
	//
	// @Override
	// public Style<Integer> getMinHeight() {
	// return super.getMinHeight();
	// }
	//
	// @Override
	// public Style<Integer> getMinWidth() {
	// return super.getMinWidth();
	// }
	//
	// public Style<EScrollBarPolicy> getHorizontalScrollBarPolicy() {
	// return horizontalScrollBarPolicy;
	// }
	//
	// public Style<EScrollBarPolicy> getVerticalScrollBarPolicy() {
	// return verticalScrollBarPolicy;
	// }
	//
	// public Style<ABackground> getScrollBarBackground() {
	// return scrollBarBackground;
	// }
	//
	// }
	//
	// private final Property<EScrollBarPolicy> horizontalScrollBarPolicy = new
	// Property<EScrollBarPolicy>(EScrollBarPolicy.AUTO, false, false);
	// private final Property<EScrollBarPolicy> verticalScrollBarPolicy = new
	// Property<EScrollBarPolicy>(EScrollBarPolicy.AUTO, false, false);
	// private final BackgroundProperty scrollBarBackground = new
	// BackgroundProperty(new ColorBackground(Color.getArgbColor(0x60000000)),
	// true, false);
	// private final IntegerProperty scrollWidth = new IntegerProperty(0, false,
	// true);
	// private final IntegerProperty scrollHeight = new IntegerProperty(0,
	// false, true);
	// private final DoubleProperty horizontalScrollPosition = new
	// DoubleProperty(0.0, false, false);
	// private final DoubleProperty verticalScrollPosition = new
	// DoubleProperty(0.0, false, false);
	// private final AUserControl wheelPanel = new AUserControl() {
	// @Override
	// protected boolean onPointerEventFallingDown(int screenX, int screenY, int
	// x, int y, int wheelVelocity, boolean altPressed, boolean ctrlPressed,
	// boolean shiftPressed, boolean metaPressed, int type) {
	// if (type == MouseEventTypes.TYPE_MOUSE_WHEEL) {
	// this.onMouseWheelEvent().fireEvent(new MouseWheelEventArgs(wheelVelocity,
	// altPressed, ctrlPressed, shiftPressed, metaPressed, this));
	// return true;
	// }
	// return false;
	// }
	// };
	// private final Panel hScrollBar = new Panel();
	// private final Panel vScrollBar = new Panel();
	// private final Panel contentPanel = new Panel();
	// private AComponent content;
	// private int vScrollStartPos = -1;
	// private double vScrollStartValue = 0;
	// private int hScrollStartPos = -1;
	// private double hScrollStartValue = 0;
	//
	// public ScrollPanel() {
	// wheelPanel.getElement().getStyle().setProperty("pointerEvents", "none");
	// this.getChildren().add(contentPanel);
	// this.getChildren().add(vScrollBar);
	// this.getChildren().add(hScrollBar);
	// this.getChildren().add(wheelPanel);
	//
	// contentPanel.translateXProperty().bind(new AExpression<Integer>() {
	// {
	// this.bind(horizontalScrollPosition);
	// this.bind(boundsWidthProperty());
	// this.bind(scrollWidth);
	// }
	//
	// @Override
	// public Integer calculate() {
	// if (scrollWidth.get() <= boundsWidthProperty().get()) {
	// return 0;
	// }
	// int res = -((int) ((scrollWidth.get() - boundsWidthProperty().get()) *
	// horizontalScrollPosition.get()));
	// if (res > 0) {
	// res = 0;
	// }
	// if (res < boundsWidthProperty().get() - scrollWidth.get()) {
	// res = scrollWidth.get() - boundsWidthProperty().get();
	// }
	// return res;
	// }
	// });
	// contentPanel.translateYProperty().bind(new AExpression<Integer>() {
	// {
	// this.bind(verticalScrollPosition);
	// this.bind(boundsHeightProperty());
	// this.bind(scrollHeight);
	// }
	//
	// @Override
	// public Integer calculate() {
	// if (scrollHeight.get() <= boundsHeightProperty().get()) {
	// return 0;
	// }
	// int res = -((int) ((scrollHeight.get() - boundsHeightProperty().get()) *
	// verticalScrollPosition.get()));
	// if (res > 0) {
	// res = 0;
	// }
	// if (res < boundsHeightProperty().get() - scrollHeight.get()) {
	// res = scrollHeight.get() - boundsHeightProperty().get();
	// }
	// return res;
	// }
	// });
	// vScrollBar.translateXProperty().bind(new AExpression<Integer>() {
	// {
	// this.bind(boundsWidthProperty());
	// this.bind(vScrollBar.boundsWidthProperty());
	// }
	//
	// @Override
	// public Integer calculate() {
	// return boundsWidthProperty().get() -
	// vScrollBar.boundsWidthProperty().get();
	// }
	// });
	// vScrollBar.translateYProperty().bind(new AExpression<Integer>() {
	// {
	// this.bind(boundsHeightProperty());
	// this.bind(vScrollBar.boundsHeightProperty());
	// this.bind(verticalScrollPosition);
	// }
	//
	// @Override
	// public Integer calculate() {
	// return (int) ((boundsHeightProperty().get() -
	// vScrollBar.boundsHeightProperty().get())
	// * verticalScrollPosition.get());
	// }
	// });
	// hScrollBar.translateYProperty().bind(new AExpression<Integer>() {
	// {
	// this.bind(boundsHeightProperty());
	// this.bind(hScrollBar.boundsHeightProperty());
	// }
	//
	// @Override
	// public Integer calculate() {
	// return boundsHeightProperty().get() -
	// hScrollBar.boundsHeightProperty().get();
	// }
	// });
	// hScrollBar.translateXProperty().bind(new AExpression<Integer>() {
	// {
	// this.bind(boundsWidthProperty());
	// this.bind(hScrollBar.boundsWidthProperty());
	// this.bind(horizontalScrollPosition);
	// }
	//
	// @Override
	// public Integer calculate() {
	// return (int) ((boundsWidthProperty().get() -
	// hScrollBar.boundsWidthProperty().get())
	// * horizontalScrollPosition.get());
	// }
	// });
	//
	// vScrollBar.heightProperty().bind(new AExpression<Integer>() {
	// {
	// this.bind(scrollHeight);
	// this.bind(boundsHeightProperty());
	// }
	//
	// @Override
	// public Integer calculate() {
	// if (scrollHeight.get() <= boundsHeightProperty().get()) {
	// return boundsHeightProperty().get();
	// }
	//
	// int res = ((int) ((boundsHeightProperty().get().doubleValue() /
	// scrollHeight.get())
	// * boundsHeightProperty().get()));
	// if (res < 20) {
	// res = 20;
	// }
	// return res;
	// }
	// });
	// hScrollBar.widthProperty().bind(new AExpression<Integer>() {
	// {
	// this.bind(scrollWidth);
	// this.bind(boundsWidthProperty());
	// }
	//
	// @Override
	// public Integer calculate() {
	// if (scrollWidth.get() <= boundsWidthProperty().get()) {
	// return boundsWidthProperty().get();
	// }
	//
	// int res = ((int) ((boundsWidthProperty().get().doubleValue() /
	// scrollWidth.get())
	// * boundsWidthProperty().get()));
	// if (res < 20) {
	// res = 20;
	// }
	// return res;
	// }
	// });
	//
	// vScrollBar.onMouseDownEvent().addListener(new
	// IEventListener<MouseDownEventArgs>() {
	// @Override
	// public void onFired(MouseDownEventArgs args) {
	// vScrollStartPos = args.getScreenY();
	// vScrollStartValue = verticalScrollPosition.get();
	// }
	// });
	// vScrollBar.onMouseDragEvent().addListener(new
	// IEventListener<MouseDragEventArgs>() {
	// @Override
	// public void onFired(MouseDragEventArgs args) {
	// if (vScrollStartPos < 0) {
	// return;
	// }
	// int delta = args.getScreenY() - vScrollStartPos;
	// int sw = boundsHeightProperty().get() -
	// vScrollBar.boundsHeightProperty().get();
	// double valueDelta = delta / ((double) sw);
	// double newValue = vScrollStartValue + valueDelta;
	// if (newValue < 0) {
	// newValue = 0;
	// }
	// if (newValue > 1) {
	// newValue = 1;
	// }
	// verticalScrollPosition.set(newValue);
	// }
	// });
	// vScrollBar.onMouseUpEvent().addListener(new
	// IEventListener<MouseUpEventArgs>() {
	// @Override
	// public void onFired(MouseUpEventArgs args) {
	// if (vScrollStartPos < 0) {
	// return;
	// }
	// int delta = args.getScreenY() - vScrollStartPos;
	// int sw = boundsHeightProperty().get() -
	// vScrollBar.boundsHeightProperty().get();
	// double valueDelta = delta / ((double) sw);
	// double newValue = vScrollStartValue + valueDelta;
	// if (newValue < 0) {
	// newValue = 0;
	// }
	// if (newValue > 1) {
	// newValue = 1;
	// }
	// verticalScrollPosition.set(newValue);
	// }
	// });
	// hScrollBar.onMouseDownEvent().addListener(new
	// IEventListener<MouseDownEventArgs>() {
	// @Override
	// public void onFired(MouseDownEventArgs args) {
	// hScrollStartPos = args.getScreenX();
	// hScrollStartValue = horizontalScrollPosition.get();
	// }
	// });
	// hScrollBar.onMouseDragEvent().addListener(new
	// IEventListener<MouseDragEventArgs>() {
	// @Override
	// public void onFired(MouseDragEventArgs args) {
	// if (hScrollStartPos < 0) {
	// return;
	// }
	// int delta = args.getScreenX() - hScrollStartPos;
	// int sw = boundsWidthProperty().get() -
	// hScrollBar.boundsWidthProperty().get();
	// double valueDelta = delta / ((double) sw);
	// double newValue = hScrollStartValue + valueDelta;
	// if (newValue < 0) {
	// newValue = 0;
	// }
	// if (newValue > 1) {
	// newValue = 1;
	// }
	// horizontalScrollPosition.set(newValue);
	// }
	// });
	// hScrollBar.onMouseUpEvent().addListener(new
	// IEventListener<MouseUpEventArgs>() {
	// @Override
	// public void onFired(MouseUpEventArgs args) {
	// if (hScrollStartPos < 0) {
	// return;
	// }
	// int delta = args.getScreenX() - hScrollStartPos;
	// int sw = boundsWidthProperty().get() -
	// hScrollBar.boundsWidthProperty().get();
	// double valueDelta = delta / ((double) sw);
	// double newValue = hScrollStartValue + valueDelta;
	// if (newValue < 0) {
	// newValue = 0;
	// }
	// if (newValue > 1) {
	// newValue = 1;
	// }
	// horizontalScrollPosition.set(newValue);
	// }
	// });
	// vScrollBar.visibleProperty().bind(new AExpression<Boolean>() {
	// {
	// this.bind(verticalScrollBarPolicy);
	// this.bind(boundsHeightProperty());
	// this.bind(scrollHeight);
	// }
	//
	// @Override
	// public Boolean calculate() {
	// if (verticalScrollBarPolicy.get() == EScrollBarPolicy.AUTO) {
	// return scrollHeight.get() > boundsHeightProperty().get();
	// } else if (verticalScrollBarPolicy.get() == EScrollBarPolicy.HIDDEN) {
	// return false;
	// } else {
	// return true;
	// }
	// }
	// });
	// hScrollBar.visibleProperty().bind(new AExpression<Boolean>() {
	// {
	// this.bind(horizontalScrollBarPolicy);
	// this.bind(boundsWidthProperty());
	// this.bind(scrollWidth);
	// }
	//
	// @Override
	// public Boolean calculate() {
	// if (horizontalScrollBarPolicy.get() == EScrollBarPolicy.AUTO) {
	// return scrollWidth.get() > boundsWidthProperty().get();
	// } else if (horizontalScrollBarPolicy.get() == EScrollBarPolicy.HIDDEN) {
	// return false;
	// } else {
	// return true;
	// }
	// }
	// });
	//
	// wheelPanel.onMouseWheelEvent().addListener(new
	// IEventListener<MouseWheelEventArgs>() {
	// @Override
	// public void onFired(MouseWheelEventArgs args) {
	// if (args.isShiftPressed()) {
	// double pixels = 12 * args.getWheelVelocity();
	// double delta = pixels / scrollWidth.get();
	// double newValue = horizontalScrollPosition.get() + delta;
	// if (newValue < 0) {
	// newValue = 0;
	// }
	// if (newValue > 1) {
	// newValue = 1;
	// }
	// horizontalScrollPosition.set(newValue);
	// } else {
	// double pixels = 12 * args.getWheelVelocity();
	// double delta = pixels / scrollHeight.get();
	// double newValue = verticalScrollPosition.get() + delta;
	// if (newValue < 0) {
	// newValue = 0;
	// }
	// if (newValue > 1) {
	// newValue = 1;
	// }
	// verticalScrollPosition.set(newValue);
	// }
	// }
	// });
	//
	// hScrollBar.backgroundProperty().bind(scrollBarBackground);
	// vScrollBar.backgroundProperty().bind(scrollBarBackground);
	// vScrollBar.widthProperty().set(10);
	// hScrollBar.heightProperty().set(10);
	// hScrollBar.borderProperty().set(new Border(0, Color.TRANSPARENT, 0));
	// vScrollBar.borderProperty().set(new Border(0, Color.TRANSPARENT, 0));
	//
	// scrollWidth.initReadonlyBind(contentPanel.boundsWidthProperty());
	// scrollHeight.initReadonlyBind(contentPanel.boundsHeightProperty());
	//
	// wheelPanel.widthProperty().bind(this.widthProperty());
	// wheelPanel.heightProperty().bind(this.heightProperty());
	// }
	//
	// public void setContent(AComponent component) {
	// this.contentPanel.getChildren().clear();
	// if (component != null) {
	// this.contentPanel.getChildren().add(component);
	// }
	// this.content = component;
	// }
	//
	// public AComponent getContent() {
	// return this.content;
	// }
	//
	// public final Property<EScrollBarPolicy>
	// horizontalScrollBarPolicyProperty() {
	// return horizontalScrollBarPolicy;
	// }
	//
	// public final Property<EScrollBarPolicy> verticalScrollBarPolicyProperty()
	// {
	// return verticalScrollBarPolicy;
	// }
	//
	// public final IntegerProperty scrollWidthProperty() {
	// return scrollWidth;
	// }
	//
	// public final IntegerProperty scrollHeightProperty() {
	// return scrollHeight;
	// }
	//
	// public final DoubleProperty horizontalScrollPositionProperty() {
	// return horizontalScrollPosition;
	// }
	//
	// public final DoubleProperty verticalScrollPositionProperty() {
	// return verticalScrollPosition;
	// }
	//
	// @Override
	// public final IntegerProperty widthProperty() {
	// return super.widthProperty();
	// }
	//
	// @Override
	// public final IntegerProperty heightProperty() {
	// return super.heightProperty();
	// }
	//
	// @Override
	// public final BackgroundProperty backgroundProperty() {
	// return super.backgroundProperty();
	// }
	//
	// public BackgroundProperty scrollBarBackgroundProperty() {
	// return scrollBarBackground;
	// }
	//
	// @Override
	// public IntegerProperty minWidthProperty() {
	// return super.minWidthProperty();
	// }
	//
	// @Override
	// public IntegerProperty minHeightProperty() {
	// return super.minHeightProperty();
	// }
	//
	// @Override
	// public IntegerProperty maxWidthProperty() {
	// return super.maxWidthProperty();
	// }
	//
	// @Override
	// public IntegerProperty maxHeightProperty() {
	// return super.maxHeightProperty();
	// }

}
