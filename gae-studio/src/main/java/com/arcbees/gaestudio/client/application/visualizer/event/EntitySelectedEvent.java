/**
 * Copyright (c) 2013 by ArcBees Inc., All rights reserved.
 * This source code, and resulting software, is the confidential and proprietary information
 * ("Proprietary Information") and is the intellectual property ("Intellectual Property")
 * of ArcBees Inc. ("The Company"). You shall not disclose such Proprietary Information and
 * shall use it only in accordance with the terms and conditions of any and all license
 * agreements you have entered into with The Company.
 */

package com.arcbees.gaestudio.client.application.visualizer.event;

import com.arcbees.gaestudio.client.application.visualizer.ParsedEntity;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class EntitySelectedEvent extends GwtEvent<EntitySelectedEvent.EntitySelectedHandler> {
    private ParsedEntity parsedEntity;

    protected EntitySelectedEvent() {
        // Possibly for serialization.
    }

    public EntitySelectedEvent(com.arcbees.gaestudio.client.application.visualizer.ParsedEntity parsedEntity) {
        this.parsedEntity = parsedEntity;
    }

    public static void fire(HasHandlers source,
                            com.arcbees.gaestudio.client.application.visualizer.ParsedEntity parsedEntity) {
        EntitySelectedEvent eventInstance = new EntitySelectedEvent(parsedEntity);
        source.fireEvent(eventInstance);
    }

    public static void fire(HasHandlers source, EntitySelectedEvent eventInstance) {
        source.fireEvent(eventInstance);
    }

    public interface HasEntitySelectedHandlers extends HasHandlers {
        HandlerRegistration addEntitySelectedHandler(EntitySelectedHandler handler);
    }

    public interface EntitySelectedHandler extends EventHandler {
        public void onEntitySelected(EntitySelectedEvent event);
    }

    private static final Type<EntitySelectedHandler> TYPE = new Type<EntitySelectedHandler>();

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

    public com.arcbees.gaestudio.client.application.visualizer.ParsedEntity getParsedEntity() {
        return parsedEntity;
    }
}