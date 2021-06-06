package tn.esprit.spring.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import com.stripe.model.Charge;

import tn.esprit.spring.dao.entities.Contract;
import tn.esprit.spring.dao.entities.Insured;
import tn.esprit.spring.dao.entities.Sinister;

public interface ISinisterService {
	
	List<Sinister> retrieveAllSinisters();
	
	public Sinister addSinister(Sinister sinister ) ;
	
	 String deleteSinister(int id);
	 
	 String updateSinister(Sinister sinister , int idSinister);
	
	 Sinister retrieveSinister(int id);
	 
	 List<Sinister> retrieveSinisterByContract(int contractId);
	 
	 List<Sinister> retrieveSinisterByInsurer(int insurerId);
	 List<Sinister> retrieveSinisterByInsured(int insuredId);

	 Sinister retrieveSinisterByDeclarationDate(Date from , Date to );
	 
	 Sinister retrieveSinisterBySinisterDate(Date from , Date to );
	 
	 Sinister declarerSinistre(Sinister sinister , int contractId  ) ; //, int insuredId 
	
	 
	 Sinister affecterContratSinister(int idContrat,int idSinister );
	 
	 String traiterSinistre(int siniterId ) throws Exception;
	 
	 
	 String CalculRapiditéReglementSinistre( int sinisterId) ;
	 
	 String CalculRatioSinSurvenu( int insurerId) ;
	 
	 String CalculRatioRejetDemIndemn( int sinisterId) ;
	 
	 String CalculCoutMoyen(int insurerId) ;
	 
	 
	 String verifierDelai(int sinisterId) ;
	 
	 String envoyerMailToInsurer(int insurerId) throws MessagingException ;
	 
	 String envoyerMailToInsured(int insuredId);
	 
	 String envoyerMailMetoToInsured(int insuredId);
	 
	 String suivreSinistre(int sinisterId);
	 
	 String verifierDocuments(int sinisterId) throws Exception ;

     String calculSinistraliéPortfeuille(int insurerId) ;
     
    String predictionFraud(int siniterId) ;
    
    String meteoScrapping() throws IOException;
    
    double verifierFactureIndemnisation(int sinisterId ) throws IOException ;
    
    String payerSinistre(int sinisterId);
    
    
    
    
   
    
    
   // Charge  chargeNewCard(String token, double amount) throws Exception ;
	 
	  
	  
	 
	 

}
