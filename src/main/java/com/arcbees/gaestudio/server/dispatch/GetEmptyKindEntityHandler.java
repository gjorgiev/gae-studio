/**
 * Copyright 2012 ArcBees Inc.  All rights reserved.
 */

package com.arcbees.gaestudio.server.dispatch;

import com.arcbees.gaestudio.server.dto.mapper.EntityMapper;
import com.arcbees.gaestudio.shared.dispatch.GetEmptyKindEntityAction;
import com.arcbees.gaestudio.shared.dispatch.GetEmptyKindEntityResult;
import com.arcbees.gaestudio.shared.dto.entity.EntityDto;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

import java.util.Map;

public class GetEmptyKindEntityHandler extends
        AbstractActionHandler<GetEmptyKindEntityAction, GetEmptyKindEntityResult> {
    @Inject
    public GetEmptyKindEntityHandler() {
        super(GetEmptyKindEntityAction.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public GetEmptyKindEntityResult execute(GetEmptyKindEntityAction action, ExecutionContext context)
            throws ActionException {
        DispatchHelper.disableApiHooks();
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Entity emptyEntity = new Entity(action.getKind());
        try {
            Query query = new Query(action.getKind());
            FetchOptions fetchOptions = FetchOptions.Builder.withOffset(0).limit(1);
            Entity entity = datastore.prepare(query).asList(fetchOptions).get(0);
            emptyEntity = setEmptiedProperties(emptyEntity, entity.getProperties());
        } catch (Exception e) {
            // TODO: Be able to generate entity base schema from the pojo that haven't been save yet tot the datastore
            // We will need to create an implementation to support Objectify, Twig persist, etc.
            // For objectify we can use : ObjectifyService.factory().getMetadataForEntity(String kind);
            // And call method metadata.toEntity
        }

        EntityDto entityDTO = EntityMapper.mapDTO(emptyEntity);
        return new GetEmptyKindEntityResult(entityDTO);
    }

    private Entity setEmptiedProperties(Entity entity, Map<String, Object> properties) {
        for (Map.Entry<String, Object> property : properties.entrySet()) {
            Object value = property.getValue();

            if (value instanceof Key) {
                value = createEmptyKey((Key) value);
            } else {
                value = createEmptyArbitraryObject(property);
            }
            entity.setProperty(property.getKey(), value);
        }

        return entity;
    }

    private Object createEmptyKey(Key key) {
        return KeyFactory.createKey(key.getKind(), " ");
    }

    private Object createEmptyArbitraryObject(Map.Entry<String, Object> property) {
        try {
            // Reset all property objects in the datastore with a no-args constructor
            return property.getValue().getClass().newInstance();
        } catch (Exception e) {
            // Otherwise set null
            return null;
        }
    }
}
