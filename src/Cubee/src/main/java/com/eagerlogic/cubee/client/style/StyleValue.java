/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.style;

/**
 *
 * @author dipacs
 */
public class StyleValue<T> {

    private T value;

    public StyleValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

}
