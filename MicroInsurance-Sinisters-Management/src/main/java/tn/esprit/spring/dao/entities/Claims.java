package tn.esprit.spring.dao.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Claims implements Serializable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="Claims_ID")
	private int id; // Clé primaire
	
	@Column(name="claimsDescription")
	private String description;
	
	@Column(name="claimsStatus")
	private int status;
	
	@ManyToOne(cascade = CascadeType.ALL)
	Insured insured;

}
