package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.dao.entities.Payment;

public interface IPaymentService {
	
	List<Payment> retrieveAllPayment();
	List<Payment> retrieveAllPayment(int contractId);

	
	Payment addPayment(Payment p);
	 void deletePayment(int id);
	 Payment updatePayment(Payment p);
	 Payment retrievePayment(int id);

}
