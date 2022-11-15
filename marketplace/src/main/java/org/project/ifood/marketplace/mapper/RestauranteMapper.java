package org.project.ifood.marketplace.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.project.ifood.marketplace.dto.restaurante.AddRestauranteDTO;
import org.project.ifood.marketplace.model.Restaurante;

@Mapper(componentModel = "cdi")
public interface RestauranteMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nomeFantasia", target = "nome"),
            @Mapping(source = "cnpj", target = "cnpj"),
            @Mapping(source = "proprietario", target = "proprietario"),
            @Mapping(source = "localizacao.longitude", target = "localizacao.longitude"),
            @Mapping(source = "localizacao.latitude", target = "localizacao.latitude")
    })
    Restaurante toRestaurante(AddRestauranteDTO restauranteDTO);
}
