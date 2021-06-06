package tn.esprit.spring.service;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import tn.esprit.spring.dao.entities.Contract;
import tn.esprit.spring.dao.entities.Insured;
import tn.esprit.spring.dao.entities.Insurer;
import tn.esprit.spring.dao.entities.Payment;
import tn.esprit.spring.dao.entities.Sinister;
import tn.esprit.spring.repository.ContractRepository;
import tn.esprit.spring.repository.InsuredRepository;
import tn.esprit.spring.repository.InsurerRepository;
import tn.esprit.spring.repository.PaymentRepository;
import tn.esprit.spring.repository.SinisterRepository;


@Transactional
@Service
public class SinisterServiceImpl implements ISinisterService {
	
	 
	@Autowired
    public JavaMailSender emailSender;
	
	@Autowired
	SinisterRepository sinisterRep;
	
	
	
	
	@Autowired
	InsuredRepository insuredRep ;
	
	@Autowired
	ContractRepository contractRep ;
	
	@Autowired
	InsurerRepository insurerRep ;
	
	@Autowired
	PaymentRepository paymentRep ;
	
	@Autowired
	IPaymentService paymentService ;
	
	
	
	
	
	
	
	
	
	private static final org.apache.logging.log4j.Logger l = LogManager.getLogger(UserServiceImpl.class);

	@Override
	public List<Sinister> retrieveAllSinisters() {
		List<Sinister> sinisters = (List<Sinister>) sinisterRep.findAll();
		
		for (Sinister sinister : sinisters){
			l.info("sinister++:" + sinister) ;
	}
		return sinisters;
	}

	@Override
	public Sinister addSinister(Sinister sinister ){
		
		
		sinister.setDeclarationDate(Calendar.getInstance().getTime());
		sinister.setDelaiDeclaration(7);
		sinister.setSinisterStatus("IN PROCESS");
		sinisterRep.save(sinister);
		System.out.println(sinister.getId()) ;
		
		return sinister;
	}

	@Override
	public String deleteSinister(int id) {
		sinisterRep.deleteById( id);
		return "Sinister removed !! " + id;
		
	}

	@Override
	public String updateSinister(Sinister sinister ,int idSinister ) {
		
	Sinister existingSinister = sinisterRep.findById(idSinister).orElse(null);
	if(sinister.getDeclarationDate()!=null){
	existingSinister.setDeclarationDate(sinister.getDeclarationDate());	
	}
	if(sinister.getSinisterDate()!=null){

	existingSinister.setSinisterDate(sinister.getSinisterDate());
	}
	if(sinister.getSinisterDescription()!=null){
	
	existingSinister.setSinisterDescription(sinister.getSinisterDescription());
	}
	if(sinister.getSinisterPlace()!=null){

	existingSinister.setSinisterPlace(sinister.getSinisterPlace());
	}
	if(sinister.getSinisterStatus()!=null){

	existingSinister.setSinisterStatus(existingSinister.getSinisterStatus());
	}
	if(sinister.getSinisterType()!=null){

	existingSinister.setSinisterType(sinister.getSinisterType());
	}
	if(sinister.getContract()!=null){

	existingSinister.setContract(sinister.getContract());
	}
	if(sinister.getDelaiDeclaration()!=0){

	existingSinister.setDelaiDeclaration(sinister.getDelaiDeclaration());
	}
	if(sinister.getArrangments()!=null){


	existingSinister.setArrangments(sinister.getArrangments());
	}
	
	 sinisterRep.save(existingSinister);
	 
	 return "modification terminé avec succés\n"+existingSinister ;
	
	
	}
	@Override
	public Sinister retrieveSinister(int id) {
	
		return sinisterRep.findById(id).orElse(null);
	
	}
	
	

	@Override
	public Sinister retrieveSinisterByDeclarationDate(Date from, Date to) {
		
	 return sinisterRep.findByDeclarationDateBetween(from, to);
	}

	@Override
	public Sinister retrieveSinisterBySinisterDate(Date from, Date to) {
		
		return sinisterRep.findBySinisterDateBetween(from, to);
	
	}

	
	@Override
	public String  traiterSinistre(int sinisterId) throws Exception {
		String message = null ; 
		String message1 = null ;
		//String m1 = "delai bien vérifié , delai respecté !, on passe à la deuxieme etape de verificationverifier les modification sur votre sinistreSinister" ; 
		String m2="document Justificatif accepté" ;
		Sinister s = sinisterRep.findById(sinisterId).orElse(null) ;
		if(verifierDelai(sinisterId).contains("delai bien vérifié , delai respecté !, on passe à la deuxieme etape de verification") && verifierDocuments(sinisterId).contains(m2)){
			System.out.println(verifierDelai(sinisterId).toString()) ;
			System.out.println(verifierDocuments(sinisterId).toString()) ;
			double montantIndemnisé=verifierFactureIndemnisation(sinisterId) ;
			
			  s.setChargeSinister(montantIndemnisé);
			
				s.getChargeSinister();
				
				System.out.println(s.getChargeSinister());
				//payerSinistre( sinisterId) ;
				
				double charge = 0 ;
				  int nombreDeTranche = 0 ;
				  
				
					double montantParSemaine = 0;
					double  sommePayé = 0;
					double DernierPayment = 0 ;
					int i = 0 ;
					
				//List<Payment> payments = paymentService.retrieveAllPayment() ;
				//String a = payments.toString() ;
					
					Contract contract =s.getContract() ;
					charge = s.getChargeSinister() ;
					System.out.println(charge+"zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz") ;
					if(charge <1500){
						Date d =Calendar.getInstance().getTime();
						Payment p = new Payment(d,charge);
						p.setContract(contract);
						p.setPaymentCode("vvr555");
						paymentService.addPayment(p);
						message1 = "votre prestation du sinistre declaré le :"+s.getDeclarationDate()+"est effectué"+"\n"+"voir details de paiement:"+"montant:"+p.getAmountPayed()+"code paiement:"+p.getPaymentCode()+"methode de paiement:"+p.getPaymentMethod() ;
						s.setSinisterStatus("setteled");
						s.setIndemnisationDate(d);
					sinisterRep.save(s);
						
					}else if ( charge> 1500 && charge <4500 ) {
						nombreDeTranche = 3 ;
						montantParSemaine =charge/nombreDeTranche ;

						
							Calendar calendar = Calendar.getInstance();
							  Date date =calendar.getTime();
							  System.out.println(date+"yyyyyyyyyyyyyyyyyyyyyy") ;
							  
							
							
							Payment p1 = new Payment(date,montantParSemaine);
							p1.setContract(contract);
							p1.setPaymentCode("vvr555");
							paymentService.addPayment(p1);
						

					
							Date date2 = new Date(date.getTime() + (1000 * 60 * 60 * 24));

							
							Payment p2 = new Payment(montantParSemaine);
							
							p2.setPaymentDate(date2);
							p2.setContract(contract);
							p2.setPaymentCode("vvr555");
							paymentService.addPayment(p2);
							System.out.println(p2.getId()+"idvvr");
							
							
							Date date3 = new Date(date2.getTime() + (1000 * 60 * 60 * 24));

							
							Payment p3 = new Payment(montantParSemaine);
							
							p3.setPaymentDate(date3);

							p3.setContract(contract);
							p3.setPaymentCode("vvr555");
							paymentService.addPayment(p3);
							
							s.setSinisterStatus("setteled");
							s.setIndemnisationDate(date);
							sinisterRep.save(s);

							
						message1 = " votre prestation du sinistre declaré le :"+s.getDeclarationDate()+"est effectué  sur 3 tranches : "+"\n tranche1:"+p1+"\n tranche2:"+p2+"\n tranche3:"+p3 ;
						
							
						}
					
						
				
				System.out.println(retrieveSinister(sinisterId)); 
				
			
			
			
			System.out.println("indemnisation accépté de montant"+montantIndemnisé) ;
			message = "indemnisation accépté de :"+montantIndemnisé;
			
		
		}else{ message = "indemnisation refusé" ;
		System.out.println("indemnisation refusé") ;
		s.setSinisterStatus("REJECTED");
		s.setCauseRejet("delai ou instructions non respectés");
	
		sinisterRep.save(s);


		System.out.println(verifierDelai(sinisterId).toString()) ;
		System.out.println(verifierDocuments(sinisterId).toString()) ;
		
		}
		
		
		return message+"\n"+message1+"\n"+s;
	}

	@Override
	public List<Sinister> retrieveSinisterByContract(int contractId) {
		
		Contract c = contractRep.findById(contractId).orElse(null) ;
		
		List<Sinister> sinisters = sinisterRep.findAllByContract(c);
		return sinisters ;
			
			
	}
	
			
		 
		
	@Override
	public Sinister affecterContratSinister(int idContrat, int idSinister) {
		
		Contract c = contractRep.findById(idContrat).orElse(null) ;
		Sinister s = sinisterRep.findById(idSinister).orElse(null) ;
		
		s.setContract(c);
		sinisterRep.save(s);
		
		return s;
	}

	@Override
	public String CalculRapiditéReglementSinistre(int sinisterId) {
		double rapiditéReglementSinistre =0;
		//double ratioChargeEngagé =0; 
		//double ratioSinistreSurvenu=0 ;
		//double ratioRejetDemandeIndemnisation =0;
		
		Sinister s= sinisterRep.findById(sinisterId).orElse(null);
	Date	a=s.getDeclarationDate();
	
	Date b = s.getIndemnisationDate();
	
	long diff = b.getTime() - a.getTime();
	rapiditéReglementSinistre = (diff / (1000*60*60*24));
	System.out.println("Nombre de jours entre les deux dates est: "+rapiditéReglementSinistre+"jours");
	String chaine = rapiditéReglementSinistre +"jours" ;
		return  chaine ;
	}
	
	

	@Override
	public String CalculRatioSinSurvenu(int insurerId) { //Ratio des sinistres survenus n =somme charge des  Sinistres survenus a une period n/ Primes acquises
		double ratioSinistreSurvenu=0 ;
		double ChargeSinistreSurvenu=0 ;
		double PrimesAcquisesTotal =0 ;
		double resultat = 0 ; 
		
		Insurer ins= insurerRep.findById(insurerId).orElse(null);
		if(ins==null){
			System.out.println("null");
		} else {
			System.out.print("not null");
		}
		System.out.println("i"+ins);
		
		
		List <Contract> contracts = contractRep.findAllByInsurer(ins) ;
		System.out.println("aaaaaa"+contractRep.findAllByInsurer(ins));
		
		for (Contract c : contracts ){
			c.getTotalPemium();
			PrimesAcquisesTotal = c.getTotalPemium() + PrimesAcquisesTotal;			
			System.out.println("la prime aquises total est de tous les contrat d'assureur avec id" + insurerId+"est"+PrimesAcquisesTotal);
			System.out.println("nombre de contrat est : "+contractRep.count());
			List <Sinister> sinisters = sinisterRep.findAllByContract(c);
			for (Sinister s :sinisters) {
				s.getChargeSinister();
				ChargeSinistreSurvenu=s.getChargeSinister()+ChargeSinistreSurvenu ;
				
				}
			}
		
		ratioSinistreSurvenu = (ChargeSinistreSurvenu/PrimesAcquisesTotal) ;
		
		resultat = PrimesAcquisesTotal-ChargeSinistreSurvenu ;
		
		System.out.println("le ratio de sinistre survenu est : " +ratioSinistreSurvenu+"%" );
		 
		return "le ratio de sinistre survenu est : " +ratioSinistreSurvenu+"%"+"\n"+"les resultat de votre companie est :"+resultat ;
		
	}

	@Override
	public String CalculRatioRejetDemIndemn(int insurerId) { //Ratio de rejet des demandes d’indemnisation = Nbre de demandes rejetées / Nbre de demandes dans l’échantillon
		
		double nombreSinisterRejeté =0 ;
		double nombreSinistreTotal = 0 ;
		double RatioRejetDemIndemn = 0 ;
		
		Insurer ins= insurerRep.findById(insurerId).orElse(null);
		
		List <Contract> contracts = contractRep.findAllByInsurer(ins) ;
		
		for (Contract c : contracts ){
			List <Sinister> sinisters = sinisterRep.findAllByContract(c);
			for (Sinister s :sinisters){
				 
				if(s.getSinisterStatus().equals("REJECTED")){
					nombreSinisterRejeté = nombreSinisterRejeté + 1 ;
					
					
				}	     
				
			}
		}
		 nombreSinistreTotal =	(int) sinisterRep.count() ;
	      System.out.println("cccccc" +sinisterRep.count());
	      System.out.println("nooooooooooombre" +nombreSinisterRejeté);

	      
	      RatioRejetDemIndemn = (nombreSinisterRejeté/nombreSinistreTotal)*100  ;
		
		return "le ratio de rejet d'indemnisation est :"+RatioRejetDemIndemn+"%";
	}
	
	
	
	@Override
	public String CalculCoutMoyen(int insurerId) { //somme de charge des sinistre / nombre de sinistres 
		double nombreSinistreTotal = 0 ;
		double ratioSinistreSurvenu=0 ;
		double ChargeSinistreSurvenu=0 ;
		double PrimesAcquisesTotal =0 ;
		double coutMoyen = 0 ; 
		
Insurer ins= insurerRep.findById(insurerId).orElse(null);
		
List <Contract> contracts = contractRep.findAllByInsurer(ins) ;
System.out.println("aaaaaa"+contractRep.findAllByInsurer(ins));

for (Contract c : contracts ){
	c.getTotalPemium();
	PrimesAcquisesTotal = c.getTotalPemium() + PrimesAcquisesTotal;			
	System.out.println("la prime aquises total est de tous les contrat d'assureur avec id" + insurerId+"est"+PrimesAcquisesTotal);
	System.out.println("nombre de contrat est : "+contractRep.count());
	List <Sinister> sinisters = sinisterRep.findAllByContract(c);
	for (Sinister s :sinisters) {
		s.getChargeSinister();
		ChargeSinistreSurvenu=s.getChargeSinister()+ChargeSinistreSurvenu ;
		
		}
					
					
				}	     
				
			

		 nombreSinistreTotal =	(int) sinisterRep.count() ;
		 
		 coutMoyen=ChargeSinistreSurvenu/nombreSinistreTotal ;
	      System.out.println("cccccc" +sinisterRep.count());
	      System.out.println("nooooooooooombre" );

	      
	     
		
		return "le cout Moyen d'un sinistre est  :"+coutMoyen+" "+"dinars";
	}
	
	
	
	
	
	
	
	@Override
	public String calculSinistraliéPortfeuille(int insurerId ) {
		double  nombreSinistre=0;
		double nombreContrat =0; 
		double sinistralitéPortfeuille = 0 ;

		Insurer ins= insurerRep.findById(insurerId).orElse(null);
		
		List <Contract> contracts = contractRep.findAllByInsurer(ins);
		nombreContrat = contracts.size() ;
		
		
		for (Contract c : contracts ){
			List <Sinister> sinisters = sinisterRep.findAllByContract(c);
			nombreSinistre =sinisters.size();
			 //List <Insured> insureds = insuredRep.findAllByContract(c) ;
			
		}
		
	  sinistralitéPortfeuille =nombreSinistre/nombreContrat ;
	  
	  
	  
		
		
		return "vous etes assureur avec id:"+insurerId+"\n vous avez un portfeuille de :"+""+nombreContrat+""+"contrats\n"+"le nombre de sinistre declaré est:"+""+nombreSinistre+"\n"+
				"le taux de sinistralité de ton portfeuille est :"+""+sinistralitéPortfeuille;
		
		
	}

	@Override
	public Sinister declarerSinistre(Sinister sinister , int contractId )  { //, int insuredId 
		
		//Insured insd = insuredRep.findById(insuredId).orElse(null);
		
		//List <Contract> contracts =insd.getContracts();
		//for (Contract c : contracts){
			
		//}
		
		
		Contract contract = contractRep.findById(contractId).orElse(null) ;
		//int idInsurer = contract.getInsurer().getId() ;
		//contract.getInsured().getId() ;
		
			System.out.println("declaration de sinistre avec succés") ;
		sinister.setSinisterStatus("IN PROCESS");
		sinister.setDeclarationDate(Calendar.getInstance().getTime());
		sinister.setIndemnisationDate(null);
		sinister.setCauseRejet(null);
		sinister.setDelaiDeclaration(7);
		sinister.setChargeSinister(0);
		sinister.setContract(contract);
		 //sinister.setSinisterType(contract.getContractType()) ;
		sinisterRep.save(sinister);
		//envoyerMailToInsurer(idInsurer);
		
		
		
		return sinister ;
		
	}

	@Override
	public String verifierDelai(int sinisterId) {
		double nombreJours =0;
		String message = null ; 
Sinister sinister = sinisterRep.findById(sinisterId).orElse(null)	;
  int idInsured = sinister.getContract().getInsured().getId();
 

Date a = sinister.getDeclarationDate() ;
int b =sinister.getDelaiDeclaration();
Date c = sinister.getSinisterDate() ;

long diff = a.getTime() - c.getTime();
nombreJours = (diff / (1000*60*60*24));
if(nombreJours<= b){
	 System.out.println("delai bien vérifié , delai respecté !, on passe à la deuxieme etape de verification ,envoyer justificatif") ;
	 message = "delai bien vérifié , delai respecté !, on passe à la deuxieme etape de verification" ;
	
	
	
}else {
	System.out.println("delai expiré , vous n'avez pas le droit d'indemnisation , demande rejetéé !! ");
	
	
	
	message = "delai expiré , vous n'avez pas le droit d'indemnisation , demande rejetéé !! ";
	
	
}




		return message ;
		
		
	}


	
	

	@Override
	public String envoyerMailToInsured(int insuredId ) {
		

		Insured insured= insuredRep.findById(insuredId).orElse(null) ;
   	 String email =insured.getEmail();
   	 String nom = insured.getName();
   	 
   	 
       // Create a Simple MailMessage.
       SimpleMailMessage message = new SimpleMailMessage();
        
       message.setTo(email);
       message.setSubject( " demande des pieces justificatives ");
       message.setText("Bonjour Mr"+" "+nom+","+"\n " + "\n" +"veillez envoyer votres pieces justificative dansun delais de 48 heures depuis la reception de ce mail pour l'appuie de traitement de votre demande d'indemnisation " + "\n "+ "\n "+ "Cordilement"
       +"\n" +"\n" + "ce mail est envoyer à"+ "\n "+ "\n "+Calendar.getInstance().getTime());

       // Send Message!
       this.emailSender.send(message);

       return "Email Sent!";
		
		
	}

	@Override
	public String envoyerMailToInsurer(int insurerId) throws MessagingException {
		
		Insurer insurer= insurerRep.findById(insurerId).orElse(null) ;
	   	 String email =insurer.getEmail();
	   	 String nom = insurer.getName();
	     MimeMessage message = emailSender.createMimeMessage();
		 
	        boolean multipart = true;
	 
	        MimeMessageHelper helper = new MimeMessageHelper(message, multipart);
	 
	        helper.setTo(email);
	        helper.setSubject("les documents justificatives de mon sinistre");
	         
	        helper.setText("Bonjour Mr"+" "+nom+","+"\n " + "\n" +"veuillez trouvez les documents justificatives suite a mon sinistre "+ "\n "+ "\n "+ "Cordilement"
		 		       +"\n" +"\n" + "ce mail est envoyer à"+ "\n "+ "\n "+Calendar.getInstance().getTime());
		    
	         
	        String path1 = "C:/Users/abder/Desktop/a.txt";
	        String path2 = "C:/Users/abder/Desktop/b.txt";
	 
	        // Attachment 1
	        FileSystemResource file1 = new FileSystemResource(new File(path1));
	        helper.addAttachment("a", file1);
	 
	        // Attachment 2
	        FileSystemResource file2 = new FileSystemResource(new File(path2));
	        helper.addAttachment("b", file2);
	 
	        emailSender.send(message);
	 
	        return "Email Sent!";
	        
	}

	@Override
	public String suivreSinistre(int sinisterId) {
		
		Sinister sinister = sinisterRep.findById(sinisterId).orElse(null);
		
		
		
		
		if(sinister.getSinisterStatus().equals("REJECTED")){
			
			return "votre sinistre declaré le : "+sinister.getDeclarationDate()+"est rejecté,"+"\n"+"motif de rejet:"+sinister.getCauseRejet();
			
		}else
		
		return "votre sinistres est en cours de traitement";
	}

	@Override
	public String verifierDocuments(int sinisterId ) throws Exception {
		
		File f = new File("C:/Users/abder/Desktop/demande stage/Vneuron/attestation de stage RiraDEV.pdf");
		
		 
		  PDDocument document = PDDocument.load(f);
			      
			      PDFTextStripper stripper = new PDFTextStripper();
			      String text = stripper.getText(document);
			      System.out.println(text);
			      document.close();
			     
			      List<String> listePartenaire =  new ArrayList<String>() ;
			      listePartenaire.add("gérante ikram boukhatem ");
			      listePartenaire.add("Abderrahim Makina");
			      listePartenaire.add("RIRA Dev");
			      listePartenaire.add("Docteur mouhamed salah");
			      listePartenaire.add("Expert foulen");
			      listePartenaire.add("fournisseur mahmoud");
			      System.out.println(text.indexOf("Abderrahim")+"aaaaaaaaaaaaaaaaaaaaaaaaaa");
			      System.out.println(text.substring(276, 286)); 
			      
			      System.out.println(text.indexOf("numéro")+"vvvvvvvvvvvvvvvvvvv");
			      System.out.println(text.substring(244, 251));
			      
			      
			      
			      
			      String m =null ;
			      int compteur =0 ;
			      for ( int i = 0 ; i < listePartenaire.size(); i++){
			    	  String b =listePartenaire.get(i) ;
			    	  System.out.println(b);
			    	  
			    	 
		    		  
		    		  

			    	  if(text.contains(b)){
			    		  
			    		  System.out.println("document Justificatif accepté ") ;
			    	 m ="document Justificatif accepté ";
			    	 compteur=compteur+1;
			    	  }else if(!text.contains(b)&&compteur==0){
			    		  
			    		  System.out.println("document justificatif refusé ! vous devez avoir des justificatifs seulement depuis nos partennaire:") ;
			    		   m ="document justificatif refusé ! vous devez avoir des justificatifs seulement depuis nos partenaires:"+listePartenaire+"\n";
			    	  }
			    	  
			    	  
			      }
			   
			      
				return m+"\n"+"\n"+text;
			}

	@Override
	public String predictionFraud(int siniterId) {
		
			Sinister sinister = sinisterRep.findById(siniterId).orElse(null);
			
		String description =	sinister.getSinisterDescription();
		
		
				 List<String> fraudPredictionPython =  new ArrayList<String>() ;
				 fraudPredictionPython .add("arrivé") ;
				 fraudPredictionPython .add("malade") ;
				 fraudPredictionPython .add("incident") ;
				 fraudPredictionPython .add("impliqué") ;
				 fraudPredictionPython .add("exactement") ;
				 fraudPredictionPython .add("travail") ;
				 fraudPredictionPython .add("inclus") ;
				 fraudPredictionPython .add("jure") ;
				 fraudPredictionPython .add("honetement") ;
				 fraudPredictionPython .add("je crois") ;
				 
				
				int fraudPourcentage=0 ;
				
				 String m =null ;
				
			      for ( int i = 0 ; i < fraudPredictionPython.size(); i++){
			    	  String b =fraudPredictionPython.get(i) ;
			    	  System.out.println(b);
		    		  
			    	if(description.contains(b) ){
			    
			    	 fraudPourcentage=fraudPourcentage+1;
			    	 System.out.println(fraudPourcentage);
			    	 double fraud=fraudPourcentage*10 ;
			    	
			    		// System.out.println("pourcentage de fraude :"+fraud+"%") ;
			    		 //m="pourcentage de fraude :"+fraud+"%";
			    		 
			    	  //} else if(description.contains(b)&&fraudPourcentage>=4 && fraudPourcentage<7){
			    		 
			    		 System.out.println("suspession of  :"+fraud+"%"+"\n"+"to be a fraud  !! be careful !!!") ;
			    		 m="suspession of :"+fraud+"%"+" "+"to be a fraud  !!\n be careful !!! !!" ;
			    		 
			    		 String isFraud =fraud+"%"+"fraud";
			    		 sinister.setIsFraud(isFraud);
			    		 sinisterRep.save(sinister);
			    	
			    		// }else System.out.println("pourcentage de fraude :"+fraud+"%"+"\n"+"it's a fraud !!") ; 
			    	      //m= "pourcentage de fraude :"+fraud+"%"+"\n"+"it's a fraud !!" ;
			    	
			    	 }
			    	  else if(!description.contains(b)&&fraudPourcentage==0){
			    		  
			    		  System.out.println("Declaration Non frauduleuse");
			    		  m="Declaration Non frauduleuse";
			    		  sinister.setIsFraud("NotFraud");
			    	  }
		
			   }
		
			      return m;
	}

	@Override
	public String meteoScrapping() throws IOException {
		
		Document doc = Jsoup.connect("https://www.tameteo.com/meteo_Tunis-Afrique-Tunisie-Tunis-DTTA-1-8952.html")
				.timeout(6000).get();
		Document doc1 = Jsoup.connect("https://meteo-tunisie.net/tunis")
			.timeout(6000).get();
		
		Elements body1 = doc1.select("tbody");
		Elements body = doc.select("tbody");

		
		//System.out.println(body.select("tr").size());
		
	/*	
	String  heure = null; 
	String temperature=null ;
	String description = null ;
	String sudeEst = null ;
	String radiacion = null ; 
	String MéteoAujourdhui = null ;
	
	*/
	String	alerte = null ;
	String a = null ; 
	
	/*
	
		for(Element e : body.select("tr"))
		
		{
			String titre = doc.select("span.titulo").text();
			System.out.println(titre);
			
			 heure = e.select("span.hora").text();
			System.out.println(heure);
			 temperature = e.select("td.temperatura.changeUnitT").text();
			System.out.println(temperature);
			description =  e.select("td.descripcion").text();
			*/
	       a = doc1.select("td").text();
	       System.out.println(a);
			if(a.contains("Partiellement nuageux")||a.contains("Quelques averses")||a.contains("Nuageux") ){
		
				alerte = "votre sécurité est notre responsabilité, c'est un jour de pluie aujourd'hui, alors soyez prudent pendant votre conduite, soyez prudent !!" ;
				
				System.out.println("votre sécurité est notre responsabilité, c'est un jour de pluie aujourd'hui, alors soyez prudent pendant votre conduite, be safe  !!");
				
				
			//	Insurer in = insurerRep.findById(insurerId).orElse(null);
				
			//	List <Contract> contracts = contractRep.findAllByInsurer(in);
				

			//	for (Contract contract : contracts){     
				
				List<Insured> insureds = (List<Insured>) insuredRep.findAll();
				
				for(Insured ins : insureds){
				int idInsured = ins.getId();
					
					envoyerMailMetoToInsured(idInsured);
					}
				}
				
			/*	//}
				
			}
			System.out.println(description);
			sudeEst = e.select("span.datos-viento").text();
			System.out.println(sudeEst);
			
			radiacion= e.select("span.datos-radiacion").text() ;
	
			System.out.println(radiacion);
		}
		
		*/
			
		
	//	MéteoAujourdhui=heure+"\n"+temperature+"\n"+description+"\n"+sudeEst+"\n"+radiacion;
			
		return a+"\n"+alerte ;

}

	@Override
	public String envoyerMailMetoToInsured(int insuredId) {

		Insured insured= insuredRep.findById(insuredId).orElse(null) ;
   	 String email =insured.getEmail();
   	 String nom = insured.getName();
   	 
   	 
       // Create a Simple MailMessage.
       SimpleMailMessage message = new SimpleMailMessage();
        
       message.setTo(email);
       message.setSubject( " Météo ");
       message.setText("Bonjour Mr"+" "+nom+","+"\n " + "\n" +"votre sécurité est notre responsabilité, c'est un jour de pluie aujourd'hui alors soyez prudent en conduisant " + "\n "+ "\n "+ "Cordilement"
       +"\n" +"\n" + "ce mail est envoyer à"+ "\n "+ "\n "+Calendar.getInstance().getTime());

       // Send Message!
       this.emailSender.send(message);

       return "Email Sent!";
		
		
	}

	@Override
	public  double verifierFactureIndemnisation(int sinisterId) throws IOException {

		File f = new File("C:/Users/abder/Desktop/facturePi/facture3000.pdf");
		Sinister s = sinisterRep.findById(sinisterId).orElse(null) ;
		
		System.out.println(s.getChargeSinister());
		
		// Sinister s = sinisterRep.findById(arg0)
		  PDDocument document = PDDocument.load(f);
			      
			      PDFTextStripper stripper = new PDFTextStripper();
			      String text = stripper.getText(document);
			      System.out.println(text);
			      document.close();
			      
			      System.out.println(text.indexOf("euros")+"vvvvvvvvvvvvvvvvvvv");
			   String   montant = text.substring(415, 419);
			  
			   
			   
			   double	 montantDouble =Double.parseDouble( montant);
			   
			      System.out.println(montant+"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			      
			      
			      System.out.println(montantDouble+"mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");  
			     
		return montantDouble;
	}

	@Override
	public String payerSinistre(int sinisterId) {
		double charge = 0 ;
	  int nombreDeTranche = 0 ;
	  String message = null ;
	
		double montantParSemaine = 0;
		double  sommePayé = 0;
		double DernierPayment = 0 ;
		int i = 0 ;
		
	//List<Payment> payments = paymentService.retrieveAllPayment() ;
	//String a = payments.toString() ;
		
		Sinister sinister = sinisterRep.findById(sinisterId).orElse(null);
		Contract contract =sinister.getContract() ;
		charge = sinister.getChargeSinister() ;
		System.out.println(charge+"zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz") ;
		if(charge <1500){
			Payment p = new Payment(Calendar.getInstance().getTime(),charge);
			p.setContract(contract);
			p.setPaymentCode("vvr555");
			paymentService.addPayment(p);
			message = "votre prestation du sinistre declaré le :"+sinister.getDeclarationDate()+"est effectué"+"\n"+"voir details de paiement:"+"montant:"+p.getAmountPayed()+"code paiement:"+p.getPaymentCode()+"methode de paiement:"+p.getPaymentMethod() ;
			
		}else if ( charge> 1500 && charge <4500 ) {
			nombreDeTranche = 3 ;
			montantParSemaine =charge/nombreDeTranche ;

			
				Calendar calendar = Calendar.getInstance();
				  Date date =calendar.getTime();
				  System.out.println(date+"yyyyyyyyyyyyyyyyyyyyyy") ;
				  
				
				
				Payment p1 = new Payment(date,montantParSemaine);
				p1.setContract(contract);
				p1.setPaymentCode("vvr555");
				paymentService.addPayment(p1);
			

		
				Date date2 = new Date(date.getTime() + (7000 * 60 * 60 * 24));

				
				Payment p2 = new Payment(montantParSemaine);
				
				p2.setPaymentDate(date2);
				p2.setContract(contract);
				p2.setPaymentCode("vvr555");
				paymentService.addPayment(p2);
				System.out.println(p2.getId()+"idvvr");
				
				
				Date date3 = new Date(date2.getTime() + (7000 * 60 * 60 * 24));

				
				Payment p3 = new Payment(montantParSemaine);
				
				p3.setPaymentDate(date3);

				p3.setContract(contract);
				p3.setPaymentCode("vvr555");
				paymentService.addPayment(p3);
				
			message = " votre prestation du sinistre declaré le :"+sinister.getDeclarationDate()+"est effectué  sur 3 tranches : "+"\n tranche1:"+p1+"\n tranche2:"+p2+"\n tranche3:"+p3 ;
				
				
			}
			
		
		return message;
	}

	@Override
	public List<Sinister> retrieveSinisterByInsured(int insuredId) {
		Insured in = insuredRep.findById(insuredId).orElse(null) ;
		
	List <Contract> contracts = contractRep.findAllByInsured(in);
	
	for (Contract contract : contracts){     
	
	List<Sinister> sinisters = (List<Sinister>) sinisterRep.findAllByContract(contract);
	for (Sinister sinister : sinisters){
		l.info("sinister++:" + sinister) ;
		System.out.println(sinister) ;

	}return sinisters ;
		
	}return null ;
	}
	
	@Override
	public List<Sinister> retrieveSinisterByInsurer(int insurerId) {
		Insurer in = insurerRep.findById(insurerId).orElse(null) ;
		
	List <Contract> contracts = contractRep.findAllByInsurer(in);
	
	for (Contract contract : contracts){     
	
	List<Sinister> sinisters = (List<Sinister>) sinisterRep.findAllByContract(contract);
	for (Sinister sinister : sinisters){
		l.info("sinister++:" + sinister) ;
		System.out.println(sinister) ;

	}return sinisters ;
		
	}return null ;
	}

	
	
	


	
	/*
	String a ="pk_test_AuAMdXwE57NnBcd4Xld65Ez4" ;
	
	String b ="rk_test_oGfrFNOjpnRPklUVzjelPHgf" ;
String d =	"sk_test_yIqEVjLUzA1vwKhr1PjhnS9I" ;
	
	
	String STRIPE_PUBLIC_KEY ="pk_test_51GvLO4Hs69O1mxqWpbXx417BCxxunZVSPHhT2Lz7STZBTthV2DQdzZCTPLOStvBLgcJHzF1kJQ16S1wQ7GyDiL3000gYIwduCf" ;
	String STRIPE_SECRET_KEY ="sk_test_51GvLO4Hs69O1mxqWa4st7RTFN01tmGzOHoU8yw9JQ7424gLojvnYNqO6U40hpnJfrevhQUvwg7jOIKY8N8V7tHEh00twihvmTg" ;
	 

	    @Autowired
	    public SinisterServiceImpl() {
	        Stripe.apiKey = a;
	    }

	    public Charge chargeNewCard(String token, double amount) throws Exception {
	        Map<String, Object> chargeParams = new HashMap<String, Object>();
	        
	        chargeParams.put("amount", (int)(amount * 100));
	        chargeParams.put("currency", "USD");
	        
	        chargeParams.put("source", token);
	        Charge charge = Charge.create(chargeParams);
	        return charge;
	    }
	    
	    */
	
	 
}


	
	

	
	
	
	
	


