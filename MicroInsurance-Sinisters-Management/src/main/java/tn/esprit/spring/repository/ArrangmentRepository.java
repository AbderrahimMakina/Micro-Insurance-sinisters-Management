package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.dao.entities.Arrangment;


@Repository
public interface ArrangmentRepository extends CrudRepository<Arrangment, Integer> {

}
