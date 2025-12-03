package TestNGIntegration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import ExtentFactory.ExtentDriverFactory;



public class ValidateYoutubeSearch {

	@Test
	public void ValidateUserYoutube() throws Exception {
//		driver.get("https://www.youtube.com/watch?v=sua7xKN0cPc");
		System.setProperty("webdriver.edge.driver", "C:\\Users\\V\\Downloads\\BrowserDrivers\\edgedriver_win64 (2)\\msedgedriver.exe");
		WebDriver driver= new EdgeDriver();
		driver.get("https://www.youtube.com/watch?v=V_jUoJIX1jQ");

		
	}
}
