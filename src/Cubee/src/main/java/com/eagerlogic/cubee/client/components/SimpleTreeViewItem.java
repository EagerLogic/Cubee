package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.components.HBox;
import com.eagerlogic.cubee.client.components.Label;
import com.eagerlogic.cubee.client.components.Panel;
import com.eagerlogic.cubee.client.events.ClickEventArgs;
import com.eagerlogic.cubee.client.events.EventArgs;
import com.eagerlogic.cubee.client.events.IEventListener;
import com.eagerlogic.cubee.client.properties.AExpression;
import com.eagerlogic.cubee.client.properties.BackgroundProperty;
import com.eagerlogic.cubee.client.properties.ColorProperty;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.Property;
import com.eagerlogic.cubee.client.properties.ext.CondExp;
import com.eagerlogic.cubee.client.style.styles.ABackground;
import com.eagerlogic.cubee.client.style.styles.Color;
import com.eagerlogic.cubee.client.style.styles.ColorBackground;
import com.eagerlogic.cubee.client.style.styles.ECursor;
import com.eagerlogic.cubee.client.style.styles.EVAlign;
import com.eagerlogic.cubee.client.style.styles.FontFamily;

/**
 *
 * @author dipacs
 */
public final class SimpleTreeViewItem<T> extends AbstractTreeViewItem<T> {

    public static interface ValueConverter<T> {

        public String convert(T value);

    }

    private final ColorProperty textForeColor = new ColorProperty(Color.BLACK, false, false);
    private final IntegerProperty fontSize = new IntegerProperty(12, false, false);
    private final Property<FontFamily> fontFamily = new Property<FontFamily>(FontFamily.Arial, false, false);
    private final BackgroundProperty selectedBackground = new BackgroundProperty(new ColorBackground(Color.getArgbColor(
            0x8000c0ff)), true, false);
    private AbstractTreeViewItemGliph gliph;
    private HBox root;

    private final IEventListener<EventArgs> onChildrenChangedEventListener = new IEventListener<EventArgs>() {

        @Override
        public void onFired(EventArgs args) {
            refresh();
        }
    };

    private final ValueConverter<T> valueConverter;

    public SimpleTreeViewItem(T value) {
        this(value, null);
    }

    public SimpleTreeViewItem(T value, ValueConverter<T> valueConverter) {
        super(value);
        this.valueConverter = valueConverter;
        Panel rootPanel = new Panel();
        rootPanel.pointerTransparentProperty().set(true);
        rootPanel.cursorProperty().set(ECursor.POINTER);
        this.setRootComponent(rootPanel);

        root = new HBox();
        root.pointerTransparentProperty().set(true);
        rootPanel.getChildren().add(root);
        this.onChildrenChangedEvent().addListener(onChildrenChangedEventListener);

        this.onClickEvent().addListener(new IEventListener<ClickEventArgs>() {

            @Override
            public void onFired(ClickEventArgs args) {
                fireItemClickedEvent(SimpleTreeViewItem.this);
            }
        });

        rootPanel.backgroundProperty().bind(new AExpression<ABackground>() {

            {
                bind(selectedProperty());
            }

            @Override
            public ABackground calculate() {
                return selectedProperty().get() ? selectedBackground.get() : null;
            }
        });

        refresh();
    }

    private void refresh() {
        root.getChildren().clear();

        ExpandButton expandButton = new ExpandButton();
        expandButton.expandedProperty().bind(expandedProperty());
        expandButton.visibleProperty().bind(new CondExp<Boolean>(leafProperty(), false, true));
        expandButton.onClickEvent().addListener(new IEventListener<ClickEventArgs>() {

            @Override
            public void onFired(ClickEventArgs args) {
                expandedProperty().set(!expandedProperty().get());
            }
        });
//        FAIcon icon = new FAIcon(expandedProperty().get() ? EIcon.MINUS_SQUARE_O : EIcon.PLUS_SQUARE_O);
//        icon.foreColorProperty().bind(iconColor);
//        icon.sizeProperty().bind(iconSize);
//        icon.visibleProperty().bind(new CondExp<Boolean>(leafProperty(), false, true));
//        icon.onClickEvent().addListener(new IEventListener<ClickEventArgs>() {
//
//            @Override
//            public void onFired(ClickEventArgs args) {
//                expandedProperty().set(!expandedProperty().get());
//            }
//        });
        root.getChildren().add(expandButton);
        root.setLastCellVAlign(EVAlign.MIDDLE);

        root.addEmptyCell(5);

        if (gliph != null) {
            gliph.handlePointerProperty().set(false);
            root.getChildren().add(gliph);
            root.setLastCellVAlign(EVAlign.MIDDLE);
            root.addEmptyCell(5);
        }

        Label lbl = new Label();
        lbl.handlePointerProperty().set(false);
        if (valueConverter != null) {
            lbl.textProperty().set(valueConverter.convert(getValue()));
        } else {
            lbl.textProperty().set(getValue() == null ? "<NULL>" : getValue().toString());
        }
        lbl.foreColorProperty().bind(textForeColor);
        lbl.fontSizeProperty().bind(fontSize);
        lbl.fontFamilyProperty().bind(fontFamily);
        root.getChildren().add(lbl);
        root.setLastCellVAlign(EVAlign.MIDDLE);
    }

    public AbstractTreeViewItemGliph getGliph() {
        return gliph;
    }

    public void setGliph(AbstractTreeViewItemGliph gliph) {
        this.gliph = gliph;
        refresh();
    }

    public ColorProperty textForeColorProperty() {
        return textForeColor;
    }

    public IntegerProperty fontSizeProperty() {
        return fontSize;
    }

    public Property<FontFamily> fontFamilyProperty() {
        return fontFamily;
    }

    public BackgroundProperty selectedBackgroundProperty() {
        return selectedBackground;
    }

}
