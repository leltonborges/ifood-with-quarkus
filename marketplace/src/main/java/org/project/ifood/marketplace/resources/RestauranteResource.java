package org.project.ifood.marketplace.resources;

import io.smallrye.mutiny.Multi;
import org.project.ifood.marketplace.dto.prato.PratoDTO;
import org.project.ifood.marketplace.mapper.PratoMapper;
import org.project.ifood.marketplace.model.Restaurante;
import org.project.ifood.marketplace.service.PratoService;
import org.project.ifood.marketplace.service.RestauranteService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/restaurantes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestauranteResource {

    PratoMapper pratoMapper;
    RestauranteService restauranteService;
    PratoService pratoService;

    @Inject
    public RestauranteResource(PratoMapper pratoMapper,
                               RestauranteService restauranteService,
                               PratoService pratoService) {
        this.pratoMapper = pratoMapper;
        this.restauranteService = restauranteService;
        this.pratoService = pratoService;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @GET
    @Path("/{idRestaurante}/pratos")
    public Multi<PratoDTO> buscarPratos(@PathParam("idRestaurante") Long idRestaurante) {
        Restaurante restaurante = this.restauranteService.getRestauranteById(idRestaurante);
        return this.pratoService.getPratoByRestaurante(restaurante).map(this.pratoMapper::toDTO);
    }
}
