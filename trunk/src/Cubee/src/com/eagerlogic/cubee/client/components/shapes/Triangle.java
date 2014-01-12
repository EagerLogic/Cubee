/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eagerlogic.cubee.client.components.shapes;

import com.eagerlogic.cubee.client.components.AUserControl;
import com.eagerlogic.cubee.client.properties.AExpression;
import com.eagerlogic.cubee.client.properties.ColorProperty;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.Property;
import com.eagerlogic.cubee.client.styles.Border;
import com.eagerlogic.cubee.client.styles.Color;
import com.eagerlogic.cubee.client.styles.EOrientation;

/**
 *
 * @author dipacs
 */
public final class Triangle extends AUserControl {
    
    private final Property<EOrientation> orientation = new Property<EOrientation>(EOrientation.BOTTOM, false, false);
    private final ColorProperty color = new ColorProperty(Color.RED, false, false);
    private final IntegerProperty width = new IntegerProperty(100, false, false);
    private final IntegerProperty height = new IntegerProperty(100, false, false);

    public Triangle() {
        this.borderProperty().bind(new AExpression<Border>() {

            {
                bind(orientation, color, width, height);
            }
            
            @Override
            public Border calculate() {
               EOrientation ori = orientation.get();
               int lb = 0;
               int tb = 0;
               int rb = 0;
               int bb = 0;
               switch (ori) {
                   case LEFT:
                       rb = 0;
                       tb = width.get() / 2;
                       bb = width.get() - lb;
                       lb = height.get();
                       break;
                   case TOP:
                       bb = 0;
                       lb = width.get() / 2;
                       rb = width.get() - lb;
                       tb = height.get();
                       break;
                   case RIGHT:
                       lb = 0;
                       tb = width.get() / 2;
                       bb = width.get() - lb;
                       rb = height.get();
                       break;
                   case BOTTOM:
                       tb = 0;
                       lb = width.get() / 2;
                       rb = width.get() - lb;
                       bb = height.get();
                       break;
               }
               return new Border(lb, tb, rb, bb, color.get(), color.get(), color.get(), color.get(), 0, 0, 0, 0);
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

    @Override
    public void setHeight(Integer height) {
        this.height.set(height);
    }

    @Override
    public Integer getHeight() {
        return this.height.get();
    }

    @Override
    public void setWidth(Integer width) {
        this.width.set(width);
    }

    @Override
    public Integer getWidth() {
        return this.width.get();
    }

    @Override
    public IntegerProperty heightProperty() {
        return width;
    }

    @Override
    public IntegerProperty widthProperty() {
        return width;
    }
    
}
