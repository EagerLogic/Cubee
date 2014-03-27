package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.events.IEventListener;
import com.eagerlogic.cubee.client.events.MouseDownEventArgs;
import com.eagerlogic.cubee.client.events.MouseUpEventArgs;
import com.eagerlogic.cubee.client.properties.AExpression;
import com.eagerlogic.cubee.client.properties.BackgroundProperty;
import com.eagerlogic.cubee.client.properties.BooleanProperty;
import com.eagerlogic.cubee.client.properties.BorderProperty;
import com.eagerlogic.cubee.client.properties.ColorProperty;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.PaddingProperty;
import com.eagerlogic.cubee.client.properties.Property;
import com.eagerlogic.cubee.client.properties.StringProperty;
import com.eagerlogic.cubee.client.properties.ext.AlignMiddleExp;
import com.eagerlogic.cubee.client.style.Style;
import com.eagerlogic.cubee.client.style.styles.ABackground;
import com.eagerlogic.cubee.client.style.styles.Border;
import com.eagerlogic.cubee.client.style.styles.Color;
import com.eagerlogic.cubee.client.style.styles.ColorBackground;
import com.eagerlogic.cubee.client.style.styles.ECursor;
import com.eagerlogic.cubee.client.style.styles.ETextAlign;
import com.eagerlogic.cubee.client.style.styles.ETextOverflow;
import com.eagerlogic.cubee.client.style.styles.FontFamily;
import com.eagerlogic.cubee.client.style.styles.Padding;

/**
 *
 * @author dipacs
 */
public final class Button extends AUserControl {

    public static class StyleClass<T extends Button> extends AUserControl.StyleClass<T> {

        private final Style<Boolean> bold = new Style<Boolean>(null, false);
        private final Style<Boolean> italic = new Style<Boolean>(null, false);
        private final Style<Boolean> underline = new Style<Boolean>(null, false);
        private final Style<FontFamily> fontFamily = new Style<FontFamily>(null, false);
        private final Style<Integer> fontSize = new Style<Integer>(null, false);
        private final Style<Color> foreColor = new Style<Color>(null, false);
        private final Style<ETextAlign> textAlign = new Style<ETextAlign>(null, false);
        private final Style<ETextOverflow> textOverflow = new Style<ETextOverflow>(null, false);

        @Override
        public void apply(T component) {
            super.apply(component);

            bold.apply(component.boldProperty());
            italic.apply(component.italicProperty());
            underline.apply(component.underlineProperty());
            fontFamily.apply(component.fontFamilyProperty());
            fontSize.apply(component.fontSizeProperty());
            foreColor.apply(component.foreColorProperty());
            textAlign.apply(component.textAlignProperty());
            textOverflow.apply(component.textOverflowProperty());
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
        public Style<Border> getBorder() {
            return super.getBorder();
        }

        @Override
        public Style<Padding> getPadding() {
            return super.getPadding();
        }

        public Style<Boolean> getBold() {
            return bold;
        }

        public Style<Boolean> getItalic() {
            return italic;
        }

        public Style<Boolean> getUnderline() {
            return underline;
        }

        public Style<FontFamily> getFontFamily() {
            return fontFamily;
        }

        public Style<Integer> getFontSize() {
            return fontSize;
        }

        public Style<Color> getForeColor() {
            return foreColor;
        }

        public Style<ETextAlign> getTextAlign() {
            return textAlign;
        }

        public Style<ETextOverflow> getTextOverflow() {
            return textOverflow;
        }

    }

    private final Label label;

    public Button() {
        this.paddingProperty().set(new Padding(5));
        this.cursorProperty().set(ECursor.POINTER);
        this.borderProperty().set(new Border(1, Color.getRgbColor(0xc0c0c0), 0));
        this.backgroundProperty().set(new ColorBackground(Color.getRgbColor(0xf0f0f0)));

        label = new Label();
        label.textOverflowProperty().set(ETextOverflow.ELLIPSIS);
        label.widthProperty().bind(new AExpression<Integer>() {

            {
                bind(widthProperty());
            }

            @Override
            public Integer calculate() {
                if (widthProperty().get() == null) {
                    return null;
                } else {
                    return widthProperty().get();
                }
            }
        });
        label.textAlignProperty().set(ETextAlign.CENTER);
        label.translateYProperty().bind(new AlignMiddleExp(this, label));
        label.pointerTransparentProperty().set(Boolean.TRUE);
        label.selectableProperty().set(Boolean.FALSE);
        this.getChildren().add(label);

        this.onMouseDownEvent().addListener(new IEventListener<MouseDownEventArgs>() {
            @Override
            public void onFired(MouseDownEventArgs args) {
                alphaProperty().set(0.5);
            }
        });
        this.onMouseUpEvent().addListener(new IEventListener<MouseUpEventArgs>() {
            @Override
            public void onFired(MouseUpEventArgs args) {
                alphaProperty().set(1.0);
            }
        });

        this.applyDefaultStyle(Button.class);
    }

    @Override
    public IntegerProperty widthProperty() {
        return super.widthProperty();
    }

    @Override
    public IntegerProperty heightProperty() {
        return super.heightProperty();
    }

    @Override
    public BackgroundProperty backgroundProperty() {
        return super.backgroundProperty();
    }

    @Override
    public PaddingProperty paddingProperty() {
        return super.paddingProperty();
    }

    @Override
    public BorderProperty borderProperty() {
        return super.borderProperty();
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

    public Property<FontFamily> fontFamilyProperty() {
        return label.fontFamilyProperty();
    }
}
