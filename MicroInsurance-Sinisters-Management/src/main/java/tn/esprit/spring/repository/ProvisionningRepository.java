package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.dao.entities.Provisioning;


@Repository
public interface ProvisionningRepository extends CrudRepository<Provisioning, Integer> {

}
