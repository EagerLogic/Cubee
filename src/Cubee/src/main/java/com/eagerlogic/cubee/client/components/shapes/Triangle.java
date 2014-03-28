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
import com.eagerlogic.cubee.client.style.Style;
import com.eagerlogic.cubee.client.style.styles.Border;
import com.eagerlogic.cubee.client.style.styles.Color;
import com.eagerlogic.cubee.client.style.styles.EOrientation;
import com.google.gwt.user.client.DOM;

/**
 *
 * @author dipacs
 */
public final class Triangle extends AUserControl {

    public static class StyleClass<T extends Triangle> extends AUserControl.StyleClass<T> {

        private final Style<EOrientation> orientation = new Style<EOrientation>(null, false);
        private final Style<Color> color = new Style<Color>(null, false);

        public StyleClass() {
        }

        @Override
        public void apply(T component) {
            super.apply(component);

            orientation.apply(component.orientationProperty());
            color.apply(component.colorProperty());
        }

        @Override
        public Style<Integer> getWidth() {
            return super.getWidth();
        }

        @Override
        public Style<Integer> getHeight() {
            return super.getHeight();
        }

        public Style<EOrientation> getOrientation() {
            return orientation;
        }

        public Style<Color> getColor() {
            return color;
        }
    }
    private final Property<EOrientation> orientation = new Property<EOrientation>(EOrientation.BOTTOM, false, false);
    private final ColorProperty color = new ColorProperty(Color.RED, false, false);
    private final IntegerProperty width = new IntegerProperty(100, false, false);
    private final IntegerProperty height = new IntegerProperty(100, false, false);
    private final TriangleInner innerTriangle;

    public Triangle() {
        super.widthProperty().bind(width);
        super.heightProperty().bind(height);
        innerTriangle = new TriangleInner();
        innerTriangle.widthProperty().bind(this.widthProperty());
        innerTriangle.heightProperty().bind(this.heightProperty());
        innerTriangle.orientationProperty().bind(orientation);
        innerTriangle.colorProperty().bind(color);
        this.getChildren().add(innerTriangle);
    }

    public Property<EOrientation> orientationProperty() {
        return orientation;
    }

    public ColorProperty colorProperty() {
        return color;
    }

    @Override
    public IntegerProperty heightProperty() {
        return this.height;
    }

    @Override
    public IntegerProperty widthProperty() {
        return this.width;
    }

    private static class TriangleInner extends AComponent {

        private final Property<EOrientation> orientation = new Property<EOrientation>(EOrientation.BOTTOM, false, false);
        private final ColorProperty color = new ColorProperty(Color.RED, false, false);
        private final IntegerProperty width = new IntegerProperty(100, false, false);
        private final IntegerProperty height = new IntegerProperty(100, false, false);

        public TriangleInner() {
            // TODO box into an AUserControl and handle correct translation
            super(DOM.createDiv());
            getElement().getStyle().setWidth(0, com.google.gwt.dom.client.Style.Unit.PX);
            getElement().getStyle().setHeight(0, com.google.gwt.dom.client.Style.Unit.PX);
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
                            bottomWidth = height.get() - topWidth;
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
                            bottomWidth = height.get() - topWidth;
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
                    getElement().getStyle().setWidth(0, com.google.gwt.dom.client.Style.Unit.PX);
                    getElement().getStyle().setHeight(0, com.google.gwt.dom.client.Style.Unit.PX);
//                    TriangleInner.this.translateXProperty().set(-leftWidth);
//                    TriangleInner.this.translateYProperty().set(topWidth);
                    return new Border(leftWidth, topWidth, rightWidth, bottomWidth, leftColor, topColor, rightColor, bottomColor, 0, 0, 0, 0);
                }
            });
        }

        public Property<EOrientation> orientationProperty() {
            return orientation;
        }

        public ColorProperty colorProperty() {
            return color;
        }

        public IntegerProperty heightProperty() {
            return height;
        }

        public IntegerProperty widthProperty() {
            return width;
        }
    }
}
