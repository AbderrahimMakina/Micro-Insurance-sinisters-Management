package tn.esprit.spring.dao.entities;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.control.UserControllerImpl;
import tn.esprit.spring.repository.InsuredRepository;
import tn.esprit.spring.repository.SinisterRepository;

@Named
@RequestScoped
public class SignatureView {

    private String value;
    
    @Autowired
	SinisterRepository sinisterRep;
    
    @Autowired
	InsuredRepository insuredRep ;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
  
   public void  affecter ( String value) {
	   
	   int idUserConnecté =	UserControllerImpl.getIdUserC() ;
		Insured in = insuredRep.findById(idUserConnecté).orElse(null) ;
		in.setAdress(value);
		insuredRep.save(in);
	   
   }
    
    
}
