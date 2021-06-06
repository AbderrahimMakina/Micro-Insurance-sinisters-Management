package tn.esprit.spring.dao.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Sinister implements Serializable{
	
	
	public Sinister(int id, Date declarationDate, Date sinisterDate, Date indemnisationDate, int delaiDeclaration,
			String sinisterPlace, String sinisterDescription, String sinisterStatus, String sinisterType,
			String causeRejet, Set<Arrangment> arrangments, Contract contract, List<Documents> documents, double chargeSinister) {
		super();
		this.id = id;
		this.declarationDate = declarationDate;
		this.sinisterDate = sinisterDate;
		IndemnisationDate = indemnisationDate;
		this.delaiDeclaration = delaiDeclaration;
		this.sinisterPlace = sinisterPlace;
		this.sinisterDescription = sinisterDescription;
		this.sinisterStatus = sinisterStatus;
		this.sinisterType = sinisterType;
		this.causeRejet = causeRejet;
		this.arrangments = arrangments;
		this.contract = contract;
		this.documents = documents;
		this.chargeSinister=chargeSinister;
	}




// le montant du dommage a ajouter
	
	public Sinister(int id, Date declarationDate, Date sinisterDate, String sinisterPlace,
			String sinisterDescription, String sinisterStatus, String sinisterType, Set<Arrangment> arrangments,
			Contract contract, List<Documents> documents) {
		super();
		this.id = id;
		this.declarationDate = declarationDate;
		this.sinisterDate = sinisterDate;
		this.sinisterPlace = sinisterPlace;
		this.sinisterDescription = sinisterDescription;
		this.sinisterStatus = sinisterStatus;
		this.sinisterType = sinisterType;
		this.arrangments = arrangments;
		this.contract = contract;
		this.documents = documents;
	}




	public Sinister() {
		
	}
	



	public Sinister(int id, Date declarationDate, Date sinisterDate, String sinisterPlace, 
			String sinisterDescription, String sinisterStatus, String sinisterType, Set<Arrangment> arrangments,
			Contract contract) {
		super();
		this.id = id;
		this.declarationDate = declarationDate;
		this.sinisterDate = sinisterDate;
		this.sinisterPlace = sinisterPlace;
		this.sinisterDescription = sinisterDescription;
		this.sinisterStatus = sinisterStatus;
		this.sinisterType = sinisterType;
		this.arrangments = arrangments;
		this.contract = contract;
	}
	
	public Sinister( Date sinisterDate, String sinisterPlace, String sinisterDescription,  String sinisterType ) {
		super();
		
	
		this.sinisterDate = sinisterDate;
		this.sinisterPlace = sinisterPlace;
		this.sinisterDescription = sinisterDescription;
		
		this.sinisterType = sinisterType;		
	}
	public Sinister( Date sinisterDate, String sinisterPlace, String sinisterDescription,  String sinisterType, Contract contract) {
		super();
		
	
		this.sinisterDate = sinisterDate;
		this.sinisterPlace = sinisterPlace;
		this.sinisterDescription = sinisterDescription;
		
		this.sinisterType = sinisterType;
	this.contract = contract ; 
		
	}


	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="Sinister_ID")
	private int id; // Clé primaire
	
	@Autowired(required = false)
	@Temporal (TemporalType.DATE )
	private Date declarationDate;
	
	@Autowired(required = false)
	@Temporal (TemporalType.DATE)
	private Date sinisterDate;
	
	@Autowired(required = false)
	@Temporal (TemporalType.DATE)
	private Date IndemnisationDate;
	
	@Autowired(required = false)
	@Column(name="delaiDeclaration",nullable=true)
	private int delaiDeclaration; // 
	
	@Autowired(required = false)
	@Column(name="sinisterPlace",nullable=true)
	private String sinisterPlace;
	
	
	@Autowired(required = false)
	@Column(name="chargeSinister",nullable=true)
	private double chargeSinister;
	
	@Autowired(required = false)
	@Column(name="sinisterDescription",nullable=true)
	private String sinisterDescription;
	
	@Column(name="sinisterStatus",nullable=true)
	private String sinisterStatus; //rejeté  payé in process
	
	@Column(name="sinisterType",nullable=true)
	private String sinisterType;
	
	@Column(name="causeRejet",nullable=true)
	private String causeRejet;
	
	@Column(name="isFraud",nullable=true)
	private String isFraud;
	

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Arrangment> arrangments;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	Contract contract;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	private List<Documents> documents;


	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public Date getDeclarationDate() {
		return declarationDate;
	}




	public void setDeclarationDate(Date declarationDate) {
		this.declarationDate = declarationDate;
	}




	public Date getSinisterDate() {
		return sinisterDate;
	}




	public void setSinisterDate(Date sinisterDate) {
		this.sinisterDate = sinisterDate;
	}




	public Date getIndemnisationDate() {
		return IndemnisationDate;
	}




	public void setIndemnisationDate(Date indemnisationDate) {
		IndemnisationDate = indemnisationDate;
	}




	public int getDelaiDeclaration() {
		return delaiDeclaration;
	}




	public void setDelaiDeclaration(int delaiDeclaration) {
		this.delaiDeclaration = delaiDeclaration;
	}




	public String getSinisterPlace() {
		return sinisterPlace;
	}




	public void setSinisterPlace(String sinisterPlace) {
		this.sinisterPlace = sinisterPlace;
	}




	public double getChargeSinister() {
		return chargeSinister;
	}




	public void setChargeSinister(double chargeSinister) {
		this.chargeSinister = chargeSinister;
	}




	public String getSinisterDescription() {
		return sinisterDescription;
	}




	public void setSinisterDescription(String sinisterDescription) {
		this.sinisterDescription = sinisterDescription;
	}




	public String getSinisterStatus() {
		return sinisterStatus;
	}




	public void setSinisterStatus(String sinisterStatus) {
		this.sinisterStatus = sinisterStatus;
	}




	public String getSinisterType() {
		return sinisterType;
	}




	public void setSinisterType(String sinisterType) {
		this.sinisterType = sinisterType;
	}




	public String getCauseRejet() {
		return causeRejet;
	}




	public void setCauseRejet(String causeRejet) {
		this.causeRejet = causeRejet;
	}




	public Set<Arrangment> getArrangments() {
		return arrangments;
	}




	public void setArrangments(Set<Arrangment> arrangments) {
		this.arrangments = arrangments;
	}




	public Contract getContract() {
		return contract;
	}




	public void setContract(Contract contract) {
		this.contract = contract;
	}




	public List<Documents> getDocuments() {
		return documents;
	}




	public void setDocuments(List<Documents> documents) {
		this.documents = documents;
	}




	@Override
	public String toString() {
		return "Sinister [id=" + id + ", declarationDate=" + declarationDate + ", sinisterDate=" + sinisterDate
				+ ", IndemnisationDate=" + IndemnisationDate + ", delaiDeclaration=" + delaiDeclaration
				+ ", sinisterPlace=" + sinisterPlace + ", chargeSinister=" + chargeSinister + ", sinisterDescription="
				+ sinisterDescription + ", sinisterStatus=" + sinisterStatus + ", sinisterType=" + sinisterType
				+ ", causeRejet=" + causeRejet + ", arrangments=" + arrangments + ", contract=" + contract
				+ ", documents=" + documents + "]";
	}




	public String getIsFraud() {
		return isFraud;
	}




	public void setIsFraud(String isFraud) {
		this.isFraud = isFraud;
	}
	


	
	
	
	
	
	
	
	
	
	

}
