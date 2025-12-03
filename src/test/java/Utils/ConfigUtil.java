package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import com.google.common.base.Strings;

import jdk.internal.org.jline.utils.Log;

public class ConfigUtil {
	public static Properties prop;

	
	public static String getConfigProperty(String strKey) throws FileNotFoundException, IOException {
		String userDirectory = FileUtils.findRootPOMDirectory();
		if(prop==null) {
			prop= new Properties();
			prop.load(new FileInputStream(userDirectory+"/src/test/resources/Resources/config.properties"));
		}
		
		if(!Strings.isNullOrEmpty(System.getProperty(strKey))) {
			return System.getProperty(strKey);
		} else {
			
			String strData = prop.getProperty(strKey);			
			if(strData != null && strData != ""){
				strData = strData.trim();			
			}else {
				Set<Object> keys = prop.keySet();
				if(!keys.contains(strKey)) {
					System.out.println("Property with name - "+ strKey + "which you are lookinf for is not found in config properties file. Please define and set the value for that or there might be some typo error with that ");
				}else {
					System.out.println("Property with name - "+ strKey + "is not initialised in the config file");
				}
			}
			
			return strData;
		}
	}
	
	public static String getEnvironment() throws IOException {
		String env = getConfigProperty("ENVIRONMENT");
		return env;
	}
	
	public static String getBrowserType() throws IOException {
		String browserType = getConfigProperty("BROWSER");
		return browserType;
	}
	
	public static String getDriverInstance() throws IOException {
		String browserType = getConfigProperty("DRIVER_INSTANCE");
		return browserType;
	}
}
