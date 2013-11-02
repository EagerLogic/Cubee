package com.eagerlogic.cubee.client.styles;

import com.google.gwt.dom.client.Element;

/**
 *
 * @author dipacs
 */
public class LinearGradient extends ABackground {
	
	private final double startX;
	private final double startY;
	private final double endX;
	private final double endY;
	private final ColorStop[] stops;

	public LinearGradient(double startX, double startY, double endX, double endY, ColorStop... stops) {
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.stops = stops;
	}
	
	
	
//	background: #ff0000; /* Old browsers */
//	background: -moz-linear-gradient(-45deg, #ff0000 0%, #00ff00 100%); /* FF3.6+ */
//	background: -webkit-gradient(linear, left top, right bottom, color-stop(0%,#ff0000), color-stop(100%,#00ff00)); /* Chrome,Safari4+ */
//	background: -webkit-linear-gradient(-45deg, #ff0000 0%,#00ff00 100%); /* Chrome10+,Safari5.1+ */
//	background: -o-linear-gradient(-45deg, #ff0000 0%,#00ff00 100%); /* Opera 11.10+ */
//	background: -ms-linear-gradient(-45deg, #ff0000 0%,#00ff00 100%); /* IE10+ */
//	background: linear-gradient(135deg, #ff0000 0%,#00ff00 100%); /* W3C */
//	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ff0000', endColorstr='#00ff00',GradientType=1 ); /* IE6-9 fallback on horizontal gradient */

	@Override
	public void apply(Element element) {
		applyMozilla(element);
		applyWebkitOld(element);
		applyWebkitNew(element);
		applyOpera(element);
		applyMs(element);
		applyStandard(element);
	}
	
	public void applyMozilla(Element element) {
		String style = "-moz-linear-gradient(" + ((int)(startX * 100)) + "% " 
				+ ((int)(startY * 100)) + "%, " + ((int)(endX * 100)) + "% " 
				+ ((int)(endY * 100)) + "%";
		for (ColorStop stop : stops) {
			style += ", " + stop.getColor().toCSS() + "," + ((int)stop.getPosition() * 100) + "%";
		}
		style += ")";
		element.getStyle().setProperty("background", style);
	}
	
	public void applyWebkitOld(Element element) {
		// background: -webkit-gradient(linear, left top, right bottom, color-stop(0%,#ff0000), color-stop(100%,#00ff00)); /* Chrome,Safari4+ */
		String style = "-webkit-gradient(linear, " + ((int)(startX * 100)) + "% " 
				+ ((int)(startY * 100)) + "%, " + ((int)(endX * 100)) + "% " 
				+ ((int)(endY * 100)) + "%";
		for (ColorStop stop : stops) {
			style += ", color-stop(" + ((int)stop.getPosition() * 100) + "%," + stop.getColor().toCSS() + ")";
		}
		style += ")";
		element.getStyle().setProperty("background", style);
	}
	
	public void applyWebkitNew(Element element) {
		String style = "-webkit-linear-gradient(" + ((int)(startX * 100)) + "% " 
				+ ((int)(startY * 100)) + "%, " + ((int)(endX * 100)) + "% " 
				+ ((int)(endY * 100)) + "%";
		for (ColorStop stop : stops) {
			style += ", " + stop.getColor().toCSS() + "," + ((int)stop.getPosition() * 100) + "%";
		}
		style += ")";
		element.getStyle().setProperty("background", style);
	}
	
	public void applyOpera(Element element) {
		String style = "-o-linear-gradient(" + ((int)(startX * 100)) + "% " 
				+ ((int)(startY * 100)) + "%, " + ((int)(endX * 100)) + "% " 
				+ ((int)(endY * 100)) + "%";
		for (ColorStop stop : stops) {
			style += ", " + stop.getColor().toCSS() + "," + ((int)stop.getPosition() * 100) + "%";
		}
		style += ")";
		element.getStyle().setProperty("background", style);
	}
	
	public void applyMs(Element element) {
		String style = "-ms-linear-gradient(" + ((int)(startX * 100)) + "% " 
				+ ((int)(startY * 100)) + "%, " + ((int)(endX * 100)) + "% " 
				+ ((int)(endY * 100)) + "%";
		for (ColorStop stop : stops) {
			style += ", " + stop.getColor().toCSS() + "," + ((int)stop.getPosition() * 100) + "%";
		}
		style += ")";
		element.getStyle().setProperty("background", style);
	}
	
	public void applyStandard(Element element) {
		String style = "linear-gradient(" + ((int)(startX * 100)) + "% " 
				+ ((int)(startY * 100)) + "%, " + ((int)(endX * 100)) + "% " 
				+ ((int)(endY * 100)) + "%";
		for (ColorStop stop : stops) {
			style += ", " + stop.getColor().toCSS() + "," + ((int)stop.getPosition() * 100) + "%";
		}
		style += ")";
		element.getStyle().setProperty("background", style);
	}
	
	public void applyFilter(Element element) {
		// TODO implement
	}

}
