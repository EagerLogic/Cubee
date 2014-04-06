/*
 * Copyright 2014 dipacs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.events.IEventListener;
import com.eagerlogic.cubee.client.events.MouseDownEventArgs;
import com.eagerlogic.cubee.client.events.MouseDragEventArgs;
import com.eagerlogic.cubee.client.properties.AExpression;
import com.eagerlogic.cubee.client.properties.BackgroundProperty;
import com.eagerlogic.cubee.client.properties.BorderProperty;
import com.eagerlogic.cubee.client.properties.ColorProperty;
import com.eagerlogic.cubee.client.properties.DoubleProperty;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.ext.AlignMiddleExp;
import com.eagerlogic.cubee.client.style.Style;
import com.eagerlogic.cubee.client.style.styles.ABackground;
import com.eagerlogic.cubee.client.style.styles.Border;
import com.eagerlogic.cubee.client.style.styles.BoxShadow;
import com.eagerlogic.cubee.client.style.styles.Color;
import com.eagerlogic.cubee.client.style.styles.ColorBackground;
import com.eagerlogic.cubee.client.style.styles.ColorStop;
import com.eagerlogic.cubee.client.style.styles.ECursor;
import com.eagerlogic.cubee.client.style.styles.LinearGradient;
import com.eagerlogic.cubee.shared.utils.AValidator;
import com.eagerlogic.cubee.shared.utils.ValidationException;

/**
 *
 * @author dipacs
 */
public final class Slider extends AUserControl {

    public static class StyleClass<T extends Slider> extends AUserControl.StyleClass<T> {

        private final Style<ABackground> background = new Style<ABackground>(null, true);
        private final Style<Border> border = new Style<Border>(null, true);
        private final Style<ABackground> buttonBackground = new Style<ABackground>(null, true);
        private final Style<Border> buttonBorder = new Style<Border>(null, true);

        @Override
        public void apply(T component) {
            super.apply(component);

            background.apply(component.backgroundProperty());
            border.apply(component.borderProperty());
            buttonBackground.apply(component.buttonBackgroundProperty());
            buttonBorder.apply(component.buttonBorderProperty());
        }

        @Override
        public Style<ABackground> getBackground() {
            return background;
        }

        @Override
        public Style<Border> getBorder() {
            return border;
        }

        public Style<ABackground> getButtonBackground() {
            return buttonBackground;
        }

        public Style<Border> getButtonBorder() {
            return buttonBorder;
        }

    }

    private static final AValidator<Double> valueValidator = new AValidator<Double>() {

        @Override
        public Double validate(Double value) throws ValidationException {
            if (value == null) {
                return 0.0;
            }

            if (value < 0.0) {
                return 0.0;
            }

            if (value > 1.0) {
                return 1.0;
            }

            return value;
        }
    };

    private final DoubleProperty value = new DoubleProperty(0.0, false, false, valueValidator);

    private final IntegerProperty width = new IntegerProperty(200);
    private final BackgroundProperty background = new BackgroundProperty(new ColorBackground(Color.getRgbColor(0xf0f0f0)));
    private final BorderProperty border = new BorderProperty(new Border(1, Color.LIGHT_GRAY, 3));
    private final BackgroundProperty selectedAreaBackground = new BackgroundProperty(null);
    private final ColorProperty buttonColor = new ColorProperty(Color.getRgbColor(0xf8f8f8), false);

    private Panel slider;
    private Panel button;

    private int startTranslateX;

    public Slider() {
        slider = new Panel();
        slider.widthProperty().bind(this.widthProperty());
        slider.heightProperty().set(6);
        slider.translateYProperty().bind(new AlignMiddleExp(this, slider));
        slider.backgroundProperty().bind(this.backgroundProperty());
        slider.borderProperty().bind(this.borderProperty());
        slider.shadowProperty().set(new BoxShadow(1, 2, 2, 0, Color.getArgbColor(0x10000000), true));
        this.getChildren().add(slider);

        button = new Panel();
        button.widthProperty().set(16);
        button.heightProperty().set(16);
        button.cursorProperty().set(ECursor.POINTER);
        button.backgroundProperty().bind(new AExpression<ABackground>() {

            {
                bind(buttonColor);
            }

            @Override
            public ABackground calculate() {
                LinearGradient lg = new LinearGradient(0.0,
                        new ColorStop(0.0, Color.fadeColors(Color.WHITE, buttonColor.get(), 0.5)),
                        new ColorStop(0.0, buttonColor.get()),
                        new ColorStop(1.0, Color.fadeColors(Color.BLACK, buttonColor.get(), 0.8))
                );
                return lg;
            }
        });
        button.borderProperty().bind(new AExpression<Border>() {

            {
                bind(buttonColor);
            }

            @Override
            public Border calculate() {
                return new Border(1, Color.fadeColors(Color.BLACK, buttonColor.get(), 0.6), 8);
            }
        });
        button.translateXProperty().bind(new AExpression<Integer>() {

            {
                bind(value, clientWidthProperty());
            }

            @Override
            public Integer calculate() {
                return (int) ((clientWidthProperty().get() - button.boundsWidthProperty().get()) * value.get());
            }
        });
        this.getChildren().add(button);

        button.onMouseDownEvent().addListener(new IEventListener<MouseDownEventArgs>() {

            @Override
            public void onFired(MouseDownEventArgs args) {
                startTranslateX = button.translateXProperty().get();
            }
        });
        button.onMouseDragEvent().addListener(new IEventListener<MouseDragEventArgs>() {

            @Override
            public void onFired(MouseDragEventArgs args) {
                int deltaX = args.getDeltaX();
                int newTx = startTranslateX + deltaX;
                if (newTx <= 0) {
                    value.set(0.0);
                    return;
                }
                int slideWidth = slider.clientWidthProperty().get() - button.boundsWidthProperty().get();
                if (newTx >= slideWidth) {
                    value.set(1.0);
                    return;
                }

                double newValue = newTx / (double) slideWidth;
                value.set(newValue);
            }
        });

        this.applyDefaultStyle(Slider.class);
    }

    @Override
    public BackgroundProperty backgroundProperty() {
        return this.background;
    }

    @Override
    public IntegerProperty widthProperty() {
        return this.width;
    }

    @Override
    public BorderProperty borderProperty() {
        return this.border;
    }

    public DoubleProperty valueProperty() {
        return value;
    }

    public BackgroundProperty selectedAreaBackgroundProperty() {
        return selectedAreaBackground;
    }

    public final BorderProperty buttonBorderProperty() {
        return button.borderProperty();
    }

    public BackgroundProperty buttonBackgroundProperty() {
        return button.backgroundProperty();
    }



}
