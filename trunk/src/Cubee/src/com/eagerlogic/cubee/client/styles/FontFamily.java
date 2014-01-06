package com.eagerlogic.cubee.client.styles;

import com.google.gwt.dom.client.Element;

/**
 *
 * @author dipacs
 */
public class FontFamily implements IStyle {

    public static final FontFamily Georgia = new FontFamily("Georgia, serif");
    public static final FontFamily PalatinoLinotype = new FontFamily("'Palatino Linotype', 'Book Antiqua', Palatino, serif");
    public static final FontFamily TimesNewRoman = new FontFamily("'Times New Roman', Times, serif");
    public static final FontFamily Arial = new FontFamily("Arial, Helvetica, sans-serif");
    public static final FontFamily ArialBlack = new FontFamily("Arial Black, Gadget, sans-serif");
    public static final FontFamily ComicSansMS = new FontFamily("'Comic Sans MS', cursive, sans-serif");
    public static final FontFamily Impact = new FontFamily("Impact, Charcoal, sans-serif");
    public static final FontFamily Lucida = new FontFamily("'Lucida Sans Unicode', 'Lucida Grande', sans-serif");
    public static final FontFamily Tahoma = new FontFamily("Tahoma, Geneva, sans-serif");
    public static final FontFamily TrebuchetMS = new FontFamily("'Trebuchet MS', Helvetica, sans-serif");
    public static final FontFamily Verdana = new FontFamily("Verdana, Geneva, sans-serif");
    public static final FontFamily CourierNew = new FontFamily("'Courier New', Courier, monospace");
    public static final FontFamily LucidaConsole = new FontFamily("'Lucida Console', Monaco, monospace");

    private final String css;

    public FontFamily(String css) {
        this.css = css;
    }

    public String toCSS() {
        return this.css;
    }

    @Override
    public void apply(Element element) {
        element.getStyle().setProperty("fontFamily", css);
    }

}
