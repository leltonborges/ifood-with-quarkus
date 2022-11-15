package org.project.ifood.marketplace.service;

import org.project.ifood.marketplace.model.Restaurante;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
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

    public void saveRestaurente(Restaurante restaurante) {
        restaurante.persist()
                   .await()
                   .asOptional()
                   .indefinitely()
                   .orElseThrow(() -> new RuntimeException("Erro ao salvar o restaurante"));
    }
}
