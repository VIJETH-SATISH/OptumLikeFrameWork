package Utils;

import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public final class NavigationUtil {

	public static void selectValueFromDropDown(WebElement webElement, String visibleText) {
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
}
