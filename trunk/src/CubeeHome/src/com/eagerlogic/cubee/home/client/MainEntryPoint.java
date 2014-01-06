package com.eagerlogic.cubee.home.client;

import com.eagerlogic.cubee.client.components.CubeePanel;
import com.eagerlogic.cubee.client.components.Label;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class MainEntryPoint implements EntryPoint {

	@Override
	public void onModuleLoad() {
		// adding cubee panel to the root
		RootPanel.get().getElement().appendChild(CubeePanel.getInstance().getElement());
		
		// creating a label
		Label lblHello = new Label();
		lblHello.textProperty().set("Hello Cubee!");
		
		// adding label as the child of the CubeePanel
		CubeePanel.getInstance().setRootComponent(lblHello);
	}

}
