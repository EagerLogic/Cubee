package com.eagerlogic.cubee.client.properties;

import com.eagerlogic.cubee.client.events.Event;
import com.eagerlogic.cubee.client.properties.Property;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author dipacs
 */
public final class Timeline extends AAnimator {

    private final KeyFrame[] keyFrames;
    private final LinkedList<PropertyLine<?>> propertyLines = new LinkedList<PropertyLine<?>>();
    private int repeatCount = 0;
    private final Event<TimelineFinishedEventArgs> finishedEvent = new Event<TimelineFinishedEventArgs>();

    public Timeline(List<KeyFrame> keyFrames) {
        this(keyFrames.toArray(new KeyFrame[keyFrames.size()]));
    }

    public Timeline(KeyFrame... keyFrames) {
        this.keyFrames = keyFrames;
    }

    private void createPropertyLines() {
        HashMap<Property<?>, LinkedList<KeyFrame>> plMap = new HashMap<Property<?>, LinkedList<KeyFrame>>();
        for (KeyFrame keyFrame : keyFrames) {
            LinkedList<KeyFrame> propertyLine = plMap.get(keyFrame.getProperty());
            if (propertyLine == null) {
                propertyLine = new LinkedList<KeyFrame>();
                plMap.put(keyFrame.getProperty(), propertyLine);
            }
            if (propertyLine.size() > 0) {
                if (propertyLine.getLast().getTime() >= keyFrame.getTime()) {
                    throw new IllegalArgumentException("The keyframes must be in ascending time order per property.");
                }
            }
            propertyLine.add(keyFrame);
        }

        for (LinkedList<KeyFrame> frameList : plMap.values()) {
            PropertyLine<?> propertyLine = frameList.getFirst().getProperty().createPropertyLine(frameList);
            propertyLines.add(propertyLine);
        }
    }

    @Override
    public void start() {
    	createPropertyLines();
        repeatCount = 0;
        long startTime = System.currentTimeMillis();
        for (PropertyLine<?> propertyLine : propertyLines) {
            propertyLine.setStartTime(startTime);
        }
        super.start();
    }

    public void start(int repeatCount) {
        createPropertyLines();
        this.repeatCount = repeatCount;
        long startTime = System.currentTimeMillis();
        for (PropertyLine<?> propertyLine : propertyLines) {
            propertyLine.setStartTime(startTime);
        }
        super.start();
    }

    @Override
	public void stop() {
    	if (!this.isStarted()) {
    		return;
    	}
		super.stop();
		this.finishedEvent.fireEvent(new TimelineFinishedEventArgs(this, true));
	}

	@Override
    protected void onAnimate() {
        boolean finished = true;
        for (PropertyLine<?> propertyLine : propertyLines) {
            finished &= propertyLine.animate();
        }

        if (finished) {
            if (repeatCount < 0) {
                long startTime = System.currentTimeMillis();
                for (PropertyLine<?> propertyLine : propertyLines) {
                    propertyLine.setStartTime(startTime);
                }
            } else {
                repeatCount--;
                if (repeatCount > -1) {
                    long startTime = System.currentTimeMillis();
                    for (PropertyLine<?> propertyLine : propertyLines) {
                        propertyLine.setStartTime(startTime);
                    }
                } else {
                    super.stop();
                    this.finishedEvent.fireEvent(new TimelineFinishedEventArgs(this, false));
                }
            }
        }
    }

	public Event<TimelineFinishedEventArgs> onFinishedEvent() {
		return finishedEvent;
	}
}
