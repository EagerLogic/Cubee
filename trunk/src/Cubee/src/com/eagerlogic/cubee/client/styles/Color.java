package com.eagerlogic.cubee.client.styles;

/**
 *
 * @author dipacs
 */
public final class Color {
	
	public static final Color BLACK = new Color(0xff000000);
	public static final Color LIGHT_GRAY = new Color(0xffc0c0c0);
	public static final Color GRAY = new Color(0xff808080);
	public static final Color DARK_GRAY = new Color(0xff404040);
	public static final Color RED = new Color(0xffff0000);
	public static final Color DARK_RED = new Color(0xff800000);
	public static final Color GREEN = new Color(0xff00ff00);
	public static final Color DARK_GREEN = new Color(0xff008000);
	public static final Color GRASS_GREEN = new Color(0xff90ff00);
	public static final Color DARK_BLUE = new Color(0xff000080);
	public static final Color BLUE = new Color(0xff0000ff);
	public static final Color YELLOW = new Color(0xffffff00);
	public static final Color CYAN = new Color(0xff00ffff);
	public static final Color PURPLE = new Color(0xffff00ff);
	public static final Color ORANGE = new Color(0xffff8000);
	public static final Color RED_ORANGE = new Color(0xffff4000);
	public static final Color SKY_BLUE = new Color(0xff0080ff);
	public static final Color FUNKY_BLUE = new Color(0xff00c0ff);
	public static final Color PINK_PURPLE = new Color(0xffff0080);
	public static final Color BLUE_PURPLE = new Color(0xffff00c0);
	public static final Color BROWN = new Color(0xff603000);
	public static final Color PINK = new Color(0xffff8080);
	public static final Color WHITE = new Color(0xffffffff);
	public static final Color TRANSPARENT = new Color(0x00000000);
	
	public static Color getArgbColor(int argb) {
		return new Color(argb);
	}
	
	public static Color getArgbColor(int alpha, int red, int green, int blue) {
		alpha = fixComponent(alpha);
		red = fixComponent(red);
		green = fixComponent(green);
		blue = fixComponent(blue);
		
		return getArgbColor(
				alpha << 24
				| red << 16
				| green << 8
				| blue
				);
	}
	
	public static Color getRgbColor(int rgb) {
		return getArgbColor(rgb | 0xff000000);
	}
	
	public static Color getRgbColor(int red, int green, int blue) {
		return getArgbColor(255, red, green, blue);
	} 
	
	private static int fixComponent(int component) {
		if (component < 0) {
			return 0;
		}
		
		if (component > 255) {
			return 255;
		}
		
		return component;
	}
	
	public static Color fadeColors(Color startColor, Color endColor, double fadePosition) {
		return Color.getArgbColor(
				mixComponent(startColor.getAlpha(), endColor.getAlpha(), fadePosition), 
				mixComponent(startColor.getRed(), endColor.getRed(), fadePosition), 
				mixComponent(startColor.getGreen(), endColor.getGreen(), fadePosition), 
				mixComponent(startColor.getBlue(), endColor.getBlue(), fadePosition)
				);
	}
	
	private static int mixComponent(int startValue, int endValue, double pos) {
		int res = (int) (startValue + ((endValue - startValue) * pos));
		res = fixComponent(res);
		return res;
	}
	
	
	
	
	private final int argb;
	
	
	
	
	public Color(int argb) {
		this.argb = argb;
	}

	
	
	
	public int getArgb() {
		return argb;
	}
	
	public final int getAlpha() {
		return (argb >>> 24) & 0xff;
	}
	
	public final int getRed() {
		return (argb >>> 16) & 0xff;
	}
	
	public final int getGreen() {
		return (argb >>> 8) & 0xff;
	}
	
	public final int getBlue() {
		return argb & 0xff;
	}
	
	public final Color fade(Color fadeColor, double fadePosition) {
		return Color.fadeColors(this, fadeColor, fadePosition);
	}
	
	public String toCSS() {
		return "rgba(" + getRed() + ", " + getGreen() + ", " + getBlue() + ", " + (getAlpha() / 255.0) + ")";
	}

}
