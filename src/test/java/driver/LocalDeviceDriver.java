package driver;

import java.util.HashMap;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import ExtentFactory.ExtentDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LocalDeviceDriver {

	public LocalDeviceDriver() {
		
	}
	public static void getLocalDeviceDriver() {
		HashMap edgePrefs;
		if(ExtentDriverFactory.browserType.equalsIgnoreCase("EDGE")) {
			System.out.println("Choosing Edge!!");
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments(new String[] {"--start-maximized"});
			edgeOptions.addArguments(new String[] {"--disable-extension"});
			edgeOptions.addArguments(new String[] {"--test-type"});
			edgeOptions.addArguments(new String[] {"--ignore-certificate-errors"});
			edgeOptions.addArguments(new String[] {"--allow-running-insecure-content"});
			edgeOptions.addArguments(new String[] {"--disable-popup-blocking"});
			edgeOptions.addArguments(new String[] {"--always-authorize-plugins"});
			edgeOptions.addArguments(new String[] {"--disable-infobars"});
			edgeOptions.addArguments(new String[] {"--enable-automation"});
			edgePrefs = new HashMap();
			edgePrefs.put("useAutomationExtension", false);
			System.out.println("Edge arguments and preffs set!!");
//			edgePrefs.put("download.default_directory", BaseUtil.getDownloadsPath());
			edgeOptions.setExperimentalOption("prefs", edgePrefs);
			WebDriverManager.edgedriver().clearDriverCache().setup();
			ExtentDriverFactory.setDriver(new EdgeDriver(edgeOptions));
			
			
		} else {
			
		}
		
	}
}


