package org.project.ifood.marketplace.message;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.project.ifood.marketplace.dto.restaurante.AddRestauranteDTO;
import org.project.ifood.marketplace.mapper.RestauranteMapper;
import org.project.ifood.marketplace.model.Restaurante;
import org.project.ifood.marketplace.service.RestauranteService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class RestauranteIncoming {

    RestauranteMapper restauranteMapper;
    RestauranteService restauranteService;

    @Inject
    public RestauranteIncoming(RestauranteMapper restauranteMapper, RestauranteService restauranteService) {
        this.restauranteMapper = restauranteMapper;
        this.restauranteService = restauranteService;
    }

    @Incoming("IncludeRestaurante")
    @Transactional
    public Response receiveRestaureante(JsonObject jsonObject) {
        AddRestauranteDTO addRestauranteDTO = jsonObject.mapTo(AddRestauranteDTO.class);
        restauranteMapper.toRestaurante(addRestauranteDTO);
        return Response.ok().build();
//        restauranteService.saveRestaurente(restaurante);
    }
}
