package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.dao.entities.Register;
import tn.esprit.spring.repository.RegisterRepository;



@Service
public class RegisterServiceImpl implements IRegisterService{
	
	
	@Autowired
	RegisterRepository registerRep;
	
	

	@Override
	public List<Register> retrieveAllRegisters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Register addRegister(Register register) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRegister(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Register updateRegister(Register register) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Register retrieveRegister(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
