package utilities.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;

import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class ExportDB {
	
	public static void makeFile(Element rootElement, String pathDtd,String RealPath){
		System.out.println("Sono makeFile "+rootElement+pathDtd+RealPath);
		Document documento = new Document();
		DocType type = new DocType(rootElement.getName(), pathDtd);
		documento.setDocType(type);
		documento.setRootElement(rootElement);
		System.out.println("Doctype " + documento.getDocType());

		XMLOutputter xmlOutputter = new XMLOutputter();
		xmlOutputter.setFormat(Format.getPrettyFormat());
		/* Validazione xml ottenuto */
		String xmlOttenuto = xmlOutputter.outputString(documento);
		System.out.println("xmlOttenuto: " + xmlOttenuto);
		SAXBuilder builder = new SAXBuilder(XMLReaders.DTDVALIDATING);
		
		try {
			Document documentoCorretto = builder.build(new StringReader(xmlOttenuto));
			FileOutputStream fileOutputStream = new FileOutputStream(new File(RealPath+rootElement.getName()+".xml"));
			xmlOutputter.output(documentoCorretto, fileOutputStream);
		} catch (FileNotFoundException e1){
			System.err.println(e1);
		} catch(IOException e2){
			System.err.println(e2);
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		
	}
}
