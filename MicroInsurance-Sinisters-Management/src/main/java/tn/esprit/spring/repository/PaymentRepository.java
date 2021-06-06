package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.dao.entities.Contract;
import tn.esprit.spring.dao.entities.Payment;
import tn.esprit.spring.dao.entities.Sinister;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer> {
	
	@Query(value = "SELECT * FROM payment p WHERE p.contract_contract_id= ?1 " , nativeQuery = true)
    List<Payment>findAllByContract(Contract c ) ; 

}
