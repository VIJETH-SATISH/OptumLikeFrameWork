package StepDefinitions;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import EnumUtils.GetFileUtil;
import Utils.ExcelDataUtil;
import io.cucumber.java.en.Given;

public class WriteInExcelWithRowAndColumnCreationStepDefinition {

	
	String filePath = GetFileUtil.filePath;
	
	@Given("I Write in the Excel Sheet the required output in the trial attempt")
	public void i_fetch_value_from_excel_where_paths_are_stored_a_enum() {	    
		
	}

}
