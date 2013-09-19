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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import java.util.ArrayList;

/**
 *
 * @author dipacs
 */
public class CubeePanel extends SimplePanel implements Runnable {

    private final Canvas canvas;
    private AComponent root;
    private boolean valid = false;

    public CubeePanel() {
        if (!Canvas.isSupported()) {
            throw new UnsupportedOperationException("The canvas isn't supported in this browser.");
        }

        this.canvas = Canvas.createIfSupported();
        this.addHandler(new ResizeHandler() {
            @Override
            public void onResize(ResizeEvent event) {
                canvas.setWidth(event.getWidth() + "px");
                canvas.setHeight(event.getHeight() + "px");
            }
        }, ResizeEvent.getType());

        canvas.setWidth(this.getElement().getClientWidth() + "px");
        canvas.setHeight(this.getElement().getClientHeight() + "px");

        EventQueue.getInstance().invokeLater(this);
    }

    public void setRoot(AComponent root) {
        this.root = root;
    }

    boolean isValid() {
        return this.valid;
    }

    void invalidate() {
        this.valid = false;
    }

    void draw(Context2d ctx) {
    }

    @Override
    public void run() {
        if (!isValid()) {
            try {
                draw(canvas.getContext2d());
            } catch (Throwable t) {
                t.printStackTrace();
            } finally {
                EventQueue.getInstance().invokeLater(this);
            }
        }
    }
}
