package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.dao.entities.Contract;
import tn.esprit.spring.dao.entities.Insured;
import tn.esprit.spring.dao.entities.Sinister;



@Repository
public interface InsuredRepository extends CrudRepository<Insured, Integer> {
	/*
	@Query(value = "SELECT * FROM insured ins WHERE ins.contract_contract_id= ?1 " , nativeQuery = true)
    List<Insured>findAllByContract(Contract c ) ;
*/
}
