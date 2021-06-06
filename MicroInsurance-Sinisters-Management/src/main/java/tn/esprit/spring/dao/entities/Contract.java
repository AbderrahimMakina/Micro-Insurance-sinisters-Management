package tn.esprit.spring.dao.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Contract implements Serializable {
	
	public Contract(int id, String contractType, Date signDate, Date deadLineDate, String police, double payedAmount,
			double reminingAmount, double netPremiuim, double totalPemium, String status, double discount, double tax,
			double comission, double reInsurancePart, String qRCode, int nombreSinistre, String natureRiskGarantee,
			double montantDeGarantee, Insurer insurer, Insured insured, Set<Payment> payments, Set<Product> products,
			Set<Amendment> amendments, List<Sinister> sinisters) {
		super();
		this.id = id;
		ContractType = contractType;
		this.signDate = signDate;
		this.deadLineDate = deadLineDate;
		Police = police;
		this.payedAmount = payedAmount;
		this.reminingAmount = reminingAmount;
		this.netPremiuim = netPremiuim;
		this.totalPemium = totalPemium;
		this.status = status;
		this.discount = discount;
		this.tax = tax;
		this.comission = comission;
		this.reInsurancePart = reInsurancePart;
		QRCode = qRCode;
		this.nombreSinistre = nombreSinistre;
		NatureRiskGarantee = natureRiskGarantee;
		this.montantDeGarantee = montantDeGarantee;
		this.insurer = insurer;
		this.insured = insured;
		this.payments = payments;
		this.products = products;
		this.amendments = amendments;
		this.sinisters = sinisters;
	}

	public Contract(){
		
	}
	
	public Contract(int id, String contractType, Date signDate, Date deadLineDate, String police, double payedAmount,
			double reminingAmount, double netPremiuim, double totalPemium, String status, double discount, double tax,
			double comission, double reInsurancePart, String qRCode, Insurer insurer, Insured insured,
			Set<Payment> payments, Set<Product> products, Set<Amendment> amendments, List<Sinister> sinisters) {
		super();
		this.id = id;
		ContractType = contractType;
		this.signDate = signDate;
		this.deadLineDate = deadLineDate;
		Police = police;
		this.payedAmount = payedAmount;
		this.reminingAmount = reminingAmount;
		this.netPremiuim = netPremiuim;
		this.totalPemium = totalPemium;
		this.status = status;
		this.discount = discount;
		this.tax = tax;
		this.comission = comission;
		this.reInsurancePart = reInsurancePart;
		QRCode = qRCode;
		this.insurer = insurer;
		this.insured = insured;
		this.payments = payments;
		this.products = products;
		this.amendments = amendments;
		this.sinisters = sinisters;
	}

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="Contract_ID")
	private int id; // Clé primaire
	
	@Column(name="ContractType",nullable=true)
	private String ContractType;
	
	@Temporal (TemporalType.DATE )
	private Date signDate;
	
	@Temporal (TemporalType.DATE)
	private Date deadLineDate;
	
	@Column(name="ContractPolice",nullable=true)
	private String Police;
	
	@Column(name="payedAmount",nullable=true)
	private double payedAmount;
	
	@Column(name="reminingAmount",nullable=true)
	private double reminingAmount;
	
	@Column(name="netPremium",nullable=true)
	private double netPremiuim;
	
	@Column(name="totalPemium",nullable=true)
	private double totalPemium;
	
	@Column(name="ContractStatus",nullable=true)
	private String status;
	
	@Column(name="discount",nullable=true)
	private double discount;
	
	@Column(name="tax",nullable=true)
	private double tax;
	
	@Column(name="comission",nullable=true)
	private double comission;
	
	@Column(name="reInsurancePart",nullable=true)
	private double reInsurancePart;
	
	@Column(name="QRCode")
	private String QRCode;
	
	@Column(name="nombreSinistre",nullable=true)
	private int nombreSinistre; 
	
	@Column(name="NatureRiskGarantee",nullable=true)
	private String NatureRiskGarantee;
	
	@Column(name="montantDeGarantee",nullable=true)
	private double montantDeGarantee;
	

	
	@ManyToOne
	Insurer insurer;
	
	@ManyToOne
	Insured insured;
	
	@OneToMany
	private Set<Payment> payments;
	
	
	
	@ManyToMany
	private Set<Product> products;
	
	
	@OneToMany
	private Set<Amendment> amendments;
	
	@OneToMany
	private List<Sinister> sinisters;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContractType() {
		return ContractType;
	}

	public void setContractType(String contractType) {
		ContractType = contractType;
	}

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public Date getDeadLineDate() {
		return deadLineDate;
	}

	public void setDeadLineDate(Date deadLineDate) {
		this.deadLineDate = deadLineDate;
	}

	public String getPolice() {
		return Police;
	}

	public void setPolice(String police) {
		Police = police;
	}

	public double getPayedAmount() {
		return payedAmount;
	}

	public void setPayedAmount(double payedAmount) {
		this.payedAmount = payedAmount;
	}

	public double getReminingAmount() {
		return reminingAmount;
	}

	public void setReminingAmount(double reminingAmount) {
		this.reminingAmount = reminingAmount;
	}

	public double getNetPremiuim() {
		return netPremiuim;
	}

	public void setNetPremiuim(double netPremiuim) {
		this.netPremiuim = netPremiuim;
	}

	public double getTotalPemium() {
		return totalPemium;
	}

	public void setTotalPemium(double totalPemium) {
		this.totalPemium = totalPemium;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getComission() {
		return comission;
	}

	public void setComission(double comission) {
		this.comission = comission;
	}

	public double getReInsurancePart() {
		return reInsurancePart;
	}

	public void setReInsurancePart(double reInsurancePart) {
		this.reInsurancePart = reInsurancePart;
	}

	public String getQRCode() {
		return QRCode;
	}

	public void setQRCode(String qRCode) {
		QRCode = qRCode;
	}

	public Insurer getInsurer() {
		return insurer;
	}

	public void setInsurer(Insurer insurer) {
		this.insurer = insurer;
	}

	public Insured getInsured() {
		return insured;
	}

	public void setInsured(Insured insured) {
		this.insured = insured;
	}

	public Set<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Set<Amendment> getAmendments() {
		return amendments;
	}

	public void setAmendments(Set<Amendment> amendments) {
		this.amendments = amendments;
	}

	public List<Sinister> getSinisters() {
		return sinisters;
	}

	public void setSinisters(List<Sinister> sinisters) {
		this.sinisters = sinisters;
	}

	public int getNombreSinistre() {
		return nombreSinistre;
	}

	public void setNombreSinistre(int nombreSinistre) {
		this.nombreSinistre = nombreSinistre;
	}

	public String getNatureRiskGarantee() {
		return NatureRiskGarantee;
	}

	public void setNatureRiskGarantee(String natureRiskGarantee) {
		NatureRiskGarantee = natureRiskGarantee;
	}

	public double getMontantDeGarantee() {
		return montantDeGarantee;
	}

	public void setMontantDeGarantee(double montantDeGarantee) {
		this.montantDeGarantee = montantDeGarantee;
	}

	@Override
	public String toString() {
		return "Contract [id=" + id + ", ContractType=" + ContractType + ", signDate=" + signDate + ", deadLineDate="
				+ deadLineDate + ", Police=" + Police + ", payedAmount=" + payedAmount + ", reminingAmount="
				+ reminingAmount + ", netPremiuim=" + netPremiuim + ", totalPemium=" + totalPemium + ", status="
				+ status + ", discount=" + discount + ", tax=" + tax + ", comission=" + comission + ", reInsurancePart="
				+ reInsurancePart + ", QRCode=" + QRCode + ", nombreSinistre=" + nombreSinistre
				+ ", NatureRiskGarantee=" + NatureRiskGarantee + ", montantDeGarantee=" + montantDeGarantee
				+ ", insurer=" + insurer + ", insured=" + insured + ", payments=" + payments + ", products=" + products
				+ ", amendments=" + amendments + ", sinisters=" + sinisters + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
