package tn.esprit.spring.service;

import java.util.List;


import tn.esprit.spring.dao.entities.Arrangment;

public interface IArrangmentService {
	
	List<Arrangment> retrieveAllArrangments();
	Arrangment addAmendment(Arrangment ar);
	 void deleteArrangment(int id);
	 Arrangment updateArrangment(Arrangment a);
	 Arrangment retrieveArrangment(int id);

}
