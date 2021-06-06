package tn.esprit.spring.dao.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Provisioning implements Serializable{
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="Provisionning_ID")
	private int id; // Clé primaire
	
	
	@Temporal (TemporalType.DATE)
	private Date startDate;
	
	@Temporal (TemporalType.DATE)
	private Date endDate;
	
	@Column(name="provisioningType")
	private String provisioningType;
	
	
	
	

}
