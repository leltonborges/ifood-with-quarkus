package org.project.ifood.pedido.handle.exceptions;

import java.io.Serializable;

public class PersistenceException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 4852273032613321858L;

    public PersistenceException(String message) {
        super(message);
    }
}
