/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client;

import com.google.gwt.user.client.Timer;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dipacs
 */
public final class EventQueue {

	private static final Logger LOG = Logger.getLogger(EventQueue.class.getName());
	
    private static EventQueue instance;

    public static EventQueue getInstance() {
        if (instance == null) {
            instance = new EventQueue();
        }

        return instance;
    }
    
    private final LinkedList<Runnable> queue = new LinkedList<Runnable>();
    private final Timer timer;

    private EventQueue() {
        timer = new Timer() {
            @Override
            public void run() {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Runnable task;
                    task = queue.get(0);
                    queue.remove(0);
                    if (task != null) {
                        try {
                            task.run();
                        } catch (Throwable t) {
                            LOG.log(Level.SEVERE, t.getMessage(), t);
                        }
                    }
                }
                timer.schedule(10);
            }
        };
        timer.schedule(10);
    }

    public void invokeLater(Runnable task) {
        if (task == null) {
            throw new NullPointerException("The task can not be null.");
        }
        queue.add(task);
    }

    public void invokePrior(Runnable task) {
        if (task == null) {
            throw new NullPointerException("The task can not be null.");
        }
        queue.addFirst(task);
    }
}
