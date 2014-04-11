/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.properties.IntegerProperty;

/**
 *
 * @author dipacs
 */
public final class ViewSwitcher extends AUserControl {

    private AFillView currentView;

    public ViewSwitcher() {

    }

    public void showView(AFillView view) {
        if (view == currentView) {
            return;
        }
        if (currentView != null) {
            currentView.hiddenWidthProperty().unbind();
            currentView.hiddenHeightProperty().unbind();
            this.getChildren().remove(currentView);
            currentView = null;
        }
        if (view != null) {
            this.getChildren().add(view);
            view.hiddenWidthProperty().bind(this.clientWidthProperty());
            view.hiddenHeightProperty().bind(this.clientHeightProperty());
        }
        currentView = view;
    }

    public AFillView getView() {
        return currentView;
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
