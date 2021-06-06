package tn.esprit.spring.service;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.dao.entities.Contract;
import tn.esprit.spring.dao.entities.Payment;
import tn.esprit.spring.dao.entities.Sinister;
import tn.esprit.spring.repository.ContractRepository;
import tn.esprit.spring.repository.PaymentRepository;
@Transactional
@Service
public class PaymentServiceImpl implements IPaymentService {
	
	@Autowired
	PaymentRepository paymentRep ;
	
	@Autowired
	ContractRepository contractRep ;
	
	
	private static final org.apache.logging.log4j.Logger l = LogManager.getLogger(UserServiceImpl.class);


	@Override
	public List<Payment> retrieveAllPayment() {
		
		List<Payment> payments = (List<Payment>) paymentRep.findAll();
		for (Payment payment : payments){
			l.info("payments++:" + payment) ;
	}
		return payments;
	}

	@Override
	public Payment addPayment(Payment p) {
	
		
		p.setPaymentCode("aaa");
		p.setPaymentMethod("virement");
		
		paymentRep.save(p);
		System.out.println(p.getId()) ;
		
		return p;
	}

	@Override
	public void deletePayment(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Payment updatePayment(Payment p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Payment retrievePayment(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Payment> retrieveAllPayment(int contractId) {
		
		Contract contract = contractRep.findById(contractId).orElse(null);
		
		
		return paymentRep.findAllByContract(contract);
	}
	
	

}
