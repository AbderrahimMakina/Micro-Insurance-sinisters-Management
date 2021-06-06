package tn.esprit.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.dao.entities.Amendment;
import tn.esprit.spring.repository.AmendmentRepository;

@Transactional
@Service
public class AmendmentServiceImpl implements IAmendmentService{
	
	
	@Autowired
	AmendmentRepository amendmentRep;

	@Override
	public List<Amendment> retrieveAllAmendments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Amendment addAmendment(Amendment a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAmendment(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Amendment updateAmendment(Amendment a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Amendment retrieveAmendment(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
