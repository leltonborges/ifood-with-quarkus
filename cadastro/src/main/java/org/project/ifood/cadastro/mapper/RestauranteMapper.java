package org.project.ifood.cadastro.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.project.ifood.cadastro.dto.restaurante.AddRestauranteDTO;
import org.project.ifood.cadastro.dto.restaurante.AtualizarRestaurante;
import org.project.ifood.cadastro.dto.restaurante.RestauranteDTO;
import org.project.ifood.cadastro.model.Restaurante;

@Mapper(componentModel = "cdi")
public interface RestauranteMapper {

    @Mappings({
            @Mapping(target = "nome", source = "nomeFantasia"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "dataCriacao", ignore = true),
            @Mapping(target = "dataAtualizacao", ignore = true),
            @Mapping(target = "localizacao.id", ignore = true)
    })
    Restaurante toRestaureante(AddRestauranteDTO dto);

    @Mappings({
            @Mapping(source = "nome", target = "nomeFantasia"),
            @Mapping(target = "localizacao", dateFormat = "dd/MM/yyyy HH:mm:ss")
    })
    RestauranteDTO toRestauranteDTO(Restaurante restaurante);

    @Mappings({
            @Mapping(target = "nome", source = "nomeFantasia"),
    })
    public void toRestaurante(AtualizarRestaurante dto, @MappingTarget Restaurante restaurante);
}
