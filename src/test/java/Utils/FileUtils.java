package Utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
	
	public static String findRootPOMDirectory() {
		
		String rootDirectory=null;
		Path userDirectory= Paths.get(".").normalize().toAbsolutePath();
		rootDirectory = userDirectory.toString(); 
		System.out.println(rootDirectory);
		return rootDirectory;
	}

}
