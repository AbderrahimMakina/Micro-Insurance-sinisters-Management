package tn.esprit.spring.service;



import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.dao.entities.Contract;
import tn.esprit.spring.dao.entities.Documents;
import tn.esprit.spring.dao.entities.Sinister;
import tn.esprit.spring.repository.DocumentRepository;
import tn.esprit.spring.repository.SinisterRepository;


@Service
public class DocumentServiceImpl implements IDocumentService{
	
	@Autowired
	DocumentRepository documentRep;
	
	@Autowired
	SinisterRepository sinisterRep;
	
	

	@Override
	public List<Documents> retrieveAllDocuments() {
		List<Documents> docs = (List<Documents>) documentRep.findAll();
		return docs;
	}

	@Override
	public Documents addDocument(Documents d) {
	
		d.setReceptionDate(Calendar.getInstance().getTime());
		d.setContent("ATTESTATION DE STAGE Je soussigné(e), Mlle. Ichraf Ouhibi, gérante de l’entreprise RIRA Dev, domiciliée au Pépinière des entreprises Sousse Tech à ISET Sousse; matricule fiscale numéro 1658941/Y. Atteste que M. Abderrahim Makina a effectué un stage au sein de notre entreprise du 01/07/2020 au 31/08/2020 Cette attestation est délivrée à l’intéressé pour servir et valoir ce que de droit . Fait à Sousse, le 07 Septembre 2020 Signature");
		documentRep.save(d) ;
		
		return d;
	}

	@Override
	public void deleteDocument(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Documents updateDocument(Documents d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Documents retrieveDocumentById(int id) {
		
		return documentRep.findById(id).orElse(null);
	}

	@Override
	public Sinister affecterDocumentSinister(int idDoc, int idSinister) {
		Documents d = documentRep.findById(idDoc).orElse(null) ;
		Sinister s = sinisterRep.findById(idSinister).orElse(null) ;
		
		d.setSinister(s);
		documentRep.save(d);
		
		return s;	}
	
	
	
	
     
}
