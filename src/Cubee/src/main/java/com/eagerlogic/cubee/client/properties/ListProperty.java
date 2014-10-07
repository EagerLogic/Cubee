/*
 * Copyright 2014 dipacs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.eagerlogic.cubee.client.properties;

import com.eagerlogic.cubee.client.utils.IListChangeListener;
import com.eagerlogic.cubee.client.utils.ObservableList;

/**
 *
 * @author dipacs
 * @param <T>
 */
public class ListProperty<T> extends Property<ObservableList<T>> {
    
    private final IListChangeListener listener = new IListChangeListener() {

        @Override
        public void onItemAdded(int index, Object item, ObservableList sender) {
            invalidateIfNeeded();
        }

        @Override
        public void onItemRemoved(int index, Object item, ObservableList sender) {
            invalidateIfNeeded();
        }

        @Override
        public void onItemUpdated(int index, Object oldItem, Object newItem, ObservableList sender) {
            invalidateIfNeeded();
        }

        @Override
        public void onCleared(ObservableList sender) {
            invalidateIfNeeded();
        }
    };
    
    private ObservableList<T> lastValue;

    public ListProperty() {
        this(new ObservableList<T>());
    }

    public ListProperty(ObservableList<T> defaultValue) {
        super(defaultValue, false);
        this.lastValue = defaultValue;
        defaultValue.addListener(listener);
        this.addChangeListener(new IChangeListener() {

            @Override
            public void onChanged(Object sender) {
                if (lastValue != get()) {
                    lastValue.removeListener(listener);
                    get().addListener(listener);
                    lastValue = get();
                }
            }
        });
    }
    
}
