package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class DataBaseUtil {
	
	public static Connection connection;
	public static Statement statement;
	public static ResultSet rs;

	/**
	 *  Launch MySQLWorkBench and there is table called jack_ryan in the db "vijethdb"
	 *  file is tored in path "G:\MYSQL-Queries\table_For_Selenium_HashMap.sql"
	 **/
	
	public static ArrayList<Map<Object, Object>> storeDBResultSet(String sSql) throws SQLException{
		ArrayList<Map<Object, Object>> queryResult = new ArrayList<Map<Object, Object>>();
		
		
		try {
	    	
			String username = "root";	       
			//Database Password		
			String password = "mysql@123";
			String dbUrl = "jdbc:mysql://127.0.0.1:3306/vijethdb";

			//Query to Execute		
			String query = "select * from vijethdb.lisa_cuddy;";	

//			Class.forName(DBDriver)
		    Class.forName("com.mysql.cj.jdbc.Driver");		

			
			connection = DriverManager.getConnection(dbUrl,username,password);
			
			statement = connection.createStatement();

			
			/*The ResultSet object has a cursor/pointer
			 *  which points to the current row. Initially this cursor is positioned before first row.*/
			rs = statement.executeQuery(sSql);
			
			/*The ResultSetMetaData provides information about the obtained ResultSet object like, the number of columns
 				names of the columns, datatypes of the columns, name of the table etc…*/
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount  = rsmd.getColumnCount();
			
			/*iteration over rows through resultset object*/
			while(rs.next()) {				
				Map<Object, Object> row = new HashMap<>();					
				for(int i=1;i<= columnCount;i++) {					
					row.put(rsmd.getColumnName(i), rs.getObject(i));
				}
				queryResult.add(row);
			}
			
			rs.close();
			statement.close();
			connection.close();
			
			if(queryResult.isEmpty()) {
				System.out.println("NO reults by the SQL Query, please check the SQL!!");
			}
		
		}catch (SQLException sqle) {			
			sqle.printStackTrace();
			connection.close();
			
		}catch (Exception e) {
			connection.close();
			
		}finally {
			try {
				 if(statement != null)
					 statement.close();
			}catch (SQLException se2) {
				se2.printStackTrace();
			}
			
			try {
				if(connection != null) {
					connection.close();
				}
			} catch(SQLException se) {
				
			}
		}
		return queryResult;
	}
	
	/**
	 *  Launch MySQLWorkBench and there is table called jack_ryan in the db "vijethdb"
	 *  file is tored in path "G:\MYSQL-Queries\table_For_Selenium_HashMap.sql"
	 *  it has columns "firstname", "lastname", "country", "age"
	 *  we frame query such that it always returns a single row result
	 *  we take the single row result and map them into key value pairs and store the map in ArrayList
	 **/
	
	/** We retrive map from the array list and then use ENTRY INTERFACE  
	 *  toMap.Entry interface in Java provides certain methods to access the entry in the Map. 
	 *  By gaining access to the entry of the Map we can easily read/manipulate them	
	 **/
	public static String getColumnValueFromDB(String sql, String columnName) throws SQLException {
		String columnValue = null;
		try {
			ArrayList<Map<Object, Object>> queryResult = DataBaseUtil.storeDBResultSet(sql);
			System.out.println(queryResult);/*[{country=britain, firstname=tom, age=55, lastname=clancy}*/
			loop1:
				for(Map<Object, Object> map: queryResult) {
					for(Entry<Object, Object> mapEntry : map.entrySet()) {
						System.out.println(mapEntry);/*country=britain*/
						if(mapEntry.getKey().toString().equals(columnName)) {
							System.out.println("inside seraching value loop!!");
							Object value = mapEntry.getValue();
							columnValue = value != null ? value.toString() : "null";
							break loop1;
						}
					}
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return columnValue;
		
	}
}
