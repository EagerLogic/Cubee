package com.eagerlogic.cubee.client.properties;

import com.eagerlogic.cubee.client.events.EventArgs;

public class TimelineFinishedEventArgs extends EventArgs {
	
	private final boolean stopped;

	public TimelineFinishedEventArgs(Object sender, boolean stopped) {
		super(sender);
		this.stopped = stopped;
	}

	public boolean isStopped() {
		return stopped;
	}

}
