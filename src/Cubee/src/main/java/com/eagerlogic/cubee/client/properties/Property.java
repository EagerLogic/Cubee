/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.properties;

import com.eagerlogic.cubee.shared.utils.AValidator;
import com.eagerlogic.cubee.shared.utils.ValidationException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author dipacs
 * @param <T> The type of the value which is boxed in this property.
 */
public class Property<T> implements IProperty<T>, IAnimateable<T>, IBindable<IProperty<T>> {

    private final LinkedList<IChangeListener> changeListeners = new LinkedList<IChangeListener>();
    private T value;
    private boolean valid = false;
    private final boolean nullable;
    private final boolean readonly;
    private IProperty<T> bindingSource;
    private IChangeListener bindListener = new IChangeListener() {
        @Override
        public void onChanged(Object sender) {
            invalidateIfNeeded();
        }
    };

    private IProperty<T> readonlyBind;
    private Property<T> bidirectionalBindProperty;
    private IChangeListener bidirectionalChangeListenerThis;
    private IChangeListener bidirectionalChangeListenerOther;

    private final AValidator<T> validator;

    /**
     * Creates a new nullable, writeable Property instance with null default value and null validator.
     */
    public Property() {
        this(null, true, false, null);
    }

    /**
     * Creates a new nullable, writeable Property instance with null validator.
     *
     * @param defaultValue The default value of this property.
     */
    public Property(T defaultValue) {
        this(defaultValue, true, false, null);
    }

    /**
     * Creates a new writeable Property instance with null validator.
     *
     * @param defaultValue The default value of this property.
     */
    public Property(T defaultValue, boolean nullable) {
        this(defaultValue, nullable, false, null);
    }

    /**
     * Creates a new nullable, writable Property instance.
     *
     * @param defaultValue The default value of this property.
     * @param validator The validator which called every time this property get's a new value. This can be null.
     */
    public Property(T defaultValue, AValidator<T> validator) {
        this(defaultValue, true, false, validator);
    }

    /**
     * Creates a new Property instance with null validator.
     *
     * @param defaultValue The default value of this Property.
     * @param nullable Indicates if this property is nullable.
     * @param readonly Indicates if this property is readonly.
     */
    public Property(T defaultValue, boolean nullable, boolean readonly) {
        this(defaultValue, nullable, readonly, null);
    }

    /**
     * Creates a new Property instance.
     *
     * @param defaultValue The default value of this Property.
     * @param nullable Indicates if this property is nullable.
     * @param readonly Indicates if this property is readonly.
     * @param validator The validator which called every time this property get's a new value. This can be null.
     */
    public Property(T defaultValue, boolean nullable, boolean readonly, AValidator<T> validator) {
        this.value = defaultValue;
        this.nullable = nullable;
        this.readonly = readonly;
        this.validator = validator;

        if (value == null && nullable == false) {
            throw new IllegalArgumentException("A nullable property can not be null.");
        }

        if (this.value != null && validator != null) {
            try {
                this.value = validator.validate(value);
            } catch (ValidationException ex) {
                throw new RuntimeException("Validator exception", ex);
            }
        }

        invalidate();
    }

    public final void initReadonlyBind(IProperty<T> readonlyBind) {
        if (this.readonlyBind != null) {
            throw new IllegalStateException("The readonly bind is already initialized.");
        }
        this.readonlyBind = readonlyBind;
        if (readonlyBind != null) {
            readonlyBind.addChangeListener(bindListener);
        }
        this.invalidate();
    }

    public final T get() {
        this.valid = true;

        if (bindingSource != null) {
            if (validator != null) {
                try {
                    return validator.validate((T) bindingSource.getObjectValue());
                } catch (ValidationException ex) {
                    throw new RuntimeException("Validator exception", ex);
                }
            }
            return (T) bindingSource.getObjectValue();
        }

        if (readonlyBind != null) {
            if (validator != null) {
                try {
                    return validator.validate((T) readonlyBind.getObjectValue());
                } catch (ValidationException ex) {
                    throw new RuntimeException("Validator exception", ex);
                }
            }
            return (T) readonlyBind.getObjectValue();
        }

        return this.value;
    }

    public final void set(T newValue) {
        if (this.readonly) {
            throw new IllegalStateException("Can not change the value of a readonly property.");
        }

        if (isBound()) {
            throw new IllegalStateException("Can not change the value of a bound property.");
        }

        if (!this.nullable && newValue == null) {
            throw new IllegalStateException("Can not set the value to null of a non nullable property.");
        }

        if (validator != null) {
            try {
                newValue = validator.validate(newValue);
            } catch (ValidationException ex) {
                throw new RuntimeException("Validator exception", ex);
            }
        }

        if (this.value == newValue) {
            return;
        }

        if (this.value != null && this.value.equals(newValue)) {
            return;
        }

        this.value = newValue;

        invalidate();
    }

    @Override
    public final void invalidate() {
    	this.valid = false;
        fireChangeListeners();
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

    @Override
    public Object getObjectValue() {
        return this.get();
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
    public T animate(double pos, T startValue, T endValue) {
        if (pos < 0.5) {
            return startValue;
        }

        return endValue;
    }

    @Override
    public void bind(IProperty<T> source) {
        if (source == null) {
            throw new NullPointerException("The source can not be null.");
        }

        if (isBound()) {
            throw new IllegalStateException("This property is already bound.");
        }

        if (readonly) {
            throw new IllegalStateException("Can't bind a readonly property.");
        }

        this.bindingSource = source;
        this.bindingSource.addChangeListener(bindListener);
        this.invalidate();
    }

    public void bidirectionalBind(Property<T> other) {
    	if (isBound()) {
            throw new IllegalStateException("This property is already bound.");
        }

        if (readonly) {
            throw new IllegalStateException("Can't bind a readonly property.");
        }
        
        if (other == null) {
        	throw new NullPointerException("The other parameter can not be null.");
        }
        
        if (other.isReadonly()) {
        	throw new IllegalArgumentException("Can not bind a property bidirectionally to a readonly property.");
        }
        
        if (other == this) {
            throw new IllegalArgumentException("Can not bind a property bidirectionally for themself.");
        }
        
        if (isBound()) {
            throw new IllegalStateException("This property is already bound.");
        }

        this.bidirectionalBindProperty = other;
        this.bidirectionalChangeListenerOther = new IChangeListener() {

            @Override
            public void onChanged(Object sender) {
                set(bidirectionalBindProperty.get());
            }
        };
        other.addChangeListener(bidirectionalChangeListenerOther);
        this.bidirectionalChangeListenerThis = new IChangeListener() {

            @Override
            public void onChanged(Object sender) {
                bidirectionalBindProperty.set(get());
            }
        };
        this.addChangeListener(bidirectionalChangeListenerThis);

        other.bidirectionalBindProperty = this;
        other.bidirectionalChangeListenerOther = bidirectionalChangeListenerThis;
        other.bidirectionalChangeListenerThis = bidirectionalChangeListenerOther;

    }

    @Override
    public void unbind() {
        if (this.bindingSource != null) {
            this.bindingSource.removeChangeListener(this.bindListener);
            this.bindingSource = null;
            invalidate();
        } else if (this.isBidirectionalBound()) {
            this.removeChangeListener(bidirectionalChangeListenerThis);
            bidirectionalBindProperty.removeChangeListener(bidirectionalChangeListenerOther);
            bidirectionalBindProperty.bidirectionalBindProperty = null;
            bidirectionalBindProperty.bidirectionalChangeListenerOther = null;
            bidirectionalBindProperty.bidirectionalChangeListenerThis = null;
            bidirectionalBindProperty = null;
            bidirectionalChangeListenerOther = null;
            bidirectionalChangeListenerThis = null;
        }
    }
    
    public void unbindTargets() {
    	// TODO null bindingSource of targets
    	changeListeners.clear();
    }

    @Override
    public boolean isBound() {
        return this.bindingSource != null;
    }

    public final boolean isBidirectionalBound() {
        return this.bidirectionalBindProperty != null;
    }

    public final boolean isNullable() {
        return nullable;
    }

    public final boolean isReadonly() {
        return readonly;
    }

    PropertyLine<T> createPropertyLine(LinkedList<KeyFrame> keyFrames) {
        return new PropertyLine<T>(keyFrames);
    }
    
    public final void destroy() {
        unbind();
        changeListeners.clear();
        bindListener = null;
    }
}
