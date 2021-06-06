package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.dao.entities.Register;

public interface IRegisterService {
	
	
	List<Register> retrieveAllRegisters();
	Register addRegister(Register register);
	 void deleteRegister(int id);
	 Register updateRegister(Register register);
	 Register retrieveRegister(int id);

}
