package utilities.xml;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdom2.Element;

import database.Database;

public class EsportaProdotti {
	
	public static Element makeExportShoesXML(String pathDtd) throws SQLException{
		dtd=pathDtd;
		Database.openConnection();
		
		String countItems = "SELECT COUNT(*) FROM lisca.scarpe";
		String items = "SELECT * FROM lisca.scarpe";
		String images = "SELECT * FROM lisca.immagini";
		String countImages = "SELECT COUNT(*) FROM lisca.immagini";
		String details = "SELECT * FROM lisca.dettagli";
		String countDetails = "SELECT COUNT(*) FROM lisca.dettagli";
		
		ResultSet numOfItems = null;
		ResultSet itemsToExport = null;
		ResultSet imageToExport = null;
		ResultSet numOfImages = null;
		ResultSet detailsToExport = null;
		ResultSet numOfDetails = null;
		
		try {
			numOfItems = Database.executeQuery(countItems);
			itemsToExport = Database.executeQuery(items);
			numOfImages = Database.executeQuery(countImages);
			imageToExport = Database.executeQuery(images);
			numOfDetails = Database.executeQuery(countDetails);
			detailsToExport = Database.executeQuery(details);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(numOfItems != null && items != null){
			if(numOfImages != null && imageToExport != null){
				numOfImages.next();
				hasImage = true;
			}
			
			if(numOfDetails != null && detailsToExport != null){
				numOfDetails.next();
				hasDetails = true;
			}
			
			scarpe = new Element("scarpe"); //root element
			
			numOfItems.next(); // mi posiziono sul risultato
			
			for(int i = 1; i <= numOfItems.getInt(1); i++){
				itemsToExport.next(); // mi posiziono sulla riga successiva
				
				scarpa = new Element("scarpa");
				
				idScarpe = new Element("idScarpe");
				marca = new Element("marca");
				modello = new Element("modello");
				prezzo_vendita = new Element("prezzo_vendita");
				prezzo_acquisto = new Element("prezzo_acquisto");
				quantitaDisp = new Element("quantitaDisp");
				scorta_minima = new Element("scorta_minima");
				alt = new Element("alt");
				descrizione = new Element("descrizione");
				immagini = new Element("immagini");
				dettagli = new Element("dettagli");
				
				idScarpe.addContent(String.valueOf(itemsToExport.getInt("idScarpe")));
				marca.addContent(itemsToExport.getString("marca"));
				modello.addContent(itemsToExport.getString("modello"));
				prezzo_vendita.addContent(String.valueOf(itemsToExport.getInt("prezzo_vendita")));
				prezzo_acquisto.addContent(String.valueOf(itemsToExport.getInt("prezzo_acquisto")));
				quantitaDisp.addContent(String.valueOf(itemsToExport.getInt("quantitaDisp")));
				scorta_minima.addContent(String.valueOf(itemsToExport.getInt("scorta_minima")));
				alt.addContent(itemsToExport.getString("alt"));
				descrizione.addContent(itemsToExport.getString("descrizione"));
				
				scarpa.addContent(idScarpe);
				scarpa.addContent(marca);
				scarpa.addContent(modello);
				scarpa.addContent(prezzo_vendita);
				scarpa.addContent(prezzo_acquisto);
				scarpa.addContent(quantitaDisp);
				scarpa.addContent(scorta_minima);
				scarpa.addContent(alt);
				scarpa.addContent(descrizione);
				
				if(hasImage){
					for(int j = 1; j <= numOfImages.getInt(1); j++){
						
						boolean isImage = imageToExport.next();
						if(isImage && itemsToExport.getInt("idScarpe") == imageToExport.getInt("scarpa")){
							url = new Element("url");
							System.out.println(imageToExport.getString("url"));
							url.addContent(imageToExport.getString("url"));
							immagini.addContent(url);
						}	
					}
					scarpa.addContent(immagini);
				}
				
				imageToExport.beforeFirst();
				
				if(hasDetails){
					for(int z = 1; z <= numOfDetails.getInt(1); z++){
						boolean isDetail = detailsToExport.next();
						if(isDetail && itemsToExport.getInt("idScarpe") == detailsToExport.getInt("scarpa")){
							dettaglio = new Element("dettaglio");
							intestazione = new Element("intestazione");
							corpo = new Element("corpo");
						
							intestazione.addContent(detailsToExport.getString("intestazione"));
							corpo.addContent(detailsToExport.getString("corpo"));
							dettaglio.addContent(intestazione);
							dettaglio.addContent(corpo);
							dettagli.addContent(dettaglio);
						}
					}
					detailsToExport.beforeFirst();
					scarpa.addContent(dettagli);
				}	
				scarpe.addContent(scarpa);// aggiungo a root	
			}
		}
		return scarpe;
	}
	
	public static String getDtd(){
		return dtd;
	}
	
	private static Element scarpe;
	private static Element scarpa;
	private static Element idScarpe, marca, modello, prezzo_vendita, prezzo_acquisto;
	private static Element quantitaDisp, scorta_minima, immagini, url;
	private static Element alt, descrizione, dettagli, dettaglio, intestazione, corpo;
	private static boolean hasImage, hasDetails;
	
	private static String dtd;
	
	static{	
		hasImage = false;
		hasDetails = false;
	}


}
