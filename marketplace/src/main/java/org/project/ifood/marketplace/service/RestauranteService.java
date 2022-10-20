package org.project.ifood.marketplace.service;

import io.smallrye.mutiny.Multi;
import org.project.ifood.marketplace.model.Prato;
import org.project.ifood.marketplace.model.Restaurante;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class RestauranteService {
    public Restaurante getRestauranteById(Long id) {
        return Restaurante.<Restaurante>findById(id)
                          .await()
                          .asOptional()
                          .indefinitely()
                          .orElseThrow(() -> new NotFoundException("Restaurante não encontrado por id: " + id));
    }

    public Multi<Prato> getPratoByRestaurante(Restaurante restaurante) {
        return Prato.stream("restaurante", restaurante);
    }


}
