/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.events;

/**
 *
 * @author dipacs
 */
public class EventArgs {

    private final Object sender;

    public EventArgs(Object sender) {
        this.sender = sender;
    }

    public final Object getSender() {
        return sender;
    }

}
