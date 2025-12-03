package sqls;

public class EmployeeSQLConstants {
	
	public static final String CHECK_UNIQUE_SSN = "select count(*) from vijethdb.lisa_cuddy where ssn='$SubsSSN$'";
	
	public static final String CHECK_UNIQUE_LASTNAME = "SELECT COUNT(*) FROM vijethdb.lisa_cuddy WHERE lastname='$LASTNAME$'";
	
	public static final String CHECK_UNIQUE_PARTNER_EMPLOYER_ID = "SELECT COUNT(*) FROM vijethdb.lisa_cuddy WHERE partner_id='$Partner_ID$'";
	
	public static final String GET_SPENDING_ACCOUNT_NAME = "";

}
