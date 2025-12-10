package Utils;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class WaitUtil {

	/*
	 * Why does the lambda need to return something?
	 * Because wait.until(...) expects a function that returns a boolean (or a non-null value).
	 * Repeatedly calls your lambda (every 500 ms)
	 * If your lambda returns:
	 * true → the condition PASSES → wait ends
	 * false → keep waiting
	 **/
	public static void waitForPageLoad(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25L));
		
		try {
			wait.until((webDriver) -> {
				/* passing new Object[0] is optional as execute script is varargs method and used in older versions of selenium0*/
				return "complete".equals(((JavascriptExecutor)webDriver).executeScript("return document.readyState", new Object[0]));
				/* "loading" → page still loading
				   "interactive" → DOM loaded, subresources still loading
				   "complete" → page fully loaded */
			});
		} catch (TimeoutException var3) {
			Assert.fail("Webpage did not load with in 25 seconds!");
		}
		
	}
}
