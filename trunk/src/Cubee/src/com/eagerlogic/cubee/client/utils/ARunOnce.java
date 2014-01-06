package com.eagerlogic.cubee.client.utils;

import com.eagerlogic.cubee.client.EventQueue;

/**
 *
 * @author dipacs
 */
public abstract class ARunOnce {

    private boolean scheduled = false;

    public final void run() {
        if (scheduled) {
            return;
        }
        scheduled = true;
        EventQueue.getInstance().invokeLater(new Runnable() {

            @Override
            public void run() {
                scheduled = false;
                onRun();
            }
        });
    }

    protected abstract void onRun();

}
