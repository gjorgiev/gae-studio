package com.arcbees.gaestudio.companion.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.arcbees.gaestudio.companion.dao.CarDao;
import com.arcbees.gaestudio.companion.domain.Car;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HelloResource {
    private final CarDao carDao;

    @Inject
    HelloResource(CarDao carDao) {
        this.carDao = carDao;
    }

    @POST
    @Path(TestEndPoints.PUT_OBJECT)
    public Response createObject(Car car) {
        car.setId(null);

        carDao.put(car);

        return Response.ok(car).build();
    }


}
