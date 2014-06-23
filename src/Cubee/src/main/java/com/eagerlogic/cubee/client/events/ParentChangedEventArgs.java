/*
 * Copyright 2014 Barnabas.
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
package com.eagerlogic.cubee.client.events;

import com.eagerlogic.cubee.client.components.ALayout;

/**
 *
 * @author Barnabas
 */
public class ParentChangedEventArgs extends EventArgs {
    
    private final ALayout newParent;

    public ParentChangedEventArgs(ALayout newParent, Object sender) {
        super(sender);
        this.newParent = newParent;
    }

    public ALayout getNewParent() {
        return newParent;
    }
    
}
