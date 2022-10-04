package org.project.ifood.cadastro.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.project.ifood.cadastro.dto.prato.AdicionarPratoDTO;
import org.project.ifood.cadastro.dto.prato.AtualizarPratoDTO;
import org.project.ifood.cadastro.dto.prato.PratoDTO;
import org.project.ifood.cadastro.model.Prato;

@Mapper(componentModel = "cdi")
public interface PratoMapper {
    PratoDTO toDTO(Prato prato);
    Prato toPrato(AdicionarPratoDTO dto);
    void toPrato(AtualizarPratoDTO dto, @MappingTarget Prato prato);
}
