/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.ui;

import com.eagerlogic.cubee.client.properties.DoubleProperty;
import com.eagerlogic.cubee.client.properties.IChangeListener;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.Property;
import com.google.gwt.canvas.dom.client.Context2d;

/**
 *
 * @author dipacs
 */
public abstract class AComponent {
    
    private final IntegerProperty x = new IntegerProperty(0, false, false);
    private final IntegerProperty y = new IntegerProperty(0, false, false);
    private final DoubleProperty alpha = new DoubleProperty(1.0, false, false);
    private final DoubleProperty rotate = new DoubleProperty(0.0, false, false);
    private final IntegerProperty rotateCenterX = new IntegerProperty(0, false, false);
    private final IntegerProperty rotateCenterY = new IntegerProperty(0, false, false);
    private final DoubleProperty scaleX = new DoubleProperty(1.0, false, false);
    private final DoubleProperty scaleY = new DoubleProperty(1.0, false, false);
    private final IntegerProperty scaleCenterX = new IntegerProperty(0, false, false);
    private final IntegerProperty scaleCenterY = new IntegerProperty(0, false, false);
    
    private int measuredWidth;
    private int measuredHeight;
    private int transformedX;
    private int transformedY;
    private int transformedWidth;
    private int transformedHeight;
    
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
    
    
    private ALayout parent;
    private CubeePanel cubeePanel;
    
    private boolean valid = false;
    private boolean needLayout = true;

    public AComponent() {
        x.addChangeListener(invalidateAndLayoutListener);
        y.addChangeListener(invalidateAndLayoutListener);
        alpha.addChangeListener(invalidateListener);
        rotate.addChangeListener(invalidateAndLayoutListener);
        rotateCenterX.addChangeListener(invalidateAndLayoutListener);
        rotateCenterY.addChangeListener(invalidateAndLayoutListener);
        scaleX.addChangeListener(invalidateAndLayoutListener);
        scaleY.addChangeListener(invalidateAndLayoutListener);
        scaleCenterX.addChangeListener(invalidateAndLayoutListener);
        scaleCenterY.addChangeListener(invalidateAndLayoutListener);
    }

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

    public int getTranslatedWidth() {
        return transformedWidth;
    }

    public int getTranslatedHeight() {
        return transformedHeight;
    }

    public int getTranslatedX() {
        return transformedX;
    }

    public int getTranslatedY() {
        return transformedY;
    }
    
    private void calculateTransformedBounds() {
        // TODO implementálni
        // TODO calculate transformed bounds
        this.transformedX = 0;
        this.transformedY = 0;
        this.transformedWidth = measuredWidth;
        this.transformedHeight = measuredHeight;
    }
    
    void setParent(ALayout parent) {
        this.parent = parent;
    }
    
    void setCubeePanel(CubeePanel cubeePanel) {
        this.cubeePanel = cubeePanel;
    }
    
    protected void measure() {
        onMeasure();
        calculateTransformedBounds();
        this.needLayout = false;
    }
    
    public final void requestLayout() {
        this.needLayout = true;
        if (this.parent != null) {
            this.parent.requestLayout();
        }
    }
    
    public final boolean isNeedLayout() {
        return this.needLayout;
    }

    public final IntegerProperty xProperty() {
        return x;
    }

    public final IntegerProperty yProperty() {
        return y;
    }

    public final DoubleProperty alphaProperty() {
        return alpha;
    }

    public final DoubleProperty rotateProperty() {
        return rotate;
    }

    public final IntegerProperty rotateCenterXProperty() {
        return rotateCenterX;
    }

    public final IntegerProperty rotateCenterYProperty() {
        return rotateCenterY;
    }

    public final DoubleProperty scaleXProperty() {
        return scaleX;
    }

    public final DoubleProperty scaleYProperty() {
        return scaleY;
    }

    public final IntegerProperty scaleCenterXProperty() {
        return scaleCenterX;
    }

    public final IntegerProperty scaleCenterYProperty() {
        return scaleCenterY;
    }
    
    public void draw(Context2d ctx) {
        this.onDraw(ctx);
        this.valid = true;
    }
    
    protected abstract void onMeasure();
    protected abstract void onDraw(Context2d ctx);
    
}
