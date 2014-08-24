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

package com.eagerlogic.cubee.client.utils;

/**
 *
 * @author dipacs
 */
public interface IListChangeListener<T> {
    
    public void onItemAdded(int index, T item, ObservableList<T> sender);
    public void onItemRemoved(int index, T item, ObservableList<T> sender);
    public void onItemUpdated(int index, T oldItem, T newItem, ObservableList<T> sender);
    public void onCleared(ObservableList<T> sender);
    
}
