/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.components.shapes.Triangle;
import com.eagerlogic.cubee.client.events.ClickEventArgs;
import com.eagerlogic.cubee.client.events.IEventListener;
import com.eagerlogic.cubee.client.properties.AExpression;
import com.eagerlogic.cubee.client.properties.BackgroundProperty;
import com.eagerlogic.cubee.client.properties.BooleanProperty;
import com.eagerlogic.cubee.client.properties.ColorProperty;
import com.eagerlogic.cubee.client.properties.IChangeListener;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.KeyFrame;
import com.eagerlogic.cubee.client.properties.Property;
import com.eagerlogic.cubee.client.properties.StringProperty;
import com.eagerlogic.cubee.client.properties.Timeline;
import com.eagerlogic.cubee.client.style.Style;
import com.eagerlogic.cubee.client.style.styles.ABackground;
import com.eagerlogic.cubee.client.style.styles.Color;
import com.eagerlogic.cubee.client.style.styles.ECursor;
import com.eagerlogic.cubee.client.style.styles.EOrientation;
import com.eagerlogic.cubee.client.style.styles.ETextOverflow;
import com.eagerlogic.cubee.client.style.styles.EVAlign;
import com.eagerlogic.cubee.client.style.styles.FontFamily;

/**
 *
 * @author dipacs
 */
public final class CollapseLabel extends AUserControl {
    
    public static class StyleClass<T extends CollapseLabel> extends AUserControl.StyleClass<T> {
        
        private final Style<Integer> spaceWidth = new Style<Integer>(null, false);
        private final Style<Integer> triangleSize = new Style<Integer>(null, false);
        private final Style<Color> triangleColor = new Style<Color>(null, false);
        private final Style<Integer> fontSize = new Style<Integer>(null, false);
        private final Style<Color> foreColor = new Style<Color>(null, false);
        private final Style<FontFamily> fontFamily = new Style<FontFamily>(null, false);
        private final Style<Boolean> bold = new Style<Boolean>(null, false);
        private final Style<Boolean> italic = new Style<Boolean>(null, false);
        private final Style<Boolean> underline = new Style<Boolean>(null, false);

        @Override
        public void apply(T component) {
            super.apply(component);
            
            spaceWidth.apply(component.spaceWidthProperty());
            triangleSize.apply(component.triangleSizeProperty());
            triangleColor.apply(component.triangleColorProperty());
            fontSize.apply(component.fontSizeProperty());
            foreColor.apply(component.foreColorProperty());
            fontFamily.apply(component.fontFamilyProperty());
            bold.apply(component.boldProperty());
            italic.apply(component.italicProperty());
            underline.apply(component.underlineProperty());
            
        }

        @Override
        public Style<Integer> getWidth() {
            return super.getWidth();
        }

        @Override
        public Style<Integer> getHeight() {
            return super.getHeight();
        }

        @Override
        public Style<ABackground> getBackground() {
            return super.getBackground();
        }

        public Style<Integer> getSpaceWidth() {
            return spaceWidth;
        }

        public Style<Integer> getTriangleSize() {
            return triangleSize;
        }

        public Style<Color> getTriangleColor() {
            return triangleColor;
        }

        public Style<Integer> getFontSize() {
            return fontSize;
        }

        public Style<Color> getForeColor() {
            return foreColor;
        }

        public Style<FontFamily> getFontFamily() {
            return fontFamily;
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
        
    }

    private final BooleanProperty collapsed = new BooleanProperty(false, false, false);
    private final IntegerProperty spaceWidth = new IntegerProperty(5, false, false);
    private final IntegerProperty triangleSize = new IntegerProperty(8, false, false);
    private final ColorProperty triangleColor = new ColorProperty(Color.GRAY, false, false);
    private final IntegerProperty fontSize = new IntegerProperty(12, false, false);
    private final ColorProperty foreColor = new ColorProperty(Color.GRAY, false, false);
    private final Property<FontFamily> fontFamily = new Property<FontFamily>(FontFamily.Arial, false, false);
    private final BooleanProperty bold = new BooleanProperty(false, false, false);
    private final BooleanProperty italic = new BooleanProperty(false, false, false);
    private final BooleanProperty underline = new BooleanProperty(false, false, false);
    private final StringProperty text = new StringProperty("", false, false);
    private Triangle triangle;
    private Label label;
    private Panel space;
    private Panel triangleContainer;
    private Timeline tl = null;

    public CollapseLabel() {
        this.cursorProperty().set(ECursor.POINTER);
        
        HBox root = new HBox();
        root.handlePointerProperty().set(false);
        this.getChildren().add(root);
        
        triangleContainer = new Panel();
        triangleContainer.widthProperty().bind(new AExpression<Integer>() {
            
            {
                bind(triangleSize);
            }

            @Override
            public Integer calculate() {
                int ts = triangleSize.get();
                return ((int) Math.sqrt(ts * ts * 2)); 
            }
        });
        triangleContainer.heightProperty().bind(triangleContainer.widthProperty());
        root.getChildren().add(triangleContainer);
        root.setLastCellVAlign(EVAlign.MIDDLE);

        triangle = new Triangle();
        triangle.widthProperty().bind(triangleSize);
        triangle.heightProperty().bind(triangleSize);
        triangle.colorProperty().bind(triangleColor);
        triangle.orientationProperty().set(EOrientation.BOTTOM);
        triangle.cursorProperty().bind(cursorProperty());
        triangle.translateXProperty().bind(new AExpression<Integer>() {
            
            {
                bind(triangle.clientWidthProperty(), triangleContainer.clientWidthProperty());
            }

            @Override
            public Integer calculate() {
                return (triangleContainer.clientWidthProperty().get() - triangle.clientWidthProperty().get()) / 2;
            }
        });
        triangle.translateYProperty().bind(triangle.translateXProperty());
        triangleContainer.getChildren().add(triangle);

        space = new Panel();
        space.widthProperty().bind(spaceWidth);
        space.heightProperty().set(1);
        space.cursorProperty().bind(cursorProperty());
        root.getChildren().add(space);

        label = new Label();
        label.textProperty().bind(text);
        label.fontSizeProperty().bind(fontSize);
        label.foreColorProperty().bind(foreColor);
        label.fontFamilyProperty().bind(fontFamily);
        label.boldProperty().bind(bold);
        label.italicProperty().bind(italic);
        label.underlineProperty().bind(underline);
        label.textOverflowProperty().set(ETextOverflow.ELLIPSIS);
        label.cursorProperty().bind(cursorProperty());
        label.widthProperty().bind(new AExpression<Integer>() {
            {
                bind(widthProperty(), triangleSize, spaceWidth);
            }

            @Override
            public Integer calculate() {
                if (widthProperty().get() == null) {
                    return null;
                }

                int res = widthProperty().get() - triangleSize.get() - spaceWidth.get();
                if (res < 0) {
                    res = 0;
                }
                return res;
            }
        });
        root.getChildren().add(label);
        root.setLastCellVAlign(EVAlign.MIDDLE);

        this.collapsed.addChangeListener(new IChangeListener() {
            @Override
            public void onChanged(Object sender) {
                if (tl != null) {
                    tl.stop();
                }
                if (collapsed.get()) {
                    tl = new Timeline(new KeyFrame(0, triangle.rotateProperty(), triangle.rotateProperty().get()),
                            new KeyFrame(100, triangle.rotateProperty(), -0.25));
                    tl.start();
                } else {
                    tl = new Timeline(new KeyFrame(0, triangle.rotateProperty(), triangle.rotateProperty().get()),
                            new KeyFrame(100, triangle.rotateProperty(), 0.0));
                    tl.start();
                }
            }
        });

        this.onClickEvent().addListener(new IEventListener<ClickEventArgs>() {
            @Override
            public void onFired(ClickEventArgs args) {
                collapsed.set(!collapsed.get());
            }
        });
        
        this.applyDefaultStyle(CollapseLabel.class);
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

    public BooleanProperty collapsedProperty() {
        return collapsed;
    }

    public IntegerProperty spaceWidthProperty() {
        return spaceWidth;
    }

    public IntegerProperty triangleSizeProperty() {
        return triangleSize;
    }

    public ColorProperty triangleColorProperty() {
        return triangleColor;
    }

    public IntegerProperty fontSizeProperty() {
        return fontSize;
    }

    public ColorProperty foreColorProperty() {
        return foreColor;
    }

    public Property<FontFamily> fontFamilyProperty() {
        return fontFamily;
    }

    public BooleanProperty boldProperty() {
        return bold;
    }

    public BooleanProperty italicProperty() {
        return italic;
    }

    public BooleanProperty underlineProperty() {
        return underline;
    }

    public StringProperty textProperty() {
        return text;
    }
}
