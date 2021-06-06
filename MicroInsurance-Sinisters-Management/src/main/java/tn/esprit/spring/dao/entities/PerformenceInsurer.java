package tn.esprit.spring.dao.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class PerformenceInsurer implements Serializable{
	
	public PerformenceInsurer(){
		
	}
	
	public PerformenceInsurer(int idPerformence, Insurer insurer, double ratioChargeEngagé, double ratioSinSurvenu,
			double rapiditéReglementSinister, double ratioRejetDemIndemn, double tauxFidélisation,
			double ratioDeCroissance, double resultat) {
		super();
		this.idPerformence = idPerformence;
		this.insurer = insurer;
		RatioChargeEngagé = ratioChargeEngagé;
		RatioSinSurvenu = ratioSinSurvenu;
		RapiditéReglementSinister = rapiditéReglementSinister;
		RatioRejetDemIndemn = ratioRejetDemIndemn;
		TauxFidélisation = tauxFidélisation;
		RatioDeCroissance = ratioDeCroissance;
		this.resultat = resultat;
	}


	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idPerformence")
	private int idPerformence; // Clé primaire
	   
	
	@OneToOne
	private Insurer insurer ;
	
	@Column(name="RatioChargeEngagé") 
	private double RatioChargeEngagé;  //ratio des charges engagées n= Charges engagées n / Primes acquises n (period n )
	
	@Column(name="RatioSinSurvenu")
	private double RatioSinSurvenu; //Ratio des sinistres survenus n =somme charge des  Sinistres survenus a une period n/ Primes acquises
	
	@Column(name="RapiditéReglementSinister")
	private double RapiditéReglementSinister; // duré entre la date dans laquelle les sinistres ont été signalés et la date d' indemnisation ou rejet 
	
	@Column(name="RatioRejetDemIndemn")
	private double RatioRejetDemIndemn; //Ratio de rejet des demandes d’indemnisation = Nbre de demandes rejetées / Nbre de demandes dans l’échantillon
	
	@Column(name="TauxFidélisation ") //taux de renouvellement des contrat apres fin de contrat 
	private double TauxFidélisation ;
	
	@Column(name="RatioDeCroissance ") //RatioDeCroissance n= (Nombre d’assurés n – Nombre d’assurés n-1) / Nombre d’assurés n-1 
	private double RatioDeCroissance ;
	
	@Temporal (TemporalType.DATE)
	private Date dateCalcul;
	
	
	@Column(name="resultat ") // prime - charge sinistre 
	private double resultat ;


	public int getIdPerformence() {
		return idPerformence;
	}


	public void setIdPerformence(int idPerformence) {
		this.idPerformence = idPerformence;
	}


	public Insurer getInsurer() {
		return insurer;
	}


	public void setInsurer(Insurer insurer) {
		this.insurer = insurer;
	}


	public double getRatioChargeEngagé() {
		return RatioChargeEngagé;
	}


	public void setRatioChargeEngagé(double ratioChargeEngagé) {
		RatioChargeEngagé = ratioChargeEngagé;
	}


	public double getRatioSinSurvenu() {
		return RatioSinSurvenu;
	}


	public void setRatioSinSurvenu(double ratioSinSurvenu) {
		RatioSinSurvenu = ratioSinSurvenu;
	}


	public double getRapiditéReglementSinister() {
		return RapiditéReglementSinister;
	}


	public void setRapiditéReglementSinister(double rapiditéReglementSinister) {
		RapiditéReglementSinister = rapiditéReglementSinister;
	}


	public double getRatioRejetDemIndemn() {
		return RatioRejetDemIndemn;
	}


	public void setRatioRejetDemIndemn(double ratioRejetDemIndemn) {
		RatioRejetDemIndemn = ratioRejetDemIndemn;
	}


	public double getTauxFidélisation() {
		return TauxFidélisation;
	}


	public void setTauxFidélisation(double tauxFidélisation) {
		TauxFidélisation = tauxFidélisation;
	}


	public double getRatioDeCroissance() {
		return RatioDeCroissance;
	}


	public void setRatioDeCroissance(double ratioDeCroissance) {
		RatioDeCroissance = ratioDeCroissance;
	}


	public double getResultat() {
		return resultat;
	}


	public void setResultat(double resultat) {
		this.resultat = resultat;
	}

	@Override
	public String toString() {
		return "PerformenceInsurer [idPerformence=" + idPerformence + ", insurer=" + insurer + ", RatioChargeEngagé="
				+ RatioChargeEngagé + ", RatioSinSurvenu=" + RatioSinSurvenu + ", RapiditéReglementSinister="
				+ RapiditéReglementSinister + ", RatioRejetDemIndemn=" + RatioRejetDemIndemn + ", TauxFidélisation="
				+ TauxFidélisation + ", RatioDeCroissance=" + RatioDeCroissance + ", resultat=" + resultat + "]";
	}
	
	
	
	
	
	
	
	
	
	
	 
	//Ratio de croissance 07-09 = (Nombre d’assurés 2009 – Nombre d’assurés 2006) / Nombre d’assurés 2006
	
	
	
	

}
