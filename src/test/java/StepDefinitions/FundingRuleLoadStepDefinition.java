package StepDefinitions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ExtentFactory.ExtentDriverFactory;
import PageObject.FundingRulePageObjects;
import Utils.DataBaseUtil;
import Utils.DateTimeUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import sqls.EmployerSQLConstants;

public class FundingRuleLoadStepDefinition {
	
	FundingRulePageObjects FundingRulePageObjectsObj;

	public FundingRuleLoadStepDefinition() {
		FundingRulePageObjectsObj = new FundingRulePageObjects();
	}
	
	@Given("I am customised site for Select Drop Down")
	public void amCustomisedsiteforSelectDropDown() throws InterruptedException {
		ExtentDriverFactory.getInstance().getDriver().get("E:\\HTML%20Custom%20Pages%20Used%20For%20Selenium\\SelectDropDownEnroll.html");
//		Thread.sleep(4000);
	}
	
	@Given("I Enroll New Employee with Account through Funding Rule via {string}")
	public void enrollNewEmployeewithAccountthroughFundingRuleVia(String sEmployeeLoadType, DataTable dt) throws Exception {
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		
		for(int i=0; i< list.size(); i++) {
			String sScn_Id = list.get(i).get("Scenrio ID");
			String sPayrollIdentifier = list.get(i).get("Spending Account Type");
			String sContributionAmt = list.get(i).get("Contribution Amount");
			String sPlanYear = list.get(i).get("Plan Year Start Date");
			String sEff_Dt = list.get(i).get("Effective Date");
			String sAccount_Name = list.get(i).get("Spending Account Type");
			String sEmployer_ID = "";
			
			if(sEff_Dt.contains("Date")) {
				sEff_Dt = DateTimeUtil.generateDateUsingParsePath(sEff_Dt, "Date:MM/DD/CCYY");
				System.out.println("sEff_Dt is "+sEff_Dt);
			}
			
			if(sPlanYear.contains("Date")) {
				sPlanYear = DateTimeUtil.generateDateUsingParsePath(sPlanYear, "Date:DD-MMM-YY");
				System.out.println("sPlanYear is "+sPlanYear);
			}
			
			if(sAccount_Name == null || (sAccount_Name.equalsIgnoreCase("null"))) {                   
				/*sPayrollIdentifier=HRA*/
				/*sEmployer_ID=liberty*/
				/*sPlanYear=01-01-2025*/
				String SQL = EmployerSQLConstants.GET_SPENDING_ACCOUNT_NAME.replace("$Employer_ID", sEmployer_ID).replace("$PayrollIdentifier", sPayrollIdentifier).replace("$Plan_Year_Start_Date$", sPlanYear);
				
				ArrayList<Map<Object, Object>> queryResult = DataBaseUtil.storeDBResultSet(SQL);
				
				loop1:
				for(Map<Object, Object> map: queryResult) {
					for(Entry<Object, Object> mapEntry : map.entrySet()) {
						System.out.println(mapEntry);/*country=britain*/
						if(mapEntry.getKey().toString().equals("ACCOUNT_NAME")) {
							sAccount_Name = mapEntry.getValue().toString();							
							break loop1;
						}
					}
				}					
			}
			
			FundingRulePageObjectsObj.enrollAccountForEmployee(sAccount_Name, sContributionAmt, sEff_Dt);
				
		}
	}
}
