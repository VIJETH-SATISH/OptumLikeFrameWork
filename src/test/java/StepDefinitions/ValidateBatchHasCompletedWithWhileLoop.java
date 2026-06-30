package StepDefinitions;

import PageObject.InterfaceDashPageObjects;
import io.cucumber.java.en.And;

public class ValidateBatchHasCompletedWithWhileLoop {

	InterfaceDashPageObjects interfaceDashPageObj;
	
	public ValidateBatchHasCompletedWithWhileLoop(){	
		interfaceDashPageObj = new InterfaceDashPageObjects();
	}
	
	
	@And("I look whether execution of batch is completed and completion status has appeared for a batch {string}")
	public void Validate_Batch_Completion(String sBatch) {
		try {		
			interfaceDashPageObj.CheckBatchRunningInQueue(sBatch);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
}
