package org.project.ifood.marketplace.resources;

import io.smallrye.mutiny.Multi;
import org.project.ifood.marketplace.dto.prato.PratoDTO;
import org.project.ifood.marketplace.mapper.PratoMapper;
import org.project.ifood.marketplace.model.Prato;
import org.project.ifood.marketplace.model.Restaurante;
import org.project.ifood.marketplace.service.RestauranteService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicReference;

@Path("/restaurantes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestauranteResource {

    PratoMapper pratoMapper;
    RestauranteService restauranteService;

    @Inject
    public RestauranteResource(PratoMapper pratoMapper, RestauranteService restauranteService) {
        this.pratoMapper = pratoMapper;
        this.restauranteService = restauranteService;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @GET
    @Path("/{idRestaurante}/pratos")
    public Multi<PratoDTO> buscarPratos(@PathParam("idRestaurante") Long idRestaurante) {
        Restaurante restaurante = this.restauranteService.getRestauranteById(idRestaurante);
        return  this.restauranteService.getPratoByRestaurante(restaurante).map(this.pratoMapper::toDTO);
    }
}
