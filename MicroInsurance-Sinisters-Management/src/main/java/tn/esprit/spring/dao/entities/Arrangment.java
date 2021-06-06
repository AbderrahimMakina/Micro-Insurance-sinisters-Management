package tn.esprit.spring.dao.entities;

import java.io.Serializable;
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

@Entity
public class Arrangment implements Serializable{
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="Arrangment_ID")
	private int id; // Clé primaire
	
	@Temporal (TemporalType.DATE)
	@Column(name="arrangmentDate")
	private Date arrangmentDate;
	
	@Column(name="expertCost")
	private int expertCost;
	
	@Column(name="franchise")
	private String franchise;
	
	@Column(name="indemnity")
	private String indemnity;
	
	@Column(name="arrangmentMethod")
	private String arrangmentMethod;
	
	@Column(name="amountArranged")
	private int amountArranged;
	
	@ManyToOne(cascade = CascadeType.ALL)
	Sinister sinister;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getArrangmentDate() {
		return arrangmentDate;
	}

	public void setArrangmentDate(Date arrangmentDate) {
		this.arrangmentDate = arrangmentDate;
	}

	public int getExpertCost() {
		return expertCost;
	}

	public void setExpertCost(int expertCost) {
		this.expertCost = expertCost;
	}

	public String getFranchise() {
		return franchise;
	}

	public void setFranchise(String franchise) {
		this.franchise = franchise;
	}

	public String getIndemnity() {
		return indemnity;
	}

	public void setIndemnity(String indemnity) {
		this.indemnity = indemnity;
	}

	public String getArrangmentMethod() {
		return arrangmentMethod;
	}

	public void setArrangmentMethod(String arrangmentMethod) {
		this.arrangmentMethod = arrangmentMethod;
	}

	public int getAmountArranged() {
		return amountArranged;
	}

	public void setAmountArranged(int amountArranged) {
		this.amountArranged = amountArranged;
	}

	public Sinister getSinister() {
		return sinister;
	}

	public void setSinister(Sinister sinister) {
		this.sinister = sinister;
	}
	
	
	
	
	
	
	
	
	
	

}
