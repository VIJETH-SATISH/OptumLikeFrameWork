package Utils;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import BaseTest.BasePage;
import ExtentFactory.ExtentDriverFactory;
import PageObject.PageObjectBase;

public class WebTableUtil {
	PageObjectBase pageObjectBaseBasedObj;
	
	public WebTableUtil() {
		pageObjectBaseBasedObj = new PageObjectBase();
	}

	/**
	 * Finds link automatically on specified rownumber and columnnumber - rownumber
	 * being excluding the header itself
	 *
	 * @param tableNumber
	 * @param rowNumber
	 * @param columnName
	 * @throws InterruptedException
	 */
	synchronized public void findAndClickLinkInSelectedRow(Integer tableNumber, Integer rowNumber, String columnName) throws InterruptedException {		
	    WebElement rowHeader = ExtentDriverFactory.getDriver().findElement(By.xpath("(.//*[@id='listTable'])[" + tableNumber + "]/"));
	    List<WebElement> tableColumns = rowHeader.findElements(By.tagName("th")); // headers 
	    int numOfCols = tableColumns.size(); // headers 
	    
	    for (int i = 1; i <= numOfCols; i++) {
	        WebElement colToBeIdentified = ExtentDriverFactory.getDriver().findElement(By.xpath("(.//*[@id='listTable'])[" + tableNumber + "]/"));
	        if (colToBeIdentified.getText().equals(columnName)) {
	            String xPathRowCol = "(.//*[@id='listTable'])[" + tableNumber + "]/tbody/tr[" + rowNumber + "]/td[" + i + "]";
	            WebElement linkElement = ExtentDriverFactory.getDriver().findElement(By.xpath(xPathRowCol + "/a"));
	            PageObjectBase.PageLoadWait();
	            
	            BasePage.mediumWait.get().until(ExpectedConditions.elementToBeClickable(linkElement));
	            pageObjectBaseBasedObj.jse.executeScript("arguments[0].click();", linkElement);
	            PageObjectBase.PageLoadWait();
	            break;
	        }
	    }
	}
	
	/**
	 * Finds link automatically on specified rownumber and columnnumber 
	 * @param IdentifierColName - name of the column that has the link
	 * @param LinkToBeClicked   - Link Text name to be clicked on
	 * @param ListTableNum      - There could be multiple web tables with id = listTable on th CAMS UI. Provide the occurence fo the Web Table link needs to be clicked
	 * @throws java.lang.InterruptedException
	 * @author V
	 */
		
    synchronized public void clickLinkInListTable(String IdentifierColName, String LinkToBeClicked, String ListTableNum) throws InterruptedException {
        WebElement IdentifierCol = null;
        WebElement IdentifierRow = null;
        boolean linkfound = false;

        // get all Rows of a list table
        List<WebElement> ListTable_INI_TBL_Rows = ExtentDriverFactory.getDriver().findElements(By.xpath("(//div[@class='simple_list_panel_inner']//table[gid='null']][" + ListTableNum + "]//tbody/tr"));

        // get all Columns of a list table
        List<WebElement> ListTable_INI_TBL_Cols = ListTable_INI_TBL_Rows.get(0).findElements(By.tagName("td"));

        for (int i = 1; i < ListTable_INI_TBL_Cols.size(); i++) {   
        	// UI Columns
        	//Column index  should be picked up for locating requrred column
        	PageObjectBase.PageLoadWait();
        	IdentifierCol = ExtentDriverFactory.getDriver().findElement(By.xpath("(.//*[@class='simple_list_panel_inner']//table[@id='null'])["+ ListTableNum +"]/tbody/tr[1]/th[" + i + "]"));
    		
            if (IdentifierCol.getText().equalsIgnoreCase(IdentifierColName)) {
                for (int j = 2; j <= ListTable_INI_TBL_Rows.size(); j++) {   // UI Rows
                	//iterate through rows to check the required directory like eearchieve, eereceive, when matches required traverse via xpath and click
                	IdentifierRow = ExtentDriverFactory.getDriver().findElement(By.xpath("(//div[@class='simple_list_panel_inner']//table[@id='null'])[" + ListTableNum + "]//tbody/tr[" + j + "]/td[" + i + "]"));

                    if (IdentifierRow.getText().equals(LinkToBeClicked)) {                	
                        WebElement Clicklink = ExtentDriverFactory.getDriver().findElement(By.xpath("(//div[@class='simple_list_panel_inner']//table[gid='null']][" + ListTableNum + "]//tbody/tr[" + i + "]/td[" + j + "]"));
                        PageObjectBase.PageLoadWait();
                        
                        BasePage.mediumWait.get().until(ExpectedConditions.elementToBeClickable(Clicklink));	                      
                        pageObjectBaseBasedObj.jse.executeScript("arguments[0].click();", Clicklink);
                        PageObjectBase.PageLoadWait();
                        linkfound = true;
                        Assert.assertTrue("Link to be clicked is found & clicked", linkfound);
                        break;//break from row iteration loop
                    }
                }
                break;//break from column iteration loop
            }

            if (linkfound == false) {
                Assert.assertTrue("Link to be clicked is NOT found on UI : " + ExtentDriverFactory.getDriver().getTitle(), linkfound);
            }
        }
    }
	
}