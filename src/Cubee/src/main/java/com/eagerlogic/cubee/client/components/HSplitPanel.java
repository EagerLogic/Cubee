package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.events.IEventListener;
import com.eagerlogic.cubee.client.events.MouseDownEventArgs;
import com.eagerlogic.cubee.client.events.MouseDragEventArgs;
import com.eagerlogic.cubee.client.properties.AExpression;
import com.eagerlogic.cubee.client.properties.BackgroundProperty;
import com.eagerlogic.cubee.client.properties.ColorProperty;
import com.eagerlogic.cubee.client.properties.DoubleProperty;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.ext.AlignCenterExp;
import com.eagerlogic.cubee.client.properties.ext.AlignMiddleExp;
import com.eagerlogic.cubee.client.style.styles.ABackground;
import com.eagerlogic.cubee.client.style.styles.Border;
import com.eagerlogic.cubee.client.style.styles.Color;
import com.eagerlogic.cubee.client.style.styles.ColorBackground;
import com.eagerlogic.cubee.client.style.styles.ECursor;

/**
 *
 * @author dipacs
 */
public class HSplitPanel extends AUserControl {
    
    private final IntegerProperty separatorWidth = new IntegerProperty(10, false, false);
    private final BackgroundProperty separatorBackground = new BackgroundProperty(new ColorBackground(Color.getRgbColor(
            0xf0f0f0)), true, false);
    private final ColorProperty separatorDotColor = new ColorProperty(Color.GRAY, true, false);
    private final DoubleProperty separatorPosition = new DoubleProperty(0.5, false, false, new SplitPanelSeparatorPositionValidator());
    
    private Panel leftPanel;
    private Panel rightPanel;
    private Panel separatorPanel;
    
    private AFillView leftContent;
    private AFillView rightContent;

    public HSplitPanel() {
        leftPanel = new Panel();
        leftPanel.widthProperty().bind(new AExpression<Integer>() {

            {
                bind(clientWidthProperty(), separatorWidth, separatorPosition);
            }
            
            @Override
            public Integer calculate() {
                int fullWidth = clientWidthProperty().get();
                int usableWidth = fullWidth - separatorWidth.get();
                return (int) (usableWidth * separatorPosition.get());
            }
        });
        leftPanel.heightProperty().bind(this.clientHeightProperty());
        this.getChildren().add(leftPanel);
        
        rightPanel = new Panel();
        rightPanel.widthProperty().bind(new AExpression<Integer>() {

            {
                bind(clientWidthProperty(), separatorWidth, separatorPosition);
            }
            
            @Override
            public Integer calculate() {
                int fullWidth = clientWidthProperty().get();
                int usableWidth = fullWidth - separatorWidth.get();
                return  usableWidth - ((int)(usableWidth * separatorPosition.get()));
            }
        });
        rightPanel.translateXProperty().bind(new AExpression<Integer>() {

            {
                bind(separatorWidth, leftPanel.boundsWidthProperty());
            }
            
            @Override
            public Integer calculate() {
                return separatorWidth.get() + leftPanel.boundsWidthProperty().get();
            }
        });
        rightPanel.heightProperty().bind(this.clientHeightProperty());
        this.getChildren().add(rightPanel);
        
        separatorPanel = new Panel();
        separatorPanel.backgroundProperty().bind(separatorBackground);
        separatorPanel.widthProperty().bind(separatorWidth);
        separatorPanel.heightProperty().bind(this.clientHeightProperty());
        separatorPanel.cursorProperty().set(ECursor.E_RESIZE);
        separatorPanel.translateXProperty().bind(leftPanel.widthProperty());
        this.getChildren().add(separatorPanel);
        
        AComponent dots = createDots();
        dots.translateXProperty().bind(new AlignCenterExp(separatorPanel, dots));
        dots.translateYProperty().bind(new AlignMiddleExp(separatorPanel, dots));
        separatorPanel.getChildren().add(dots);
        
        separatorPanel.onMouseDragEvent().addListener(new IEventListener<MouseDragEventArgs>() {

            @Override
            public void onFired(MouseDragEventArgs args) {
                int componentLeft = HSplitPanel.this.getScreenX();
                separatorPosition.set((args.getScreenX() - componentLeft) / ((double)HSplitPanel.this.clientWidthProperty().get()));
            }
        });
    }
    
    private AComponent createDots() {
        VBox res = new VBox();
        res.handlePointerProperty().set(false);
        
        res.getChildren().add(createDot());
        
        AComponent sep = createDot();
        sep.alphaProperty().set(0.0);
        res.getChildren().add(sep);
        
        res.getChildren().add(createDot());
        
        sep = createDot();
        sep.alphaProperty().set(0.0);
        res.getChildren().add(sep);
        
        res.getChildren().add(createDot());
        
        return res;
    }
    
    private AComponent createDot() {
        Panel res = new Panel();
        res.backgroundProperty().bind(new AExpression<ABackground>() {

            {
                bind(separatorDotColor);
            }
            
            @Override
            public ABackground calculate() {
                if (separatorDotColor.get() == null) {
                    return null;
                }
                return new ColorBackground(separatorDotColor.get());
            }
        });
        res.widthProperty().bind(new AExpression<Integer>() {
            
            {
                bind(separatorWidth);
            }

            @Override
            public Integer calculate() {
                int quarter = separatorWidth.get() / 4;
                return separatorWidth.get() - (quarter * 2);
            }
        });
        res.heightProperty().bind(res.widthProperty());
        res.borderProperty().bind(new AExpression<Border>() {

            {
                bind(separatorWidth);
            }
            
            @Override
            public Border calculate() {
                int quarter = separatorWidth.get() / 4;
                int width = separatorWidth.get() - (quarter * 2);
                return new Border(0, Color.BLACK, width / 2);
            }
        });
        return res;
    }

    public AFillView getLeftContent() {
        return leftContent;
    }

    public void setLeftContent(AFillView leftContent) {
        leftPanel.getChildren().clear();
        if (this.leftContent != null) {
            this.leftContent.hiddenWidthProperty().unbind();
            this.leftContent.hiddenHeightProperty().unbind();
        }
        this.leftContent = null;
        if (leftContent != null) {
            leftContent.hiddenWidthProperty().bind(leftPanel.clientWidthProperty());
            leftContent.hiddenHeightProperty().bind(leftPanel.clientHeightProperty());
            leftPanel.getChildren().add(leftContent);
        }
        this.leftContent = leftContent;
    }

    public AFillView getRightContent() {
        return rightContent;
    }

    public void setRightContent(AFillView rightContent) {
        rightPanel.getChildren().clear();
        if (this.rightContent != null) {
            this.rightContent.hiddenWidthProperty().unbind();
            this.rightContent.hiddenHeightProperty().unbind();
        }
        this.rightContent = null;
        if (rightContent != null) {
            rightContent.hiddenWidthProperty().bind(rightPanel.clientWidthProperty());
            rightContent.hiddenHeightProperty().bind(rightPanel.clientHeightProperty());
            rightPanel.getChildren().add(rightContent);
        }
        this.rightContent = rightContent;
    }

    @Override
    public IntegerProperty widthProperty() {
        return super.widthProperty();
    }

    @Override
    public IntegerProperty heightProperty() {
        return super.heightProperty();
    }

}
