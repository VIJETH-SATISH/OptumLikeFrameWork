package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.common.base.Strings;


public class XMLParser {

	public static HashMap<String, String> testDataSearchAndReturnFieldValues(String application,String group){
		String strEnv = null;
		String userDirectory = FileUtils.findRootPOMDirectory();
		String basePath = "//src//main//resources//TestData//";
		String fileName = null;
		HashMap<String, String> fieldValue = new HashMap<String, String>();
		
		try {
//			Document doc =getXMLDoc("./src/main/resources/TestData/TestData_QA1.xml");
			strEnv = ConfigUtil.getEnvironment().toUpperCase();
			Document doc =getXMLDoc(userDirectory+basePath+"TestData_"+strEnv+".xml");
			doc.getDocumentElement().normalize();
			Element envRootElement = getElementFromRoot(doc.getDocumentElement(), "QA3");
			Element applicationElement = getElementFromRoot(envRootElement, application);
			Element userGroupElement = getElementFromRoot(applicationElement, group);
			NodeList nodeList = userGroupElement.getChildNodes();
			
			for(int i=0;i< nodeList.getLength();i++) {
				Node currentNode = nodeList.item(i);
				if(currentNode.getNodeType()==1) {
					fieldValue.put(currentNode.getNodeName(), currentNode.getTextContent());
				}
			}
			
			
			return fieldValue;
		} catch (Exception e) {
			return null;
		}
		
		
		
	}
	
	public static Element getElementFromRoot(Element ele, String elementByName) {
		NodeList eList = ele.getElementsByTagName(elementByName);
		Node eNode= eList.item(0);
		Element eElement =(Element)eNode;
		return eElement;
	}
	
	public static Document getXMLDoc(String filePath) throws ParserConfigurationException, SAXException, IOException{
		File xmlFile = new File(filePath);
		
		if(!xmlFile.exists()) {
			throw new FileNotFoundException();
		} else {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbuilder= dbFactory.newDocumentBuilder();
			Document doc = dbuilder.parse(xmlFile);
			return doc;
		}
		
		
	}

}
