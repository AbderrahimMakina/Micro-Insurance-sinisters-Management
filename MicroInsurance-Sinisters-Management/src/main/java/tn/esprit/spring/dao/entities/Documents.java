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

import org.primefaces.model.file.UploadedFile;

import com.lowagie.text.Rectangle;

@Entity
public class Documents implements Serializable{
	
	public Documents(){
		
	}
	
	public Documents(int id, String documentName, Date receptionDate, String label, Sinister sinister) {
		super();
		this.id = id;
		DocumentName = documentName;
		this.receptionDate = receptionDate;
		this.label = label;
		this.sinister = sinister;
	}
	
	public Documents( String documentName,  String label, Sinister sinister) {
		super();
		DocumentName = documentName;
		this.label = label;
		this.sinister = sinister;
	}
	
	
	public Documents( String documentName,  String label) {
		super();
		DocumentName = documentName;
		this.label = label;
	}
	
	public Documents( String documentName,  String label, String content) {
		super();
		DocumentName = documentName;
		this.label = label;
		this.content=content;
	}



	



	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="Document_ID")
	private int id; // Clé primaire
	
	
	
	@Column(name="DocumentName")
	private String DocumentName;
	
	@Temporal (TemporalType.DATE)
	private Date receptionDate;
	
	
	@Column(name="label")
	private String label;
	
	@Column(name="content")
	private String content;
	
	

	
	@ManyToOne(cascade = CascadeType.ALL)
	Sinister sinister;



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getDocumentName() {
		return DocumentName;
	}



	public void setDocumentName(String documentName) {
		DocumentName = documentName;
	}



	public Date getReceptionDate() {
		return receptionDate;
	}



	public void setReceptionDate(Date receptionDate) {
		this.receptionDate = receptionDate;
	}



	public String getLabel() {
		return label;
	}



	public void setLabel(String label) {
		this.label = label;
	}



	public Sinister getSinister() {
		return sinister;
	}



	public void setSinister(Sinister sinister) {
		this.sinister = sinister;
	}



	@Override
	public String toString() {
		return "Document [id=" + id + ", DocumentName=" + DocumentName + ", receptionDate=" + receptionDate + ", label="
				+ label + ", sinister=" + sinister + "]";
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}



}
