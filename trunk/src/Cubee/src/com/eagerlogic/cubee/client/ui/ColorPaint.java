/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.ui;

import com.google.gwt.canvas.dom.client.Context2d;

/**
 *
 * @author dipacs
 */
public final class ColorPaint extends APaint {
    
    private final Color color;

    public ColorPaint(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void applyPaint(Context2d ctx, int left, int top, int width, int height) {
        if (color == null) {
            ctx.setFillStyle("rgba(0,0,0,0.0)");
        } else {
            ctx.setFillStyle(color.toCss());
        }
    }
    
}
