package com.arcbees.gaestudio.companion.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.google.appengine.api.utils.SystemProperty;
import com.googlecode.objectify.Key;

import static com.arcbees.gaestudio.companion.dao.OfyService.ofy;

@Path(TestEndPoints.CLEAR)
public class ClearResource {
    @GET
    public Response get() {
        ResponseBuilder responseBuilder;

        if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
            responseBuilder = Response.status(Status.FORBIDDEN);
        } else {
            List<Key<Object>> keys = ofy().load().keys().list();

            ofy().delete().entities(keys).now();
            ofy().clear();

            responseBuilder = Response.ok();
        }

        return responseBuilder.build();
    }
}