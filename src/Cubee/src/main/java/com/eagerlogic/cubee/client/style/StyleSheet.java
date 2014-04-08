/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.style;

import com.eagerlogic.cubee.client.components.AComponent;
import java.util.HashMap;

/**
 *
 * @author dipacs
 */
public class StyleSheet {

    private static StyleSheet defaultStyleSheet = new DefaultStyleSheet();

    public static StyleSheet getDefault() {
        return defaultStyleSheet;
    }

    public static void setDefault(StyleSheet defaultStyleSheet) {
        StyleSheet.defaultStyleSheet = defaultStyleSheet;
    }

    public static <T extends AComponent> void applyStyle(T component, AStyleClass<? super T> style) {
        style.apply(component);
    }

    public static void applyDefaultStyle(AComponent component) {
        getDefault().applyStyle(component);
    }

    private final HashMap<Class<? extends AComponent>, AStyleClass<? extends AComponent>> styles = new HashMap<Class<? extends AComponent>, AStyleClass<? extends AComponent>>();

    public StyleSheet() {

    }

    public StyleSheet(StyleSheet base) {
        styles.putAll(base.styles);
    }

    public <T extends AComponent> void setStyle(Class<T> componentClass,
            AStyleClass<T> styleClass) {
        styles.put(componentClass, styleClass);
    }

    public <T extends AComponent> AStyleClass<T> getStyle(Class<T> componentClass) {
        return (AStyleClass<T>) styles.get(componentClass);
    }

    public <T extends AComponent> void applyStyle(T component) {
        AStyleClass<T> style = getStyle((Class<T>) component.getClass());
        if (style != null) {
            style.apply(component);
        }
    }


}
