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
import com.eagerlogic.cubee.client.style.styles.ABackground;
import com.eagerlogic.cubee.client.style.styles.Border;
import com.eagerlogic.cubee.client.style.styles.Color;
import com.eagerlogic.cubee.client.style.styles.ColorBackground;
import com.eagerlogic.cubee.client.style.styles.ECursor;
import com.eagerlogic.cubee.client.style.styles.ETextAlign;
import com.eagerlogic.cubee.client.style.styles.ETextOverflow;
import com.eagerlogic.cubee.client.style.styles.FontFamily;
import com.eagerlogic.cubee.client.style.styles.Padding;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author dipacs
 */
public final class Button extends AUserControl {

    @Getter
    @Setter
    public static class StyleClass<T extends Button> extends AUserControl.StyleClass<T> {

        private boolean bold = false;
        private boolean italic = false;
        private boolean underline = false;
        private FontFamily fontFamily = FontFamily.Arial;
        private int fontSize = 12;
        private Color foreColor = Color.BLACK;
        private ETextAlign textAlign = ETextAlign.CENTER;
        private ETextOverflow textOverflow = ETextOverflow.ELLIPSIS;

        @Override
        public void apply(T component) {
            super.apply(component);

            component.boldProperty().set(bold);
            component.italicProperty().set(italic);
            component.underlineProperty().set(underline);
            component.fontFamilyProperty().set(fontFamily);
            component.fontSizeProperty().set(fontSize);
            component.foreColorProperty().set(foreColor);
            component.textAlignProperty().set(textAlign);
            component.textOverflowProperty().set(textOverflow);
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
