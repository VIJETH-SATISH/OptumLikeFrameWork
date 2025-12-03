package Utils;

public class GlobalParameters {

	private static final ThreadLocal<String> browserType = new ThreadLocal<String>();
	
	
	
	public static void setBrowserType(String browserType) {
		//accessing static variable within sttaic method where parameter passed possess same name as variable name
		GlobalParameters.browserType.set(browserType);
	}
	
}


