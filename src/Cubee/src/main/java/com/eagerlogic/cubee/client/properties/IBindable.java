/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.properties;

/**
 *
 * @author dipacs
 */
public interface IBindable<T> {

    public void bind(T source);

    public void unbind();

    public boolean isBound();

}
