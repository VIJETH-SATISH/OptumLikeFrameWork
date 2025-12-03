package StepDefinitions;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class AutoPredictedSearchGoogle {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.edge.driver", "C:\\Users\\V\\Downloads\\edgedriver_win64 (2)\\msedgedriver.exe");
		WebDriver driver= new EdgeDriver();
		driver.get("https://www.google.co.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
//		WebElement ele = driver.findElement(By.xpath("//*[@id=\"APjFqb\"]"));
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(2))
				.pollingEvery(Duration.ofSeconds(1));
		
		WebElement ele = wait.until(new Function<WebDriver, WebElement>() {			
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//*[@id=\"APjFqbdrf\"]"));
			}
		});
		ele.sendKeys("Selenium");
		Thread.sleep(1500);
//		List<WebElement> options = driver.findElements(By.xpath("(//ul[contains(@class,'G43f7e')])[1]/li/div/div[2]/div[1]/div[1]/spann"));
//		options.get(0).getText();
	}

}
