package com.eagerlogic.cubee.client.components;

import com.google.gwt.user.client.DOM;

/**
 *
 * @author dipacs
 */
public final class PasswordBox extends TextBox {

    public PasswordBox() {
        super(DOM.createInputPassword());
    }

}
