package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.events.ClickEventArgs;
import com.eagerlogic.cubee.client.events.IEventListener;
import com.eagerlogic.cubee.client.properties.BooleanProperty;
import com.eagerlogic.cubee.client.properties.ColorProperty;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.Property;
import com.eagerlogic.cubee.client.properties.StringProperty;
import com.eagerlogic.cubee.client.styles.Color;
import com.eagerlogic.cubee.client.styles.ECursor;
import com.eagerlogic.cubee.client.styles.ETextAlign;
import com.eagerlogic.cubee.client.styles.ETextOverflow;
import com.eagerlogic.cubee.client.styles.EVAlign;

/**
 *
 * @author dipacs
 */
public final class CheckBox extends AUserControl {

    private final BooleanProperty checked = new BooleanProperty(false, false, false);

    private final HBox root;
    private final Led led;
    private final Label label;

    public CheckBox() {
        root = new HBox();
        this.getChildren().add(root);

        led = new Led();
        led.pointerTransparentProperty().set(true);
        root.getChildren().add(led);
        root.setCellVAlign(0, EVAlign.MIDDLE);

        root.getChildren().add(null);
        root.setCellWidth(1, 10);

        label = new Label();
        label.pointerTransparentProperty().set(true);
        root.getChildren().add(label);
        root.setCellVAlign(2, EVAlign.MIDDLE);

        this.cursorProperty().set(ECursor.POINTER);

        led.lightProperty().bind(checked);

        this.onClickEvent().addListener(new IEventListener<ClickEventArgs>() {

            @Override
            public void onFired(ClickEventArgs args) {
                checked.set(!checked.get());
            }
        });
    }

    public Property<Color> ledColorProperty() {
        return led.colorProperty();
    }

    public IntegerProperty ledSizeProperty() {
        return led.sizeProperty();
    }

    public final IntegerProperty textWidthProperty() {
        return label.widthProperty();
    }

    public final StringProperty textProperty() {
        return label.textProperty();
    }

    public final Property<ETextOverflow> textOverflowProperty() {
        return label.textOverflowProperty();
    }

    public ColorProperty foreColorProperty() {
        return label.foreColorProperty();
    }

    public BooleanProperty boldProperty() {
        return label.boldProperty();
    }

    public BooleanProperty italicProperty() {
        return label.italicProperty();
    }

    public BooleanProperty underlineProperty() {
        return label.underlineProperty();
    }

    public Property<ETextAlign> textAlignProperty() {
        return label.textAlignProperty();
    }

    public IntegerProperty fontSizeProperty() {
        return label.fontSizeProperty();
    }

    public BooleanProperty checkedProperty() {
        return checked;
    }

}
