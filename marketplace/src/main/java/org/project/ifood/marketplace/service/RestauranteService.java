package org.project.ifood.marketplace.service;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
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

    public Uni<Restaurante> saveRestaurente(Restaurante restaurante) {
        restaurante.cnpj = restaurante.cnpj.replaceAll("\\x2E|\\x2F|\\x2D", "");
        return Panache.withTransaction(restaurante::persist);
    }
}
