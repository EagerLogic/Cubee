package com.eagerlogic.cubee.client.properties;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author dipacs
 */
class PropertyLine<T> {

    public static void create(Property<?> property) {

    }

    private final LinkedList<KeyFrame> keyFrames;
    private final Property<T> property;
    private long startTime = -1;
    private long lastRunTime = -1;
    private KeyFrame previousFrame = null;

    public PropertyLine(LinkedList<KeyFrame> keyFrames) {
        this.keyFrames = keyFrames;
        this.property = keyFrames.get(0).getProperty();
        KeyFrame firstFrame = keyFrames.get(0);
        if (firstFrame.getTime() > 0) {
            keyFrames.add(0, new KeyFrame(0, firstFrame.getProperty(), firstFrame.getProperty().get()));
        }
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public boolean animate() {
        long actTime = System.currentTimeMillis();
        
        if (actTime == startTime) {
        	return false;
        }
        
        
        KeyFrame nextFrame = null;
        KeyFrame actFrame = null;
        for (KeyFrame frame : keyFrames) {
        	if (actTime >= startTime + frame.getTime()) {
        		actFrame = frame;
        	} else {
        		nextFrame = frame;
        		break;
        	}
        	
        	if (startTime + frame.getTime() > lastRunTime && startTime + frame.getTime() <= actTime) {
        		if (frame.getKeyFrameReachedListener() != null) {
        			frame.getKeyFrameReachedListener().run();
        		}
        	}
        }
        
        if (actFrame != null) {
        	if (nextFrame != null) {
        		double pos = ((actTime - startTime - actFrame.getTime())) / ((double)(nextFrame.getTime() - actFrame.getTime()));
        		actFrame.getProperty().set(actFrame.getProperty().animate(pos, actFrame.getEndValue(), nextFrame.getEndValue()));
        	} else {
        		actFrame.getProperty().set(actFrame.getEndValue());
        	}
        }
        
        lastRunTime = actTime;
        
        return actTime >= startTime + keyFrames.getLast().getTime();
        
    }

}
