package org.project.ifood.cadastro.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.project.ifood.cadastro.dto.AddRestauranteDTO;
import org.project.ifood.cadastro.model.Restaurante;

@Mapper(componentModel = "cdi")
public interface RestauranteMapper {
    @Mapping(target = "nome", source = "nomeFantasia")
    Restaurante toRestaureante(AddRestauranteDTO dto);
}
