package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.events.ClickEventArgs;
import com.eagerlogic.cubee.client.events.IEventListener;
import com.eagerlogic.cubee.client.properties.ext.CondExp;
import com.eagerlogic.cubee.client.style.styles.ABackground;
import com.eagerlogic.cubee.client.style.styles.Border;
import com.eagerlogic.cubee.client.style.styles.Color;
import com.eagerlogic.cubee.client.style.styles.ColorBackground;
import com.eagerlogic.cubee.client.style.styles.ECursor;
import com.eagerlogic.cubee.client.style.styles.EHAlign;
import com.eagerlogic.cubee.client.style.styles.ETextAlign;
import com.eagerlogic.cubee.client.style.styles.Padding;

/**
 *
 * @author dipacs
 */
public final class DefaultComboBoxDialog<T> extends AComboBoxDialog<T> {

    private VBox vbox;
    private int result = -1;

    public DefaultComboBoxDialog() {
        super(false);
    }

    private AComponent createItem(String title, final int result, final VBox root) {
    	Panel res = new Panel();
    	res.cursorProperty().set(ECursor.POINTER);
    	res.backgroundProperty().bind(new CondExp<ABackground>(res.hoveredProperty(), new ColorBackground(Color.FUNKY_BLUE), (ABackground)null));
    	res.onClickEvent().addListener(new IEventListener<ClickEventArgs>() {
    		
    		@Override
    		public void onFired(ClickEventArgs args) {
    			DefaultComboBoxDialog.this.result = result;
    			close();
    		}
    	});
    	
        Label label = new Label();
        label.paddingProperty().set(new Padding(25, 3, 25, 3));
        label.textProperty().set(title.toString());
        label.minWidthProperty().bind(root.clientWidthProperty().subtract(50));
        label.textAlignProperty().set(ETextAlign.CENTER);
        label.handlePointerProperty().set(false);
        res.getChildren().add(label);
        
        return res;
    }

    @Override
    protected int onClose() {
        return result;
    }

    @Override
    protected void init(int selectedIndex, T[] items, boolean emptySelectionEnabled, String emptySelectionText) {
        Panel root = new Panel();
        root.backgroundProperty().set(new ColorBackground(Color.WHITE));
        root.borderProperty().set(new Border(1, Color.getRgbColor(0x202020), 5));
        this.setRootComponent(root);

        VBox vbMain = new VBox();
        root.getChildren().add(vbMain);

        ScrollPanel spMain = new ScrollPanel();
        spMain.maxHeightProperty().set(400);
        vbMain.getChildren().add(spMain);

        this.vbox = new VBox();
        spMain.widthProperty().bind(vbox.boundsWidthProperty());
        spMain.heightProperty().bind(vbox.boundsHeightProperty().add(20));
        spMain.contentProperty().set(this.vbox);

        if (emptySelectionEnabled) {
            vbox.getChildren().add(createItem(emptySelectionText, -1, vbox));
        }

        int i = 0;
        for (T item : items) {
            vbox.getChildren().add(createItem(item.toString(), i, vbox));
            vbox.setLastCellHAlign(EHAlign.CENTER);
            i++;
        }

        vbMain.getChildren().add(null);
        vbMain.setCellHeight(vbMain.getChildren().size() - 1, 10);

        Panel btnPanel = new Panel();
        btnPanel.paddingProperty().set(new Padding(5));

        Button btnCancel = new Button();
        btnCancel.textProperty().set("Cancel");
        btnCancel.onClickEvent().addListener(new IEventListener<ClickEventArgs>() {

            @Override
            public void onFired(ClickEventArgs args) {
                cancel();
            }
        });
        btnPanel.getChildren().add(btnCancel);
        vbMain.getChildren().add(btnPanel);
        vbMain.setLastCellHAlign(EHAlign.CENTER);
    }
}
