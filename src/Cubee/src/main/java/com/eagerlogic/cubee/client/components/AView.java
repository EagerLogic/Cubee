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
public abstract class AView extends AUserControl {

    public AView() {
    }

    @Override
    @Deprecated
    protected final IntegerProperty heightProperty() {
        throw new RuntimeException("Can't access a view's width property.");
    }

    @Override
    @Deprecated
    protected final IntegerProperty widthProperty() {
        throw new RuntimeException("Can't access a view's width property.");
    }

    final IntegerProperty hiddenWidthProperty() {
        return super.widthProperty();
    }

    final IntegerProperty hiddenHeightProperty() {
        return super.heightProperty();
    }

}
