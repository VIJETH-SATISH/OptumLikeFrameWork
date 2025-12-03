package PageObject;

import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BaseTest.BasePage;
import ExtentFactory.ExtentDriverFactory;



public class InterfaceDashPageObjects extends PageObjectBase{

	public static ThreadLocal<Boolean> batchCompleted = new ThreadLocal<Boolean>();
	@FindBy(xpath = "//input[@type='email']")
	private WebElement interfaceDash;
	
	
	/**  entire Interface Dash- Active batches table is highlighted
	 *  and picture attached as BatchTextElement-InterfaceDash.jpg
	 **/
	@FindBy(xpath = "//textarea[@title='Search' and @id='APjFqb']")
	private WebElement batchText;
	
	
	/****** highlights just all the rows in the Interface Dash- Active batches table *********/
	@FindBy(xpath = "//*[@id='report']/tbody/tr")
	private List<WebElement> batchSize;
	

	/****** Description column all names are located Interface Dash- Active batches table ****/
	@FindBy(xpath = "//*[@id='report']/tbody/tr/td[6]")
	private List<WebElement> batchNameDescription;
	
	/** BATCH CAN BE AT 3 STATUS
	 * Running->text(Running) WILL be shown and batch will be shown in Interface Dash - Active batches table
	 * Failed-> the text(Passed) WILL be shown and batch will be shown in Interface Dash - Active batches table
	 * Passed-> the text(Passed) WILL NOT be shown and batch will be NOT shown in Interface Dash - Active batches table
	 **/

	/** LOGIC on HOW IT works
	 * For running status we try to locate the "Running" status text element
	 * when checking for Running status we use WHILE loop to check whether is the batch still RUNNING or NOT
	 * if found running ->  We keep refreshing interface dash page to refresh
	 * if not found running -> check for failure
	 **/
	
	/******CHECK OUT PICTURE ATTACHED (BatchRunning-InterfaceDash.JPG)
	 *  IN THIS PACKAGE FOR
	 *  UNDERSTANDING TAKEN FROM APP ITSELF 
	 *  **********/
	public boolean CheckBatchRunningInQueue(String sBatch) throws Exception {
		boolean batchStatus = false;
		try {
			interfaceDash.click();
			batchCompleted.set(false);
			
			String statusText = batchText.getText().trim();
			clickRefreshToCheckBatchStatus();
			
			if(statusText.contentEquals("Nothing found to display")) {
				batchCompleted.set(true);
				System.out.println("Batch is completed, No batch is Running");
			} else {
				while(isBatchStillRunning(sBatch)) {
					clickRefreshToCheckBatchStatus();
				}
			}
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			System.out.println("Element not found!!");
		}
		
		if(!(batchCompleted.get())) {
			batchStatus = checkFailedBatchInQueue(sBatch);
			
		}
		
		return !(batchStatus);
		
				
	}
	
	/******CHECK OUT PICTURE ATTACHED (BatchRunning-InterfaceDash.JPG)
	 *  IN THIS PACKAGE FOR
	 *  UNDERSTANDING TAKEN FROM APP ITSELF
	 *  **********/
	public void clickRefreshToCheckBatchStatus() {
		try {
			interfaceDash.click();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error while refreshing the interface Dash page");
		}
	}
	
	/******CHECK OUT PICTURE ATTACHED (BatchRunning-InterfaceDash.JPG)
	 *  IN THIS PACKAGE FOR
	 *  UNDERSTANDING TAKEN FROM APP ITSELF
	 *  **********/
	public boolean isBatchStillRunning(String sBatch) {
		boolean runningStatus = false;
		int batchCount = batchSize.size();
		
		try {
			if(batchCount==0) {
				
				boolean bBatchQueue = batchText.isDisplayed();
				
				if(bBatchQueue) {
					System.out.println("Batch is completed, No batch is Running");
					batchCompleted.set(true);
				}
				
			} else {
				for(int i=1;i<=batchCount;i++) {
					String sBatchName = ExtentDriverFactory.getInstance().getDriver().findElement(By.xpath("/tbody/tr["+i+"]/td[6]")).getText();
					String sStatus = ExtentDriverFactory.getInstance().getDriver().findElement(By.xpath("/tbody/tr["+i+"]/td[2]")).getText();
					
					if(sBatchName.equals(sBatch) && sStatus.equals("Running")) {
						runningStatus = true;
						break;
					}
					
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("No element found!!");
		}
		
		
		
		
		return runningStatus;
	}
	
	public boolean checkFailedBatchInQueue(String batchName) {
		boolean batchFailedStatus = false;
				
		try {
			interfaceDash.click();
			
			String statusText = batchText.getText().trim();
			if(statusText.contentEquals("Nothing found to display")) {
				batchCompleted.set(true);
				System.out.println("Batch is completed, No batch is Running");
			}else {
				if(batchSize.size()>0) {
					Optional<WebElement> batchWebElement = batchNameDescription.stream().filter(webElement -> webElement.getText().equalsIgnoreCase(batchName)).findFirst();
					
					if(batchWebElement.isPresent()) {
						String status = batchWebElement.get().findElement(By.xpath(statusText)).getText().trim();
						
						if(status.equalsIgnoreCase("Failed")) {
							batchFailedStatus = true;
						}
					}
				}
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("batch has failed!!");
		}
		
		return batchFailedStatus;
	}
}
