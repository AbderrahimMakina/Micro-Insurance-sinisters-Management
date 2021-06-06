package tn.esprit.spring.control;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import com.lowagie.text.DocumentException;

import tn.esprit.spring.dao.entities.Contract;
import tn.esprit.spring.dao.entities.Documents;
import tn.esprit.spring.dao.entities.Insured;
import tn.esprit.spring.dao.entities.Insurer;
import tn.esprit.spring.dao.entities.Payment;
import tn.esprit.spring.dao.entities.PdfExporter;
import tn.esprit.spring.dao.entities.Sinister;
import tn.esprit.spring.dao.entities.User;
import tn.esprit.spring.service.IContractService;
import tn.esprit.spring.service.IDocumentService;
import tn.esprit.spring.service.IPaymentService;
import tn.esprit.spring.service.ISinisterService;


@Scope(value = "session")
@Controller(value = "sinisterController") // Name of the bean in Spring IoC
@ELBeanName(value = "sinisterController")
public class SinisterControllerImpl {
	
	@Autowired
	ISinisterService IsinisterService ; 
	
	@Autowired
	IPaymentService paymentService ;
	
	@Autowired
	IContractService contractService ;
	
	@Autowired
	UserControllerImpl userController ;
	
	@Autowired
	IDocumentService docService ;
	
	private Date sDate;
	private String sDescription;
	private String sPlace ; 
	private String sType ; 
	private int contId;
	private Contract cont ; 
	String navigateTo=null ;
	private static int idSinister=0;
	private List<Sinister> sinisters;
	
	private UploadedFile file;
	
	private Sinister selectedSinister;

    private List<Sinister> selectedSinisters;
	
	private List<Sinister> sinisterInsurers ;
	
	private Documents d ;
	
	private String docName ; 
	private String docLabel ; 
	
	private Sinister sinisterAjustifier ;
	
	private Sinister sinister;
	
	private int idSinAjustifier = 0 ;
	
	
	
	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
	}
	
	public void decalarerSinistre()  {
		
		cont = contractService.retrieveContract(contId) ;
		
		sinister=new Sinister(sDate,sPlace,sDescription,sType);
		//sinister.getId() ;
		IsinisterService.addSinister(sinister) ;
		
		IsinisterService.affecterContratSinister(contId, sinister.getId()) ;
		
		idSinister=sinister.getId() ;
		//IsinisterService.affecterContratSinister(contractId, sinister.getId()) ;
		addMessage(FacesMessage.SEVERITY_INFO, "Success", "votre sinistre est bien déclarée");
		
		}
	
	public void ajouterJustificatif()
	{
		sinisterAjustifier = IsinisterService.retrieveSinister(idSinAjustifier) ;
		
		d = new Documents(docName ,docLabel) ;
		docService.addDocument(d);
		
		docService.affecterDocumentSinister(d.getId(), idSinAjustifier) ;
		addMessage(FacesMessage.SEVERITY_INFO, "Success", "votre document est bien ajouté");

		
	}
	
	
	
	public String fraudPrediction (int siniterId){
				
		addMessage(FacesMessage.SEVERITY_INFO, "Success", "fraud prediction done !!");

	return	 IsinisterService.predictionFraud(siniterId) ;
	
		 
		// return  "/pages/admin/gererSinistre.xhtml?faces-redirect=true";

	}
	
	
	
	public String verifierDelai(int sinisterId ){
		
		return IsinisterService.verifierDelai(  sinisterId);
	}
	
	public double verifierFacture(int sinisterId ) throws Exception {
		
		
		return IsinisterService.verifierFactureIndemnisation(sinisterId) ;
		
	}
	
	public String  verifierDocuments(int sinisterId ) throws Exception{
		
		return IsinisterService.verifierDocuments(sinisterId) ;
		
		
	}
	
	
	public String traiterSinistre(int sinisterId) throws Exception{
		
		 
		 
		 addMessage(FacesMessage.SEVERITY_INFO, "Success", "deadline verification done !");
		 addMessage(FacesMessage.SEVERITY_INFO, "Success", "documents verification done ! ");
		 
return  IsinisterService.traiterSinistre(sinisterId);
		
	}
	
	public String afficherResultat(){
		
		return   "/pages/admin/gererSinistre.xhtml?faces-redirect=true" ;
	}
	
	
	public void exportToPDF(HttpServletResponse response,int contractId) throws DocumentException, IOException {
	       response.setContentType("application/pdf");
	       DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	       String currentDateTime = dateFormatter.format(new Date());
	        
	       String headerKey = "Content-Disposition";
	       String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
	       response.setHeader(headerKey, headerValue);
	        
	       List<Payment> listPayments = paymentService.retrieveAllPayment(contractId);
	       System.out.println(listPayments+"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa") ;
	        
	       PdfExporter exporter = new PdfExporter(listPayments);
	      
	       exporter.export(response);
	       
	      
	        
	   }


	public List<Sinister>  getSinistersByInsured( ) { 
	int idUserConnecté =	UserControllerImpl.getIdUserC() ;
		sinisters = IsinisterService.retrieveSinisterByInsured( idUserConnecté  ) ;
		
		return sinisters ; 

	}
	

	public List<Sinister>  getSinistersByInsurer( ) { 
	int idUserConnecté =	UserControllerImpl.getIdUserC() ;
	sinisterInsurers = IsinisterService.retrieveSinisterByInsurer( idUserConnecté  ) ;
		
		return sinisterInsurers ; 

	}
	
	
	public void afficherSinisters() {
		sinisters = IsinisterService.retrieveAllSinisters();
		
		
		}
	
	public String afficherSinister(int sinisterId) {
		
		sinister = IsinisterService.retrieveSinister(sinisterId);
		
		 return  "/pages/admin/traiterSinistre.xhtml?faces-redirect=true"; 
		}
	
	public String suivreSinistre(int sinisterId) {
		
		
	return	IsinisterService.suivreSinistre(sinisterId);
		
		}
	
	
	
	public String doLogout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login?faces-redirect=true";
		}
	
	

	public IPaymentService getPaymentService() {
		return paymentService;
	}

	public void setPaymentService(IPaymentService paymentService) {
		this.paymentService = paymentService;
	}

	

	public Sinister getSinister() {
		return sinister;
	}

	public void setSinister(Sinister sinister) {
		this.sinister = sinister;
	}



	public List<Sinister> getSinisters() {
		sinisters=IsinisterService.retrieveSinisterByInsured(UserControllerImpl.getIdUserC() );
		return sinisters;
	}



	public void setSinisters(List<Sinister> sinisters) {
		this.sinisters = sinisters;
	}

	public ISinisterService getIsinisterService() {
		return IsinisterService;
	}

	public void setIsinisterService(ISinisterService isinisterService) {
		IsinisterService = isinisterService;
	}

	public IContractService getContractService() {
		return contractService;
	}

	public void setContractService(IContractService contractService) {
		this.contractService = contractService;
	}





	public Date getsDate() {
		return sDate;
	}



	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}



	public String getsDescription() {
		return sDescription;
	}



	public void setsDescription(String sDescription) {
		this.sDescription = sDescription;
	}



	public String getsPlace() {
		return sPlace;
	}



	public void setsPlace(String sPlace) {
		this.sPlace = sPlace;
	}



	public String getsType() {
		return sType;
	}



	public void setsType(String sType) {
		this.sType = sType;
	}



	public int getContId() {
		return contId;
	}



	public void setContId(int contId) {
		this.contId = contId;
	}



	public Contract getCont() {
		return cont;
	}



	public void setCont(Contract cont) {
		this.cont = cont;
	}

	public String getNavigateTo() {
		return navigateTo;
	}

	public void setNavigateTo(String navigateTo) {
		this.navigateTo = navigateTo;
	}

	public List<Sinister> getSinisterInsurers() {
		int idUserConnecté =	UserControllerImpl.getIdUserC() ;
		sinisterInsurers = IsinisterService.retrieveSinisterByInsurer( idUserConnecté  ) ;
		return sinisterInsurers;
	}

	public void setSinisterInsurers(List<Sinister> sinisterInsurers) {
		this.sinisterInsurers = sinisterInsurers;
	}

	public static int getIdSinister() {
		return idSinister;
	}

	public static void setIdSinister(int idSinister) {
		SinisterControllerImpl.idSinister = idSinister;
	}

	public Documents getD() {
		return d;
	}

	public void setD(Documents d) {
		this.d = d;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocLabel() {
		return docLabel;
	}

	public void setDocLabel(String docLabel) {
		this.docLabel = docLabel;
	}

	public Sinister getSinisterAjustifier() {
		return sinisterAjustifier;
	}

	public void setSinisterAjustifier(Sinister sinisterAjustifier) {
		this.sinisterAjustifier = sinisterAjustifier;
	}

	public int getIdSinAjustifier() {
		return idSinAjustifier;
	}

	public void setIdSinAjustifier(int idSinAjustifier) {
		this.idSinAjustifier = idSinAjustifier;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public Sinister getSelectedSinister() {
		return selectedSinister;
	}

	public void setSelectedSinister(Sinister selectedSinister) {
		this.selectedSinister = selectedSinister;
	}

	public List<Sinister> getSelectedSinisters() {
		return selectedSinisters;
	}

	public void setSelectedSinisters(List<Sinister> selectedSinisters) {
		this.selectedSinisters = selectedSinisters;
	}
	
	public String CalculRatioSinSurvenu(){
		
		int idUserConnecté =	UserControllerImpl.getIdUserC() ;
		 return IsinisterService.CalculRatioSinSurvenu(idUserConnecté) ;
		}
	
	public String  CalculRatioRejetDemIndemn( ) {
		int idUserConnecté =	UserControllerImpl.getIdUserC() ;

		return IsinisterService.CalculRatioRejetDemIndemn(idUserConnecté) ;
		}
	
	public String  CalculCoutMoyen( ) {
		int idUserConnecté =	UserControllerImpl.getIdUserC() ;
		return IsinisterService.CalculCoutMoyen(idUserConnecté) ;
		}
	
	public String  calculSinistraliéPortfeuille() {
		int idUserConnecté =	UserControllerImpl.getIdUserC() ;

		return IsinisterService.calculSinistraliéPortfeuille(idUserConnecté) ;
		}

	



	
	

}
