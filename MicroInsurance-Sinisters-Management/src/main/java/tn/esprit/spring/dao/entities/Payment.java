package tn.esprit.spring.dao.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Payment implements Serializable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="Payment_ID")
	private int id; // Clé primaire
	
	@Column(name="paymentCode")
	private String paymentCode;
	
	@Temporal (TemporalType.DATE)
	private Date paymentDate;
	
	@Column(name="amountPayed")
	private double amountPayed;
	
	@Column(name="paymentMethod")
	private String paymentMethod;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	Contract contract;
	
	public Payment(){
		
		
	}


	
	public Payment(int id ,String paymentCode , Date paymentDate , double amountPayed , String paymentMethod){
		
		this.id=id ; 
		this.paymentCode = paymentCode ;
		this.paymentDate = paymentDate ;
		this.amountPayed = amountPayed ;
		this.paymentMethod = paymentMethod ;
		
	}
	
	
	public Payment( Date paymentDate , double amountPayed ){
		this.paymentDate = paymentDate ;
		this.amountPayed = amountPayed ;
	
	}
	
	public Payment( double amountPayed ){
		
		this.amountPayed = amountPayed ;

	}
	
	



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getPaymentCode() {
		return paymentCode;
	}



	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}



	public Date getPaymentDate() {
		return paymentDate;
	}



	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}



	public double getAmountPayed() {
		return amountPayed;
	}



	public void setAmountPayed(double amountPayed) {
		this.amountPayed = amountPayed;
	}



	public String getPaymentMethod() {
		return paymentMethod;
	}



	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}



	public Contract getContract() {
		return contract;
	}



	public void setContract(Contract contract) {
		this.contract = contract;
	}



	@Override
	public String toString() {
		return "Payment [id=" + id + ", paymentCode=" + paymentCode + ", paymentDate=" + paymentDate + ", amountPayed="
				+ amountPayed + ", paymentMethod=" + paymentMethod + ", contract=" + contract + "]";
	}
	
	
	
	
	

}
