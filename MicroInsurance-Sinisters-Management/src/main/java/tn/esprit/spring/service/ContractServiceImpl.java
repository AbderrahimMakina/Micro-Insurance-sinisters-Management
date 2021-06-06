package tn.esprit.spring.service;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.dao.entities.Contract;
import tn.esprit.spring.repository.ContractRepository;

@Transactional
@Service
public class ContractServiceImpl implements IContractService {
	
	
	@Autowired
	ContractRepository contractRep;

	@Override
	public List<Contract> retrieveAllContracts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contract addContract(Contract contract) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteContract(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Contract updateContract(Contract contract) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contract retrieveContract(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
