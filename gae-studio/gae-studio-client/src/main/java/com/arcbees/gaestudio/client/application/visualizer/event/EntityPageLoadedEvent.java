/**
 * Copyright 2015 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.arcbees.gaestudio.client.application.visualizer.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class EntityPageLoadedEvent extends GwtEvent<EntityPageLoadedEvent.EntityPageLoadedHandler> {
    public interface EntityPageLoadedHandler extends EventHandler {
        void onEntityPageLoaded(EntityPageLoadedEvent event);
    }

    private static final Type<EntityPageLoadedHandler> TYPE = new Type<EntityPageLoadedHandler>();

    public EntityPageLoadedEvent() {
        // Possibly for serialization.
    }

    public static void fire(HasHandlers source) {
        EntityPageLoadedEvent eventInstance = new EntityPageLoadedEvent();
        source.fireEvent(eventInstance);
    }

    public static void fire(HasHandlers source, EntityPageLoadedEvent eventInstance) {
        source.fireEvent(eventInstance);
    }

    public static Type<EntityPageLoadedHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<EntityPageLoadedHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(EntityPageLoadedHandler handler) {
        handler.onEntityPageLoaded(this);
    }
}
