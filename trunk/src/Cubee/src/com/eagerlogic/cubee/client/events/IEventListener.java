/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.events;

/**
 *
 * @author dipacs
 */
public interface IEventListener<T extends EventArgs> {
    
    public void onFired(T args);
    
}
