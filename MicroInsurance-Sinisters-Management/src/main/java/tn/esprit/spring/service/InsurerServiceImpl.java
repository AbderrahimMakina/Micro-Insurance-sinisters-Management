package tn.esprit.spring.service;




import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.dao.entities.Insurer;
import tn.esprit.spring.repository.InsurerRepository;


@Transactional
@Service
public class InsurerServiceImpl implements IInsurerService{
	
	@Autowired
	InsurerRepository insurerRep;

	@Override
	public List<Insurer> retrieveAllInsurers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Insurer addInsurer(Insurer insurer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInsurer(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Insurer updateInsurer(Insurer insurer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Insurer retrieveInsurer(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
