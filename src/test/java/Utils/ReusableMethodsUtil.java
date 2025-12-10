package Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ReusableMethodsUtil {

	synchronized public static String randomNumberLengthbased(int Length){
		long timeSeed = System.nanoTime();
		long randSeed = (long) (Math.random() * Long.parseLong(DateTimeUtil.getCurrentDate("yyyyMMddHHmmsss")));
		long midSeed = Math.abs(timeSeed * randSeed);
		
		String s =midSeed+"";
		
		if(s.length()< Length) {
			
			s = s + randomNumberLengthbased(Length- s.length());
		}
		String subStr = s.substring(0,Length);
		return subStr;
	}
	
	public static String captureScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
}
