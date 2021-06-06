package tn.esprit.spring.control;


import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
 
import com.lowagie.text.DocumentException;

import tn.esprit.spring.dao.entities.Payment;
import tn.esprit.spring.dao.entities.PdfExporter;
import tn.esprit.spring.dao.entities.Sinister;
import tn.esprit.spring.service.IPaymentService;
import tn.esprit.spring.service.ISinisterService;

@RestController
public class SinisterRestController {
	
	@Autowired
	ISinisterService IsinisterService ; 
	
	@Autowired
	IPaymentService paymentService ;
	
	
	
	
	
	
	
	// http://localhost:8081/SpringMVC/servlet/retrieve-all-sinisters
 @GetMapping("/retrieve-all-sinisters")
 @ResponseBody
	public List<Sinister> getSinisters(){
		
		List<Sinister> list = IsinisterService.retrieveAllSinisters();
		return list;
		
		
	}
 
 
 
// http://localhost:8081/SpringMVC/servlet/add-sinister
@PostMapping("/add-sinister")
 @ResponseBody
 public Sinister addSinister(@RequestBody Sinister s  ) { 
 Sinister sinister = IsinisterService.addSinister(s  );
 return sinister;
 }



//http://localhost:8081/SpringMVC/servlet/declarerSinistre/{id-contract} 
@PutMapping("/declarerSinistre/{id-contract}")
@ResponseBody
public Sinister declarerSinistre(@RequestBody Sinister sinister  , @PathVariable("id-contract") int contractId ) throws MessagingException{
	
	 return  IsinisterService.declarerSinistre(sinister, contractId );
	
}


//http://localhost:8081/SpringMVC/servlet/affecter-sinister-contract/{id-contract}/{id-sinister}
@PutMapping("/affecter-sinister-contract/{id-contract}/{id-sinister}")
@ResponseBody
public Sinister affecterContratSinister(@PathVariable("id-contract") int idContrat, @PathVariable("id-sinister") int idSinister){
	
	 return  IsinisterService.affecterContratSinister(idContrat, idSinister) ;
	
}




//http://localhost:8081/SpringMVC/servlet/remove-sinister/{sinister-id}
@DeleteMapping("/remove-sinister/{sinister-id}")
@ResponseBody
 public String removeSinister(@PathVariable("sinister-id") int id) {
	IsinisterService.deleteSinister(id);
	return "sinister deleted!" ;
 }
 
 
//http://localhost:8081/SpringMVC/servlet/retrieve-sinister/{sinister-id}
 @GetMapping("/retrieve-sinister/{sinister-id}")
 @ResponseBody
 public Sinister  getSinistersById(@PathVariable("sinister-id") int id) {
	Sinister s = IsinisterService.retrieveSinister(id);
 return s ;
 }
 
 
 
//http://localhost:8081/SpringMVC/servlet/calculRapidité/{sinister-id}
@GetMapping("/calculRapidité/{sinister-id}")
@ResponseBody
public String  ratioRapidité(@PathVariable("sinister-id") int sinisterId) {
return IsinisterService.CalculRapiditéReglementSinistre(sinisterId) ;
}



//http://localhost:8081/SpringMVC/servlet/CalculRatioSinSurvenu/{insurer-id}
@GetMapping("/CalculRatioSinSurvenu/{insurer-id}")
@ResponseBody
public String CalculRatioSinSurvenu(@PathVariable("insurer-id") int insurerId) {
  
 return IsinisterService.CalculRatioSinSurvenu(insurerId) ;
}



//http://localhost:8081/SpringMVC/servlet/CalculRatioRejetDemIndemn/{insurer-id}
@GetMapping("/CalculRatioRejetDemIndemn/{insurer-id}")
@ResponseBody
public String  CalculRatioRejetDemIndemn(@PathVariable("insurer-id") int insurerId) {
return IsinisterService.CalculRatioRejetDemIndemn(insurerId) ;
}


//http://localhost:8081/SpringMVC/servlet/CalculCoutMoyen/{insurer-id}
@GetMapping("/CalculCoutMoyen/{insurer-id}")
@ResponseBody
public String  CalculCoutMoyen(@PathVariable("insurer-id") int insurerId) {
return IsinisterService.CalculCoutMoyen(insurerId) ;
}



//http://localhost:8081/SpringMVC/servlet/CalculRatioRejetDemIndemn/{insurer-id}
@GetMapping("/calculSinistraliéPortfeuille/{insurer-id}")
@ResponseBody
public String  calculSinistraliéPortfeuille(@PathVariable("insurer-id") int insurerId) {
return IsinisterService.calculSinistraliéPortfeuille(insurerId) ;
}




//http://localhost:8081/SpringMVC/servlet/retrieve-sinister-ByContractId/{contract-id}
@GetMapping("/retrieve-sinister-ByContractId/{contract-id}")
@ResponseBody
public List<Sinister>   getSinistersByContract(@PathVariable("contract-id") int idContract) {   
	  List<Sinister> list =IsinisterService.retrieveSinisterByContract( idContract  ) ;
 return list ;
}




//http://localhost:8081/SpringMVC/servlet/retrieve-sinister-ByInsurerId/{insurer-id}
@GetMapping("/retrieve-sinister-ByInsurerId/{insurer-id}")
@ResponseBody
public List<Sinister>  getSinistersByInsurer(@PathVariable("insurer-id") int insurerId) {    
	List<Sinister> list = IsinisterService.retrieveSinisterByInsurer( insurerId  ) ;
	
	return list ; 

}

//http://localhost:8081/SpringMVC/servlet/retrieve-sinister-ByInsuredId/{insured-id}
@GetMapping("/retrieve-sinister-ByInsuredId/{insured-id}")
@ResponseBody
public List<Sinister>  getSinistersByInsured(@PathVariable("insured-id") int insuredId) {    
	List<Sinister> list = IsinisterService.retrieveSinisterByInsured( insuredId  ) ;
	
	return list ; 

}


 
//http://localhost:8081/SpringMVC/servlet/modify-sinister/{sinister-id}
@PutMapping("/modify-sinister/{sinister-id}")
@ResponseBody
 public String modifySinister(@RequestBody Sinister sinister ,@PathVariable("sinister-id") int idSinister) {
 
		 
		 
		 return IsinisterService.updateSinister(sinister ,  idSinister);
 }




//http://localhost:8081/SpringMVC/servlet/verifierDelai/{sinister-id}
@GetMapping("/verifierDelai/{sinister-id}")
@ResponseBody
public String  verifierDelai(@PathVariable("sinister-id") int sinisterId) {
return IsinisterService.verifierDelai(  sinisterId);

}

//http://localhost:8081/SpringMVC/servlet/traiterSinistre/{sinister-id}
@PutMapping("/traiterSinistre/{sinister-id}")
@ResponseBody
public String  traiterSinistre(@PathVariable("sinister-id") int sinisterId) throws Exception {
	String s = IsinisterService.traiterSinistre(sinisterId);
return s ;
		

}



//http://localhost:8081/SpringMVC/servlet/sendEmailToInsurer/{insurer-id}
@ResponseBody
@RequestMapping("/sendEmailToInsurer/{insurer-id}")
public String sendEmailToInsurer(@PathVariable("insurer-id") int insurerId )throws MessagingException {
	
	return IsinisterService.envoyerMailToInsurer(insurerId) ;

}



//http://localhost:8081/SpringMVC/servlet/sendEmailToInsured/{insured-id}

@ResponseBody
@RequestMapping("/sendEmailToInsured/{insured-id}")
public String sendEmailToInsured(@PathVariable("insured-id") int insuredId )  {
	
	return IsinisterService.envoyerMailToInsured(insuredId) ;

}



//http://localhost:8081/SpringMVC/servlet/suivreSinistre/{sinister-id}
@GetMapping("/suivreSinistre/{sinister-id}")
@ResponseBody
public String  suivreSinister(@PathVariable("sinister-id") int sinisterId) {
return IsinisterService.suivreSinistre(sinisterId) ;
}



//http://localhost:8081/SpringMVC/servlet/verifierDocuments/{sinister-id}
	@GetMapping("/verifierDocuments/{sinister-id}")
	@ResponseBody
	public String  getPdfInfo(@PathVariable("sinister-id")int sinisterId) throws Exception  {
		
		 
	return IsinisterService.verifierDocuments(sinisterId) ;
	
	}
	
	

//http://localhost:8081/SpringMVC/servlet/verifierFacture/{sinister-id}
	@GetMapping("/verifierFacture/{sinister-id}")
	@ResponseBody
	public double  verifierFacture(@PathVariable("sinister-id")int sinisterId) throws Exception  {
			
			 
		return IsinisterService.verifierFactureIndemnisation(sinisterId) ;
		
		}
	
	
	//http://localhost:8081/SpringMVC/servlet/predictionFraudSinister/{sinister-id}
	@GetMapping("/predictionFraudSinister/{sinister-id}")
	@ResponseBody
	public String  predictionFraudSinister(@PathVariable("sinister-id") int sinisterId) {
	return IsinisterService.predictionFraud(sinisterId) ;
	}
	
	//http://localhost:8081/SpringMVC/servlet/meteoScrapping
	@GetMapping("/meteoScrapping")
	@ResponseBody
	public String  meteoScrapping() throws Exception  {
		
		 
	return IsinisterService.meteoScrapping() ;
	
	}
	
	
	
	
	// http://localhost:8081/SpringMVC/servlet/pay-sinister/{sinister-id}
	@PutMapping("/pay-sinister/{sinister-id}")
	 @ResponseBody
	 public String payerSinistre(@PathVariable("sinister-id")int sinisterId) {
	  
	  return   IsinisterService.payerSinistre(sinisterId) ;
	 }
	
	
	// http://localhost:8081/SpringMVC/servlet/paymentsExportPdf/{contract-id}
   @GetMapping("/paymentsExportPdf/{contract-id}")
   public void exportToPDF(HttpServletResponse response,@PathVariable("contract-id")int contractId) throws DocumentException, IOException {
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


}
	/*
	
	String STRIPE_PUBLIC_KEY ="pk_test_51GvLO4Hs69O1mxqWpbXx417BCxxunZVSPHhT2Lz7STZBTthV2DQdzZCTPLOStvBLgcJHzF1kJQ16S1wQ7GyDiL3000gYIwduCf" ;
	String STRIPE_SECRET_KEY ="sk_test_51GvLO4Hs69O1mxqWa4st7RTFN01tmGzOHoU8yw9JQ7424gLojvnYNqO6U40hpnJfrevhQUvwg7jOIKY8N8V7tHEh00twihvmTg" ;
	
	 

	    @RequestMapping("/")
	    public String home(Model model) {
	        model.addAttribute("amount", 50 * 100); // In cents
	        model.addAttribute("stripePublicKey", STRIPE_PUBLIC_KEY);
	        return "index";
	    }
	    
	   

	    @RequestMapping(value = "/charge")
	    public String chargeCard(HttpServletRequest request) throws Exception {
	        String token = request.getParameter("stripeToken");
	        Double amount = 10.0;
	        IsinisterService.chargeNewCard(token, amount);
	        return "result";
	    }
	    
	    
	    */
	    
	
	





 
