package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.dao.entities.Claims;



public interface IclaimService {
	
	List<Claims> retrieveAllClaims();
	Claims addAmendment(Claims c);
	 void deleteClaims(int id);
	 Claims updateClaims(Claims c);
	 Claims retrieveClaims(int id);

}
