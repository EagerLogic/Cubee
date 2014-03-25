package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.events.ClickEventArgs;
import com.eagerlogic.cubee.client.events.IEventListener;
import com.eagerlogic.cubee.client.properties.AExpression;
import com.eagerlogic.cubee.client.properties.BackgroundProperty;
import com.eagerlogic.cubee.client.properties.BooleanProperty;
import com.eagerlogic.cubee.client.properties.BorderProperty;
import com.eagerlogic.cubee.client.properties.ColorProperty;
import com.eagerlogic.cubee.client.properties.IChangeListener;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.PaddingProperty;
import com.eagerlogic.cubee.client.properties.Property;
import com.eagerlogic.cubee.client.properties.StringProperty;
import com.eagerlogic.cubee.client.style.styles.ABackground;
import com.eagerlogic.cubee.client.style.styles.Border;
import com.eagerlogic.cubee.client.style.styles.Color;
import com.eagerlogic.cubee.client.style.styles.ColorStop;
import com.eagerlogic.cubee.client.style.styles.ECursor;
import com.eagerlogic.cubee.client.style.styles.FontFamily;
import com.eagerlogic.cubee.client.style.styles.LinearGradient;
import com.eagerlogic.cubee.client.style.styles.Padding;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author dipacs
 */
public final class ComboBox<T> extends AUserControl {

    @Getter
    @Setter
    public static class StyleClass<T extends ComboBox> extends AUserControl.StyleClass<T> {

        private FontFamily fontFamily = FontFamily.Arial;
        private int fontSize = 12;
        private Color foreColor = Color.BLACK;
        private boolean bold;
        private boolean italic;
        private boolean underline;

        @Override
        public void apply(T component) {
            super.apply(component);

            component.fontFamilyProperty().set(fontFamily);
            component.fontSizeProperty().set(fontSize);
            component.foreColorProperty().set(foreColor);
            component.boldProperty().set(bold);
            component.italicProperty().set(italic);
            component.underlineProperty().set(underline);
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
        public Integer getMinHeight() {
            return super.getMinHeight();
        }

        @Override
        public void setMinWidth(Integer minWidth) {
            super.setMinWidth(minWidth);
        }

        @Override
        public Integer getMinWidth() {
            return super.getMinWidth();
        }

        @Override
        public void setBorder(Border border) {
            super.setBorder(border);
        }

        @Override
        public Border getBorder() {
            return super.getBorder();
        }

        @Override
        public void setPadding(Padding padding) {
            super.setPadding(padding);
        }

        @Override
        public Padding getPadding() {
            return super.getPadding();
        }

    }

//	private final BooleanProperty emptySelectionEnabled = new BooleanProperty(true, false, false);
    private final IntegerProperty selectedIndex = new IntegerProperty(-1, false, false);
    private final Property<T> selectedItem = new Property<T>(null, true, false);
    private final StringProperty emptySelectionText = new StringProperty("-- Empty --", false, false);

    private T[] items;

    private final Label label = new Label();

    public ComboBox() {
        this.paddingProperty().set(new Padding(5));
        this.borderProperty().set(new Border(1, Color.getRgbColor(0xc0c0c0), 0));
        this.backgroundProperty().set(new LinearGradient(0.0,
                new ColorStop(0.0, Color.getRgbColor(0xe0e0e0)),
                new ColorStop(1.0, Color.getRgbColor(0xc0c0c0))
        ));
        this.cursorProperty().set(ECursor.POINTER);

        label.textProperty().set(emptySelectionText.get());
        label.pointerTransparentProperty().set(Boolean.TRUE);
        label.translateXProperty().bind(new AExpression<Integer>() {

            {
                bind(clientWidthProperty(), label.boundsWidthProperty());
            }

            @Override
            public Integer calculate() {
                if (clientWidthProperty().get() > label.boundsWidthProperty().get()) {
                    return (clientWidthProperty().get() - label.boundsWidthProperty().get()) / 2;
                }
                return 0;
            }
        });
        this.getChildren().add(label);

        this.onClickEvent().addListener(new IEventListener<ClickEventArgs>() {

            @Override
            public void onFired(ClickEventArgs args) {
                final DefaultComboBoxDialog<T> dialog = new DefaultComboBoxDialog<T>();
                dialog.onInit(selectedIndex.get(), items, true, emptySelectionText.get());
                dialog.setCloseListener(new Runnable() {

                    @Override
                    public void run() {
                        selectedIndex.set(dialog.getResultIndex());
                    }
                });
                dialog.show();
            }
        });

        this.label.textProperty().bind(new AExpression<String>() {

            {
                this.bind(emptySelectionText, selectedIndex);
            }

            @Override
            public String calculate() {
                if (selectedIndex.get() < 0) {
                    return emptySelectionText.get();
                }
                int index = selectedIndex.get();
                if (index > items.length - 1) {
                    return emptySelectionText.get();
                }
                // handles null properly
                return "" + items[selectedIndex.get()];
            }
        });
        this.selectedItem.addChangeListener(new IChangeListener() {

            @Override
            public void onChanged(Object sender) {
                int idx = -1;
                T refItem = selectedItem.get();
                if (refItem != null) {
                    int i = 0;
                    for (T item : items) {
                        if (item == refItem) {
                            idx = i;
                            break;
                        }
                        i++;
                    }
                }
                selectedIndex.set(idx);
            }
        });
        this.selectedIndex.addChangeListener(new IChangeListener() {

            @Override
            public void onChanged(Object sender) {
                if (selectedIndex.get() < 0) {
                    selectedItem.set(null);
                }
                int index = selectedIndex.get();
                if (index > items.length - 1) {
                    selectedItem.set(null);
                }
                // handles null properly
                selectedItem.set(items[selectedIndex.get()]);
            }
        });

        this.applyDefaultStyle(ComboBox.class);
    }

    public T[] getItems() {
        return items;
    }

    public void setItems(T[] items) {
        this.items = items;
        this.selectedIndex.set(-1);
    }

    public IntegerProperty selectedIndexProperty() {
        return selectedIndex;
    }

    public Property<T> selectedItemProperty() {
        return selectedItem;
    }

    public StringProperty emptySelectionTextProperty() {
        return emptySelectionText;
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
    public final BackgroundProperty backgroundProperty() {
        return super.backgroundProperty();
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
    public final IntegerProperty minWidthProperty() {
        return super.minWidthProperty();
    }

    @Override
    public final IntegerProperty minHeightProperty() {
        return super.minHeightProperty();
    }

    @Override
    public final IntegerProperty maxWidthProperty() {
        return super.maxWidthProperty();
    }

    @Override
    public final IntegerProperty maxHeightProperty() {
        return super.maxHeightProperty();
    }

    public final ColorProperty foreColorProperty() {
        return label.foreColorProperty();
    }

    public final BooleanProperty boldProperty() {
        return label.boldProperty();
    }

    public final BooleanProperty italicProperty() {
        return label.italicProperty();
    }

    public final BooleanProperty underlineProperty() {
        return label.underlineProperty();
    }

    public final IntegerProperty fontSizeProperty() {
        return label.fontSizeProperty();
    }

    public final Property<FontFamily> fontFamilyProperty() {
        return label.fontFamilyProperty();
    }

}
