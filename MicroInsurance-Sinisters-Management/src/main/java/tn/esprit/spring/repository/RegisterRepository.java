package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.dao.entities.Register;


@Repository
public interface RegisterRepository extends CrudRepository<Register, Integer>{

}
