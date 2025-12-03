package StepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.WebElement;

public class SendKeysWithKeys {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.edge.driver", "C:\\Users\\V\\Downloads\\edgedriver_win64 (2)\\msedgedriver.exe");
		WebDriver driver= new EdgeDriver();
		driver.get("https://www.google.co.in/");
		WebElement ele = driver.findElement(By.xpath("//*[@id=\"APjFqb\"]"));
		ele.sendKeys("Selenium");
		Thread.sleep(2000);
		ele.sendKeys(Keys.CONTROL+"a");
		ele.sendKeys(Keys.CONTROL+"x");
		Thread.sleep(2000);
		ele.sendKeys(Keys.CONTROL+"v");
		
	}

}
