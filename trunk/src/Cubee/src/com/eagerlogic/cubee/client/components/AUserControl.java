package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.properties.BackgroundProperty;
import com.eagerlogic.cubee.client.properties.IChangeListener;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.Property;
import com.eagerlogic.cubee.client.styles.ABackground;
import com.eagerlogic.cubee.client.styles.BoxShadow;
import com.eagerlogic.cubee.client.styles.Color;
import com.eagerlogic.cubee.client.styles.ColorBackground;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.DOM;

/**
 *
 * @author dipacs
 */
public abstract class AUserControl extends ALayout {

    private IntegerProperty width = new IntegerProperty(null, true, false);
    private IntegerProperty height = new IntegerProperty(null, true, false);
    private BackgroundProperty background = new BackgroundProperty(new ColorBackground(Color.TRANSPARENT), true, false);
    private Property<BoxShadow> shadow = new Property<BoxShadow>(null, true, false);

    public AUserControl() {
        super(DOM.createDiv());
        getElement().getStyle().setOverflow(Style.Overflow.HIDDEN);
        width.addChangeListener(new IChangeListener() {
            @Override
            public void onChanged(Object sender) {
                if (width.get() == null) {
                    getElement().getStyle().clearWidth();
                    getElement().getStyle().setOverflowX(Style.Overflow.HIDDEN);
                } else {
                    getElement().getStyle().setWidth(width.get(), Style.Unit.PX);
                    getElement().getStyle().setOverflowX(Style.Overflow.HIDDEN);
                }
                requestLayout();
            }
        });
        width.invalidate();
        height.addChangeListener(new IChangeListener() {
            @Override
            public void onChanged(Object sender) {
                if (height.get() == null) {
                    getElement().getStyle().clearHeight();
                    getElement().getStyle().setOverflowY(Style.Overflow.HIDDEN);
                } else {
                    getElement().getStyle().setHeight(height.get(), Style.Unit.PX);
                    getElement().getStyle().setOverflowY(Style.Overflow.HIDDEN);
                }
                requestLayout();
            }
        });
        height.invalidate();
        background.addChangeListener(new IChangeListener() {
            @Override
            public void onChanged(Object sender) {
                getElement().getStyle().clearBackgroundColor();
                getElement().getStyle().clearBackgroundImage();
                getElement().getStyle().setProperty("background", "none");
                if (background.get() != null) {
                    background.get().apply(getElement());
                }
            }
        });
        background.invalidate();
        shadow.addChangeListener(new IChangeListener() {

            @Override
            public void onChanged(Object sender) {
                if (shadow.get() == null) {
                    getElement().getStyle().clearProperty("boxShadow");
                } else {
                    shadow.get().apply(getElement());
                }
            }
        });
    }

    protected IntegerProperty widthProperty() {
        return width;
    }

    protected IntegerProperty heightProperty() {
        return height;
    }

    protected BackgroundProperty backgroundProperty() {
        return background;
    }

    protected Property<BoxShadow> shadowProperty() {
        return shadow;
    }

    @Override
    protected final void onChildAdded(AComponent child) {
        if (child != null) {
            getElement().appendChild(child.getElement());
        }
        requestLayout();
    }

    @Override
    protected final void onChildRemoved(AComponent child, int index) {
        if (child != null) {
            getElement().removeChild(child.getElement());
        }
        requestLayout();
    }

    @Override
    protected final void onChildrenCleared() {
        Element root = getElement();
        Element e = getElement().getFirstChildElement();
        while (e != null) {
            root.removeChild(e);
            e = root.getFirstChildElement();
        }
        requestLayout();
    }

    @Override
    protected final void onLayout() {
        if (widthProperty().get() != null && heightProperty().get() != null) {
            setSize(widthProperty().get(), heightProperty().get());
        } else {
            int maxW = 0;
            int maxH = 0;
            for (AComponent component : getChildren()) {
                int cW = component.boundsWidthProperty().get() + component.boundsLeftProperty().get() + component.translateXProperty().get();
                int cH = component.boundsHeightProperty().get() + component.boundsTopProperty().get() + component.translateYProperty().get();

                if (cW > maxW) {
                    maxW = cW;
                }

                if (cH > maxH) {
                    maxH = cH;
                }
            }

            if (widthProperty().get() != null) {
                maxW = widthProperty().get();
            }

            if (heightProperty().get() != null) {
                maxH = heightProperty().get();
            }

            setSize(maxW, maxH);
        }
    }

    protected Integer getWidth() {
        return widthProperty().get();
    }

    protected void setWidth(Integer width) {
        this.widthProperty().set(width);
    }

    protected Integer getHeight() {
        return heightProperty().get();
    }

    protected void setHeight(Integer height) {
        this.heightProperty().set(height);
    }

    protected ABackground getBackground() {
        return backgroundProperty().get();
    }

    protected void setBackground(ABackground background) {
        this.backgroundProperty().set(background);
    }

    protected BoxShadow getShadow() {
        return shadowProperty().get();
    }

    protected void setShadow(BoxShadow shadow) {
        this.shadowProperty().set(shadow);
    }
}
