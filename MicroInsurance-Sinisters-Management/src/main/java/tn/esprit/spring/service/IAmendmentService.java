package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.dao.entities.Amendment;

public interface IAmendmentService {
	
	List<Amendment> retrieveAllAmendments();
	Amendment addAmendment(Amendment a);
	 void deleteAmendment(int id);
	 Amendment updateAmendment(Amendment a);
	 Amendment retrieveAmendment(int id);

}
