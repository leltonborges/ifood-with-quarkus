package org.project.ifood.marketplace.resources;

import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.project.ifood.marketplace.dto.prato.PratoDTO;
import org.project.ifood.marketplace.mapper.PratoMapper;
import org.project.ifood.marketplace.service.PratoService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/pratos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PratoResource {

    PratoMapper pratoMapper;
    PratoService pratoService;

    @Inject
    public PratoResource(PratoMapper pratoMapper,
                         PratoService pratoService) {
        this.pratoMapper = pratoMapper;
        this.pratoService = pratoService;
    }

    @APIResponses({
            @APIResponse(responseCode = "200", content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = PratoDTO.class)))
    })
    @GET
    public Multi<PratoDTO> buscarPratos() {
        return this.pratoService.getPratoAll().map(this.pratoMapper::toDTO);
    }
}
