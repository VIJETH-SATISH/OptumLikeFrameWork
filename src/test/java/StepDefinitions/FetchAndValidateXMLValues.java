package StepDefinitions;

import io.cucumber.java.en.And;

import java.util.HashMap;

import Utils.XMLParser;

public class FetchAndValidateXMLValues {

	@And("I fetch value from the given XML based on parameters")
	public void Fetch_Value_From_XML() {
		HashMap<String, String> XMLFieldValue = new HashMap<String, String>();
		XMLFieldValue = XMLParser.testDataSearchAndReturnFieldValues("CYC", "APPCREDENTIALS");
		System.out.println(XMLFieldValue.get("USERNAME"));
		System.out.println(XMLFieldValue.get("PASSWORD"));
		
	}
	
}
