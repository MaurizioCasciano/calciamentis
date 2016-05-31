package utilities.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;

public class ImportDB {

	public static Element loadFromFile(File file) throws FileNotFoundException{
		Document toLoad = null;
		
		SAXBuilder builder =  new SAXBuilder();//new SAXBuilder(XMLReaders.DTDVALIDATING);
		//InputStream streamFile = ImportDB.class.getResourceAsStream(file);
		FileInputStream streamFile = new FileInputStream(file);
		System.out.println("streaFile" + streamFile);
		try {
			toLoad = builder.build(streamFile);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Element radiceElemento = null;
		
		if(toLoad != null && toLoad.hasRootElement()){
			radiceElemento = toLoad.getRootElement();
		}
		return radiceElemento;
	}
}
