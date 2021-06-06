package tn.esprit.spring.service;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.dao.entities.Insured;
import tn.esprit.spring.repository.InsuredRepository;

@Transactional
@Service
public class InsuredServiceImpl implements IInsuredService {
	
	
	@Autowired
	InsuredRepository insuredRep;

	@Override
	public List<Insured> retrieveAllInsureds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Insured addInsured(Insured insured) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInsured(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Insured updateInsured(Insured insured) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Insured retrieveInsured(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
