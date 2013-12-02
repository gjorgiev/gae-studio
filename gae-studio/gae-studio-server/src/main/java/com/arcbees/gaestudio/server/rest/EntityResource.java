/**
 * Copyright (c) 2013 by ArcBees Inc., All rights reserved.
 * This source code, and resulting software, is the confidential and proprietary information
 * ("Proprietary Information") and is the intellectual property ("Intellectual Property")
 * of ArcBees Inc. ("The Company"). You shall not disclose such Proprietary Information and
 * shall use it only in accordance with the terms and conditions of any and all license
 * agreements you have entered into with The Company.
 */

package com.arcbees.gaestudio.server.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.arcbees.gaestudio.server.dto.mapper.EntityMapper;
import com.arcbees.gaestudio.server.service.EntityService;
import com.arcbees.gaestudio.server.util.AppEngineHelper;
import com.arcbees.gaestudio.shared.dto.entity.EntityDto;
import com.arcbees.gaestudio.shared.dto.entity.KeyDto;
import com.arcbees.gaestudio.shared.rest.UrlParameters;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.inject.assistedinject.Assisted;

@Produces(MediaType.APPLICATION_JSON)
public class EntityResource {
    private final EntityMapper entityMapper;
    private final Long entityId;
    private final EntityService entityService;
    private final String entityName;

    @Inject
    EntityResource(EntityService entityService,
                   EntityMapper entityMapper,
                   @Assisted Long entityId,
                   @Assisted String entityName) {
        this.entityMapper = entityMapper;
        this.entityId = entityId;
        this.entityService = entityService;
        this.entityName = entityName;
    }

    @GET
    public Response getEntity(@QueryParam(UrlParameters.NAMESPACE) String namespace,
                              @QueryParam(UrlParameters.APPID) String appId,
                              @QueryParam(UrlParameters.KIND) String kind,
                              @QueryParam(UrlParameters.PARENT_ID) String parentId,
                              @QueryParam(UrlParameters.PARENT_KIND) String parentKind)
            throws EntityNotFoundException {
        ResponseBuilder responseBuilder;

        Entity entity = entityService.getEntity(entityId, entityName, namespace, appId, kind, parentId, parentKind);

        if (entity == null) {
            responseBuilder = Response.status(Status.NOT_FOUND);
        } else {
            EntityDto entityDto = entityMapper.mapEntityToDto(entity);
            responseBuilder = Response.ok(entityDto);
        }

        return responseBuilder.build();
    }

    @PUT
    public Response updateEntity(EntityDto newEntityDto) throws EntityNotFoundException {
        ResponseBuilder responseBuilder;
        Entity newEntity = entityMapper.mapDtoToEntity(newEntityDto);
        Entity updatedEntity = entityService.updateEntity(newEntity);

        if (updatedEntity == null) {
            responseBuilder = Response.status(Status.NOT_FOUND);
        } else {
            EntityDto updatedEntityDto = entityMapper.mapEntityToDto(updatedEntity);
            responseBuilder = Response.ok(updatedEntityDto);
        }

        return responseBuilder.build();
    }

    @DELETE
    public Response deleteEntity(KeyDto keyDto) {
        AppEngineHelper.disableApiHooks();
        Key key = KeyFactory.createKey(keyDto.getKind(), keyDto.getId());

        entityService.deleteEntity(key);

        return Response.noContent().build();
    }
}
