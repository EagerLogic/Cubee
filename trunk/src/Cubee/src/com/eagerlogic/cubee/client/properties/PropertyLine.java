package com.eagerlogic.cubee.client.properties;

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
	private KeyFrame previousFrame = null;

	public PropertyLine(LinkedList<KeyFrame> keyFrames) {
		this.keyFrames = keyFrames;
		this.property = keyFrames.get(0).getProperty();
		KeyFrame firstFrame = keyFrames.get(0);
		if (firstFrame.getTime() > 0) {
			keyFrames.add(new KeyFrame(0, firstFrame.getProperty(), firstFrame.getProperty().get()));
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
			previousFrame = keyFrames.get(0);
			return false;
		}
		KeyFrame actFrame = keyFrames.getFirst();
		KeyFrame nextFrame = null;
		KeyFrame prevFrame = null;
		boolean foundPreviousFrame = false;
		for (KeyFrame frame : keyFrames) {
			if (startTime + frame.getTime() >= actTime) {
				nextFrame = frame;
				actFrame = prevFrame;
				break;
			}
			
			if (frame == previousFrame) {
				foundPreviousFrame = true;
			}
			
			// notify key frame reached listeners
			if (foundPreviousFrame && actFrame == null) {
				if (frame.getKeyFrameReachedListener() != null) {
					frame.getKeyFrameReachedListener().run();
				}
			}
			
			prevFrame = frame;
		}
		
		if (actFrame == null) {
			if (keyFrames.getLast().getKeyFrameReachedListener() != null) {
				keyFrames.getLast().getKeyFrameReachedListener().run();
			}
		}
		
		if (nextFrame == null || actFrame == null) {
			property.set((T) keyFrames.getLast().getEndValue());
			return true;
		} else if (nextFrame == null) {
			property.set((T) keyFrames.getLast().getEndValue());
			return true;
		} else {
			double pos = ((double)(actTime - startTime - actFrame.getTime())) / (nextFrame.getTime() - actFrame.getTime());
			pos = nextFrame.getInterpolator().interpolate(pos);
			property.set(property.animate(pos, (T)actFrame.getEndValue(), (T)nextFrame.getEndValue()));
		}
		
		previousFrame = actFrame;
		
		return false;
	}

}
