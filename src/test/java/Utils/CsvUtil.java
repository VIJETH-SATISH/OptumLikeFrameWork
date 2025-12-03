package Utils;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

import EnumUtils.GetFileUtil;

public class CsvUtil {
	
	public static final String filePath = GetFileUtil.filePath;
	public static String[] Path;

	synchronized public static String readDataUsingParsedPath(String Pathfrominputfile) throws IOException {
		String temp = Pathfrominputfile.substring(14);
		Path = temp.split("/");
		
		for(int i=0;i< Path.length;i++){
			temp = readDatafromCsv(filePath+ GetFileUtil.GetFileName(Path[0]), Path[1], Path[2]);
		}
		return temp;
	}
	
	synchronized public static String readDatafromCsv(String file, String Scenario_ID, String column_name) throws IOException {
		
		FileReader filereader = new FileReader(file);
		
		/*this below is try with resource It's called try-with-resource. 
		 * It's a way so as to not have to clean after yourself as the language will do it for you
		 * which means I don't have to close the "filereader" like how we do usually, the try block itslef wil
		 * DO IT for us*/
		try(CSVReader csvReader = new CSVReader(filereader)){
			String[] nextRecord;
			int colIdx=0;
			nextRecord= csvReader.readNext();/*ENTIRE 1st row is picked here, as we 
			have used readNext() only once, so all columns values on 1st ROW is stored in nextRecord[] array*/
			
			int i=0;
			/*iterate for the all columns (nextRecord[]) array on 1st ROW  and look for the matching one*/
			for(String rec: nextRecord) {
				if(rec.equals(column_name)) {
					colIdx =i;
				}
				i++;/*Column Index is picked*/
			}
			
			/*traverse through the remaining rows in sheet and store the entire entire ROW values on the picked
			 * collumn in nextRecord[] variable, 
			 * pick the 1st value in nextRecord[] array as it contains the Scenario name  */

			/*|Scenario_ID	  |Employer | Account SSN        | Birth Date |
			  |Vijeth	      |# - ParseP| #- Auto_Generated |# - Date/Month:01/Day:01/year:Current-30 |*/

			while((nextRecord=csvReader.readNext()) != null) {
				System.out.println("scenarioID column value "+nextRecord[0]);
				if(nextRecord[0].equals(Scenario_ID)) {
					return nextRecord[colIdx];/*Column value is returned of the required row*/
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			/* printStackTrace()=pinpoints line number where the exception coming from example: at package.Test.main(Test.java:74)*/
		}
		return "";
		
	}
}

	/* What is the use of printStackTrace() method in Java?
	 *  Using sysout here:
	 *  try {
	    throw new NullPointerException();
		}
		catch (NullPointerException e) {
		    System.out.println(e);
		}
		
		using printStackTrace here:
		try {
		    throw new IOException();
		}
		catch (IOException e) {
		    e.printStackTrace();
		}
		System.exit(0);
		
		Calling println(e):
	
			java.lang.NullPointerException
		
		Calling e.printStackTrace():
		
		java.io.IOException
	      	at package.Test.main(Test.java:74)
	      */
