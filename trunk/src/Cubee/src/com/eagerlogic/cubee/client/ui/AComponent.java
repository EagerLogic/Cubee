/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.ui;

import com.eagerlogic.cubee.client.properties.Property;
import com.google.gwt.canvas.dom.client.Context2d;

/**
 *
 * @author dipacs
 */
public abstract class AComponent {
    
    private final Property<Integer> x = new Property<Integer>(0, false, false);
    private final Property<Integer> y = new Property<Integer>(0, false, false);
    private final Property<Double> alpha = new Property<Double>(1.0, false, false);
    private final Property<Double> rotate = new Property<Double>(1.0, false, false);
    private final Property<Integer> rotateCenterX = new Property<Integer>(0, false, false);
    private final Property<Integer> rotateCenterY = new Property<Integer>(0, false, false);
    private final Property<Double> scaleX = new Property<Double>(1.0, false, false);
    private final Property<Double> scaleY = new Property<Double>(1.0, false, false);
    private final Property<Integer> scaleCenterX = new Property<Integer>(0, false, false);
    private final Property<Integer> scaleCenterY = new Property<Integer>(0, false, false);
    
    private int measuredWidth;
    private int measuredHeight;
    
    private ALayout parent;
    private CubeePanel cubeePanel;
    
    private boolean valid = false;

    protected final int getMeasuredWidth() {
        return measuredWidth;
    }

    protected final void setMeasuredWidth(int measuredWidth) {
        this.measuredWidth = measuredWidth;
    }

    protected final int getMeasuredHeight() {
        return measuredHeight;
    }

    protected final void setMeasuredHeight(int measuredHeight) {
        this.measuredHeight = measuredHeight;
    }
    
    public boolean isValid() {
        return this.valid;
    }
    
    public void invalidate() {
        if (!this.valid) {
            return;
        }
        this.valid = false;
        
        if (this.parent != null) {
            this.parent.invalidate();
        } else if (this.cubeePanel != null) {
            this.cubeePanel.invalidate();
        }
    }

    public final ALayout getParent() {
        return parent;
    }

    public final CubeePanel getCubeePanel() {
        return cubeePanel;
    }
    
    protected abstract void measure();
    public abstract void draw(Context2d ctx);
    
}
