package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.components.AComponent;
import com.eagerlogic.cubee.client.components.AUserControl;
import com.eagerlogic.cubee.client.events.Event;
import com.eagerlogic.cubee.client.events.EventArgs;

/**
 *
 * @author dipacs
 */
public class AbstractTreeViewItemGliph extends AUserControl {
    
    private AComponent rootComponent;

    public AbstractTreeViewItemGliph() {
    }

    protected final AComponent getRootComponent() {
        return rootComponent;
    }

    protected final void setRootComponent(AComponent rootComponent) {
        this.getChildren().clear();
        this.rootComponent = rootComponent;
        if (rootComponent != null) {
            this.getChildren().add(rootComponent);
        }
    }

}
