package tn.esprit.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.dao.entities.User;
import tn.esprit.spring.repository.UserRepository;

@Transactional
@Service
public class UserServiceImpl implements IUserService{
	

	private static final org.apache.logging.log4j.Logger l = LogManager.getLogger(UserServiceImpl.class);

	
	
	@Autowired
	UserRepository userRep;

	@Override
	public List<User> retrieveAllUsers() {
		List<User> users = (List<User>) userRep.findAll();
		for (User user : users){
			l.info("user++:" + user);
		}
		
		return users;
	}

	@Override
	public User addUser(User u) {
		userRep.save(u) ;	
		return u;
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User updateUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User retrieveUser(int id) {
		return userRep.findById(id).get();
	}

	@Override
	public User authenticate(String login, String password) {
		return userRep.findUserByLoginAndPassword(login, password);
	}

	
	
	
	
	
	
}