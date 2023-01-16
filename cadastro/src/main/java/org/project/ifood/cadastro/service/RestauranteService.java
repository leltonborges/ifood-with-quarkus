package org.project.ifood.cadastro.service;

import org.project.ifood.cadastro.model.Restaurante;
import org.project.ifood.cadastro.handler.exception.client.ClientErrorException;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RestauranteService {

    public Restaurante findByRestaurante(Long id, String proprietario) {

        return Restaurante.<Restaurante>find("id = ?1 and proprietario = ?2", id, proprietario)
                          .firstResultOptional()
                          .orElseThrow(() ->
                                               new ClientErrorException(String.format(
                                                       "Restaurante %s não encontrado ou %s invalido para acessar o restraurante",
                                                       id,
                                                       proprietario)));
    }
}
