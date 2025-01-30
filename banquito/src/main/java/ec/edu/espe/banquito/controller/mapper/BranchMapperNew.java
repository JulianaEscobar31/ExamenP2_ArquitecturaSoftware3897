package ec.edu.espe.banquito.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import ec.edu.espe.banquito.controller.dto.BranchDTO;
import ec.edu.espe.banquito.model.Branch;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING, 
    unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface BranchMapperNew {

    BranchDTO toDTO(Branch model);

    Branch toModel(BranchDTO dto);
}
