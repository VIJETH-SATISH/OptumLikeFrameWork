package Utils;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeUtil {

	
	synchronized public static String getCurrentDate(String format) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setTimeZone(TimeZone.getDefault());
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	synchronized public static String generateDateUsingParsePath(String Datefrominputfile, String DateFormat) {
		
		String reqDate = null;
		String Reqyear = null;
		
		/* difference between String a = null and String a = new String() 
		 * Answer :
		 * public static void main (String args[]){
			    String s;       
			    System.out.println(s); // compiler error variable may not be initialized
			}
			
			V/S
			
			public static void main (String args[]){
			    String s=null;      
			    System.out.println(s); // no compiler error
			    System.out.println(s.equals("helo")); // but this will generate an exception
			}
		 */
		String[] DateFormatarr;
		String ReqDateFormat;
				
		if(DateFormat.contains("Date")) {
			DateFormatarr = DateFormat.split(":");
			ReqDateFormat = DateFormatarr[1].trim();			
		}else {
			ReqDateFormat = DateFormat.trim();
		}
		
		
		
		
		
		String DesiredDate;
		String Monthstr;
		String daystr;
		String Yearstr;
		StringBuilder MONTH = null;
		StringBuilder day = null;
		String[] Path;
		char sign;
		String Yeartemp;
		int CurrentYear;
		/*# - Date/Month:01/Day:01/Year:Current-30*/
		if(Datefrominputfile.contains("CurrentYear")) {
			
		}else {
			DesiredDate = Datefrominputfile.substring(9);/*Month:01/Day:01/Year:Current-30*/
			Path = DesiredDate.split("/");/*[Month:01,Day:01,Current-30 ]*/
			Monthstr = Path[0];/*Month:01*/
			daystr = Path[1];/*Day:01*/
			Yearstr = Path[2];/*Year:Current-30*/	
			
			
			String[] Montharr =  Monthstr.split(":");
			MONTH = new StringBuilder(Montharr[1].trim());/*01*/
			
			int length = MONTH.length();/*length - 2*/
			
			while(length<2) {
				MONTH.insert(0, "0");
				length = MONTH.toString().trim().length();
			}
			
			String[] dayarr = daystr.split(":"); /*01*/
			day = new StringBuilder(dayarr[1].trim());
			
			length = day.length(); /*length - 2*/
			
			while(length<2) {
				day.insert(0, "0");
				length = day.toString().trim().length();
			}
			
			String[] Yeararr = Yearstr.split(":"); /*[Year, Current-30]*/
			
			if(!Yeararr[1].equals(9999) && Yeararr[1].contains("Current")) {
				sign = Yeararr[1].charAt(7);/* "-" */
				Yeartemp = Yeararr[1].substring(8);/* 30 */
				
				CurrentYear = Year.now().getValue();/* 2025 */
				
				if(sign == '-') {
					Reqyear =  String.valueOf(CurrentYear - Integer.parseInt(Yeartemp)); /* 1994 */
				} else if(sign == '+') {
					Reqyear =  String.valueOf(CurrentYear + Integer.parseInt(Yeartemp));
				}					
				
			} else if(!Yeararr[1].equals(9999)) {
				Reqyear = Yeararr[1];
			} else {
				Reqyear = "9999";
			}
			
		}
		
		if(Datefrominputfile.contains("CurrentMonth")) {
			
		} else if(Datefrominputfile.contains("CurrentYear")) {
			
		} else {
			assert Reqyear!= null;
			assert MONTH!= null;/* 01 */
			
			switch(DateFormat) {
			case "Date:MM/DD/CCYY":
				reqDate = MONTH + "/" + day + "/" + Reqyear;
				break;
			case "Date:MM-DD-CCYY":
				reqDate = MONTH + "-" + day + "-" + Reqyear;
				break;
			case "Date:DD-MMM-YY":
				reqDate = day + "-" +  MONTH + "-" + Reqyear.substring(2);
				break;
			}
		}
		
		
		
		return reqDate;
	}
}
