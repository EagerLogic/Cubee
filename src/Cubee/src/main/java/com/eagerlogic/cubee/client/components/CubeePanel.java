package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.EventQueue;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.utils.ARunOnce;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;

/**
 *
 * @author dipacs
 */
public final class CubeePanel {

    private ARunOnce layoutRunOnce;

    private final Panel contentPanel;
    private AComponent rootComponent;
    
    
    private final Element element;
    
    private int left = -1;
    private int top = -1;
    private int clientWidth = -1;
    private int clientHeight = -1;
    private int offsetWidth = -1;
    private int offsetHeight = -1;

    public CubeePanel(DivElement element) {
        this.element = element;
        Window.addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(ResizeEvent event) {
                requestLayout();
            }
        });

        this.contentPanel = new Panel();
        this.contentPanel.getElement().getStyle().setProperty("pointerEvents", "none");
        this.contentPanel.pointerTransparentProperty().set(true);
        element.appendChild(this.contentPanel.getElement());

        checkBounds();
        requestLayout();
        
        new Timer() {

            @Override
            public void run() {
                EventQueue.getInstance().invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        checkBounds();
                    }
                });
            }
        }.scheduleRepeating(100);
    }
    
    private void checkBounds() {
        int newLeft = element.getAbsoluteLeft();
        int newTop = element.getAbsoluteTop();
        int newClientWidth = element.getClientWidth();
        int newClientHeight = element.getClientHeight();
        int newOffsetWidth = element.getOffsetWidth();
        int newOffsetHeight = element.getOffsetHeight();
        if (newLeft != left || newTop != top || newClientWidth != clientWidth || newClientHeight != clientHeight
                || newOffsetWidth != offsetWidth || newOffsetHeight != offsetHeight) {
            left = newLeft;
            top = newTop;
            clientWidth = newClientWidth;
            clientHeight = newClientHeight;
            offsetWidth = newOffsetWidth;
            offsetHeight = newOffsetHeight;
            this.contentPanel.widthProperty().set(offsetWidth);
            this.contentPanel.heightProperty().set(offsetHeight);
            if (Position.ABSOLUTE.getCssName().equals(element.getStyle().getPosition())) {
                this.contentPanel.translateXProperty().set(0);
                this.contentPanel.translateYProperty().set(0);
            } else {
                this.contentPanel.translateXProperty().set(left);
                this.contentPanel.translateYProperty().set(top);
            }
            requestLayout();
        }
    }

    public void requestLayout() {
        if (layoutRunOnce == null) {
            layoutRunOnce = new ARunOnce() {
                @Override
                protected void onRun() {
                    // TODO remove sout
                    //long ss = System.currentTimeMillis();
                    layout();
                    //long es = System.currentTimeMillis();
                    //System.out.println("!!! LAYING OUT !!!" + (es - ss) + "ms.");
                }
            };
        }
        layoutRunOnce.run();
    }
    
    private void layout() {
        this.contentPanel.layout();
    }

    public AComponent getRootComponent() {
        return rootComponent;
    }

    public void setRootComponent(AComponent rootComponent) {
        this.contentPanel.getChildren().clear();
        if (this.rootComponent != null) {
            this.rootComponent.setCubeePanel(null);
        }
        this.rootComponent = null;
        if (rootComponent != null) {
            this.contentPanel.getChildren().add(rootComponent);
            this.rootComponent = rootComponent;
            this.rootComponent.setCubeePanel(this);
        }
    }

//    void showPopup(APopup popup) {
//        this.popupPanel.getChildren().add(popup.getPopupRoot());
//    }
//
//    void closePopup(APopup popup) {
//        this.popupPanel.getChildren().remove(popup.getPopupRoot());
//    }
    
    boolean doPointerEventClimbingUp(int screenX, int screenY, int x, int y, int wheelVelocity,
            boolean altPressed, boolean ctrlPressed, boolean shiftPressed, boolean metaPressed, int type) {
        if (Popups.doPointerEventClimbingUp(screenX, screenY, x, y, wheelVelocity, altPressed, ctrlPressed, shiftPressed, metaPressed, type)) {
            return true;
        }
        
        if (!Position.ABSOLUTE.equals(element.getStyle().getPosition())) {
            x = screenX - left;
            y = screenY - top;
        }
        return contentPanel.onPointerEventClimbingUp(screenX, screenY, x, y, wheelVelocity, altPressed, ctrlPressed, shiftPressed, metaPressed, type);
    }

    public final IntegerProperty clientWidthProperty() {
        return contentPanel.clientWidthProperty();
    }

    public final IntegerProperty clientHeightProperty() {
        return contentPanel.clientHeightProperty();
    }

    public final IntegerProperty boundsWidthProperty() {
        return contentPanel.boundsWidthProperty();
    }

    public final IntegerProperty boundsHeightProperty() {
        return contentPanel.boundsHeightProperty();
    }

    public final IntegerProperty boundsLeftProperty() {
        return contentPanel.boundsLeftProperty();
    }

    public final IntegerProperty boundsTopProperty() {
        return contentPanel.boundsTopProperty();
    }

}
