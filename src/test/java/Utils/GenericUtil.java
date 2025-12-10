package Utils;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import ExtentFactory.ExtentDriverFactory;


public class GenericUtil {

	
	/*I'm initialisg with false value while declaring this threadlocal itself hence I can use this as "final"
	 * (THIS IS NOT SAME AS staleCounter.set(false) which caused my confusion
	 * like how if it was set to false initially can be set again though final antha)
	 * and can set this value to true in step -->staleCounter.set(true);
	 * Once set to true it cannot be set to anything else as its final
	 * and since each and every thread gets its own ThreadLocal I can use */
	private static final ThreadLocal<Boolean> staleCounter = ThreadLocal.withInitial(()->{
		return false;
	});
	
	public GenericUtil() {
		
	}
	
	public static boolean isElementDisplayed(WebElement webElement, int seconds) {
		boolean flag= false;
		
		try {
			/*since we use the wrapper class ThreadLocal<Boolean> in staleCounter we need to Wraper of Boolean  */
			flag = (Boolean) (new FluentWait<WebDriver>(ExtentDriverFactory.getInstance().getDriver())).withTimeout(Duration.ofSeconds((long) seconds)).pollingEvery(Duration.ofSeconds(1L)).ignoring(NoSuchElementException.class).until((d)->{
				return webElement.isDisplayed() || webElement.isEnabled() ;
			});
		} catch (TimeoutException var4) {
			
			System.out.println("Element "+webElement+ "is Not Displayed within the specified time limit seconds :"+seconds);
			
		} catch (StaleElementReferenceException var5) {
			
			if(!(Boolean) staleCounter.get()) {
				staleCounter.set(true); 
				ExtentDriverFactory.getInstance().getDriver().navigate().refresh();
				WaitUtil.waitForPageLoad(ExtentDriverFactory.getInstance().getDriver());
				isElementDisplayed(webElement, seconds);
			}
			
		}
		
		if(!flag) {
			System.out.println("Element not visible:--> "+ webElement);
		} else {
			System.out.println("Element Is visible:--> "+ webElement);
		}
		
		return flag;
	}
	
	public static boolean isElementEnabled(WebElement webElement, int seconds) {
		boolean flag= false;
		
		try {
			/*since we use the wrapper class ThreadLocal<Boolean> in staleCounter we need to Wraper of Boolean  */
			flag = (Boolean) (new FluentWait<WebDriver>(ExtentDriverFactory.getInstance().getDriver())).withTimeout(Duration.ofSeconds((long) seconds)).pollingEvery(Duration.ofSeconds(1L)).ignoring(NoSuchElementException.class).until((d)->{
				return webElement.isDisplayed() || webElement.isEnabled() ;
			});
		} catch (TimeoutException var4) {
			
			System.out.println("Element "+webElement+ "is Not Displayed within the specified time limit seconds :"+seconds);
			
		} catch (StaleElementReferenceException var5) {
			
			if(!(Boolean) staleCounter.get()) {
				staleCounter.set(true); 
				ExtentDriverFactory.getInstance().getDriver().navigate().refresh();
				WaitUtil.waitForPageLoad(ExtentDriverFactory.getInstance().getDriver());
				isElementDisplayed(webElement, seconds);
			}
			
		}
		
		if(!flag) {
			System.out.println("Element not visible:--> "+ webElement);
		} else {
			System.out.println("Element Is visible:--> "+ webElement);
		}
		
		return flag;
	}
}
