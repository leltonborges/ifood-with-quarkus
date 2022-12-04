package org.project.ifood.marketplace.resources;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.project.ifood.marketplace.dto.prato.PratoDTO;
import org.project.ifood.marketplace.dto.restaurante.AddRestauranteDTO;
import org.project.ifood.marketplace.mapper.PratoMapper;
import org.project.ifood.marketplace.mapper.RestauranteMapper;
import org.project.ifood.marketplace.model.Restaurante;
import org.project.ifood.marketplace.service.PratoService;
import org.project.ifood.marketplace.service.RestauranteService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/restaurantes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestauranteResource {

    PratoMapper pratoMapper;
    RestauranteService restauranteService;
    PratoService pratoService;
    RestauranteMapper restauranteMapper;

    @Inject
    public RestauranteResource(PratoMapper pratoMapper,
                               RestauranteService restauranteService,
                               PratoService pratoService,
                               RestauranteMapper restauranteMapper) {
        this.pratoMapper = pratoMapper;
        this.restauranteService = restauranteService;
        this.pratoService = pratoService;
        this.restauranteMapper = restauranteMapper;
    }

    @GET
    @Path("/{idRestaurante}/pratos")
    public Multi<PratoDTO> buscarPratos(@PathParam("idRestaurante") Long idRestaurante) {
        Restaurante restaurante = this.restauranteService.getRestauranteById(idRestaurante);
        return this.pratoService.getPratoByRestaurante(restaurante).map(this.pratoMapper::toDTO);
    }

    @POST
    @Transactional
    public Uni<Response> save(AddRestauranteDTO addRestauranteDTO) {
        Restaurante restaurante = restauranteMapper.toRestaurante(addRestauranteDTO);
        return this.restauranteService
                .saveRestaurente(restaurante)
                .onItem()
                .transform(t -> Response.status(Response.Status.CREATED).build());
    }

    @GET
    public Multi<Restaurante> save() {
        return Restaurante.streamAll();
    }

}
