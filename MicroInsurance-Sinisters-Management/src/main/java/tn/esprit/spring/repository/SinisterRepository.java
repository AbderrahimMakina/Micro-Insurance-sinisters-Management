package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.dao.entities.Contract;
import tn.esprit.spring.dao.entities.Sinister;


@Repository
public interface SinisterRepository extends CrudRepository<Sinister, Integer> {
	
	
	Sinister findByDeclarationDateBetween(Date from,Date to );
	
	
	Sinister findBySinisterDateBetween(Date from,Date to );
	
	
	@Query(value = "SELECT * FROM sinister s WHERE s.contract_contract_id= ?1 " , nativeQuery = true)
    List<Sinister>findAllByContract(Contract c ) ; 
	

	
	List<Sinister> findByContract(Contract contract) ;
	
	
	
	 
	

}
