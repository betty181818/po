package UITestCases;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import model.LogStatistics;
//import service.LogStatisticsService;
import util.Assert;
import util.ScenarioRunning;
import framework.global.Configuration;
import framework.objects.CommonVariables;

import org.junit.Before;

import com.csvreader.CsvWriter;

/**
 * This case is super case of every test case.
 */

/**
* Date : 2017-11-08
* Owner : lixia.yuan
* @author : lixia.yuan
*/

public class BaseTestCase extends CommonVariables{
	private LogStatistics ls = new LogStatistics();
	private Calendar cal = Calendar.getInstance();
	private DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	private LogStatisticsService lss = new LogStatisticsService();
	
	private static final int PASS = 1;
	private static final int FAIL = 0;
	
	protected Assert assertVerify = new Assert();
	
	/**
	 * set test case name to database record
	 * @param testName
	 */
	//set test case name to database record
	protected void setTestName(String testName) {
		ls.setCaseName(testName);
	}
	
	protected void writeTestNameToCsvFile() throws IOException {
		String csvFilePath = Configuration.pro.getTestCasesListCsvFilePath();
		String testName = ls.getCaseName();
//		String douhao = ",";
//		testName = testName + ',';
		
//		StringBuilder builder = new StringBuilder();
//		builder.append(testName);
//		builder.append(',');		
		
		File file = new File(csvFilePath);  
        if ( !file.exists() )
            file.createNewFile();
		
        FileWriter writer = new FileWriter(file, true);
		try{
		    writer.write(testName);
		    writer.append(',');
//		    writer.write(douhao);
//		    System.out.println(testName);
//		    System.out.println(douhao);
		    writer.flush();
		    writer.close();
		}  
    	catch(IOException e)
		{
		   e.printStackTrace();
		} 

	}
	
	protected void writeTestKeyToCsvFile(String testKey) throws IOException {
		String csvFilePath = Configuration.pro.getTestCasesListCsvFilePath();
		
		File file = new File(csvFilePath);  
        if ( !file.exists() )
            file.createNewFile();
		
        FileWriter writer = new FileWriter(file, true);
		try{
		    writer.write(testKey);
		    writer.append(',');
		    writer.flush();
		    writer.close();
		}  
    	catch(IOException e)
		{
		   e.printStackTrace();
		} 

	}
	
	/**
	 * call this method when case executes successfully, handle log, add database record
	 */	
	protected void success() {
		ls.setStatus(PASS);
    	String time = sdf.format(cal.getTime());
    	Timestamp ts = Timestamp.valueOf(time);
    	ls.setCreatedOn(ts);
//    	lss.createLogStatistics(ls);
		fin.success(logHandle);
	}
	
	/**
	 * call this method when case executes unsuccessfully.
	 */
	protected void fail() {		
		ls.setStatus(FAIL);			
    	String time = sdf.format(cal.getTime());
    	Timestamp ts = Timestamp.valueOf(time);
    	ls.setCreatedOn(ts);   	
//    	lss.createLogStatistics(ls);
    	fin.fail(logHandle);
	}
	
	/**
	 * call this method when case executes successfully, handle log, add database record
	 */	
	protected void success1() {
		ls.setStatus(PASS);
    	String time = sdf.format(cal.getTime());
    	Timestamp ts = Timestamp.valueOf(time);
    	ls.setCreatedOn(ts);
//    	lss.createLogStatistics(ls);
		fin.success1(logHandle);
	}
	
	/**
	 * call this method when case executes unsuccessfully.
	 */
	protected void fail1() {		
		ls.setStatus(FAIL);			
    	String time = sdf.format(cal.getTime());
    	Timestamp ts = Timestamp.valueOf(time);
    	ls.setCreatedOn(ts);   	
//    	lss.createLogStatistics(ls);
    	fin.fail1(logHandle);
	}
	
	/**
	 * verify whether login to the server. 
	 * If not, assert running case, continue the next case.
	 * If yes, continue the running case.
	 */
	protected void verifyLogin() {
		try{
			assertVerify.verifyTrue(loggedIn);			
		}catch (Exception e){
			fin.fail(logHandle);
		}
	}
	

}
