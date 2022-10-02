package org.project.ifood.cadastro.resource;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.tags.Tags;
import org.project.ifood.cadastro.model.Prato;
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
public class PradoResource {

    @GET
    @Path("/{idRestaurante}/pratos")
    @Tags({@Tag(name = "Prato"), @Tag(name = "Restaurante")})
    public List<Prato> todos(@PathParam("idRestaurante") Long idRestaurante) {
        Optional<Restaurante> opRestaurante = validaRestaurante(idRestaurante);

        return Prato.list("restaurante", opRestaurante.get());
    }

    @POST
    @Path("/{idRestaurante}/pratos")
    @Transactional
    @Tags({@Tag(name = "Prato"), @Tag(name = "Restaurante")})
    public void savar(@PathParam("idRestaurante") Long idRestaurante, Prato dto) {
        Optional<Restaurante> opRestaurante = validaRestaurante(idRestaurante);

        Prato prato = new Prato();
        prato.nome = dto.nome;
        prato.descricao = dto.descricao;
        prato.preco = dto.preco;
        prato.restaurante = opRestaurante.get();

        prato.persist();
        Response.status(Response.Status.CREATED).build();
    }

    @Path("/{idRestaurante}/prato/{idPrato}")
    @PUT
    @Transactional
    @Tags({@Tag(name = "Prato"), @Tag(name = "Restaurante")})
    public void atualizar(@PathParam("idRestaurante") Long idRestaurante, @PathParam("idPrato") Long idPrato, Prato prato) {
        validaRestaurante(idRestaurante);

        Optional<Prato> opPrato = Prato.findByIdOptional(idPrato);
        if (opPrato.isEmpty()) throw new NotFoundException();

        Prato manager = opPrato.get();
        manager.nome = prato.nome;
        manager.preco = prato.preco;
        manager.descricao = prato.descricao;

        manager.persist();
    }

    @Path("{idRestaurante}/prato/{id}")
    @DELETE
    @Transactional
    @Tags({@Tag(name = "Prato"), @Tag(name = "Restaurante")})
    public void deletar(@PathParam("restaurante") Long idRestaurante, @PathParam("id") Long idPrato) {
        validaRestaurante(idRestaurante);
        Optional<Prato> opPrato = Prato.findByIdOptional(idPrato);
        opPrato.ifPresentOrElse(Prato::delete, () -> {
            throw new NotFoundException();
        });
    }

    private static Optional<Restaurante> validaRestaurante(Long idRestaurante) {
        Optional<Restaurante> opRestaurante = Restaurante.findByIdOptional(idRestaurante);
        if(opRestaurante.isEmpty()) throw new NotFoundException("Restaurante não encontrado");
        return opRestaurante;
    }
}
