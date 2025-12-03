package StepDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import ExtentFactory.ExtentDriverFactory;
import io.cucumber.java.en.Given;

public class RemoveElementAttributeAndFetchValue {

	public RemoveElementAttributeAndFetchValue() {
		
	}
	
	@Given("I remove the disbaled attribute and fetch value")
	public void RemoveAttributeAndFetchValue() {
		JavascriptExecutor js = (JavascriptExecutor)ExtentDriverFactory.getInstance().getDriver();
		WebElement ele = ExtentDriverFactory.getInstance().getDriver().findElement(By.xpath("//input[@id='lname']"));
//		WebElement ele = ExtentDriverFactory.getInstance().getDriver().findElement(By.xpath("//p[@id='lname']"));
		js.executeScript("arguments[0].removeAttribute('disabled')", ele);
		System.out.println((ele.getAttribute("value")));
	}
	
	@Given("I highlight the element using Javascript Executor")
	public void HighLightElement() {
		JavascriptExecutor js = (JavascriptExecutor)ExtentDriverFactory.getInstance().getDriver();
		WebElement ele = ExtentDriverFactory.getInstance().getDriver().findElement(By.xpath("//input[@id='lname']"));
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);",  ele,"color: yellow; border: 2px solid yellow;" );
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Given("I enter date depart and return")
	public void EnterDepartReturnText() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor)ExtentDriverFactory.getInstance().getDriver();
		WebElement depart_ele = ExtentDriverFactory.getInstance().getDriver().findElement(By.xpath("(//div[contains(@class,'css-76zvg2 css-bfa6kz r-homxoj r-ubezar')])[1]"));
		WebElement return_ele = ExtentDriverFactory.getInstance().getDriver().findElement(By.xpath("(//div[contains(@class,'css-76zvg2 css-bfa6kz r-homxoj r-ubezar')])[2]"));
	
		js.executeScript("arguments[0].innerText='Tue, 28 Jan 2025';", depart_ele);
		Thread.sleep(2000);
//		js.executeScript("arguments[0].innerText='Thu, 31 Jan 2025';", return_ele);

		try {	
			WebElement return_ele_Nosuchlement = ExtentDriverFactory.getInstance().getDriver().findElement(By.xpath("(//div[contains(@class,'css-76zvg2 css-bfa6kz r-homxoj r-ubezar')])[2]"));
			return_ele_Nosuchlement.sendKeys("Thu, 31 Jan 2025");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ElementNotInteractableException with such a path found!! being caught in catch block");
		}
		Thread.sleep(2000);
		
	}
	@Given("I enter the destination and choose date of travel before clicking search")
	public void EnterDesination() throws InterruptedException {
		
		/*SENDING "BANGALORE" AS THE DESTINATION IN DESTINATION INPUT BOX "blr" IS USED*/
		ExtentDriverFactory.getInstance().getDriver().findElement(By.xpath("//*[@id='main-container']/div/div[1]/div[3]/div[2]/div[3]/div/div[3]/div[1]/div[2]/input")).sendKeys("BLR");
		Thread.sleep(4000);
		
		/*CLICKING ON NUMBER 28 IN THE CALENDER*/
		ExtentDriverFactory.getInstance().getDriver().findElement(By.xpath("//*[@id=\"main-container\"]/div/div[1]/div[3]/div[2]/div[4]/div/div[2]/div[2]/div[3]/div[2]/div/div[1]/div/div[3]/div[5]/div[2]/div/div")).click();
	}
	
	@Given("I click on the search button wrapped in try and catch block")
	public void MoveToNextStep() {
		try {			
			Thread.sleep(2000);
			ExtentDriverFactory.getInstance().getDriver().findElement(By.xpath("//*[@id='main-container']/div/div[1]/div[3]/div[2]/div[7]/div[2]/div")).click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Given("I wait after the search is sorted to verify visually")
	public void SortTheSearch() throws InterruptedException {
		Thread.sleep(4000);
	}
	
	@Given("I await for the sort element after clinking search using Try and catch block with recursive function")
	public void AwaitUsingTryAndCatch() {
		try {
			/*Clicking on Sort Button before which we wait using try and cathc block> where 
			 * we use recursive method in cath block*/
			ExtentDriverFactory.getInstance().getDriver().findElement(By.xpath("//*[@id=\"list-results-section-0\"]/div[4]/div[2]/div[2]/div")).click();
			Thread.sleep(300);
		} catch (Exception e) {
			// TODO: handle exception
			AwaitUsingTryAndCatch();
		}
		

	}
	
	@Given("I want the page to load using jsexecutor page ready state")
	public void PageLoadWithJsExecutor() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor)ExtentDriverFactory.getInstance().getDriver();
		js.executeScript("return((window.jQuery != null) && (jQuery.active === 0))").equals("true");
//		wait.until((wb)-> {
//			return "complete".equals(((JavascriptExecutor)wb).executeScript("return document.readyState", new Object[0]));
//		});
		String str_generics = "UnderStanding Generics";
//		try {
			/*FluentWait<T>*/
			Wait<WebDriver> wait = new FluentWait<WebDriver>(ExtentDriverFactory.getInstance().getDriver())
					.pollingEvery(Duration.ofSeconds(3))
					.withTimeout(Duration.ofSeconds(30))
					.ignoring(ElementClickInterceptedException.class);
//			
//			
//			WebElement sort_ele = (WebElement) wait.until(new Function<WebDriver, WebElement>(){
//				public WebElement apply(WebDriver driver) {
//					return driver.findElement(By.xpath("//*[@id=\"list-results-section-0\"]/div[4]/div[2]/div[2]/div"));
//				
//				}
//			});
			
			Wait<WebDriver> wait2 = new WebDriverWait(ExtentDriverFactory.getInstance().getDriver(), Duration.ofSeconds(40));
			WebElement ele2 = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"list-results-section-0\"]/div[4]/div[2]/div[2]/div")));
//			ele2.click();
			Thread.sleep(1000);
			js.executeScript("arguments[0].click();", ele2);
			
			Thread.sleep(4000);
			
//			if(sort_ele.isDisplayed()) {
//				sort_ele.click();
//			}
			

//			sort_ele.click();
//			ExpectedCondition<Boolean> exp = new ExpectedCondition<Boolean>() {
//				   
//				   @Override
//				   public Boolean apply(WebDriver driver) {
//				    // TODO Auto-generated method stub
//				    return null;
//				   }
//			  };

//			ExtentDriverFactory.getInstance().getDriver().findElement(By.xpath("//*[@id=\"list-results-section-0\"]/div[4]/div[2]/div[2]/div")).click();
//		} catch (ElementNotInteractableException e) {
//			// TODO: handle exception
//			
//		}
	}
	
	
	
	@Given("I remove thereadonly attribute and enter date in to and from")
	public void removeReadOnlyAndEnterText() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor)ExtentDriverFactory.getInstance().getDriver();
		WebElement depart_ele = ExtentDriverFactory.getInstance().getDriver().findElement(By.xpath("(//div[contains(@class,'css-76zvg2 css-bfa6kz r-homxoj r-ubezar')])[1]"));
		WebElement return_ele = ExtentDriverFactory.getInstance().getDriver().findElement(By.xpath("(//div[contains(@class,'css-76zvg2 css-bfa6kz r-homxoj r-ubezar')])[2]"));
		js.executeScript("arguments[0].innerText='Tue, 28 Jan 2025';", depart_ele);
		Thread.sleep(2000);
		js.executeScript("arguments[0].innerText='Thu, 31 Jan 2025';", return_ele);
		Thread.sleep(2000);
		
	}
	
	@Given("I visit the site and provide the authentication credentials")
	public void visitSiteAndEnterCrendentials() throws InterruptedException {
		
		String userName = "admin";
		String passWord = "admin";
//		ExtentDriverFactory.getInstance().getDriver().get("https://the-internet.herokuapp.com/basic_auth");
		ExtentDriverFactory.getInstance().getDriver().get("https://" + userName + ":" + passWord + "@the-internet.herokuapp.com/basic_auth");
		Thread.sleep(4000);
	}
	
	@Given("I visit the youtube for a video and click at {string} % length")
	public void clickAtTheRequiredLengthOfVideo(String str) throws InterruptedException {
		ExtentDriverFactory.getInstance().getDriver().get("https://www.youtube.com/watch?v=cZnebtC-mdE");
		WebElement progress_bar = ExtentDriverFactory.getInstance().getDriver().findElement(By.xpath("//div[@class='ytp-progress-bar']"));
		Thread.sleep(2000);
		/*PAUSING THE THE VIDEO BY PRESSING THE SPACE AS IT MAKES THE PROGRESS BAR AND SCRUBBER VISIBLE
		 * THE PROGRESS BAR AND SCRIBBER DISAPPERS AFTER 3 SECONDS OF PLAYING */
		ExtentDriverFactory.getInstance().getDriver().findElement(By.xpath("//div[@class='ytp-progress-bar']")).sendKeys(Keys.SPACE);
		
		Thread.sleep(20000);
		/*PLAYING THE THE VIDEO BY PRESSING THE SPACE AS IT MAKES THE PROGRESS BAR AND SCRUBBER VISIBLE
		 * */
		ExtentDriverFactory.getInstance().getDriver().findElement(By.xpath("//div[@class='ytp-progress-bar']")).sendKeys(Keys.SPACE);
		
		int width = progress_bar.getSize().getWidth();
		System.out.println(width);
		System.out.println((width/2)-2);

	    Actions act = new Actions(ExtentDriverFactory.getInstance().getDriver());
	    /* THIS CLICKS ON THE 75% OF THE SLIDER AS moveToElement COMES TO THE CENTER OF ELEMENT AUTOMATICALLY- 50% 
	     * OF DISTANCE WOULD BE COVERED THERE ITSELF, ADDING REST 1/4 -> 1/2 + 1/4 = 3/4 */
	    act.moveToElement(progress_bar).moveByOffset((width*1/4), 0).click().perform();
	    Thread.sleep(3000);
	    /* THIS CLICKS ON THE 100% OF THE SLIDER AS moveToElement COMES TO THE CENTER OF ELEMENT AUTOMATICALLY- 50% 
	     * OF DISTANCE(WIDTH) WOULD BE COVERED THERE ITSELF,, ADDING REST 1/2 -> 1/2 + 1/2 = 1 SUBRATICING 2 PX FOR SCRUBBER LENGTH */
	    act.moveToElement(progress_bar).moveByOffset((width/2)-2, 0).click().perform();
	    Thread.sleep(3000);
	    /* THIS CLICKS ON THE 25% OF THE SLIDER AS moveToElement COMES TO THE CENTER OF ELEMENT AUTOMATICALLY- 50% 
	     * OF DISTANCE(WIDTH) WOULD BE COVERED THERE ITSELF,, ADDING REST 1/2 -> 1/2 + 1/2 = 1 SUBRATICING 2 PX FOR SCRUBBER LENGTH */
	    act.moveToElement(progress_bar).moveByOffset(-(width*1)/4, 0).click().perform();
	    Thread.sleep(3000);
	    /** THIS CLICKS ON THE 75% OF THE SLIDER AS moveToElement COMES TO THE CENTER OF ELEMENT AUTOMATICALLY- 50% 
	     * OF DISTANCE WOULD BE COVERED THERE ITSELF **/
	    act.moveToElement(progress_bar).click().perform();
	    Thread.sleep(5000);
		
	}
	
	@Given("I visit the youtube to find position of video container from left and top")
	public void fetchPositionOfVideoContainer() throws InterruptedException{
		ExtentDriverFactory.getInstance().getDriver().get("https://www.youtube.com/watch?v=cZnebtC-mdE");
		ExtentDriverFactory.getInstance().getDriver().manage().window().maximize();
		Thread.sleep(2000);
		WebElement video_container = ExtentDriverFactory.getInstance().getDriver().findElement(By.xpath("//div[contains(@class,'html5-video-player')]"));
		Thread.sleep(10000);
		
		Point point = video_container.getLocation();
		int xcord = point.getX();
		System.out.println("Position of the webelement from left side is "+ xcord +" pixels");
		int ycord=  point.getY();
		System.out.println("Position of the webelement from top side is "+ ycord +" pixels");
//		Using Actions class
	     Actions action = new Actions(ExtentDriverFactory.getInstance().getDriver());
	     action.moveToElement(video_container).click().build().perform();
	     Thread.sleep(5000);
	
		
	}
	
	@Given("I visit the youtube and Drag the scubber along the progress bar")
	public void dragScrubber() throws InterruptedException{
		ExtentDriverFactory.getInstance().getDriver().get("https://www.youtube.com/watch?v=cZnebtC-mdE");
		ExtentDriverFactory.getInstance().getDriver().manage().window().maximize();
		Thread.sleep(2000);
		WebElement scrubber = ExtentDriverFactory.getInstance().getDriver().findElement(By.xpath("//div[@class='ytp-scrubber-container']"));
		ExtentDriverFactory.getInstance().getDriver().findElement(By.xpath("//div[@class='ytp-progress-bar']")).sendKeys(Keys.SPACE);
		WebElement progress_bar = ExtentDriverFactory.getInstance().getDriver().findElement(By.xpath("//div[@class='ytp-progress-bar-container']"));
		Dimension dm = progress_bar.getSize();
		int progress_bar_width = dm.getWidth();
		System.out.println(progress_bar_width);
		Actions action = new Actions(ExtentDriverFactory.getInstance().getDriver());
		/*dragAndDropBy will START TO move the element CONSIDERING THE START POINT AS 0 ALWAYS HENCE WHEN
		 * EVER WE ARE MAKING USE OF WIDTH OF ANY ELEMENT ( progress_bar_width) HERE WE NEED TO MAKE SURE
		 * THE STARTING POINT WOULD BE ALWAYS 0,0 SAY FOR EX. IF I HAVE MOVED THE SCRUBBER HALF WAY OF THE PROGRESS BAR A
		 * AND SAY I NEED TO MOVE TO THE 75% LATER MAKING USE OF (progress_bar_width*3)/4 WILL
		 * RESULT IN AN WRONG MOVE(DRAGGED OUT OF PROGRESS BAR) AS WE ARE ALREADY HALF WAY ALL WE HAVE TO TRAVERSE IS 1/4 OF WIDTH FROM CURRENT POSITION */
		action.moveToElement(scrubber).dragAndDropBy(scrubber, progress_bar_width/2, 0).build().perform();
		System.out.println("by 2 "+progress_bar_width/2);
		Thread.sleep(4000);
		/*THIS DRAGS TO THE 75% OF THE PROGRESS BAR AS PREVIOUSLY WE WERE AT- 50% OF WIDTH
		 * SO -> 1/2+1/4 = 3/4*/
		action.moveToElement(scrubber).dragAndDropBy(scrubber, (progress_bar_width*1)/4, 0).build().perform();
		System.out.println("by 3/4 "+(progress_bar_width*3)/4);
		Thread.sleep(4000);
		/*THIS DRAGS TO THE 25% OF THE PROGRESS BAR AS PREVIOUSLY WE WERE AT- 75% OF WIDTH WE NEED TO TRAVERSE 1/2 WAY 
		 * BACKWARDS SO -> 3/4-1/2 = 1/4*/
		action.moveToElement(scrubber).dragAndDropBy(scrubber, -(progress_bar_width*1)/2, 0).build().perform();
		System.out.println("by 1/4 "+(progress_bar_width*1)/4);
		Thread.sleep(4000);
	}
	
	
	
}
