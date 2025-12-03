package StepDefinitions;

import EnumUtils.GetFileUtil;
import Utils.Employee.EmployeeLoadUtil;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmployeeLoadStepDefinition {
	
	EmployeeLoadUtil employeeLoadUtilObj;
	String filePath = GetFileUtil.filePath;
	String InputSheet;
	
	public EmployeeLoadStepDefinition() {
		employeeLoadUtilObj = new EmployeeLoadUtil();
	}
	
	@When("I Generate Employee via New Hire {string}")
	public void GenerateNewEmployeeViaNewHire(String sScn_ID) throws Exception {
		log.info("Stepped inside the step definition for New Hire Employee Process!!");
		InputSheet = filePath + GetFileUtil.GetFileName("NEW_HIRE_INPUT");
		String outputSheet = filePath + GetFileUtil.GetFileName("NEW_HIRE_OUTPUT");
		log.info("We are about to begin the file data generation and mapping!!");
		employeeLoadUtilObj.generateDataNewHire(sScn_ID, InputSheet, outputSheet);
	}
}
