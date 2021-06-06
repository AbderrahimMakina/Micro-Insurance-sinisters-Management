package tn.esprit.spring.control;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.service.IContractService;
import tn.esprit.spring.service.IPaymentService;

@Scope(value = "session")
@Controller(value = "paymentController")
public class PaymentController {
	
	
	
	@Autowired
	IPaymentService paymentService ;
	
	@Autowired
	IContractService contractService ;
	

	private int id; // Clé primaire
	
	
	private String paymentCode;
	
	
	private Date paymentDate;
	
	
	private double amountPayed;
	
	
	private String paymentMethod;

}
