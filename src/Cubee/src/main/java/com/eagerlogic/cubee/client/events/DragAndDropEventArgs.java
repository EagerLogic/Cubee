package com.eagerlogic.cubee.client.events;

import com.google.gwt.core.client.JavaScriptObject;

public class DragAndDropEventArgs {
	
	private final JavaScriptObject jsObject;

	public DragAndDropEventArgs(JavaScriptObject jsObject) {
		super();
		this.jsObject = jsObject;
	}
	
	public native void preventDefault() /*-{
		var jso = this.@com.eagerlogic.cubee.client.events.DragAndDropEventArgs::jsObject;
		jso.preventDefault();
	}-*/;
	
	public native void stopPropagation() /*-{
		var jso = this.@com.eagerlogic.cubee.client.events.DragAndDropEventArgs::jsObject;
		jso.stopPropagation();
	}-*/;
	
	public native String getData(String type) /*-{
		var jso = this.@com.eagerlogic.cubee.client.events.DragAndDropEventArgs::jsObject;
		return jso.dataTransfer.getData(type);
	}-*/;
	
	public native void setData(String type, String data)  /*-{
		var jso = this.@com.eagerlogic.cubee.client.events.DragAndDropEventArgs::jsObject;
		jso.dataTransfer.setData(type, data);
	}-*/;
	
	public native String getEffectAllowed() /*-{
		var jso = this.@com.eagerlogic.cubee.client.events.DragAndDropEventArgs::jsObject;
		return jso.dataTransfer.effectAllowed;
	}-*/;
	
	public native void setEffectAllowed(String effectAllowed)  /*-{
		var jso = this.@com.eagerlogic.cubee.client.events.DragAndDropEventArgs::jsObject;
		jso.dataTransfer.effectAllowed = effectAllowed;
	}-*/;
	
	public native String getDropEffect() /*-{
		var jso = this.@com.eagerlogic.cubee.client.events.DragAndDropEventArgs::jsObject;
		return jso.dataTransfer.dropEffect;
	}-*/;
	
	public native void setDropEffect(String effectAllowed)  /*-{
		var jso = this.@com.eagerlogic.cubee.client.events.DragAndDropEventArgs::jsObject;
		jso.dataTransfer.dropEffect = dropEffect;
	}-*/;

}
