package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.components.fontawesome.EIcon;
import com.eagerlogic.cubee.client.components.fontawesome.FAIcon;
import com.eagerlogic.cubee.client.properties.BooleanProperty;
import com.eagerlogic.cubee.client.properties.ColorProperty;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.Property;

/**
 *
 * @author dipacs
 */
public final class FATreeViewItemGliph extends AbstractTreeViewItemGliph {
    
    private FAIcon faIcon;

    public FATreeViewItemGliph(EIcon icon) {
        faIcon = new FAIcon(icon);
        faIcon.handlePointerProperty().set(false);
        this.getChildren().add(faIcon);
    }

    public IntegerProperty sizeProperty() {
        return faIcon.sizeProperty();
    }

    public ColorProperty foreColorProperty() {
        return faIcon.foreColorProperty();
    }

    public BooleanProperty spinProperty() {
        return faIcon.spinProperty();
    }

    public Property<EIcon> iconProperty() {
        return faIcon.iconProperty();
    }

}
