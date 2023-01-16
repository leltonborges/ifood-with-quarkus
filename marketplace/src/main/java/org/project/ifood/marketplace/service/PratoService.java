package org.project.ifood.marketplace.service;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.project.ifood.marketplace.model.Prato;
import org.project.ifood.marketplace.model.Restaurante;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
@ApplicationScoped

public class PratoService {

    public Multi<Prato> getPratoAll() {
        return Prato.streamAll();
    }

    public Multi<Prato> getPratoByRestaurante(Restaurante restaurante) {
        return Prato.stream("restaurante", restaurante);
    }

    public Uni<Prato> findByPrato(Long idPrato) {
        return Prato.<Prato>findById(idPrato)
                    .onItem()
                    .ifNull()
                    .failWith(() -> new NotFoundException("Prato not found: " + idPrato));
    }
}
