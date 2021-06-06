package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.dao.entities.Product;


@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{

}
