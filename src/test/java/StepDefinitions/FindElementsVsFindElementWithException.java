package StepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import ExtentFactory.ExtentDriverFactory;

public class FindElementsVsFindElementWithException {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.edge.driver", "C:\\Users\\V\\Downloads\\edgedriver_win64 (2)\\msedgedriver.exe");
		WebDriver driver= new EdgeDriver();
		/*org.openqa.selenium.InvalidArgumentException*/
//		driver.get("www.w3schools.com/html/html_tables.asp");
		
		driver.get("https://www.w3schools.com/html/html_tables.asp");
		
		/*java.lang.indexOutofBoundException*/
//		driver.findElements(By.xpath("//*[@id='customers44']")).get(0).findElement(By.xpath("//td[text()='Magazzini Alimentari Riuniti']")).getText();
		
		/**org.openqa.selenium.NoSuchElementException*/
//		driver.findElement(By.xpath("//*[@id='customers']")).findElement(By.xpath("//td[text()='Msssagazzini Alimentari Riuniti']")).getText();
		
		/*org.openqa.selenium.InvalidSelectorException*/
//		driver.findElement(By.xpath("//*[@@@id='customers']")).findElement(By.xpath("//td[text()='Msssagazzini Alimentari Riuniti']")).getText();
		Thread.sleep(3000);
		driver.quit();
		 	

	}

}
