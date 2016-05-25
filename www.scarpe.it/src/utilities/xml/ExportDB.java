package utilities.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import database.Database;

public class ExportDB {
	
	
	public static void makeExportXML() throws SQLException{
		Database.openConnection();
		
		String countPurchase = "SELECT COUNT(*) FROM lisca.acquisti";
		
		String qryPurchase = "SELECT * FROM lisca.acquisti";
		
		String counterExport = "SELECT COUNT(*) FROM lisca.acquisti JOIN lisca.dettagli_acquisti "
					+ "ON lisca.acquisti.idAcquisti = lisca.dettagli_acquisti.idAcquisti";
		
		String query ="SELECT * "
					 +"FROM lisca.acquisti, lisca.dettagli_acquisti, lisca.scarpe "
					 +"WHERE acquisti.idAcquisti = dettagli_acquisti.idAcquisti " 
					 +"AND dettagli_acquisti.idScarpe = scarpe.idScarpe";
		
		ResultSet numberOfPurchases = null;
		ResultSet purchases = null;
		ResultSet count = null;
		ResultSet toExport = null;
		
		try {
			numberOfPurchases = Database.executeQuery(countPurchase);
			purchases = Database.executeQuery(qryPurchase);
			count = Database.executeQuery(counterExport);
			toExport = Database.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(count != null && toExport != null){
			acquisti = new Element("acquisti"); //root
			
			numberOfPurchases.next(); //posizionamento sul risultato
			count.next(); // posizionamento sul risultato
			
			for(int i = 1; i <= numberOfPurchases.getInt(1); i++) {
				purchases.next();
				
				acquisto = new Element("acquisto");
				data = new Element("data");
				username = new Element("username");
				
				acquisto.setAttribute("num", String.valueOf(purchases.getInt("idAcquisti")));
				data.addContent(purchases.getString("data"));
				username.addContent(purchases.getString("username"));
				
				acquisto.addContent(data);
				acquisto.addContent(username);
				
				toExport.beforeFirst(); // mi riposiziono all'inizio
				
				for(int j = 1; j <= count.getInt(1); j++){
					scarpe = new Element("scarpe");
					toExport.next(); //mi metto sulla riga successiva
					
					int importo_totale = 0;
					
					if(toExport.getInt("idAcquisti") == purchases.getInt("idAcquisti")){
						idScarpa = new Element("idScarpa");
						marca = new Element("marca");
						modello = new Element("modello");
						quantita = new Element("quantita");
						prezzo_vendita = new Element("prezzo_vendita");
						importo = new Element("importo");
						
						idScarpa.addContent(toExport.getString("idScarpe"));
						marca.addContent(toExport.getString("marca"));
						modello.addContent(toExport.getString("modello"));
						quantita.addContent(String.valueOf(toExport.getInt("quantita")));
						prezzo_vendita.addContent(String.valueOf(toExport.getInt("prezzo_vendita")));
						
						importo_totale = (toExport.getInt("quantita") * toExport.getInt("prezzo_vendita"));
						
						importo.addContent(String.valueOf(importo_totale));
						
						scarpe.addContent(idScarpa);
						scarpe.addContent(marca);
						scarpe.addContent(modello);
						scarpe.addContent(quantita);
						scarpe.addContent(prezzo_vendita);
						scarpe.addContent(importo);
						
						acquisto.addContent(scarpe);
					}
				} // END for interno
			acquisti.addContent(acquisto);	
			}
		}	
	}
	
	public static void makeFile(){
		
		Document documento = new Document();
		DocType type = new DocType("acquisti.dtd");
		documento.setDocType(type);
		documento.setRootElement(acquisti);
		
		XMLOutputter xmlOutputter = new XMLOutputter();
		xmlOutputter.setFormat(Format.getPrettyFormat());
		
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(new File("acquisti.xml"));
			xmlOutputter.output(documento, fileOutputStream);
		} catch (FileNotFoundException e1){
			System.err.println(e1);
		} catch(IOException e2){
			System.err.println(e2);
		}
		
	}
	
	private static Element acquisti;
	private static Element acquisto;
	private static Element data;
	private static Element username;
	private static Element scarpe;
	private static Element idScarpa, marca, modello, quantita, prezzo_vendita, importo;
	
	
	public static void main(String[] args) throws SQLException {
		ExportDB.makeExportXML();
		ExportDB.makeFile();
	}

}
