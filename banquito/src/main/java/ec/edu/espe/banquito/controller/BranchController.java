package ec.edu.espe.banquito.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ec.edu.espe.banquito.controller.dto.BranchDTO;
import ec.edu.espe.banquito.model.Branch;
import ec.edu.espe.banquito.model.BranchHoliday;
import ec.edu.espe.banquito.service.BranchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/branches")
@Slf4j
@Tag(name = "Branch", description = "Branches API")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping
    @Operation(summary = "Obtener todas las sucursales")
    @ApiResponse(responseCode = "200", description = "Lista de sucursales encontrada exitosamente")
    public ResponseEntity<List<BranchDTO>> getAllBranches() {
        log.info("Obteniendo todas las sucursales");
        List<Branch> branches = this.branchService.findAll();
        List<BranchDTO> branchesDTO = new ArrayList<>();
        branches.forEach(branch -> branchesDTO.add(this.BranchMapperNew.toDTO(branch)));
        return ResponseEntity.ok(branchesDTO);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva sucursal sin feriados")
    @ApiResponse(responseCode = "200", description = "Sucursal creada exitosamente")
    public ResponseEntity<BranchDTO> createBranch(@RequestBody BranchDTO branchDTO) {
        log.info("Creando sucursal: {}", branchDTO);
        Branch branch = this.BranchMapperNew.toModel(branchDTO);
        Branch branchCreated = this.branchService.createBranch(branch);
        return ResponseEntity.ok(this.BranchMapperNew.toDTO(branchCreated));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una sucursal por su ID")
    @ApiResponse(responseCode = "200", description = "Sucursal encontrada exitosamente")
    @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    public ResponseEntity<BranchDTO> getBranchById(
            @Parameter(description = "ID de la sucursal") @PathVariable String id) {
        log.info("Obteniendo sucursal con id: {}", id);
        Branch branch = this.branchService.findById(id);
        return ResponseEntity.ok(this.BranchMapperNew.toDTO(branch));
    }

    public ResponseEntity<BranchDTO> updateBranch(
            @Parameter(description = "ID de la sucursal") @PathVariable String id,
            @RequestBody BranchDTO branchDTO) {
        Branch branch = this.BranchMapperNew.toModel(branchDTO);
        Branch branchUpdated = this.branchService.updateBranch(id, branch);
        return ResponseEntity.ok(this.BranchMapperNew.toDTO(branchUpdated));
    }
    
    public ResponseEntity<BranchDTO> createBranchHoliday(
            @Parameter(description = "ID de la sucursal") @PathVariable String id,
            @RequestBody BranchHolidayDTO branchHolidayDTO) {
        BranchHoliday branchHoliday = this.BranchMapperNew.toModel(branchHolidayDTO);
        Branch branch = this.branchService.createBranchHoliday(id, branchHoliday);
        return ResponseEntity.ok(this.BranchMapperNew.toDTO(branch));
    }

    public ResponseEntity<BranchDTO> deleteBranchHoliday(
            @Parameter(description = "ID de la sucursal") @PathVariable String id,
            @Parameter(description = "Nombre del feriado") @PathVariable String name) {
        Branch branch = this.branchService.deleteBranchHoliday(id, name);
        return ResponseEntity.ok(this.BranchMapperNew.toDTO(branch));
    }




}
