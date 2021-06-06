package tn.esprit.spring.dao.entities;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;
 
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class PdfExporter {
	
	
	 private List<Payment> listPayments;
     
	    public PdfExporter(List<Payment> listPayments) {
	        this.listPayments = listPayments;
	    }
	    
	    
	    
	    private void writeTableHeader(PdfPTable table) {
	        PdfPCell cell = new PdfPCell();
	        cell.setBackgroundColor(Color.BLUE);
	        cell.setPadding(5);
	         
	        Font font = FontFactory.getFont(FontFactory.HELVETICA);
	        font.setColor(Color.WHITE);
	         
	        cell.setPhrase(new Phrase("Payment_ID", font));
	         
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("paymentCode", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("paymentDate", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("amountPayed", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("paymentMethod", font));
	        table.addCell(cell);       
	    }
	    
	    
	    private void writeTableData(PdfPTable table) {
	        for (Payment payment : listPayments) {
	            table.addCell(String.valueOf(payment.getId()));
	            table.addCell(payment.getPaymentCode());
	            table.addCell(payment.getPaymentDate().toString());
	            table.addCell(String.valueOf(payment.getAmountPayed()));
	            table.addCell(payment.getPaymentMethod());
	        }
	    }
	    
	    public void export(HttpServletResponse response) throws DocumentException, IOException {
	     
	         
	    	  Document document = new Document(PageSize.A4);
	          PdfWriter.getInstance(document, response.getOutputStream());
	           
	          document.open();
	          Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	          font.setSize(18);
	          font.setColor(Color.BLUE);
	           
	          Paragraph p = new Paragraph("List of Users", font);
	          p.setAlignment(Paragraph.ALIGN_CENTER);
	           
	          document.add(p);
	           
	          PdfPTable table = new PdfPTable(5);
	          table.setWidthPercentage(100f);
	          table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
	          table.setSpacingBefore(10);
	           
	          writeTableHeader(table);
	          writeTableData(table);
	           
	          document.add(table);
	           
	          document.close();
	           
	      }

}
