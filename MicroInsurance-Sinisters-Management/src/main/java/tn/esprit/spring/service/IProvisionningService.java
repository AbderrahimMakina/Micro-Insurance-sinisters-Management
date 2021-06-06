package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.dao.entities.Provisioning;



public interface IProvisionningService {
	
	List<Provisioning> retrieveAllProvisionings();
	Provisioning addInsurer(Provisioning pr);
	 void deleteProvisioning(int id);
	 Provisioning updateProvisioning(Provisioning pr);
	 Provisioning retrieveProvisioning(int id);

}
