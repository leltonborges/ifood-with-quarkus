package org.project.ifood.cadastro.resource;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.tags.Tags;
import org.project.ifood.cadastro.dto.prato.AdicionarPratoDTO;
import org.project.ifood.cadastro.dto.prato.AtualizarPratoDTO;
import org.project.ifood.cadastro.dto.prato.PratoDTO;
import org.project.ifood.cadastro.mapper.PratoMapper;
import org.project.ifood.cadastro.model.Prato;
import org.project.ifood.cadastro.model.Restaurante;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PradoResource {

    @Inject
    PratoMapper pratoMapper;

    private static Optional<Restaurante> validaRestaurante(Long idRestaurante) {
        Optional<Restaurante> opRestaurante = Restaurante.findByIdOptional(idRestaurante);
        if (opRestaurante.isEmpty()) throw new NotFoundException("Restaurante não encontrado");
        return opRestaurante;
    }

    @GET
    @Path("/{idRestaurante}/pratos")
    @Tags({@Tag(name = "Prato"), @Tag(name = "Restaurante")})
    public List<PratoDTO> todos(@PathParam("idRestaurante") Long idRestaurante) {
        Restaurante restaurante = validaRestaurante(idRestaurante).get();

        return Prato.<Prato>stream("restaurante", restaurante)
                .map(this.pratoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @POST
    @Path("/{idRestaurante}/pratos")
    @Transactional
    @Tags({@Tag(name = "Prato"), @Tag(name = "Restaurante")})
    public void savar(@PathParam("idRestaurante") Long idRestaurante, AdicionarPratoDTO dto) {
        Optional<Restaurante> opRestaurante = validaRestaurante(idRestaurante);

        Prato prato = this.pratoMapper.toPrato(dto);
        prato.restaurante = opRestaurante.get();

        prato.persist();
        Response.status(Response.Status.CREATED).build();
    }

    @Path("/{idRestaurante}/prato/{idPrato}")
    @PUT
    @Transactional
    @Tags({@Tag(name = "Prato"), @Tag(name = "Restaurante")})
    public void atualizar(@PathParam("idRestaurante") Long idRestaurante, @PathParam("idPrato") Long idPrato, AtualizarPratoDTO dto) {
        validaRestaurante(idRestaurante);

        Optional<Prato> opPrato = Prato.findByIdOptional(idPrato);
        if (opPrato.isEmpty()) throw new NotFoundException();

        Prato manager = opPrato.get();
        this.pratoMapper.toPrato(dto, manager);

        manager.persist();
    }

    @Path("{idRestaurante}/prato/{id}")
    @DELETE
    @Transactional
    @Tags({@Tag(name = "Prato"), @Tag(name = "Restaurante")})
    public void deletar(@PathParam("idRestaurante") Long idRestaurante, @PathParam("id") Long idPrato) {
        validaRestaurante(idRestaurante);
        Optional<Prato> opPrato = Prato.findByIdOptional(idPrato);
        opPrato.ifPresentOrElse(Prato::delete, () -> {
            throw new NotFoundException();
        });
    }
}
