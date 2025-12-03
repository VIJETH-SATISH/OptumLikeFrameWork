package StepDefinitions;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import ExtentFactory.ExtentDriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;

public class RemoveButtonAndClickSteDefinitions {

	public RemoveButtonAndClickSteDefinitions() {
		
	}
	
	@Given("I Click on the button after being removed from DOM with apporach {string}")
	public void ClickRemovedButton(String sScn_type) {
//		List<Map<String,String>> list = dt.asMaps(String.class, String.class);
		
//		for(int i= 0; i< list.size();i++) {
//			String sScn_ID = list.get(i).get("SCENARIO_TYPE");
			try {
				JavascriptExecutor js = (JavascriptExecutor)ExtentDriverFactory.getInstance().getDriver();
				WebElement ele = ExtentDriverFactory.getInstance().getDriver().findElement(By.xpath("//div"));
				
				/**ALTERNATE WAY/DIRECT WAY TO CLICK ON THE ELEMENT WITH getElementById method**/
				js.executeScript("document.getElementById('button-1').click();");
				String node = "";
				/*<div>
					<div class="div-1" id="div-first">
						<button id="button-1">Click Here</button>
						<button id="button-2">Remove This!!<button>
					</div>
				</div>*/
				switch (sScn_type){	
				
					case "WITH REFERENCE VARIABLE REMOVE CHILD NODE ALONE":
						/**ASSIGN THE NODE TO REFERENCE VARIABLE AND THEN REMOVE THE SAME IN THE NEXT STEP
						 * AND CLICK ON THE SAME WITH REFERNCE VARIABLE**/
						node =  "document.getElementById('button-1')";
						js.executeScript(node+".parentNode.children[0].remove();");
//						js.executeScript("document.getElementById('button-1').parentNode.firstChild.remove();");
//						js.executeScript("const node = document.getElementById('button-1');"
//								+ "if (node.parentNode) {"
//								+ "node.parentNode.removeChild(node);"
//								+ "}");
						Thread.sleep(4000);
						js.executeScript(node+".click();");	 	
						System.out.println(node);
						/*THIs WILL PASS AS WE NAVIGATE TO PARENT OF THE BUTTON AND BACK TO THE SAME BUTTON AND REMOVE IT
						 * BUT WE HAVE REFERENCED IT IN A VARIABLE HENCE WILL NOT AFFECT CLICKING*/
						
						break;
						
					case "WITH REFERENCE VARIABLE REMOVE PARENT NODE ALONE":
						/**ASSIGN THE NODE TO REFERENCE VARIABLE AND THEN REMOVE THE SAME'S PARENT IN THE NEXT STEP
						 * AND CLICK ON THE SAME WITH REFERNCE VARIABLE**/
						node =  "document.getElementById('button-1')";
						js.executeScript(node+".parentNode.remove();");
						System.out.println(node);//THIS WILL BE NULL
						js.executeScript(node+".click();");
						/*THIS WILL FAIL AS WE NAVIGATE TO THE PARENT OF THE BUTTON AND REMOVE THE PARENT ITSELF */
						break;
						
					case "WITHOUT REFERENCE VARIABLE REMOVE CHILD NODE ALONE":
						/** NOT ASSIGNING THE NODE TO REFERENCE VARIABLE, REMOVING THE NODE DIRECTLY AND THEN TRY TO CLIKC ON THE 
						 * REMOVED ELEMENT **/
//						js.executeScript("arguments[0].childNodes[0].remove();", ele);
						js.executeScript("arguments[0].remove();", ele);
						Thread.sleep(4000);
						/** ONCE THE NODE IS REMOVED ALL THE CHILD ELEMENTS THAT COME UNDER THAT RESPECTIVE NODE GETS REMOVED 
						 * AND WHEN WE TRY TO CLCIK ON THE CHILD ELEMENT WE END UP IN STALE ELEMENT EXCEPTION **/
						js.executeScript("arguments[0].click();", ele);
						System.out.println("button 1 removed and parent is clicked!!");
						Thread.sleep(4000);
						/** TRY TO CLICK ON OTHER BUTTON WHICH BECOMES THE FIRST CHILD childNodes[0] **/
						js.executeScript("arguments[0].childNodes[0].click();", ele);
						System.out.println("button 1 removed and second button WHICH HAS BECOME 1ST CHILD NOW!! is clicked!!");
						break;
						
					case "WITHOUT REFERENCE VARIABLE PARENT NODE ALONE":
						/**NOT ASSIGNING THE NODE TO REFERENCE VARIABLE, REMOVING THE NODE DIRECTLY AND THEN TRY TO CLIKC ON THE 
						 * REMOVED ELEMENT**/
						js.executeScript("arguments[0].childNodes[0].parentNode.remove();", ele);
						/**ONCE THE NODE IS REMOVED ALL THE CHILD ELEMENTS THAT COME UNDER THAT RESPECTIVE NODE GETS REMOVED 
						 * AND WHEN WE TRY TO CLCIK ON THE CHILD ELEMENT WE END UP IN STALE ELEMENT EXCEPTION**/
					
						js.executeScript("arguments[0].childNodes[0].click();", ele);
						System.out.println("button 1'S PARENT removed and SAME button WITH NO PARENT is clicked!!");
						
						break;
					case "WITH REFERENCE VARIABLE POINT TO PARENT REMOVE PARENT NODE ALONE":
						/**ASSIGN THE NODE TO REFERENCE VARIABLE AND THEN REMOVE THE SAME'S PARENT IN THE NEXT STEP
						 * AND CLICK ON THE SAME WITH REFERNCE VARIABLE**/
						node =  "document.getElementById('div-first')";
						js.executeScript(node+".parentNode.childNodes[0].remove();");
						js.executeScript(node+".click();");
						/*THIS WILL PASS  AS GO FROM PARENT AND BACK TO CHILD*/
						
						 
						System.out.println("1st child of div's parent is itself is removed and clicked ");
						js.executeScript(node+".parentNode.childNodes[0].parentNode.remove();");
						js.executeScript(node+".click();");
						/*THIS WILL FAIL AS GO FROM PARENT AND BACK TO CHILD*/
						
						break;
					default:
						break;
					
				}
			
				
				/****************/
//				ExtentDriverFactory.getInstance().getDriver().findElement(By.cssSelector("div.div-1")).click();
				
				Thread.sleep(3000);
//				js.executeScript("alert('hello Vijeth');");
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		}
		
	}
	
	
	@Given("I remove the readonly attribute and enter date in to and from")
	public void RemoveReadOnlyAttribute() throws InterruptedException  {
		JavascriptExecutor js = (JavascriptExecutor)ExtentDriverFactory.getInstance().getDriver();
		
		WebElement ele = ExtentDriverFactory.getInstance().getDriver().findElement(By.xpath("//input[@id='country']"));
		js.executeScript("arguments[0].removeAttribute('readonly');", ele);
		js.executeScript("arguments[0].setAttribute('value','Sheldon Cooper!');", ele);
		Thread.sleep(4000);

	}
	
	@Given("I write xmlHttp request to make post or get call and check page loading status")
	public void makeXmlHttpRequest() {
/**		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
		    if (this.readyState == 4 && this.status == 200) {
		       // Typical action to be performed when the document is ready:
		       document.getElementById("demo").innerHTML = xhttp.responseText;
		    };
		};
		xhttp.open("GET", "filename", true);
		xhttp.send();
**/
		//Create object of XMLHttpRequest
		//open connection with type of request like GET or POST, server url where it needs to be sent, true or not 
		//onreadystatechange property specifies a function to be executed every time the status of the XMLHttpRequest object changes:
		//so when the request is sent and response is recieved the function() would be executed. Here in the below function we fetch
		//response text and display it on the UI 
		
/**		
		"var callback = arguments[arguments.length - 1];" +
        "var xhr = new XMLHttpRequest();" +
        "xhr.open('POST', '/" + serverUrl + "', true);" +
        "xhr.onreadystatechange = function() {" +
        "  if (xhr.readyState == 4) {" +
        " 	 document.getElementById(\"demo\").innerHTML = xhr.responseText;"+
        "    callback(xhr.responseText);" +
        "  }" +
        "};" +
        "xhr.send();");
**/
	}
	
	
}
