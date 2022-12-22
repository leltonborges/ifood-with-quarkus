package org.project.ifood.pedido.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.project.ifood.pedido.dto.prato.PratoPedidoDTO;
import org.project.ifood.pedido.model.Prato;

import java.util.List;

@Mapper(componentModel = "cdi", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PratoMapper {
    @Mappings({
            @Mapping(source = "nome", target = "nome"),
            @Mapping(source = "descricao", target = "descricao"),
            @Mapping(source = "restaurante.id", target = "restaurante.idRestaurante"),
            @Mapping(source = "restaurante.nomeFantasia", target = "restaurante.nome"),
            @Mapping(source = "restaurante.cnpj", target = "restaurante.cnpj"),
            @Mapping(source = "preco", target = "preco"),
    })
    Prato from(PratoPedidoDTO dto);

    @Mappings({
            @Mapping(source = "nome", target = "nome"),
            @Mapping(source = "descricao", target = "descricao"),
            @Mapping(source = "restaurante.idRestaurante", target = "restaurante.id"),
            @Mapping(source = "restaurante.cnpj", target = "restaurante.cnpj"),
            @Mapping(source = "restaurante.nome", target = "restaurante.nomeFantasia"),
            @Mapping(source = "preco", target = "preco"),
    })
    PratoPedidoDTO from(Prato dto);

    List<Prato> listFrom(List<PratoPedidoDTO> pedidoDTOS);
}
