package tn.esprit.spring.service;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.dao.entities.Provisioning;
import tn.esprit.spring.repository.ProvisionningRepository;

@Transactional
@Service
public class ProvisionningServiceImpl implements IProvisionningService {
	
	
	@Autowired
	ProvisionningRepository provisionningRep;

	@Override
	public List<Provisioning> retrieveAllProvisionings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Provisioning addInsurer(Provisioning pr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProvisioning(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Provisioning updateProvisioning(Provisioning pr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Provisioning retrieveProvisioning(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
