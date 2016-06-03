package utilities.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFileChooser;

import org.jdom2.Element;

public class TestingXML {

	public static void main(String[] args) throws SQLException, IOException {
		/*Element root = EsportaAcquisti.makeExportPurchasesXML();
		ExportDB.makeFile(root, EsportaAcquisti.getDtd());*/ 
		
		Element root2 = EsportaProdotti.makeExportShoesXML();
		ExportDB.makeFile(root2, EsportaProdotti.getDtd());
		
		/*JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION){
			System.out.println(fileChooser.getSelectedFile().getAbsolutePath());
		}
		File file = fileChooser.getSelectedFile();
		ImportaProdotti.aggiornaProdotti(file);*/
		
		

	}

}
