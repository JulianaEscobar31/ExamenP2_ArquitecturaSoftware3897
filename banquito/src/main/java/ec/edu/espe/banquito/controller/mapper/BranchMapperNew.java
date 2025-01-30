package ec.edu.espe.banquito.controller.mapper;


import ec.edu.espe.banquito.controller.dto.BranchDTO;
import ec.edu.espe.banquito.model.Branch;

@Mapper (
    componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface BranchMapperNew {

    BranchDTO toDTO(Branch model);
    
    Branch toModel(BranchDTO dto);
}

