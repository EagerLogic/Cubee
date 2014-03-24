/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.properties;

/**
 *
 * @author dipacs
 */
public interface IObservable {

    public void addChangeListener(IChangeListener listener);

    public void removeChangeListener(IChangeListener listener);

    public boolean hasChangeListener(IChangeListener listener);

}
