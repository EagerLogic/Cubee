package com.eagerlogic.cubee.client.components.menuitems;

import com.eagerlogic.cubee.client.components.AMenuItem;
import com.eagerlogic.cubee.client.components.Label;
import com.eagerlogic.cubee.client.events.ClickEventArgs;
import com.eagerlogic.cubee.client.events.IEventListener;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.PaddingProperty;
import com.eagerlogic.cubee.client.properties.Property;
import com.eagerlogic.cubee.client.properties.StringProperty;
import com.eagerlogic.cubee.client.style.styles.ECursor;
import com.eagerlogic.cubee.client.style.styles.FontFamily;

public final class LabelMenuItem extends AMenuItem {

    private final Label label;

    public LabelMenuItem() {
        label = new Label();
        label.handlePointerProperty().set(false);
        label.fontSizeProperty().set(14);
        this.getChildren().add(label);

        this.cursorProperty().set(ECursor.POINTER);
        selectedProperty().bind(hoveredProperty());
        this.onClickEvent().addListener(new IEventListener<ClickEventArgs>() {

            @Override
            public void onFired(ClickEventArgs args) {
                closeMenu();
            }
        });
    }

    public final StringProperty textProperty() {
        return label.textProperty();
    }

    public PaddingProperty paddingProperty() {
        return label.paddingProperty();
    }

    public IntegerProperty fontSizeProperty() {
        return label.fontSizeProperty();
    }

    public Property<FontFamily> fontFamilyProperty() {
        return label.fontFamilyProperty();
    }

}
