/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.components.shapes;

import com.eagerlogic.cubee.client.components.AComponent;
import com.eagerlogic.cubee.client.components.AUserControl;
import com.eagerlogic.cubee.client.properties.AExpression;
import com.eagerlogic.cubee.client.properties.ColorProperty;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.Property;
import com.eagerlogic.cubee.client.styles.Border;
import com.eagerlogic.cubee.client.styles.Color;
import com.eagerlogic.cubee.client.styles.EOrientation;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.DOM;

/**
 *
 * @author dipacs
 */
public final class Triangle extends AComponent {

    private final Property<EOrientation> orientation = new Property<EOrientation>(EOrientation.BOTTOM, false, false);
    private final ColorProperty color = new ColorProperty(Color.RED, false, false);
    private final IntegerProperty width = new IntegerProperty(100, false, false);
    private final IntegerProperty height = new IntegerProperty(100, false, false);

    public Triangle() {
        super(DOM.createDiv());
        getElement().getStyle().setWidth(0, Style.Unit.PX);
        getElement().getStyle().setHeight(0, Style.Unit.PX);
        this.borderProperty().bind(new AExpression<Border>() {

            {
                bind(orientation, color, width, height);
            }

            @Override
            public Border calculate() {
                EOrientation ori = orientation.get();
                int leftWidth = 0;
                int topWidth = 0;
                int rightWidth = 0;
                int bottomWidth = 0;
                Color leftColor = Color.TRANSPARENT;
                Color topColor = Color.TRANSPARENT;
                Color rightColor = Color.TRANSPARENT;
                Color bottomColor = Color.TRANSPARENT;
                switch (ori) {
                    case LEFT:
                        leftWidth = 0;
                        topWidth = height.get() / 2;
                        bottomWidth = height.get() - leftWidth;
                        rightWidth = width.get();
                        rightColor = color.get();
                        break;
                    case TOP:
                        topWidth = 0;
                        leftWidth = width.get() / 2;
                        rightWidth = width.get() - leftWidth;
                        bottomWidth = height.get();
                        bottomColor = color.get();
                        break;
                    case RIGHT:
                        rightWidth = 0;
                        topWidth = height.get() / 2;
                        bottomWidth = height.get() - leftWidth;
                        leftWidth = width.get();
                        leftColor = color.get();
                        break;
                    case BOTTOM:
                        bottomWidth = 0;
                        leftWidth = width.get() / 2;
                        rightWidth = width.get() - leftWidth;
                        topWidth = height.get();
                        topColor = color.get();
                        break;
                }
                getElement().getStyle().setWidth(0, Style.Unit.PX);
                getElement().getStyle().setHeight(0, Style.Unit.PX);
                return new Border(leftWidth, topWidth, rightWidth, bottomWidth, leftColor, topColor, rightColor, bottomColor, 0, 0, 0, 0);
            }
        });
    }

    public Property<EOrientation> orientationProperty() {
        return orientation;
    }

    public final EOrientation getOrientation() {
        return orientation.get();
    }

    public final void setOrientation(EOrientation newValue) {
        orientation.set(newValue);
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public Color getColor() {
        return color.get();
    }

    public ColorProperty colorProperty() {
        return color;
    }

    public void setHeight(Integer height) {
        this.height.set(height);
    }

    public Integer getHeight() {
        return this.height.get();
    }

    public void setWidth(Integer width) {
        this.width.set(width);
    }

    public Integer getWidth() {
        return this.width.get();
    }

    public IntegerProperty heightProperty() {
        return height;
    }

    public IntegerProperty widthProperty() {
        return width;
    }

}
