package com.eagerlogic.cubee.client.style.styles;

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

    static {
        initFontContainerStyle();
    }

    private static native void initFontContainerStyle() /*-{
        $wnd.fontsStyle = document.createElement('style');
        $wnd.fontsStyle.type = "text/css";
        $doc.getElementsByTagName('head')[0].appendChild($wnd.fontsStyle);
    }-*/;

    public static native void registerFont(String name, String src, String extra) /*-{
        var ex = extra;
        if (ex == null) {
            ex = '';
        }
        var ct = "@font-face {font-family: '" + name + "'; src: url('" + src + "');" + ex + "}";
        var ih = $wnd.fontsStyle.innerHTML;
        if (ih == null) {
            ih = '';
        }
        $wnd.fontsStyle.innerHTML = ih + ct;
    }-*/;

    private final String css;

    public FontFamily(String css) {
        this.css = css;
    }

    public final String toCSS() {
        return this.css;
    }

    @Override
    public final void apply(Element element) {
        element.getStyle().setProperty("fontFamily", css);
    }

}
