/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.components.AComponent;
import com.eagerlogic.cubee.client.components.ADialog;
import com.eagerlogic.cubee.client.components.Button;
import com.eagerlogic.cubee.client.components.HBox;
import com.eagerlogic.cubee.client.components.Label;
import com.eagerlogic.cubee.client.components.Panel;
import com.eagerlogic.cubee.client.components.PasswordBox;
import com.eagerlogic.cubee.client.components.TextArea;
import com.eagerlogic.cubee.client.components.TextBox;
import com.eagerlogic.cubee.client.components.VBox;
import com.eagerlogic.cubee.client.events.ClickEventArgs;
import com.eagerlogic.cubee.client.events.IEventListener;
import com.eagerlogic.cubee.client.properties.StringProperty;
import com.eagerlogic.cubee.client.style.styles.Border;
import com.eagerlogic.cubee.client.style.styles.BoxShadow;
import com.eagerlogic.cubee.client.style.styles.Color;
import com.eagerlogic.cubee.client.style.styles.ColorBackground;
import com.eagerlogic.cubee.client.style.styles.ECursor;
import com.eagerlogic.cubee.client.style.styles.EHAlign;
import com.eagerlogic.cubee.client.style.styles.ETextAlign;
import com.eagerlogic.cubee.client.style.styles.ETextOverflow;
import com.eagerlogic.cubee.client.style.styles.EVAlign;
import com.eagerlogic.cubee.client.style.styles.Padding;

/**
 *
 * @author Dávid
 */
public abstract class ADialogWindow extends ADialog {

	private final StringProperty title = new StringProperty("UNTITLED", false);
	private final StringProperty info = new StringProperty("", false);
	private final StringProperty errorMessage = new StringProperty("", false);
	private final StringProperty okTitle = new StringProperty("OK", false);
	private final StringProperty cancelTitle = new StringProperty("Cancel", false);

	private VBox vbFields = new VBox();

	public ADialogWindow(boolean modal, boolean autoClose) {
		this(modal, autoClose, Color.getArgbColor(0x60000000));
	}

	public ADialogWindow(boolean modal, boolean autoClose, Color glassColor) {
		super(modal, autoClose, glassColor);

		Panel container = new Panel();
		container.paddingProperty().set(new Padding(20));
		this.setRootComponent(container);

		Panel root = new Panel();
		root.shadowProperty().set(new BoxShadow(1, 1, 15, 0, Color.getArgbColor(0xc0000000), false));
		root.backgroundProperty().set(new ColorBackground(Color.WHITE));
		root.borderProperty().set(new Border(0, Color.LIGHT_GRAY, 0));
		root.paddingProperty().set(new Padding(20));
		root.widthProperty().set(400);
		container.getChildren().add(root);

		container.widthProperty().bind(root.boundsWidthProperty().add(40));
		container.heightProperty().bind(root.boundsHeightProperty().add(40));

		VBox vb = new VBox();
		root.getChildren().add(vb);

		Label lblTitle = new Label();
		lblTitle.textProperty().bind(title);
		lblTitle.fontSizeProperty().set(24);
		lblTitle.boldProperty().set(true);
		lblTitle.foreColorProperty().set(Color.getRgbColor(0x444444));
		lblTitle.widthProperty().set(400);
		lblTitle.textOverflowProperty().set(ETextOverflow.ELLIPSIS);
		vb.getChildren().add(lblTitle);

		vb.addEmptyCell(20);

		Label lblInfo = new Label();
		lblInfo.textProperty().bind(info);
		lblInfo.fontSizeProperty().set(14);
		lblInfo.foreColorProperty().set(Color.GRAY);
		lblInfo.widthProperty().set(400);
		lblInfo.textAlignProperty().set(ETextAlign.CENTER);
		vb.getChildren().add(lblInfo);

		vb.addEmptyCell(20);

		Label lblError = new Label();
		lblError.textProperty().bind(errorMessage);
		lblError.fontSizeProperty().set(16);
		lblError.foreColorProperty().set(Color.getRgbColor(0xcc0000));
		lblError.widthProperty().set(400);
		lblError.textAlignProperty().set(ETextAlign.CENTER);
		vb.getChildren().add(lblError);

		vb.addEmptyCell(20);

		vbFields.widthProperty().set(400);
		vb.getChildren().add(vbFields);

		vb.addEmptyCell(10);

		HBox hbBtns = new HBox();
		vb.getChildren().add(hbBtns);
		vb.setLastCellHAlign(EHAlign.RIGHT);

		Label lblCancel = new Label();
		lblCancel.textProperty().bind(cancelTitle);
		lblCancel.fontSizeProperty().set(14);
		lblCancel.foreColorProperty().set(Color.getRgbColor(0x00aacc));
		lblCancel.cursorProperty().set(ECursor.POINTER);
		hbBtns.getChildren().add(lblCancel);
		hbBtns.setLastCellVAlign(EVAlign.MIDDLE);

		hbBtns.addEmptyCell(20);

		Button btnOk = new Button();
		btnOk.textProperty().bind(okTitle);
		hbBtns.getChildren().add(btnOk);

		lblCancel.onClickEvent().addListener(new IEventListener<ClickEventArgs>() {

			@Override
			public void onFired(ClickEventArgs args) {
				close();
			}
		});

		btnOk.onClickEvent().addListener(new IEventListener<ClickEventArgs>() {

			@Override
			public void onFired(ClickEventArgs args) {
				onOkClicked();
			}
		});
	}

	protected TextBox addTextField(String title, String value, String placeholder) {
		TextBox res = new TextBox();
		res.widthProperty().set(388);
		res.textProperty().set(value);
		res.placeholderProperty().set(placeholder);
		res.paddingProperty().set(new Padding(5, 10, 5, 10));
		res.fontSizeProperty().set(14);
		addField(title, res);
		return res;
	}

	protected PasswordBox addPasswordField(String title, String value, String placeholder) {
		PasswordBox res = new PasswordBox();
		res.widthProperty().set(388);
		res.textProperty().set(value);
		res.placeholderProperty().set(placeholder);
		res.paddingProperty().set(new Padding(5, 10, 5, 10));
		res.fontSizeProperty().set(14);
		addField(title, res);
		return res;
	}

	protected TextArea addTextAreaField(String title, String value, String placeholder) {
		TextArea res = new TextArea();
		res.widthProperty().set(388);
		res.textProperty().set(value);
		res.placeholderProperty().set(placeholder);
		res.paddingProperty().set(new Padding(5, 10, 5, 10));
		res.fontSizeProperty().set(14);
		addField(title, res);
		return res;
	}

	protected void addField(String title, AComponent field) {
		vbFields.getChildren().add(createLabel(title));
		vbFields.addEmptyCell(5);
		vbFields.getChildren().add(field);
		vbFields.addEmptyCell(20);
	}

	private Label createLabel(String title) {
		Label lbl = new Label();
		lbl.textProperty().set(title);
		lbl.fontSizeProperty().set(14);
		lbl.boldProperty().set(true);
		return lbl;
	}

	protected void addLine(AComponent component) {
		vbFields.getChildren().add(component);
		vbFields.addEmptyCell(20);
	}

	protected StringProperty titleProperty() {
		return title;
	}

	protected StringProperty infoProperty() {
		return info;
	}

	protected StringProperty errorMessageProperty() {
		return errorMessage;
	}

	protected StringProperty okTitleProperty() {
		return okTitle;
	}

	protected StringProperty cancelTitleProperty() {
		return cancelTitle;
	}

	protected abstract void onOkClicked();

}
