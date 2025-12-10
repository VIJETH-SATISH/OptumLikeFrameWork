package Utils;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import ExtentFactory.ExtentDriverFactory;
import StepDefinitions.EmployeeLoadStepDefinition;
import cucumber.deps.com.thoughtworks.xstream.InitializationException;
import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

/* Why this prints?
Because:
1.Lombok generates log
2.SLF4J logs through a backend (logback/log4j)
3.That backend prints the message to console or file

 * What does the log output look like when sing @Slf4j (lombok logger)?
 * log.info("Test started");
 * log.error("Something failed", exception);
 * log.debug("Value = {}", value);
 * 
 * 2025-01-20 10:15:32.456  INFO 12345 --- [main] com.example.MyClass : Test started
 * 2025-01-20 10:15:32.478 ERROR 12345 --- [main] com.example.MyClass : Something failed
 * java.lang.RuntimeException: <your exception message> at com.example.MyClass.myMethod(MyClass.java:25)
 * 2025-01-20 10:15:32.480 DEBUG 12345 --- [main] com.example.MyClass : Value = 42
*/

@Slf4j
public final class NavigationUtil {

	public static void mouseHoverJsClick(WebElement elementToHover) {
		if(GenericUtil.isElementDisplayed(elementToHover, 3)) {
			JavascriptExecutor js = (JavascriptExecutor)ExtentDriverFactory.getInstance();

			// Simulate mouse hover
			/*initEvent(eventType, canBubble, cancelable)
			 * Bubbling: true → event bubbles,false → event does not bubble
			 * cancelable: Whether the event can be canceled via event.preventDefault()*/
			js.executeScript(
			    "var evObj = document.createEvent('MouseEvents');" +
			    "evObj.initEvent('mouseover', true, false);" +
			    "arguments[0].dispatchEvent(evObj);",
			    elementToHover
			);
		} else {
			Assert.fail("Webelement "+ elementToHover + "is not displayed on the screen, hence Not able to select the option from drop down");
		}
	}
	
	public static void selectFromDropDown(WebElement webElement, String visibleText) {
		if(GenericUtil.isElementDisplayed(webElement, 3)) {
			try {
				(new Select(webElement)).selectByVisibleText(visibleText.trim());
			} catch (NoSuchElementException e) {
				Assert.fail("Cannot locate option with Text : "+ visibleText + " for webelement: "+ webElement);
			}
		} else {
			Assert.fail("Webelement "+ webElement + "is not displayed on the screen, hence Not able to select the option from drop down");
		}
	}
	
	public static String selectOptionWithPartialText(WebElement webElement, String partialText) throws IOException {
		if(!GenericUtil.isElementDisplayed(webElement, 3)) {					
			Assert.fail("Webelement "+ webElement + "is not displayed on the screen, hence Not able to select the option from drop down");				throw new IOException();
		 }
		
		try {
			List<WebElement> options = (new Select(webElement)).getOptions();
					
			for(WebElement option:options) {
				 if(option.getText().contains(partialText)){
					 option.click();
					 break;
				 }
			}
			
			/*ALTERNATE APPROACH using the iterator
			 * Iterator var5 = options.iterator();
			 * 
			 * while(var5.hasNext()){
			 * 	
			 * }
			 * */
			
		} catch (NoSuchElementException e) {
			log.error("No selected option found for dropdown: {}", webElement, e);
			Assert.fail("Cannot locate option with Text : "+ String.valueOf(webElement) + " for webelement: "+ webElement);			
		}
		
		return null;
	}
	
	
	public static String getSelectedDropDownValue(WebElement webElement) throws IOException {
		if(!GenericUtil.isElementDisplayed(webElement, 3)) {					
			Assert.fail("Webelement "+ webElement + "is not displayed on the screen, hence Not able to select the option from drop down");				throw new IOException();
		 }
		
		try {
			WebElement option = (new Select(webElement)).getFirstSelectedOption();
			return option.getText();
		} catch (NoSuchElementException e) {
			log.error("No selected option found for dropdown: {}", webElement, e);
			Assert.fail("Cannot locate option with Text : "+ String.valueOf(webElement) + " for webelement: "+ webElement);			
		}
		
		return null;
	}
	
	public static void jsScrollToElement(WebElement webElement) throws IOException {		
		try {
			JavascriptExecutor js = (JavascriptExecutor)ExtentDriverFactory.getDriver();
			js.executeScript("arguments[0].scrollIntoView(true)", webElement);
		} catch (NoSuchElementException e) {
			log.error("Cannot locate: "+ String.valueOf(webElement) +" webelement: " );
			Assert.fail("Cannot locate: "+ String.valueOf(webElement) + " webelement: ");			
		}
	}
	
	/*
	 * 
	 * 
	 * */
	
	public static void click(WebElement webElement) {
		try {
			
			if(!GenericUtil.isElementDisplayed(webElement,3) && !GenericUtil.isElementEnabled(webElement,3)){
				Assert.fail("WebElement:" +String.valueOf(webElement)+ " is not displayed on the screen hence clicking operation failed!");
			}else{
				webElement.click();
				WaitUtil.waitForPageLoad(ExtentDriverFactory.getDriver());
				log.info("Clicked successfully!!");
			}
			/*String.valueOf(WebElement) prints something like = ChromeDriver: chrome on WINDOWS (session id)] -> xpath: //div[@id='login']]*/
		} catch (InitializationException var2) {
			/*InitializationException in XStream usually appears when XStream cannot initialize its internal components*/
			Assert.fail("Click Operation failed on WebElement:" + String.valueOf(webElement));
		} catch (NoSuchElementException var3) {
			ExtentDriverFactory.getInstance().getDriver().navigate().refresh();
			WaitUtil.waitForPageLoad(ExtentDriverFactory.getInstance().getDriver());
			webElement.click();
		}
	}
	
	public static void actionsClick(WebElement webElement) {
		if(GenericUtil.isElementDisplayed(webElement,3)) {
			Actions builder = new Actions(ExtentDriverFactory.getInstance().getDriver());
			builder.moveToElement(webElement).build().perform();
			webElement.click();
		} else {
			Assert.fail("WebElement:" +String.valueOf(webElement)+ " is not displayed on the screen hence clicking operation failed!");
		}
	}
	
	/* new CharSequence[] => ?? because , It accepts 0 or more CharSequence parameters
	 * Varargs are just syntactic sugar for an array, 
	 * Old-fashioned = new CharSequence[] {text} as Selenium breaks it into characters internally
	 * new-Fashion = sendKeys(text);
	*/
	public static void sendKeysToElement(WebElement element, String text) {
		if(GenericUtil.isElementDisplayed(element,3)) {
			element.sendKeys(new CharSequence[] {text});			
		}else {
			Assert.fail("WebElement:" +String.valueOf(element)+ " is not displayed on the screen hence clicking operation failed!");
		}
	}
	
	public static void clearAndType(WebElement webElement, String text) {
		
		if(GenericUtil.isElementDisplayed(webElement,3)) {
			webElement.sendKeys(new CharSequence[] {Keys.DELETE});
			
			/* WHY USE while(!webElement.getAttribute("value").isEmpty())
			 * Don’t clear fields properly using .clear()& Do not respond to CTRL+A → DELETE,Have masked/JS-driven controls (like React, Angular, Salesforce, Workday)*/
			
			while(!webElement.getAttribute("value").isEmpty()) {
				webElement.sendKeys(new CharSequence[] {Keys.BACK_SPACE});
			}
					
			/* MODERN APP CAN USE
			 * Select all → delete=> webElement.sendKeys(Keys.CONTROL + "a", Keys.DELETE);*/
			webElement.clear();
			webElement.sendKeys(new CharSequence[] {text});
			
			if(webElement.getAttribute("value").trim().equals(text)) {
				log.info("Input is successfull");
			} else {
				log.error("Input field is not cleared and entered as expected!!");
			}
		} else {
			Assert.fail("WebElent "+ String.valueOf(webElement)+ " is not displayed on the screen hence clicking operation failed!");
		}
	}
	
	public static void setCheckbox() {
		
	}
	
	
	
	
}
