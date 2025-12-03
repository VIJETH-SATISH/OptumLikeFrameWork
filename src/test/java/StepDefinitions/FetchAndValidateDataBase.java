package StepDefinitions;

import java.sql.SQLException;

import Utils.DataBaseUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class FetchAndValidateDataBase {

	
	public FetchAndValidateDataBase() {
		
	}
	
	@Given("I fetch column values from the DB and filter the required value")
	public void i_fetch_column_values_from_the_db_and_filter_the_required_value() throws SQLException {
		String db_value= DataBaseUtil.getColumnValueFromDB("select * from jack_ryan where lastname = 'clancy';", "country");
		System.out.println(db_value);
	}
	
	
}
