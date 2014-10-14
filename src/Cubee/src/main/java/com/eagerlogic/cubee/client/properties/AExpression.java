/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.properties;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.eagerlogic.cubee.client.utils.ARunOnce;

/**
 *
 * @author dipacs
 */
public abstract class AExpression<T> implements IProperty<T>, IObservable {
	
	private final ARunOnce notifyListenersOnce = new ARunOnce() {
		
		@Override
		protected void onRun() {
			fireChangeListeners();
		}
	};

    private final LinkedList<IProperty> bindingSources = new LinkedList<IProperty>();
    private IChangeListener bindingListener = new IChangeListener() {

        @Override
        public void onChanged(Object sender) {
            invalidateIfNeeded();
        }
    };
    private final LinkedList<IChangeListener> changeListeners = new LinkedList<IChangeListener>();

    public abstract T calculate();
    private boolean valid = false;
    private T value;
    
    public AExpression() {
    	
    }
    
    public AExpression(IProperty<?> property, IProperty<?>... properties) {
    	this.bind(property, properties);
    }
    
    public AExpression(List<? extends IProperty<?>> properties) {
    	bind(properties);
    }

    public T get() {
        if (!valid) {
            this.value = calculate();
            this.valid = true;
        }

        return this.value;
    }

    @Override
    public void addChangeListener(IChangeListener listener) {
        if (listener == null) {
            throw new NullPointerException("The listener parameter can not be null.");
        }

        if (hasChangeListener(listener)) {
            return;
        }

        changeListeners.add(listener);
        invalidate();
    }

    @Override
    public void removeChangeListener(IChangeListener listener) {
        Iterator<IChangeListener> iterator = changeListeners.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == listener) {
                iterator.remove();
                return;
            }
        }

        if (changeListeners.size() < 1) {
            for (IProperty prop : bindingSources) {
                prop.removeChangeListener(bindingListener);
            }
        }

        invalidate();
    }

    @Override
    public boolean hasChangeListener(IChangeListener listener) {
        for (IChangeListener l : changeListeners) {
            if (l == listener) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object getObjectValue() {
        return this.get();
    }

    public void bind(IProperty<?> property) {
        property.addChangeListener(bindingListener);
        this.bindingSources.add(property);
        this.invalidate();
    }

    public void bind(IProperty<?> property, IProperty<?>... properties) {
        property.addChangeListener(bindingListener);
        this.bindingSources.add(property);

        for (IProperty prop : properties) {
            prop.addChangeListener(bindingListener);
            this.bindingSources.add(prop);
        }

        this.invalidate();
    }
    
    public void bind(List<? extends IProperty<?>> properties) {
    	for (IProperty prop : properties) {
            prop.addChangeListener(bindingListener);
            this.bindingSources.add(prop);
        }

        this.invalidate();
    }
    
    public void unbind() {
    	for (IProperty prop : bindingSources) {
    		prop.removeChangeListener(bindingListener);
    	}
    	bindingSources.clear();
    	this.invalidate();
    }
    
    public void unbind(IProperty property) {
    	property.removeChangeListener(bindingListener);
    	this.bindingSources.remove(property);
    	this.invalidate();
    }
    
    public void unbindTargets() {
    	// TODO null bindingSource of targets
    	changeListeners.clear();
    }

    @Override
    public final void invalidate() {
        this.valid = false;
        notifyListenersOnce.run();
    }
    
    public final void invalidateIfNeeded() {
    	if (!this.valid) {
    		return;
    	}
    	invalidate();
    }
    
    private void fireChangeListeners() {
    	for (IChangeListener listener : changeListeners) {
            listener.onChanged(this);
        }
    }
    
    public final void destroy() {
        changeListeners.clear();
        for (IProperty prop : bindingSources) {
            prop.removeChangeListener(bindingListener);
        }
        bindingSources.clear();
        bindingListener = null;
    }

}
