package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.properties.AExpression;
import com.eagerlogic.cubee.client.properties.BackgroundProperty;
import com.eagerlogic.cubee.client.properties.BooleanProperty;
import com.eagerlogic.cubee.client.styles.ABackground;

public abstract class AMenuItem extends AUserControl {

    final BackgroundProperty normalBackground = new BackgroundProperty(null, true, false);
    final BackgroundProperty hoveredBackground = new BackgroundProperty(null, true, false);
    private final BooleanProperty selected = new BooleanProperty(false, false, false);

    private ICloseable closeable;

    public AMenuItem() {
        this.backgroundProperty().bind(new AExpression<ABackground>() {

            {
                bind(selected, normalBackground, hoveredBackground);
            }

            @Override
            public ABackground calculate() {
                if (selected.get()) {
                    return hoveredBackground.get();
                }
                return normalBackground.get();
            }
        });
    }

    protected BooleanProperty selectedProperty() {
        return selected;
    }

    void setCloseable(ICloseable closeable) {
        this.closeable = closeable;
    }

    protected void closeMenu() {
        if (this.closeable == null) {
            throw new IllegalStateException("This MenuItem isn't shown in any menu, so it can't close the menu.");
        }
        this.closeable.close();
    }

}
