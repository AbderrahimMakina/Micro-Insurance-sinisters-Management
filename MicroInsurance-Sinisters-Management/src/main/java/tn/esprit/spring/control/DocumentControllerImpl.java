package tn.esprit.spring.control;

import java.io.IOException;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import tn.esprit.spring.dao.entities.Documents;

import tn.esprit.spring.repository.DocumentRepository;
import tn.esprit.spring.service.IDocumentService;

@Scope(value ="session")
@Controller(value ="DocumentsController") // Name of the bean in Spring IoC
@ELBeanName(value ="DocumentsController")
public class DocumentControllerImpl {
	
	
	
	@Autowired
	IDocumentService IdocumentService ; 
	
	@Autowired
	DocumentRepository docRep ;
	
	
     
  
        
        

}
    

