package tn.esprit.spring.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.service.IInsuredService;

@RestController
public class InsuredController {
	
	@Autowired
	IInsuredService IinsuredService ; 

}
