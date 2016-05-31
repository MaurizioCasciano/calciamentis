package utilities.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class ExportDB {
	
	public static void makeFile(Element rootElement, String pathDtd){
		
		Document documento = new Document();
		DocType type = new DocType(rootElement.getName(), pathDtd);
		documento.setDocType(type);
		documento.setRootElement(rootElement);
		
		XMLOutputter xmlOutputter = new XMLOutputter();
		xmlOutputter.setFormat(Format.getPrettyFormat());
		
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(new File(rootElement.getName()+".xml"));
			xmlOutputter.output(documento, fileOutputStream);
		} catch (FileNotFoundException e1){
			System.err.println(e1);
		} catch(IOException e2){
			System.err.println(e2);
		}
		
	}
}
