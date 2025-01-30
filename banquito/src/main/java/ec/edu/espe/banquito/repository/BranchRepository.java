package ec.edu.espe.banquito.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import ec.edu.espe.banquito.model.Branch;

public interface BranchRepository extends MongoRepository<Branch, String> {

    List<Branch> findAll();


}
