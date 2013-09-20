/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.ui;

import com.eagerlogic.cubee.client.EventQueue;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import java.util.ArrayList;

/**
 *
 * @author dipacs
 */
public class CubeePanel extends SimplePanel implements Runnable {

    private final Canvas canvas;
    private final AbsoluteLayout rootLayout = new AbsoluteLayout();
    private AComponent root;
    private boolean valid = false;

    public CubeePanel() {
        if (!Canvas.isSupported()) {
            throw new UnsupportedOperationException("The canvas isn't supported in this browser.");
        }

        this.canvas = Canvas.createIfSupported();
        Window.addResizeHandler(new ResizeHandler() {
            @Override
            public void onResize(ResizeEvent event) {
                canvas.setWidth(event.getWidth() + "px");
                canvas.setHeight(event.getHeight() + "px");
            }
        });

        EventQueue.getInstance().invokeLater(new Runnable() {

            @Override
            public void run() {
                canvas.setWidth(getElement().getClientWidth() + "px");
                canvas.setHeight(getElement().getClientHeight() + "px");
            }
        });
        
        canvas.setWidth(this.getElement().getClientWidth() + "px");
        canvas.setHeight(this.getElement().getClientHeight() + "px");
        this.add(canvas);

        EventQueue.getInstance().invokeLater(this);
    }

    public void setRoot(AComponent root) {
        this.root = root;
        this.rootLayout.getChildren().clear();
        this.rootLayout.getChildren().add(root);
    }

    boolean isValid() {
        return this.rootLayout.isValid();
    }

    void invalidate() {
        this.rootLayout.invalidate();
        this.rootLayout.requestLayout();
    }

    void draw(Context2d ctx) {
        this.rootLayout.draw(ctx);
    }

    @Override
    public void run() {
        if (!isValid()) {
            try {
                this.rootLayout.measure();
                draw(canvas.getContext2d());
            } catch (Throwable t) {
                t.printStackTrace();
            } finally {
                EventQueue.getInstance().invokeLater(this);
            }
        }
    }
}
