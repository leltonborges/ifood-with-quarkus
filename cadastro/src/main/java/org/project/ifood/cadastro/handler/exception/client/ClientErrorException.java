package org.project.ifood.cadastro.handler.exception.client;

import javax.ws.rs.WebApplicationException;

public class ClientErrorException extends WebApplicationException {
    private static final long serialVersionUID = -5898720594787117019L;

    public ClientErrorException(String message) {
        super(message);
    }
}
