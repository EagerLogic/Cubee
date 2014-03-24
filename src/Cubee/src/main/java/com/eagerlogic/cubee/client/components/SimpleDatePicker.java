/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.properties.BackgroundProperty;
import com.eagerlogic.cubee.client.properties.BorderProperty;
import com.eagerlogic.cubee.client.properties.ColorProperty;
import com.eagerlogic.cubee.client.properties.PaddingProperty;
import com.eagerlogic.cubee.client.properties.Property;
import com.eagerlogic.cubee.client.style.styles.ABackground;
import com.eagerlogic.cubee.client.style.styles.Border;
import com.eagerlogic.cubee.client.style.styles.Color;
import com.eagerlogic.cubee.client.style.styles.ColorBackground;
import com.eagerlogic.cubee.client.style.styles.Padding;
import java.util.Date;

/**
 *
 * @author dipacs
 */
public class SimpleDatePicker extends AUserControl {
    
    private final BackgroundProperty background = new BackgroundProperty(new ColorBackground(Color.LIGHT_GRAY), true, false);
    private final BorderProperty border = new BorderProperty(new Border(1, Color.GRAY, 3), true, false);
    private final PaddingProperty padding = new PaddingProperty(new Padding(3), true, false);
    private final ColorProperty foreColor = new ColorProperty(Color.BLACK, false, false);
    private final Property<Date> value = new Property<Date>(new Date(), false, false);

    public SimpleDatePicker() {
        HBox root = new HBox();
        this.getChildren().add(root);
        
        
    }

    public BackgroundProperty backgroundProperty() {
        return background;
    }

    public BorderProperty borderProperty() {
        return border;
    }

    public PaddingProperty paddingProperty() {
        return padding;
    }

    public ColorProperty foreColorProperty() {
        return foreColor;
    }

    public ABackground getBackground() {
        return background.get();
    }

    public void setBackground(ABackground background) {
        this.background.set(background);
    }

    public Border getBorder() {
        return border.get();
    }

    public void setBorder(Border border) {
        this.border.set(border);
    }

    public Padding getPadding() {
        return padding.get();
    }

    public void setPadding(Padding padding) {
        this.padding.set(padding);
    }

    public Color getForeColor() {
        return foreColor.get();
    }

    public void setForeColor(Color foreColor) {
        this.foreColor.set(foreColor);
    }
    
}
