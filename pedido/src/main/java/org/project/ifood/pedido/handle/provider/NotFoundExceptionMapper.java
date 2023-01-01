package org.project.ifood.pedido.handle.provider;

import org.project.ifood.pedido.handle.data.StandardHandler;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    @Context
    UriInfo uriInfo;
    @Override
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
