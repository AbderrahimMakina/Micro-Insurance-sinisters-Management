package tn.esprit.spring.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.dao.entities.User;
import tn.esprit.spring.service.IUserService;

@RestController
public class UserRestController {
	
	@Autowired
	IUserService IuserService ; 
	
	// http://localhost:8081/SpringMVC/servlet/retrieve-all-Users
 @GetMapping("/retrieve-all-Users")
 @ResponseBody
	public List<User> getUsers(){
		
		List<User> list = IuserService.retrieveAllUsers();
		return list;
		
		
	}
 
 

}
