package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.properties.AExpression;
import com.eagerlogic.cubee.client.properties.BackgroundProperty;
import com.eagerlogic.cubee.client.properties.BooleanProperty;
import com.eagerlogic.cubee.client.style.Style;
import com.eagerlogic.cubee.client.style.styles.ABackground;

public abstract class AMenuItem extends AUserControl {

    public static class StyleClass<T extends AMenuItem> extends AUserControl.StyleClass<T> {

        private final Style<ABackground> normalBackground = new Style<ABackground>(null, true);
        private final Style<ABackground> hoveredBackground = new Style<ABackground>(null, true);

        @Override
        public void apply(T component) {
            super.apply(component);

            normalBackground.apply(component.normalBackgroundProperty());
            hoveredBackground.apply(component.hoveredBackgroundProperty());

        }

        protected Style<ABackground> getNormalBackground() {
            return normalBackground;
        }

        protected Style<ABackground> getHoveredBackground() {
            return hoveredBackground;
        }

    }

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

    protected boolean isSelected() {
        return selected.get();
    }

    protected void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    void setCloseable(ICloseable closeable) {
        this.closeable = closeable;
    }

    protected BackgroundProperty normalBackgroundProperty() {
        return normalBackground;
    }

    protected BackgroundProperty hoveredBackgroundProperty() {
        return hoveredBackground;
    }

    protected void closeMenu() {
        if (this.closeable == null) {
            throw new IllegalStateException("This MenuItem isn't shown in any menu, so it can't close the menu.");
        }
        this.closeable.close();
    }

}
