package EnumUtils;

import java.io.File;

enum FileNameEnum {

	//this will call enum constructor with single argument
	READWRITE_INPUT("/TestData/ReadWriteCreateColumn.xlsx"),
	
	READWRITE_OUTPUT("/TestData/ReadWriteCreateColumn.xlsx"),
	
	NEW_HIRE_INPUT("/TestData/New_Hire_Input.xlsx"),
	
	NEW_HIRE_OUTPUT("/TestData/UI OUTPUT/New_Hire_Output.xlsx"),
	
	EMPLOYER_SETUP("/TestData/EDI INPUT/Employer_SetUp.csv");
	
	private String desc;
	
	public String getDescription() {
		return this.desc;
	}
	//this is enum constructor with single argument
	//,illi pass maddo value  "ExeclSheetsUsedforSelenium\\ReadWriteCreateColumn.xlsx" constructor call maadi desc aagi adhuna set maaduthe
	private FileNameEnum(String desc) {
		this.desc = desc;
	}
}

//enum kelage class define maadu

public class GetFileUtil{
	
	public static String filePath = System.getProperty("user.dir") + File.separator+"src"+File.separator+"main"+File.separator+"resources";
	
	public static String GetFileName(String FileType) {
		String Filename = "";
		FileNameEnum[] fileType = FileNameEnum.values();
		
		for(FileNameEnum ft:fileType) {
			if(FileType.equals(ft.name())) {
				Filename= ft.getDescription();
			}
			
		}
		
		return Filename.replace("/", File.separator).replace("\\",File.separator);
	}
	
	
}
