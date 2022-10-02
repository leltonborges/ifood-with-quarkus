package org.project.ifood.cadastro.resource;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.tags.Tags;
import org.project.ifood.cadastro.model.Restaurante;

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

    @GET
    @Tags({@Tag(name = "Restaurante")})
    public List<Restaurante> todosRestuarentes() {
        return Restaurante.listAll();
    }

    @POST
    @Transactional
    @Tags({@Tag(name = "Restaurante")})
    public void incluir(Restaurante dto) {
        dto.persist();
        Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @Tags({@Tag(name = "Restaurante")})
    public void atualizar(@PathParam("id") Long id, Restaurante dto) {
        Optional<Restaurante> entityBase = Restaurante.findByIdOptional(id);
        if (entityBase.isEmpty()) throw new NotFoundException();

        Restaurante restaurante = entityBase.get();
        restaurante.name = dto.name;

        restaurante.persist();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Tags({@Tag(name = "Restaurante")})
    public void deletar(@PathParam("id") Long id) {
        Optional<Restaurante> entityBase = Restaurante.findByIdOptional(id);
        entityBase.ifPresentOrElse(Restaurante::delete, () -> {
            throw new NotFoundException();
        });
    }
}
