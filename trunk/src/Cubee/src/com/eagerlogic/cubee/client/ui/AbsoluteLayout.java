/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.ui;

import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.Property;
import com.google.gwt.canvas.dom.client.Context2d;

/**
 *
 * @author dipacs
 */
public class AbsoluteLayout extends ALayout {

    private final IntegerProperty width = new IntegerProperty(100, false, false);
    private final IntegerProperty height = new IntegerProperty(100, false, false);

    @Override
    public void onChildrenChanged() {
        this.invalidate();
    }

    @Override
    protected void onMeasure() {
        this.setMeasuredWidth(this.width.get());
        this.setMeasuredHeight(this.height.get());
        for (AComponent component : this.getChildren()) {
            component.measure();
        }
    }

    @Override
    protected void onDraw(Context2d ctx) {
        ctx.moveTo(0.0, 0.0);
        ctx.lineTo(this.getMeasuredWidth(), 0.0);
        ctx.lineTo(this.getMeasuredWidth(), this.getMeasuredHeight());
        ctx.lineTo(0.0, this.getMeasuredHeight());
        ctx.closePath();
        ctx.clip();
        for (AComponent child : this.getChildren()) {
            try {
                ctx.save();
                ctx.translate(child.xProperty().get(), child.yProperty().get());
                // TODO set rotate center and scale center
                ctx.rotate(Math.toRadians(child.rotateProperty().get() * 360));
                ctx.scale(child.scaleXProperty().get(), child.scaleYProperty().get());
                child.draw(ctx);
            } finally {
                ctx.restore();
            }
        }
    }

    @Override
    public LayoutChildren getChildren() {
        return super.getChildren();
    }
}
