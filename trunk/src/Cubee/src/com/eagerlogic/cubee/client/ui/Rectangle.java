/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.ui;

import com.eagerlogic.cubee.client.properties.IChangeListener;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.Property;
import com.google.gwt.canvas.dom.client.Context2d;

/**
 *
 * @author dipacs
 */
public class Rectangle extends AComponent {
    
    private final IntegerProperty width = new IntegerProperty(100, false, false);
    private final IntegerProperty height = new IntegerProperty(100, false, false);
    
    private final IChangeListener invalidateListener = new IChangeListener() {

        @Override
        public void onChanged(Object sender) {
            invalidate();
        }
    };
    private final IChangeListener invalidateAndLayoutListener = new IChangeListener() {

        @Override
        public void onChanged(Object sender) {
            invalidate();
            requestLayout();
        }
    };

    public Rectangle() {
        width.addChangeListener(invalidateAndLayoutListener);
        height.addChangeListener(invalidateAndLayoutListener);
    }

    public final IntegerProperty widthProperty() {
        return width;
    }

    public final IntegerProperty heightProperty() {
        return height;
    }

    @Override
    protected void onMeasure() {
        this.setMeasuredWidth(widthProperty().get());
        this.setMeasuredHeight(heightProperty().get());
    }

    @Override
    public void onDraw(Context2d ctx) {
        ctx.setFillStyle("#ff0000");
        ctx.fillRect(0.0, 0.0, width.get(), height.get());
    }
    
}
