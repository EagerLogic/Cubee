package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.style.styles.Color;

/**
 *
 * @author dipacs
 */
public abstract class ADialog extends APopup {

    public ADialog(boolean modal, boolean autoClose) {
        this(modal, autoClose, Color.getArgbColor(0xc0000000));
    }

    public ADialog(boolean modal, boolean autoClose, Color glassColor) {
        super(modal, autoClose, glassColor);
        this.centerProperty().set(true);
    }

}
