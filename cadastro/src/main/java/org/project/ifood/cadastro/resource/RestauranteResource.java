package org.project.ifood.cadastro.resource;

import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.security.*;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.tags.Tags;
import org.project.ifood.cadastro.dto.restaurante.AddRestauranteDTO;
import org.project.ifood.cadastro.dto.restaurante.AtualizarRestaurante;
import org.project.ifood.cadastro.dto.restaurante.RestauranteDTO;
import org.project.ifood.cadastro.mapper.RestauranteMapper;
import org.project.ifood.cadastro.model.Restaurante;
import providers.response.ConstraintViolationResponse;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed("proprietario")
@SecuritySchemes({
        @SecurityScheme(
                securitySchemeName = "ifood-oauth",
                type = SecuritySchemeType.OAUTH2,
                flows = @OAuthFlows(
                        password = @OAuthFlow(tokenUrl = "http://localhost:8190/auth/realms/ifood/protocol/openid-connect/token")
                ))
})
@SecurityRequirements({
        @SecurityRequirement(name = "ifood-oauth", scopes = {})
})
//@SecurityScheme(securitySchemeName = "ifood-oauth", type = SecuritySchemeType.OAUTH2, flows = @OAuthFlows(password = @OAuthFlow(tokenUrl = "http://localhost:8180/auth/realms/ifood/protocol/openid-connect/token")))
//@SecurityRequirement(name = "ifood-oauth", scopes = {})
public class RestauranteResource {
    @Inject
    RestauranteMapper restauranteMapper;

    @GET
    @Tags({@Tag(name = "Restaurante")})
    public List<RestauranteDTO> todosRestuarentes() {
        return Restaurante.<Restaurante>streamAll()
                .map(this.restauranteMapper::toRestauranteDTO)
                .collect(Collectors.toList());
    }

    @POST
    @Transactional
    @Tags({@Tag(name = "Restaurante")})
    @APIResponses({
            @APIResponse(responseCode = "201", description = "Caso restaurante seja cadastrado com sucesso"),
            @APIResponse(responseCode = "400", description = "Erro do lado cliente", content = @Content(schema = @Schema(allOf = ConstraintViolationResponse.class)))

    })
    public Response incluir(@Valid AddRestauranteDTO dto) {
        Restaurante restaurante = restauranteMapper.toRestaureante(dto);
        restaurante.persist();
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @Tags({@Tag(name = "Restaurante")})
    public Response atualizar(@PathParam("id") Long id, AtualizarRestaurante dto) {
        Optional<Restaurante> entityBase = Restaurante.findByIdOptional(id);
        if (entityBase.isEmpty()) throw new NotFoundException();

        Restaurante restaurante = entityBase.get();

        restauranteMapper.toRestaurante(dto, restaurante);
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
