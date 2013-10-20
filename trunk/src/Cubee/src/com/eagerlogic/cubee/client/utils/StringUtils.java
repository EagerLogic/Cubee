package com.eagerlogic.cubee.client.utils;

/**
 *
 * @author dipacs
 */
public final class StringUtils {
	
	public static final String escapeHTML(String html) {
		html = html.replace("<", "&lt;");
		html = html.replace(">", "&gt;");
		html = html.replace("\"", "&quot;");
		html = html.replace("&", "&amp;");
		return html;
	}
	
	private StringUtils() {}

}
