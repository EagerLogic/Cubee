package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.components.AUserControl;
import com.eagerlogic.cubee.client.components.Panel;
import com.eagerlogic.cubee.client.properties.BooleanProperty;
import com.eagerlogic.cubee.client.properties.IChangeListener;
import com.eagerlogic.cubee.client.properties.KeyFrame;
import com.eagerlogic.cubee.client.properties.Timeline;
import com.eagerlogic.cubee.client.style.styles.Border;
import com.eagerlogic.cubee.client.style.styles.Color;
import com.eagerlogic.cubee.client.style.styles.ColorBackground;
import com.eagerlogic.cubee.client.style.styles.ColorStop;
import com.eagerlogic.cubee.client.style.styles.LinearGradient;

/**
 *
 * @author dipacs
 */
public class ExpandButton extends AUserControl {
    
    private BooleanProperty expanded = new BooleanProperty(false, false, false);
    
    private Panel horizontalLine;
    private Panel verticalLine;
    
    private Timeline timeline;

    public ExpandButton() {
        this.widthProperty().set(9);
        this.heightProperty().set(9);
        this.borderProperty().set(new Border(1, Color.GRAY, 0));
        LinearGradient lg = new LinearGradient(0.0, new ColorStop(0.0, Color.WHITE), new ColorStop(1.0, Color.getRgbColor(0xf0f0f0)));
        this.backgroundProperty().set(lg);
        
        horizontalLine = new Panel();
        horizontalLine.widthProperty().set(5);
        horizontalLine.heightProperty().set(1);
        horizontalLine.translateXProperty().set(2);
        horizontalLine.translateYProperty().set(4);
        horizontalLine.backgroundProperty().set(new ColorBackground(Color.GRAY));
        horizontalLine.handlePointerProperty().set(false);
        this.getChildren().add(horizontalLine);
        
        verticalLine = new Panel();
        verticalLine.widthProperty().set(1);
        verticalLine.heightProperty().set(5);
        verticalLine.translateXProperty().set(4);
        verticalLine.translateYProperty().set(2);
        verticalLine.backgroundProperty().set(new ColorBackground(Color.GRAY));
        verticalLine.handlePointerProperty().set(false);
        this.getChildren().add(verticalLine);
        
        expandedProperty().addChangeListener(new IChangeListener() {

            @Override
            public void onChanged(Object sender) {
                startAnimation();
            }
        });
    }

    public final BooleanProperty expandedProperty() {
        return expanded;
    }
    
    private void startAnimation() {
        if (timeline != null) {
            timeline.stop();
        }
        
        if (expandedProperty().get()) {
            timeline = new Timeline(new KeyFrame(0, horizontalLine.alphaProperty(), 1.0)
                    , new KeyFrame(200, horizontalLine.alphaProperty(), 0.0)
                    , new KeyFrame(0, horizontalLine.rotateProperty(), 0.0)
                    , new KeyFrame(200, horizontalLine.rotateProperty(), 0.25)
                    , new KeyFrame(0, verticalLine.rotateProperty(), 0.0)
                    , new KeyFrame(200, verticalLine.rotateProperty(), 0.25)
                    );
        } else {
            timeline = new Timeline(new KeyFrame(0, horizontalLine.alphaProperty(), 0.0)
                    , new KeyFrame(200, horizontalLine.alphaProperty(), 1.0)
                    , new KeyFrame(0, horizontalLine.rotateProperty(), 0.25)
                    , new KeyFrame(200, horizontalLine.rotateProperty(), 0.0)
                    , new KeyFrame(0, verticalLine.rotateProperty(), 0.25)
                    , new KeyFrame(200, verticalLine.rotateProperty(), 0.0)
                    );
        }
        timeline.start();
    }

}
