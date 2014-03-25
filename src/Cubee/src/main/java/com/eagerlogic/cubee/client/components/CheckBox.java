package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.events.ClickEventArgs;
import com.eagerlogic.cubee.client.events.IEventListener;
import com.eagerlogic.cubee.client.properties.BooleanProperty;
import com.eagerlogic.cubee.client.properties.ColorProperty;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.Property;
import com.eagerlogic.cubee.client.properties.StringProperty;
import com.eagerlogic.cubee.client.style.styles.Color;
import com.eagerlogic.cubee.client.style.styles.ECursor;
import com.eagerlogic.cubee.client.style.styles.ETextAlign;
import com.eagerlogic.cubee.client.style.styles.ETextOverflow;
import com.eagerlogic.cubee.client.style.styles.EVAlign;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author dipacs
 */
public final class CheckBox extends AUserControl {

    @Getter
    @Setter
    public static class StyleClass extends AUserControl.StyleClass<CheckBox> {

        private boolean bold = false;
        private int fontSize = 12;
        private Color foreColor = Color.BLACK;
        private boolean italic = false;
        private Color ledColor = Color.FUNKY_BLUE;
        private int ledSize = 20;
        private ETextAlign textAlign = ETextAlign.LEFT;
        private ETextOverflow textOverflow = ETextOverflow.ELLIPSIS;
        private Integer textWidth = null;
        private boolean underline = false;

        @Override
        public void apply(CheckBox component) {
            super.apply(component);

            component.boldProperty().set(bold);
            component.fontSizeProperty().set(fontSize);
            component.foreColorProperty().set(foreColor);
            component.italicProperty().set(italic);
            component.ledColorProperty().set(ledColor);
            component.ledSizeProperty().set(ledSize);
            component.textAlignProperty().set(textAlign);
            component.textOverflowProperty().set(textOverflow);
            component.textWidthProperty().set(textWidth);
            component.underlineProperty().set(underline);
        }

    }

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

        this.applyDefaultStyle(CheckBox.class);
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
