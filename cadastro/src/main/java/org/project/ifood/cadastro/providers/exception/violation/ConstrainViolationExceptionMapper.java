package org.project.ifood.cadastro.providers.exception.violation;

import org.project.ifood.cadastro.providers.exception.violation.ConstraintViolationResponse;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ConstrainViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(ConstraintViolationResponse.of(exception))
                .build();
    }
}
