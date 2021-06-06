package tn.esprit.spring.dao.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;




@Entity
@PrimaryKeyJoinColumn(name = "User_ID")
public class Insured extends User  {
	

	public Insured(int id, String name, String firstname, int cin, String email, int phoneNumber, String adress,
			String login, String password, String status, Date recordingDate, String healthStatus, String civilStatus,
			int age, List<Contract> contracts, Set<Claims> claims, String sex, String profession, String diseases,
			String sinistralité, String statut) {
		super(id, name, firstname, cin, email, phoneNumber, adress, login, password, status);
		this.recordingDate = recordingDate;
		this.healthStatus = healthStatus;
		this.civilStatus = civilStatus;
		this.age = age;
		this.contracts = contracts;
		this.claims = claims;
		this.sex = sex;
		this.profession = profession;
		this.diseases = diseases;
		this.sinistralité = sinistralité;
		this.statut = statut;
	}
	public Insured(){
		
	}
	public Insured(int id, String name, String firstname, int cin, String email, int phoneNumber, String adress,
			String login, String password, String status, Date recordingDate, String healthStatus, String civilStatus,
			int age, List<Contract> contracts, Set<Claims> claims, String sex, String profession, String diseases) {
		super(id, name, firstname, cin, email, phoneNumber, adress, login, password, status);
		this.recordingDate = recordingDate;
		this.healthStatus = healthStatus;
		this.civilStatus = civilStatus;
		this.age = age;
		this.contracts = contracts;
		this.claims = claims;
		this.sex = sex;
		this.profession = profession;
		this.diseases = diseases;
	}

	public Insured(Date recordingDate, String healthStatus, String civilStatus, int age, List<Contract> contracts,
			Set<Claims> claims, String sex, String profession, String diseases) {
		super();
		this.recordingDate = recordingDate;
		this.healthStatus = healthStatus;
		this.civilStatus = civilStatus;
		this.age = age;
		this.contracts = contracts;
		this.claims = claims;
		this.sex = sex;
		this.profession = profession;
		this.diseases = diseases;
	}

	@Temporal (TemporalType.DATE)
	private Date recordingDate;
	
	@Column(name="Insured_health")
	private String healthStatus;
	
	@Column(name="Insured_CivilStatus")
	private String civilStatus;
	
	@Column(name="Insured_Age")
	private int age;
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Contract> contracts;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Claims> claims;
	
	@Column(name="Insured_Sex")
	private String sex;
	
	@Column(name="Insured_profession")
	private String profession;
	
	@Column(name="Insured_diseases")
	private String diseases;
	
	@Column(name="sinistralité")
	private String sinistralité;
	
	@Column(name="statut")
	private String statut; //platine,or,argent,bronze

	public Date getRecordingDate() {
		return recordingDate;
	}

	public void setRecordingDate(Date recordingDate) {
		this.recordingDate = recordingDate;
	}

	public String getHealthStatus() {
		return healthStatus;
	}

	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}

	public String getCivilStatus() {
		return civilStatus;
	}

	public void setCivilStatus(String civilStatus) {
		this.civilStatus = civilStatus;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}

	public Set<Claims> getClaims() {
		return claims;
	}

	public void setClaims(Set<Claims> claims) {
		this.claims = claims;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getDiseases() {
		return diseases;
	}

	public void setDiseases(String diseases) {
		this.diseases = diseases;
	}

	@Override
	public String toString() {
		return "Insured [recordingDate=" + recordingDate + ", healthStatus=" + healthStatus + ", civilStatus="
				+ civilStatus + ", age=" + age + ", contracts=" + contracts + ", claims=" + claims + ", sex=" + sex
				+ ", profession=" + profession + ", diseases=" + diseases + ", sinistralité=" + sinistralité
				+ ", statut=" + statut + "]";
	}
	public String getSinistralité() {
		return sinistralité;
	}
	public void setSinistralité(String sinistralité) {
		this.sinistralité = sinistralité;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	
	
	
	



}
