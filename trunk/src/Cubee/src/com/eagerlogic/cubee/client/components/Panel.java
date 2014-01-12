package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.properties.BackgroundProperty;
import com.eagerlogic.cubee.client.properties.BorderProperty;
import com.eagerlogic.cubee.client.styles.ECursor;
import com.eagerlogic.cubee.client.properties.PaddingProperty;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.Property;
import com.eagerlogic.cubee.client.styles.ABackground;
import com.eagerlogic.cubee.client.styles.BoxShadow;

/**
 *
 * @author dipacs
 */
public final class Panel extends AUserControl {

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
    public void setShadow(BoxShadow shadow) {
        super.setShadow(shadow);
    }

    @Override
    public BoxShadow getShadow() {
        return super.getShadow();
    }

    @Override
    public void setBackground(ABackground background) {
        super.setBackground(background);
    }

    @Override
    public ABackground getBackground() {
        return super.getBackground();
    }

    @Override
    public void setHeight(Integer height) {
        super.setHeight(height);
    }

    @Override
    public Integer getHeight() {
        return super.getHeight();
    }

    @Override
    public void setWidth(Integer width) {
        super.setWidth(width);
    }

    @Override
    public Integer getWidth() {
        return super.getWidth();
    }

    @Override
    public void setMaxHeight(Integer maxHeight) {
        super.setMaxHeight(maxHeight);
    }

    @Override
    public Integer getMaxHeight() {
        return super.getMaxHeight();
    }

    @Override
    public void setMaxWidth(Integer maxWidth) {
        super.setMaxWidth(maxWidth);
    }

    @Override
    public Integer getMaxWidth() {
        return super.getMaxWidth();
    }

    @Override
    public void setMinHeight(Integer minHeight) {
        super.setMinHeight(minHeight);
    }

    @Override
    public void setMinWidth(Integer minWidth) {
        super.setMinWidth(minWidth);
    }

    @Override
    public Integer getMinWidth() {
        return super.getMinWidth();
    }

}
