package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.dao.entities.Insured;

public interface IInsuredService {
	
	
	List<Insured> retrieveAllInsureds();
	Insured addInsured(Insured insured);
	 void deleteInsured(int id);
	 Insured updateInsured(Insured insured);
	 Insured retrieveInsured(int id);

}
