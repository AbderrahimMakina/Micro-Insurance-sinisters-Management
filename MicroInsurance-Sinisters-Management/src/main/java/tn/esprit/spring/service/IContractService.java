package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.dao.entities.Contract;

public interface IContractService {
	
	
	List<Contract> retrieveAllContracts();
	Contract addContract(Contract contract);
	 void deleteContract(int id);
	 Contract updateContract(Contract contract);
	 Contract retrieveContract(int id);
	
	

}
