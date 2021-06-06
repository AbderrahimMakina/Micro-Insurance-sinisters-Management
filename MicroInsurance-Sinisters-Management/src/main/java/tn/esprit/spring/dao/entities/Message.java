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
public class Message implements Serializable{
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="Message_ID")
	private int id; // Clé primaire
	
	@Column(name="subject")
	private String subject;
	
	@Column(name="body")
	private String body;
	
	@Temporal (TemporalType.DATE)
	private Date sendingDate;
	
	@Column(name="status")
	private String status;
	
	
	
	

}
