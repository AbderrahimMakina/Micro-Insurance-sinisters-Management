package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.dao.entities.User;


@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	
	public User findUserByLoginAndPassword(String login, String password);

}
