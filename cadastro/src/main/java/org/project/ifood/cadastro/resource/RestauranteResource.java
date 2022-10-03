package org.project.ifood.cadastro.resource;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.tags.Tags;
import org.project.ifood.cadastro.dto.AddRestauranteDTO;
import org.project.ifood.cadastro.mapper.RestauranteMapper;
import org.project.ifood.cadastro.model.Restaurante;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestauranteResource {
    @Inject
    RestauranteMapper restauranteMapper;

    @GET
    @Tags({@Tag(name = "Restaurante")})
    public List<Restaurante> todosRestuarentes() {
        return Restaurante.listAll();
    }

    @POST
    @Transactional
    @Tags({@Tag(name = "Restaurante")})
    public Response incluir(AddRestauranteDTO dto) {
        Restaurante restaurante = restauranteMapper.toRestaureante(dto);
        restaurante.persist();
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @Tags({@Tag(name = "Restaurante")})
    public Response atualizar(@PathParam("id") Long id, Restaurante dto) {
        Optional<Restaurante> entityBase = Restaurante.findByIdOptional(id);
        if (entityBase.isEmpty()) throw new NotFoundException();

        Restaurante restaurante = entityBase.get();
        restaurante.nome = dto.nome;
        restaurante.persist();

        return Response.accepted().build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Tags({@Tag(name = "Restaurante")})
    public Response deletar(@PathParam("id") Long id) {
        Optional<Restaurante> entityBase = Restaurante.findByIdOptional(id);
        entityBase.ifPresentOrElse(Restaurante::delete, () -> {
            throw new NotFoundException();
        });
        return Response.noContent().build();
    }
}
