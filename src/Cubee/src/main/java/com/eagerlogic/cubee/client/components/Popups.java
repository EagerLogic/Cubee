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

package com.eagerlogic.cubee.client.components;

import java.util.ArrayList;
import java.util.List;

import com.eagerlogic.cubee.client.utils.ARunOnce;

/**
 *
 * @author dipacs
 */
class Popups {
    
    private static final List<APopup> popups = new ArrayList<APopup>();
    private static final ARunOnce layoutRunOnce = new ARunOnce() {
		
		@Override
		protected void onRun() {
			layout();
		}
	};
    
    static void addPopup(APopup popup) {
        popups.add(popup);
        requestLayout();
    }
    
    static void removePopup(APopup popup) {
        popups.remove(popup);
        requestLayout();
    }
    
    static void requestLayout() {
    	layoutRunOnce.run();
    }
    
    private static void layout() {
        for (APopup popup : popups) {
            popup.layout();
        }
    }
    
    public static boolean doPointerEventClimbingUp(int x, int y, int wheelVelocity,
            boolean altPressed, boolean ctrlPressed, boolean shiftPressed, boolean metaPressed, int type) {
        for (APopup popup : popups) {
            if (popup.doPointerEventClimbingUp(x, y, x, y, wheelVelocity, altPressed, ctrlPressed, shiftPressed, metaPressed, type)) {
                return true;
            }
        }
        return false;
    }
    
    private Popups() {
        
    }
    
}
