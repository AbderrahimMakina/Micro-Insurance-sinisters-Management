package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.dao.entities.Contract;
import tn.esprit.spring.dao.entities.Insured;
import tn.esprit.spring.dao.entities.Insurer;



@Repository
public interface ContractRepository extends CrudRepository<Contract, Integer> {
	
	
	@Query(value = "SELECT * FROM contract c WHERE c.insurer_user_id= ?1 " , nativeQuery = true)
	List<Contract> findAllByInsurer(Insurer insurer) ; 
	
	
	@Query(value = "SELECT * FROM contract c WHERE c.insured_user_id= ?1 " , nativeQuery = true)
	List<Contract> findAllByInsured(Insured insured) ; 
	
	
	Contract findByInsurer(Insurer insurer) ; 

}
