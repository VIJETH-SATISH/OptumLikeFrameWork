package StepDefinitions;

import io.cucumber.java.en.Given;
import EnumUtils.GetFileUtil;
import Utils.ExcelDataUtil;

public class FetchAndValidateExcelWithEnumPath {

	//GetFileUtil-> class, filePath-> static variable as it doesn't change
	String filePath = GetFileUtil.filePath;
	
	@Given("I fetch value from excel where paths are stored a enum")
	public void i_fetch_value_from_excel_where_paths_are_stored_a_enum() {	    
		String readWritePath = filePath + GetFileUtil.GetFileName("READWRITE_INPUT");
		ExcelDataUtil ed = new ExcelDataUtil("Vijeth", readWritePath);
		System.out.println(ed.getValue("Country"));
		
	}

	
}
