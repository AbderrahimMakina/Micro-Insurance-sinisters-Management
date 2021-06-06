package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.dao.entities.Insurer;


@Repository
public interface InsurerRepository extends CrudRepository<Insurer, Integer> {
	
	

}
