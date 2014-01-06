package com.eagerlogic.cubee.client.components;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.eagerlogic.cubee.client.properties.AExpression;
import com.eagerlogic.cubee.client.properties.BackgroundProperty;
import com.eagerlogic.cubee.client.properties.BooleanProperty;
import com.eagerlogic.cubee.client.properties.BorderProperty;
import com.eagerlogic.cubee.client.properties.ColorProperty;
import com.eagerlogic.cubee.client.properties.DoubleProperty;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.Property;
import com.eagerlogic.cubee.client.styles.ABackground;
import com.eagerlogic.cubee.client.styles.Border;
import com.eagerlogic.cubee.client.styles.BoxShadow;
import com.eagerlogic.cubee.client.styles.Color;
import com.eagerlogic.cubee.client.styles.ColorBackground;
import com.eagerlogic.cubee.client.styles.Padding;

public final class PopupMenu extends APopup implements ICloseable {

    private BackgroundProperty normalItemBackground = new BackgroundProperty(null, true, false);
    private BackgroundProperty selectedItemBackground = new BackgroundProperty(new ColorBackground(Color.FUNKY_BLUE), true, false);

    private final AMenuItem[] items;
    private final Panel root;

    public PopupMenu(AMenuItem... items) {
        this(true, true, Color.TRANSPARENT, items);
    }

    public PopupMenu(List<AMenuItem> items) {
        this(true, true, Color.TRANSPARENT, items);
    }

    public PopupMenu(boolean modal, boolean autoClose, Color glassColor, List<AMenuItem> items) {
        this(modal, autoClose, glassColor, items == null ? null : items.toArray(new AMenuItem[items.size()]));
    }

    public PopupMenu(boolean modal, boolean autoClose, Color glassColor, AMenuItem... items) {
        super(modal, autoClose, glassColor);
        if (items == null) {
            items = new AMenuItem[0];
        }
        this.items = items;

        this.root = new Panel();
        this.setRootComponent(root);
        this.backgroundProperty().set(new ColorBackground(Color.WHITE));

        VBox vb = new VBox();
        root.getChildren().add(vb);
        root.shadowProperty().set(new BoxShadow(2, 2, 10, 0, Color.getRgbColor(0x808080), false));
        root.borderProperty().set(new Border(1, Color.LIGHT_GRAY, 0));
        root.paddingProperty().set(new Padding(5));

        for (AMenuItem item : this.items) {
            if (item == null) {
                throw new NullPointerException("The 'items' parameter can't contains null elements.");
            }
            item.setCloseable(this);
            item.minWidthProperty().bind(root.clientWidthProperty());
            item.normalBackground.bind(normalItemBackground);
            item.hoveredBackground.bind(selectedItemBackground);
            vb.getChildren().add(item);
        }
    }

    @Override
    protected void show() {
        super.show();
    }

    public void show(int x, int y) {
        this.translateXProperty().set(x);
        this.translateYProperty().set(y);
        this.show();
    }

    @Override
    public void close() {
        super.close();
    }

    @Override
    protected void onClosed() {
        super.onClosed();
    }

    @Override
    public BooleanProperty centerProperty() {
        return super.centerProperty();
    }

    public final BorderProperty borderProperty() {
        return root.borderProperty();
    }

    public BackgroundProperty backgroundProperty() {
        return root.backgroundProperty();
    }

    public Property<BoxShadow> shadowProperty() {
        return root.shadowProperty();
    }

    public final DoubleProperty alphaProperty() {
        return root.alphaProperty();
    }

}
