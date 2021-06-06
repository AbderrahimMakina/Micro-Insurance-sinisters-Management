package tn.esprit.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.dao.entities.Claims;
import tn.esprit.spring.repository.ClaimRepository;

@Transactional
@Service
public class ClaimServiceImpl implements IclaimService{
	
	@Autowired
	ClaimRepository claimRep;

	@Override
	public List<Claims> retrieveAllClaims() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Claims addAmendment(Claims c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteClaims(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Claims updateClaims(Claims c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Claims retrieveClaims(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
