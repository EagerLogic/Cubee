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
public abstract class APaint {
    
    public abstract void applyPaint(Context2d ctx, int left, int top, int width, int height);
    
}
