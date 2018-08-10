package zapi;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import framework.global.Configuration;
import zapi.zapi1.Status;

/**
* Date : 2017-04-27
* Owner : yuhong.ma
* @author yuhong.ma
*/

public class zapiTest {

	@Test
	public void test() throws Exception {
		String url = "https://jira.oraclecorp.com";
	    String userName = "oardc-omsp_sg@oracle.com";
	    String password = "!QAZ2wsx";
	    zapi zapiTest = new zapi();
		
		generateTotalReport totalReport = new generateTotalReport();
		totalReport.totalReport();
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 'at' HH-mm");
		String time = sdf.format(cal.getTime());
		String testCycleName = "UIAuto" + "_" + time;
		
		Object testCycle = zapiTest.createTestCycle(testCycleName, url, userName, password);
		System.out.println(testCycle);
		
		String[] test ={};
		zapiTest.addExistRestTestToTestCycle(test, "src/zapi/caseId_list.csv", 136458, 30516, testCycle, url, userName, password);
		System.out.println(testCycle);
		Thread.sleep(30000);
		
		parseHTMLResults results = new parseHTMLResults();
		String resultPath = "logs/Result/" + "result.html";
		HashMap<String, String> testResults = results.parseResults(resultPath);
		
		System.out.println(testCycle);
		JSONArray testlists = zapiTest.getTestsInCycle(testCycle, url, userName, password);
		
//		JSONArray testlists = zapiTest.getTestsInCycle("93588", url, userName, password);
		
		zapiTest.updateAutomationResultToCycleBySumDes(testlists, testResults, url, userName, password);
		
		//Delete case id file
		String csvFilePath = "src/zapi/caseId_list.csv";	
		File testCaseListsfile = new File(csvFilePath);  
		testCaseListsfile.delete();
		
		
//		Object testCycle = zapiTest.createTestCycle(testCycleName, url, userName, password);
//		
//		String[] test1 ={};
//
////		zapiTest.addExistRestTestToTestCycle(test1, "C://Users//yuhma//Desktop//OMS.csv", -1, 30516, testCycle, url, userName, password);
//		zapiTest.addExistRestTestToTestCycle(test1, args[0], -1, 30516, testCycle, url, userName, password);
//		
//		parseHTMLResults results0 = new parseHTMLResults();
////		String resultPath0 = "C://Users//yuhma//Desktop//BasicReport.htm";
//		String resultPath0 = args[1];
//		HashMap<String, String> testResults0 = results0.parseOpenScriptResults(resultPath0);
//		
//		JSONArray testlists = zapiTest.getTestsInCycle(testCycle, url, userName, password);
//		
//		zapiTest.updateAutomationResultToCycleBySumDes(testlists, testResults0, url, userName, password);	
	}

}
