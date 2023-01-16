package org.project.ifood.marketplace.handler.exceptions;

import org.project.ifood.marketplace.handler.data.StandardHandler;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Context
    UriInfo uriInfo;

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(NotFoundException e) {

        StandardHandler standardHandler = new StandardHandler(e.getMessage(),
                                                              uriInfo.getPath(),
                                                              System.currentTimeMillis(),
                                                              Response.Status.NOT_FOUND.getStatusCode());

        return Response.status(Response.Status.NOT_FOUND)
                       .entity(standardHandler)
                       .build();
    }
}
