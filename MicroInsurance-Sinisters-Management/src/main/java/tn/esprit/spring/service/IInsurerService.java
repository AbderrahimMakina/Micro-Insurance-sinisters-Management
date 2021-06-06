package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.dao.entities.Insurer;

public interface IInsurerService {
	
	List<Insurer> retrieveAllInsurers();
	Insurer addInsurer(Insurer insurer);
	 void deleteInsurer(int id);
	 Insurer updateInsurer(Insurer insurer);
	 Insurer retrieveInsurer(int id);

}
