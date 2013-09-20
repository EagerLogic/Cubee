/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.ui;

/**
 *
 * @author dipacs
 */
public final class Color {
    
    private final int value;
    
    public Color(int argb) {
        this.value = argb;
    }
    
    public String toCss() {
        String res = "rgba(";
        res += ((value >>> 16) & 0xff) + ",";
        res += ((value >>> 8) & 0xff) + ",";
        res += ((value) & 0xff) + ",";
        res += ((value >>> 24) & 0xff) / 255.0;
        res += ")";
        return res;
    }
    
}
