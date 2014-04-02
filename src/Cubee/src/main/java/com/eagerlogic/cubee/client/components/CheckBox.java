package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.components.fontawesome.EIcon;
import com.eagerlogic.cubee.client.components.fontawesome.FAIcon;
import com.eagerlogic.cubee.client.events.ClickEventArgs;
import com.eagerlogic.cubee.client.events.IEventListener;
import com.eagerlogic.cubee.client.properties.BooleanProperty;
import com.eagerlogic.cubee.client.properties.ColorProperty;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.Property;
import com.eagerlogic.cubee.client.properties.StringProperty;
import com.eagerlogic.cubee.client.properties.ext.AlignCenterExp;
import com.eagerlogic.cubee.client.properties.ext.AlignMiddleExp;
import com.eagerlogic.cubee.client.style.styles.Border;
import com.eagerlogic.cubee.client.style.styles.Color;
import com.eagerlogic.cubee.client.style.styles.ColorBackground;
import com.eagerlogic.cubee.client.style.styles.ECursor;
import com.eagerlogic.cubee.client.style.styles.ETextAlign;
import com.eagerlogic.cubee.client.style.styles.ETextOverflow;
import com.eagerlogic.cubee.client.style.styles.EVAlign;

/**
 *
 * @author dipacs
 */
public final class CheckBox extends AUserControl {

    // TODO make styleable

    private final BooleanProperty checked = new BooleanProperty(false, false, false);

    private final HBox root;
    private final Label label;
    private final Panel tickBox;
    private final FAIcon tickIcon;

    public CheckBox() {
        root = new HBox();
        this.getChildren().add(root);

        tickBox = new Panel();
        tickBox.backgroundProperty().set(new ColorBackground(Color.WHITE));
        tickBox.borderProperty().set(new Border(1, Color.LIGHT_GRAY, 0));
        tickBox.widthProperty().set(10);
        tickBox.heightProperty().set(10);
        tickBox.handlePointerProperty().set(false);
        root.getChildren().add(tickBox);
        root.setCellVAlign(0, EVAlign.MIDDLE);

        tickIcon = new FAIcon(EIcon.CHECK);
        tickIcon.sizeProperty().set(8);
        tickIcon.foreColorProperty().set(Color.SKY_BLUE);
        tickIcon.translateXProperty().bind(new AlignCenterExp(tickBox, tickIcon));
        tickIcon.translateYProperty().bind(new AlignMiddleExp(tickBox, tickIcon));
        tickIcon.visibleProperty().bind(checked);
        tickBox.getChildren().add(tickIcon);

        root.addEmptyCell(10);

        label = new Label();
        label.pointerTransparentProperty().set(true);
        root.getChildren().add(label);
        root.setCellVAlign(2, EVAlign.MIDDLE);

        this.cursorProperty().set(ECursor.POINTER);

        this.onClickEvent().addListener(new IEventListener<ClickEventArgs>() {

            @Override
            public void onFired(ClickEventArgs args) {
                checked.set(!checked.get());
            }
        });

        this.applyDefaultStyle(CheckBox.class);
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
