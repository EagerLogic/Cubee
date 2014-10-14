package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.properties.BackgroundProperty;
import com.eagerlogic.cubee.client.properties.BooleanProperty;
import com.eagerlogic.cubee.client.properties.BorderProperty;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.PaddingProperty;
import com.eagerlogic.cubee.client.properties.Property;
import com.eagerlogic.cubee.client.style.Style;
import com.eagerlogic.cubee.client.style.styles.ABackground;
import com.eagerlogic.cubee.client.style.styles.Border;
import com.eagerlogic.cubee.client.style.styles.BoxShadow;
import com.eagerlogic.cubee.client.style.styles.Padding;

/**
 *
 * @author dipacs
 */
public final class Panel extends AUserControl {

    public static class StyleClass<T extends Panel> extends AUserControl.StyleClass<T> {

        @Override
        public Style<BoxShadow> getShadow() {
            return super.getShadow();
        }

        @Override
        public Style<ABackground> getBackground() {
            return super.getBackground();
        }

        @Override
        public Style<Integer> getHeight() {
            return super.getHeight();
        }

        @Override
        public Style<Integer> getWidth() {
            return super.getWidth();
        }

        @Override
        public Style<Integer> getMaxHeight() {
            return super.getMaxHeight();
        }

        @Override
        public Style<Integer> getMaxWidth() {
            return super.getMaxWidth();
        }

        @Override
        public Style<Integer> getMinHeight() {
            return super.getMinHeight();
        }

        @Override
        public Style<Integer> getMinWidth() {
            return super.getMinWidth();
        }

        @Override
        public Style<Border> getBorder() {
            return super.getBorder();
        }

        @Override
        public Style<Padding> getPadding() {
            return super.getPadding();
        }

    }

    @Override
    public final IntegerProperty widthProperty() {
        return super.widthProperty();
    }

    @Override
    public final IntegerProperty heightProperty() {
        return super.heightProperty();
    }

    @Override
    public final PaddingProperty paddingProperty() {
        return super.paddingProperty();
    }

    @Override
    public final BorderProperty borderProperty() {
        return super.borderProperty();
    }

    @Override
    public LayoutChildren getChildren() {
        return super.getChildren();
    }

    @Override
    public BackgroundProperty backgroundProperty() {
        return super.backgroundProperty();
    }

    @Override
    public Property<BoxShadow> shadowProperty() {
        return super.shadowProperty();
    }

    @Override
    public IntegerProperty minWidthProperty() {
        return super.minWidthProperty();
    }

    @Override
    public IntegerProperty minHeightProperty() {
        return super.minHeightProperty();
    }

    @Override
    public IntegerProperty maxWidthProperty() {
        return super.maxWidthProperty();
    }

    @Override
    public IntegerProperty maxHeightProperty() {
        return super.maxHeightProperty();
    }

	@Override
	public BooleanProperty draggableProperty() {
		return super.draggableProperty();
	}

}
