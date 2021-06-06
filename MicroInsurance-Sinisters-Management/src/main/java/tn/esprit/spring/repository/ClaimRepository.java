package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.dao.entities.Claims;


@Repository
public interface ClaimRepository extends CrudRepository<Claims, Integer>{

}
