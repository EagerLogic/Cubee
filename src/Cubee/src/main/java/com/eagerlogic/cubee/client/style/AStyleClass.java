/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.style;

import com.eagerlogic.cubee.client.components.AComponent;

/**
 *
 * @author dipacs
 */
public abstract class AStyleClass<T extends AComponent> {

    public abstract void apply(T component);

}
