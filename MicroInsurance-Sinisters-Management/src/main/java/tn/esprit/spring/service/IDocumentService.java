package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.dao.entities.Documents;
import tn.esprit.spring.dao.entities.Sinister;

public interface IDocumentService {
	
	
	List<Documents> retrieveAllDocuments();
	Documents addDocument(Documents d);
	 void deleteDocument(int id);
	 Documents updateDocument(Documents d);
	 Documents retrieveDocumentById(int id);
	 
	 Sinister affecterDocumentSinister(int idDoc,int idSinister );

}
