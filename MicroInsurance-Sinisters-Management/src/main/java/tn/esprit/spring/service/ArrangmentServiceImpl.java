package tn.esprit.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.dao.entities.Arrangment;
import tn.esprit.spring.repository.ArrangmentRepository;

@Transactional
@Service
public class ArrangmentServiceImpl implements IArrangmentService{
	
	
	@Autowired
	ArrangmentRepository arrangmentRep;

	@Override
	public List<Arrangment> retrieveAllArrangments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Arrangment addAmendment(Arrangment ar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteArrangment(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Arrangment updateArrangment(Arrangment a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Arrangment retrieveArrangment(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
