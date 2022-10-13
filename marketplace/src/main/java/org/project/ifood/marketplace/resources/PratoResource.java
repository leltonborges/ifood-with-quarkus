package org.project.ifood.marketplace.resources;

import io.smallrye.mutiny.Multi;
import org.project.ifood.marketplace.dto.prato.PratoDTO;
import org.project.ifood.marketplace.mapper.PratoMapper;
import org.project.ifood.marketplace.model.Prato;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/pratos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PratoResource {
    @Inject
    PratoMapper pratoMapper;

    @GET
    public Multi<List<PratoDTO>> buscarPratos() {
        return Prato.<Prato>listAll()
                .toMulti()
                .map(pratos ->
                        pratos.stream()
                                .map(this.pratoMapper::toDTO)
                                .collect(Collectors.toList()));
    }
}
