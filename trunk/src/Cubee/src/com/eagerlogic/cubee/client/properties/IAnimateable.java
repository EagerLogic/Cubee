/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.properties;

/**
 *
 * @author dipacs
 */
public interface IAnimateable<T> {
    
    public T animate(double pos, T startValue, T endValue);
    
}
