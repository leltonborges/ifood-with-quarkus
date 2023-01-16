package org.project.ifood.pedido.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.project.ifood.pedido.dto.prato.PratoPedidoDTO;
import org.project.ifood.pedido.model.Localizacao;
import org.project.ifood.pedido.model.Pedido;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface PratoMapper {
    @Mappings({
            @Mapping(source = "nome", target = "nome"),
            @Mapping(source = "descricao", target = "descricao"),
            @Mapping(source = "restaurante.id", target = "restaurante.idRestaurante"),
            @Mapping(source = "restaurante.nomeFantasia", target = "restaurante.nome"),
            @Mapping(source = "restaurante.cnpj", target = "restaurante.cnpj"),
            @Mapping(source = "preco", target = "preco"),
    })
    org.project.ifood.pedido.model.Prato from(PratoPedidoDTO dto);

    @Mappings({
            @Mapping(source = "nome", target = "nome"),
            @Mapping(source = "descricao", target = "descricao"),
            @Mapping(source = "restaurante.idRestaurante", target = "restaurante.id"),
            @Mapping(source = "restaurante.cnpj", target = "restaurante.cnpj"),
            @Mapping(source = "restaurante.nome", target = "restaurante.nomeFantasia"),
            @Mapping(source = "preco", target = "preco"),
    })
    PratoPedidoDTO from(org.project.ifood.pedido.model.Prato dto);

    @Mappings({
            @Mapping(source = "latitude", target = "localizacaoEntregador.latitude"),
            @Mapping(source = "longitude", target = "localizacaoEntregador.longitude")
    })
    Pedido addNewLocalizacao(Localizacao localizacao, @MappingTarget Pedido pedido);

    List<org.project.ifood.pedido.model.Prato> listFrom(List<PratoPedidoDTO> pedidoDTOS);
}
