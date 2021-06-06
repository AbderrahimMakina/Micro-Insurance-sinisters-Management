package tn.esprit.spring.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.service.IRegisterService;

@RestController
public class RegisterController {
	
	@Autowired
	IRegisterService IregisterService ; 

}
