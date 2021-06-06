package tn.esprit.spring.dao.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public  class User implements Serializable{
	
	public User(int id, String name, String firstname, int cin, String email, int phoneNumber, String adress,
			String login, String password, String status) {
		super();
		this.id = id;
		this.name = name;
		this.firstname = firstname;
		Cin = cin;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.adress = adress;
		this.login = login;
		this.password = password;
		this.status = status;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="User_ID")
	private int id; // Clé primaire
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="FirstNAME")
	private String firstname;
	
	@Column(name="CIN")
	private int Cin;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="PhoneNumber")
	private int phoneNumber;
	
	@Column(name="Adress")
	private String adress;
	
	@Column(name="Login")
	private String login;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="status")
	private String status;
	
	
	
	public User(){
		
	}
	



public User ( String name , String firstname ,int Cin ,String email, int phoneNumber ,String adress,String login ,String password,String status){
	
	
	
	this.name=name ;
	this.firstname=firstname ;
	this.Cin=Cin;
	this.email=email;
	this.phoneNumber=phoneNumber ;
	this.adress=adress ;
	this.login=login;
	this.password=password ;
	this.status=status;
}
	

public User(String name,String firstname){
	
	this.name=name ;
	this.firstname=firstname ;
	
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getFirstname() {
	return firstname;
}

public void setFirstname(String firstname) {
	this.firstname = firstname;
}

public int getCin() {
	return Cin;
}

public void setCin(int cin) {
	Cin = cin;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public int getPhoneNumber() {
	return phoneNumber;
}

public void setPhoneNumber(int phoneNumber) {
	this.phoneNumber = phoneNumber;
}

public String getAdress() {
	return adress;
}

public void setAdress(String adress) {
	this.adress = adress;
}

public String getLogin() {
	return login;
}

public void setLogin(String login) {
	this.login = login;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

}
