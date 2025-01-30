package ec.edu.espe.banquito.repository;

import java.util.List;

import ec.edu.espe.banquito.model.Branch;

public interface BranchRepository {

    List<Branch> findById(String id);
    List<Branch> findAll();

}
