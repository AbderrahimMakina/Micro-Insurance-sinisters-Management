package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.dao.entities.Documents;


@Repository
public interface DocumentRepository extends CrudRepository<Documents, Integer>{

}
