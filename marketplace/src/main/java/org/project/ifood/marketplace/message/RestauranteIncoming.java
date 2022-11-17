package org.project.ifood.marketplace.message;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.project.ifood.marketplace.dto.restaurante.AddRestauranteDTO;
import org.project.ifood.marketplace.mapper.RestauranteMapper;
import org.project.ifood.marketplace.model.Restaurante;
import org.project.ifood.marketplace.service.RestauranteService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class RestauranteIncoming {
    RestauranteService restauranteService;

    RestauranteMapper restauranteMapper;

    @Inject
    public RestauranteIncoming(RestauranteMapper restauranteMapper, RestauranteService restauranteService) {
        this.restauranteMapper = restauranteMapper;
        this.restauranteService = restauranteService;
    }

    @Incoming("IncludeRestaurante")
    @Transactional
    public void receiveRestaureante(JsonObject jsonObject) {
        AddRestauranteDTO addRestauranteDTO = jsonObject.mapTo(AddRestauranteDTO.class);
        Restaurante restaurante = restauranteMapper.toRestaurante(addRestauranteDTO);
        restaurante.cnpj = restaurante.cnpj.replaceAll("\\x2E|\\x2F|\\x2D", "");
        this.restauranteService.saveRestaurente(restaurante)
                               .await()
                               .asOptional()
                               .indefinitely();
    }
}
