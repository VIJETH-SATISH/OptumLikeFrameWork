package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataUtil {

	public HashMap<String, String> table_values;
	XSSFSheet sh;
	String Scenario_id;
	XSSFWorkbook wb;
	String File_Path;
	
	public ExcelDataUtil(String Scenario_ID, String FilePath) {
		try {			
			FileInputStream fis= new FileInputStream(FilePath);
			this.wb= new XSSFWorkbook(fis);
			this.sh = wb.getSheetAt(0);
			this.Scenario_id= Scenario_ID;
			this.File_Path = FilePath;
//			ArrayList<String> column_values= getAllColumnNames(sh);
//			Row row_values = getTheRowData(sh, Scenario_ID);
//			table_values= mapTheValues(column_values, row_values);
		}catch(FileNotFoundException e) {
			System.out.println("error in loading the sheet "+ e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/********** READING THE EXCEL DATA
			 * 1. GET THE COLUMN NAMES
			 * 2. GET THE ROW DATA
			 * 3. MAP THE COLUMN AND ROW DATA 
			 * 4. FETCH THE VALUE FROM MAPPED HASHMAP USING KEY 
	 *  ****************/
	
	public static ArrayList<String> getAllColumnNames(XSSFSheet sh){
		ArrayList<String> clnNames=new ArrayList<String>();
		XSSFRow row = sh.getRow(0);
		for(Cell cell : row) {
			clnNames.add(cell.getStringCellValue());
		}
		
		return clnNames;
	}
	
	public static Row getTheRowData(XSSFSheet sh, String scenarioName) {
		for(Row row :sh) {			
			if(isItTheRequiredRow(row, scenarioName)) {
				return row;
			}
		}		
		return null;
	}
	
	public static boolean isItTheRequiredRow(Row row, String scenarioName) {
		Cell cell = row.getCell(0);
		if(cell.getStringCellValue().equalsIgnoreCase(scenarioName)){
			return true;
		}
		return false;
	}
	
	public static HashMap<String, String>  mapTheValues(ArrayList<String> clns, Row row) {
		
		HashMap<String, String> dataTable = new HashMap<String, String>();
		
		for(Cell cell : row) {
			String key = clns.get(cell.getColumnIndex());
			String value= cell.getStringCellValue();
			dataTable.put(key, value);
		}
		
		return dataTable;
	}
	
	public String getValue(String col_val) {
		getRowCellData(sh, Scenario_id);
		return table_values.get(col_val);
	}
	
	synchronized public void getRowCellData(XSSFSheet sh, String Scenario_ID) {
		ArrayList<String> column_values= getAllColumnNames(sh);
		Row row_values = getTheRowData(sh, Scenario_ID);
		table_values= mapTheValues(column_values, row_values);
	}
	
	synchronized public HashMap<String, String> getFieldValues(){
		getRowCellData(sh, Scenario_id);
		return table_values;
	}
	
	
	/********** WRITING THE EXCEL DATA ****************/

	//
	synchronized public void updateTheFieldValues(String key, String value) {
		/*  Scenario_id   SSN         Account_Number
		  	------------- ----------- ----------------
		  	Claims        123456789   
		  	Impersonate   123456719   4569	  	
		*/  	
		/*SAY WE ARE TRYING TO ADD SCENARIO "Impersonate" IN NEW ROW AND COLUMN "Account_Number" IN NEW CELL*/	
 /*
  *|                         | Exists        |
   |----------------------   |---------------|
   | row = Scenario_id       | NO-> Add row -> get its index and scenario name  |
   | column = Account_Number | NO-> Add Cell ->fetch its index, fetch row index from scenario name->then update value |
*/
		try {
			/**** COLLECTING THE HEADER NAMES *****/
			ArrayList<String> columnValuesArr = new ArrayList<String>();
			Row row = sh.getRow(0);
			int col_index;
			int row_idx;
			for(Cell cell:row) {
				String columnValue =cell.getStringCellValue();
				columnValuesArr.add(columnValue);
				
			}
			System.out.println("current columns are "+columnValuesArr);
			
			/***CHECK FOR THE HEADER PRESENT IF NOT CREATE A NEW HEADER**/
			/* need to locate the last column index to CREATE CELL after that index
			 * use CREATE CELL method to create cell
			 * name that new header with method SET CELL VALUE
			 */
			if(!columnValuesArr.contains(key)) {
				System.out.println("column not present hence creating with key = "+key);
				col_index = columnValuesArr.size();
				Cell cell = row.createCell(col_index);
				System.out.println("creating column with value "+ col_index);
				cell.setCellValue(key);
				
			} else {
				col_index = columnValuesArr.indexOf(key);
			}
				
			/***CHECK FOR THE ROW (FIELD VALUES) PRESENT IF NOT CREATE A NEW ROW**/		
			/* if ROW (scenario ID) NOT present ADD ONE NEW ROW with scenario ID creating the row for UPCOMING FIELDS 
			 * containting other field key and VALUES via LOOP iterations
			 * get the last row number with method GET LAST ROW NUM
			 * create row with CREATE ROW method
			 */
			Row row_set_Idx;
			/**** search whether SCENARIO EXISTS in row's cell(0) or NOT if NOT add one *****/ 
			if(getTheRowData(sh,Scenario_id)==null) {			
				row_idx = sh.getLastRowNum();
				System.out.println("last row count happens to be "+ row_idx);
				System.out.println("Scenario ID is = "+ Scenario_id);
				row_set_Idx = sh.createRow(row_idx+1);
				row_set_Idx.createCell(0).setCellValue(Scenario_id);
				
				//creation of row once is enough IF NOT PRESENT LATER ON the row index would be found based on already 
				//present Scenario ID and same's index would used
			
			}else{
				//if row is already present, chec
				row_idx = getTheRowIndex(sh,Scenario_id);				
				System.out.println("row index to be set is = "+row_idx);
				System.out.println("column index to be set is = "+col_index);
				System.out.println("Value to be set in cell is = "+ value);						
				setTheFieldValues(sh, row_idx, col_index, value);			
			}
					
			FileOutputStream fos = new FileOutputStream(File_Path);
			wb.write(fos);
			fos.close();
			
		} catch(FileNotFoundException fe) {
			fe.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	synchronized public static void setTheFieldValues(XSSFSheet sh, int row_idx, int col_idx, String value) {
		/*SINCE THE ROW HAS BEEN ALREADY CREATED WE NEED TO USE GETROW*/
		Row row = sh.getRow(row_idx);
		/*SINCE CELL HAS NOT BEEN CREATED YET IN THE ROW, WE USE CREATECELL*/
		row.createCell(col_idx).setCellValue(value);
	}
	
	public static int getTheRowIndex(XSSFSheet sh, String scenrio_ID){
		int row_idx=0;
		
		for(int i=0;i<sh.getLastRowNum();i++) {
						
			if(sh.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase(scenrio_ID)) {
				row_idx=i;
				break;
			}
			row_idx++;
		}
		
		return row_idx;
	}
	
	
	
}
