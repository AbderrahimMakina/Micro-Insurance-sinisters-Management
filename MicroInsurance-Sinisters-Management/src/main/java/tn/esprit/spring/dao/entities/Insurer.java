package tn.esprit.spring.dao.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "User_ID")
public class Insurer extends User  {
	
	
	
	public Insurer (){
		
	}
	
	public Insurer(String matricul, List<Contract> contracts) {
		super();
		this.matricul = matricul;
		this.contracts = contracts;
	}


	@Column(name="InsurerMatricule")
	private String matricul;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Contract> contracts;
	
	


	public String getMatricul() {
		return matricul;
	}


	public void setMatricul(String matricul) {
		this.matricul = matricul;
	}


	public List<Contract> getContracts() {
		return contracts;
	}


	public void setContracts(List<Contract> contracts) {
		this.contracts =  contracts;
	}
	
	

}
