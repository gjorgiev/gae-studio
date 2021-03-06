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

import com.arcbees.gaestudio.client.application.visualizer.ParsedEntity;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class EntitySelectedEvent extends GwtEvent<EntitySelectedEvent.EntitySelectedHandler> {
    public interface EntitySelectedHandler extends EventHandler {
        void onEntitySelected(EntitySelectedEvent event);
    }

    private static final Type<EntitySelectedHandler> TYPE = new Type<>();

    private ParsedEntity parsedEntity;

    public EntitySelectedEvent(ParsedEntity parsedEntity) {
        this.parsedEntity = parsedEntity;
    }

    public static void fire(HasHandlers source, ParsedEntity parsedEntity) {
        EntitySelectedEvent eventInstance = new EntitySelectedEvent(parsedEntity);
        source.fireEvent(eventInstance);
    }

    public static void fire(HasHandlers source, EntitySelectedEvent eventInstance) {
        source.fireEvent(eventInstance);
    }

    public static Type<EntitySelectedHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<EntitySelectedHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(EntitySelectedHandler handler) {
        handler.onEntitySelected(this);
    }

    public ParsedEntity getParsedEntity() {
        return parsedEntity;
    }
}
