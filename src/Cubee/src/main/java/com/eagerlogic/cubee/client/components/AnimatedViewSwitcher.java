/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.properties.IChangeListener;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.KeyFrame;
import com.eagerlogic.cubee.client.properties.Timeline;

/**
 *
 * @author dipacs
 */
public class AnimatedViewSwitcher extends AUserControl {

    private final IntegerProperty selectedIndex = new IntegerProperty(0, false, false);
    private AView[] views;
    private int lastSelectedIndex = 0;
    private Timeline tl;

    public AnimatedViewSwitcher() {
        selectedIndex.addChangeListener(new IChangeListener() {

            @Override
            public void onChanged(Object sender) {
                if (tl != null) {
                    tl.stop();
                }

                int newSelectedIndex = selectedIndex.get();
                if (lastSelectedIndex > newSelectedIndex) {
                    // slide right
                    tl = new Timeline(new KeyFrame(0l, views[lastSelectedIndex].translateXProperty(), views[lastSelectedIndex].translateXProperty().get()),
                            new KeyFrame(0l, views[newSelectedIndex].translateXProperty(), -clientWidthProperty().get()),
                            new KeyFrame(250l, views[lastSelectedIndex].translateXProperty(), clientWidthProperty().get() + 10),
                            new KeyFrame(250l, views[newSelectedIndex].translateXProperty(), 0));
                    tl.start();
                } else if (lastSelectedIndex < newSelectedIndex) {
                    // slide left
                    tl = new Timeline(new KeyFrame(0l, views[lastSelectedIndex].translateXProperty(), views[lastSelectedIndex].translateXProperty().get()),
                            new KeyFrame(0l, views[newSelectedIndex].translateXProperty(), clientWidthProperty().get()),
                            new KeyFrame(250l, views[lastSelectedIndex].translateXProperty(), -clientWidthProperty().get() + 10),
                            new KeyFrame(250l, views[newSelectedIndex].translateXProperty(), 0));
                    tl.start();
                } else {
                    // nothing to do here
                }
                lastSelectedIndex = newSelectedIndex;
            }
        });
    }

    public void initViews(AView... views) {
        this.views = views;
        boolean first = true;
        for (AView view : views) {
            view.hiddenWidthProperty().bind(this.clientWidthProperty());
            view.hiddenHeightProperty().bind(this.clientHeightProperty());
            if (!first) {
                view.translateXProperty().set(10000);
            }
            this.getChildren().add(view);
            first = false;
        }
    }

    public IntegerProperty selectedIndexProperty() {
        return selectedIndex;
    }

    @Override
    public IntegerProperty heightProperty() {
        return super.heightProperty();
    }

    @Override
    public IntegerProperty widthProperty() {
        return super.widthProperty();
    }

}
