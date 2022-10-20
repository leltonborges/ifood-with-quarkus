package org.project.ifood.marketplace.handler.exceptions;

import org.project.ifood.marketplace.handler.data.Standard;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(NotFoundException e) {
        Standard standard = new Standard(e.getMessage(),
                                         "",
                                         System.currentTimeMillis(),
                                         Response.Status.NOT_FOUND.getReasonPhrase());

        return Response.status(Response.Status.NOT_FOUND)
                       .entity(standard)
                       .build();
    }
}
