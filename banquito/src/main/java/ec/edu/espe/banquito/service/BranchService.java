package ec.edu.espe.banquito.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ec.edu.espe.banquito.exception.NotFoundException;
import ec.edu.espe.banquito.model.Branch;
import ec.edu.espe.banquito.model.BranchHoliday;
import ec.edu.espe.banquito.repository.BranchRepository;

@Service
public class BranchService {

    private final BranchRepository branchRepository;
    public static final String ENTITY_NAME = "Branch";

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    
    public List<Branch> findAll() {
        List<Branch> branches = branchRepository.findAll();        
        if (branches.isEmpty()) {
            throw new NotFoundException("No se encontraron sucursales", ENTITY_NAME);
        }
        return branches;
    }


    public Branch createBranch(Branch branch) {       
        return branchRepository.save(branch);
    }


    public Branch findByBranch (String id) {
        Branch branch = branchRepository.findById(id).orElse(null);
        if (branch == null) {
            throw new NotFoundException("No se encontró la sucursal con el id: " + id, ENTITY_NAME);
        }else {
            return branch;
            }
            
    }

    public Branch updateBranch(String id, Branch branch) {
        Branch branchToUpdate = branchRepository.findById(id).orElse(null);
        if (branchToUpdate == null) {
            throw new NotFoundException("No se encontró la sucursal con el id: " + id, ENTITY_NAME);
        }else {
            branchToUpdate.setPhoneNumber(branch.getPhoneNumber());
            branchToUpdate.setLastModifiedDate(branch.getLastModifiedDate());
            return branchRepository.save(branchToUpdate);            
        }
    }

    public Branch createBranchHoliday(String id, BranchHoliday branchHoliday) {
        Branch branch = branchRepository.findById(id).orElse(null);
        if (branch == null) {
            throw new NotFoundException("No se encontró la sucursal con el id: " + id, ENTITY_NAME);
        }else {
            branch.getBranchHolidays().add(branchHoliday);
            return branchRepository.save(branch);
        }
    }

    public Branch deleteBranchHoliday(String id, String holidayName) {
        Branch branch = branchRepository.findById(id).orElse(null);
        if (branch == null) {
            throw new NotFoundException("No se encontró la sucursal con el id: " + id, ENTITY_NAME);
        }else {
            List<BranchHoliday> branchHolidays = branch.getBranchHolidays();
            for (BranchHoliday branchHoliday : branchHolidays) {
                if (branchHoliday.getName().equals(holidayName)) {
                    branchHolidays.remove(branchHoliday);
                    return branchRepository.save(branch);
                }
            }
            throw new NotFoundException("No se encontró el feriado con el nombre: " + holidayName, ENTITY_NAME);
        }
    }

    public List<BranchHoliday> findAllBranchHolidays(String id) {
        Branch branch = branchRepository.findById(id).orElse(null);
        if (branch == null) {
            throw new NotFoundException("No se encontró la sucursal con el id: " + id, ENTITY_NAME);
        }else {
            return branch.getBranchHolidays();
        }
    }

    public boolean isHoliday(String id, String date) {
        Branch branch = branchRepository.findById(id).orElse(null);
        if (branch == null) {
            throw new NotFoundException("No se encontró la sucursal con el id: " + id, ENTITY_NAME);
        }else {
            List<BranchHoliday> branchHolidays = branch.getBranchHolidays();
            for (BranchHoliday branchHoliday : branchHolidays) {
                if (branchHoliday.getDate().toString().equals(date)) {
                    return true;
                }
            }
            return false;
        }
    }
}
