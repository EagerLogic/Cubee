/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.properties;

/**
 *
 * @author dipacs
 */
public interface IProperty<T> extends IObservable {

    public Object getObjectValue();

    public void invalidate();

}
