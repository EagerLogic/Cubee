package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.components.fontawesome.EIcon;
import com.eagerlogic.cubee.client.components.fontawesome.FAIcon;
import com.eagerlogic.cubee.client.events.ClickEventArgs;
import com.eagerlogic.cubee.client.events.IEventListener;
import com.eagerlogic.cubee.client.properties.ext.AlignMiddleExp;
import com.eagerlogic.cubee.client.style.styles.Border;
import com.eagerlogic.cubee.client.style.styles.BoxShadow;
import com.eagerlogic.cubee.client.style.styles.Color;
import com.eagerlogic.cubee.client.style.styles.ColorBackground;
import com.eagerlogic.cubee.client.style.styles.ColorStop;
import com.eagerlogic.cubee.client.style.styles.EHAlign;
import com.eagerlogic.cubee.client.style.styles.ETextAlign;
import com.eagerlogic.cubee.client.style.styles.LinearGradient;
import com.eagerlogic.cubee.client.style.styles.Padding;

public class MessageBox extends ADialog {
	
	public static enum EType {
		INFO(EIcon.INFO_CIRCLE, Color.getRgbColor(0x0060c0)),
		WARNING(EIcon.WARNING, Color.getRgbColor(0xff9000)),
		ERROR(EIcon.WARNING, Color.getRgbColor(0xee0000)),
		QUESTION(EIcon.QUESTION_CIRCLE, Color.getRgbColor(0x0060c0));
		
		private final EIcon icon;
		private final Color color;
		
		private EType(EIcon icon, Color color) {
			this.icon = icon;
			this.color = color;
		}
		
		public EIcon getIcon() {
			return icon;
		}
		
		public Color getColor() {
			return color;
		}
	}
	
	public static interface CloseListener {
		public void onClosed(String button);
	}
	
	public static void showMessage(String title, String text, EType type, CloseListener closeListener) {
		MessageBox mb = new MessageBox(title, text, type, closeListener, "OK");
		mb.show();
	}
	
	public static void showQuestion(String title, String text, EType type, CloseListener closeListener, String... buttons) {
		MessageBox mb = new MessageBox(title, text, type, closeListener, buttons);
		mb.show();
	}
	
	private final CloseListener closeListener;

	private MessageBox(String title, String text, EType type, final CloseListener closeListener, String... buttons) {
		super(true, false);
		this.closeListener = closeListener;
		
		Panel root = new Panel();
		root.paddingProperty().set(new Padding(20));
		this.setRootComponent(root);
		
		Panel bg = new Panel();
		bg.borderProperty().set(new Border(1, Color.DARK_GRAY, 3));
		bg.shadowProperty().set(new BoxShadow(3, 3, 20, 0, Color.getArgbColor(0x80000000), false));
		root.getChildren().add(bg);
		
		VBox rootVb = new VBox();
		bg.getChildren().add(rootVb);
		
		rootVb.getChildren().add(createHeader(title));
		
		Panel body = createBody();
		rootVb.getChildren().add(body);
		
		VBox bodyVb = new VBox();
		body.getChildren().add(bodyVb);
		
		HBox bodyHb = new HBox();
		bodyVb.getChildren().add(bodyHb);

		FAIcon icon = new FAIcon(type.getIcon());
		icon.foreColorProperty().set(type.getColor());
		icon.sizeProperty().set(48);
		bodyHb.getChildren().add(icon);
		
		bodyHb.addEmptyCell(20);
		
		Label lblMessage = new Label();
		lblMessage.widthProperty().set(292);
		lblMessage.textAlignProperty().set(ETextAlign.JUSTIFY);
		lblMessage.textProperty().set(text);
		bodyHb.getChildren().add(lblMessage);
			
		bodyVb.addEmptyCell(20);
		
		HBox buttonHb = new HBox();
		bodyVb.getChildren().add(buttonHb);
		bodyVb.setLastCellHAlign(EHAlign.RIGHT);
		
		boolean isFirst = true;
		for (final String btn : buttons) {
			if (isFirst) {
				isFirst = false;
			} else {
				buttonHb.addEmptyCell(10);
			}
			Button button = new Button();
			button.textProperty().set(btn);
			buttonHb.getChildren().add(button);
			
			button.onClickEvent().addListener(new IEventListener<ClickEventArgs>() {
				
				@Override
				public void onFired(ClickEventArgs args) {
					close();
					if (closeListener != null) {
						closeListener.onClosed(btn);
					}
				}
			});
		}
	}
	
	private AComponent createHeader(String title) {
		Panel res = new Panel();
		res.backgroundProperty().set(new LinearGradient(0.0, new ColorStop(0.0, Color.getRgbColor(0x333333))
				, new ColorStop(0.0, Color.getRgbColor(0x444444)))
		);
		res.heightProperty().set(30);
		res.widthProperty().set(400);
		res.borderProperty().set(new Border(0, 0, 0, 0, null, null, null, null, 3, 3, 0, 0));
		
		Label lblTitle = new Label();
		lblTitle.textProperty().set(title);
		lblTitle.widthProperty().set(380);
		lblTitle.fontSizeProperty().set(16);
		lblTitle.foreColorProperty().set(Color.WHITE);
		lblTitle.boldProperty().set(true);
		lblTitle.alphaProperty().set(0.9);
		lblTitle.translateXProperty().set(10);
		lblTitle.translateYProperty().bind(new AlignMiddleExp(res, lblTitle));
		res.getChildren().add(lblTitle);
		
		return res;
	}
	
	private Panel createBody() {
		Panel res = new Panel();
		res.backgroundProperty().set(new ColorBackground(Color.WHITE));
		res.widthProperty().set(360);
		res.paddingProperty().set(new Padding(20));
		res.borderProperty().set(new Border(0, 0, 0, 0, null, null, null, null, 3, 3, 0, 0));
		
		return res;
	}

}
