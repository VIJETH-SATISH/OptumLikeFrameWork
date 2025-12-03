package Utils;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class WaitUtil {

	public static void waitForPageLoad(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25L));
		
		try {
			wait.until((webDriver) -> {
				return "complete".equals(((JavascriptExecutor)webDriver).executeScript("return document.readyState", new Object[0]));
			});
		}catch(TimeoutException var3) {
			Assert.fail("Webpage did not load with in 25 seconds!");
		}
		
	}
}
