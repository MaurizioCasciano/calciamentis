package utilities.xml;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.jdom2.Content;
import org.jdom2.Element;


import database.Database;

public class ImportaProdotti {
	
	public static void aggiornaProdotti(File file) throws SQLException, IOException{
		loadPrewItems();
		Element scarpe = ImportDB.loadFromFile(file);
		
		List<Element> children = scarpe.getChildren();
		
		boolean aggiornato = false;
		numOfPrewItems.next();
		int sizeOfActualDb = numOfPrewItems.getInt(1);
		
		for(int i = 0; i < children.size(); i++){
			
			//System.out.println(children.get(i).getCType());
			if(children.get(i).getCType() != Content.CType.Text){
				Element scarpa = children.get(i);
				
				//System.out.println("name: "+scarpa.getName());
				//System.out.println("child: " + scarpa.getChild("idScarpe").getText());
				//System.out.println("gct :" + scarpa.getChildText("idScarpe"));
				
				int idScarpe = Integer.parseInt(scarpa.getChild("idScarpe").getText());
				
				for(int j = 1; j <= sizeOfActualDb; j++){
					prewItems.next();
					
					if(prewItems.getInt("idScarpe") == idScarpe){
						int quantitaAttuale = prewItems.getInt("quantitaDisp");
						int quantitaDaAggiungere = Integer.parseInt(scarpa.getChild("quantitaDisp").getText());
						int nuovaQuantita = quantitaAttuale + quantitaDaAggiungere;
						
						PreparedStatement update = Database.getPreparedStatement(templateUpdate);
						update.setInt(1, nuovaQuantita);
						update.setInt(2, prewItems.getInt("idScarpe"));
						
						update.executeUpdate();
						aggiornato = true;
						break;
					} 	
				}
				prewItems.beforeFirst();
				
				if(!aggiornato){
				PreparedStatement insertScarpe = Database.getPreparedStatement(templateInserimentoScarpe);
				PreparedStatement insertImmagini = Database.getPreparedStatement(templateInserimentoImmagini);
				PreparedStatement insertDettagli = Database.getPreparedStatement(templateInserimentoDettagli);
				
				insertScarpe.setInt(1, idScarpe);
				insertScarpe.setString(2, scarpa.getChild("marca").getText());
				insertScarpe.setString(3, scarpa.getChild("modello").getText());
				insertScarpe.setInt(4, Integer.parseInt(scarpa.getChild("prezzo_vendita").getText()));
				insertScarpe.setInt(5, Integer.parseInt(scarpa.getChild("prezzo_acquisto").getText()));
				insertScarpe.setInt(6, Integer.parseInt(scarpa.getChild("quantitaDisp").getText()));
				insertScarpe.setInt(7, Integer.parseInt(scarpa.getChild("scorta_minima").getText()));
				insertScarpe.setString(8, scarpa.getChild("alt").getText());
				insertScarpe.setString(9, scarpa.getChild("descrizione").getText());
				
				insertScarpe.executeUpdate();
				
				System.out.println(scarpa.getChild(templateInserimentoDettagli));
				Element images = scarpa.getChild("immagini");
				List<Element> urls = images.getChildren();
				
				for(int j = 0; j < urls.size(); j++){
					if(urls.get(j).getCType() != Content.CType.Text){
						insertImmagini.setObject(1, null);
						insertImmagini.setInt(2, idScarpe);
						insertImmagini.setString(3, urls.get(j).getText());
						insertImmagini.executeUpdate();
					}
				}
				
				Element details = scarpa.getChild("dettagli");
				List<Element> detail = details.getChildren();
				
				for(int z = 0; z < detail.size(); z++){
					if(detail.get(z).getCType() != Content.CType.Text){
						insertDettagli.setObject(1, null);
						insertDettagli.setInt(2, idScarpe);
						insertDettagli.setString(3, detail.get(z).getChild("intestazione").getText());
						insertDettagli.setString(4, detail.get(z).getChild("corpo").getText());
						insertDettagli.executeUpdate();
					}
				}
			}	
		}
		}
	}
		
	
	public static void loadPrewItems(){
		Database.openConnection();
		String count = "SELECT COUNT(*) FROM lisca.scarpe";
		String query = "SELECT scarpe.idScarpe, scarpe.quantitaDisp FROM lisca.scarpe";
		
		try {
			numOfPrewItems = Database.executeQuery(count);
			prewItems = Database.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private static ResultSet prewItems, numOfPrewItems;
	private static String templateInserimentoScarpe, templateInserimentoImmagini;
	private static String templateInserimentoDettagli,templateUpdate;
			
	static{
		templateInserimentoScarpe = "INSERT INTO scarpe VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
		templateInserimentoImmagini = "INSERT INTO immagini VALUES(?, ?, ?);";
		templateInserimentoDettagli = "INSERT INTO dettagli VALUES(?, ?, ?, ?);";
		templateUpdate = "UPDATE scarpe SET scarpe.quantitaDisp = ? WHERE scarpe.idScarpe = ?";
	}

}
