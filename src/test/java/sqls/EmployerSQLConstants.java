package sqls;

public class EmployerSQLConstants {
	
	public static final String CHECK_UNIQUE_PARTNER_EMPLOYER_ID = "SELECT COUNT(*) FROM vijethdb.lisa_cuddy WHERE partner_id='$Partner_ID$'";
	
	public static final String GET_SPENDING_ACCOUNT_NAME = "select * from vijethdb.robertchase where Plan_Year like '%$sPlanYear$%' and PayrollIdentifier like '%$sPayrollIdentifier$%' and Employer_ID='$sEmployer_ID$';";
}
