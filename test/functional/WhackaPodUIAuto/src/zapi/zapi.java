package zapi;

import java.io.IOException;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.csvreader.CsvReader;
//import com.gargoylesoftware.htmlunit.javascript.host.intl.DateTimeFormat;

import HTTPClient.HTTPResponse;
import HTTPClient.ModuleException;
import HTTPClient.NVPair;
import HTTPClient.ParseException;
import HTTPClient.ProtocolNotSuppException;
import HTTPClient.URI;
import classes.JIRAHttpConnection;
import classes.JIRAHttpException;
 
/**
* Date : 2017-07-19
* Owner : yuhong.ma
* @author yuhong.ma
*/

/** Helper class for calling ZAPI */
public class zapi {
	 
    /** Status IDs enum */
    public enum Status {
        PASS(1), FAIL(2), WIP(3), BLOCKED(4), UNEXECUTED(0);
        private final int value;
 
        private Status(final int value) {
            this.value = value;
        }
 
        public int getValue() {
            return value;
        }
    }
 
    /** URLS */
    private static final String ZAPI_URL = "jira/rest/zapi/latest/";    
    private static final String get_project_url = "jira/rest/zapi/latest/util/project-list";
    private static final String get_version_url = "jira/rest/zapi/latest/util/versionBoard-list?projectId=";
    private static final String get_cycles_url = "jira/rest/zapi/latest/cycle";
//    private static final String create_test_issue_url = "jira/rest/api/2/issue/";
//    private static final String get_project_fields_url = "jira/rest/api/2/issue/createmeta";
//    private static final String issue_search_url = "jira/rest/api/2/search";
    private static final String add_test_to_cycle_url = "jira/rest/zapi/latest/execution/addTestsToCycle";
    private static final String get_executions_url = "jira/rest/zapi/latest/execution";
//    private static String execute_url = "jira/rest/zapi/latest/execution";
//    private static final String update_attachment_url = "jira/rest/zapi/latest/attachment?entityId=";  
    
 
//    /** HTTP Proxy details */
//    private static final boolean USE_PROXY = false;
//    private static final String PROXY_IP = "cn.proxy.jp.oracle.com";
//    private static final int PROXY_PORT = 80;
// 
//    private static final HttpHost HTTP_HOST_PROXY = new HttpHost(PROXY_IP, PROXY_PORT);
//    private static final Proxy PROXY = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_IP,
//            PROXY_PORT));
 
    /** JIRA credentials: format "username:password" or "" for none. */
//    private static final String CREDENTIALS = "oardc-omsp_sg@oracle.com:!QAZ2wsx";
    
    private static String url = "https://jira.oraclecorp.com";
    private static String userName = "oardc-omsp_sg@oracle.com";
//    private static String userName = "yuhong.ma@oracle.com";
    private static String password = "!QAZ2wsx";
    
//    final public JIRAHttpConnection conn = new JIRAHttpConnection(new URI(url), userName, password.toCharArray(), "JIRA");
    
//    private static final Cookie cookie = getCookie();
    
//    private static final String UNIQUE_ID = "OMSP Automation Function";
 
    // ================================================================================
    // ZAPI methods
    // ================================================================================
 
    /**
     * Gets the Project ID for the project. 
     * 
     * @param projectName
     * @throws IOException
     * @return the ID for the specified project
     * @throws ProtocolNotSuppException 
     * @throws JSONException 
     * @throws InterruptedException 
     * @throws ParseException 
     * @throws ModuleException 
     */
    
    public static String getProjectId(final String projectName, String url, String userName, String password)
            throws IOException, JSONException, InterruptedException, ParseException, ModuleException {
        // Get list of versions on the specified project            
        JIRAHttpConnection conn = new JIRAHttpConnection(new URI(url), userName, password.toCharArray(), "JIRA");
    	HTTPResponse response = conn.Get(get_project_url); 
    	int statusCode = response.getStatusCode();
    	String responseData = new String (response.getData());
    	if (statusCode == 200) {
//    		System.out.println("Response from Get call: " + responseData);
	        if (responseData.equals(null)) 
	        {
	        	throw new IllegalStateException("JSONObject is null");
	        }
	        
	        JSONObject responseObject = new JSONObject(new String(responseData));
	        
	        final JSONArray projectOptions = (JSONArray) responseObject.get("options");
	        
	        // Iterate over versions
	        for (int i = 0; i < projectOptions.length(); i++) {
	            final JSONObject obj2 = projectOptions.getJSONObject(i);
	            // If label matches specified version name
	            if (obj2.getString("label").equals(projectName)) {
	                // Return the ID for this version
	                return obj2.getString("value");
	            }
	        }
	        throw new IllegalStateException("Project ID not found for ProjectName=" + projectName);
    	} 
    	else 
    	{
    		return null;    	 
    	}
    }
    
//    public static void main(String[] args) throws JSONException, IOException, InterruptedException, ParseException, ModuleException, JIRAHttpException, java.text.ParseException{
////    	String projectId = getProjectId("Oracle Migration Services", url, userName, password);
////    	String versionId = getVersionID("1.0", "30516", url, userName, password);
////    	updateTestExecution("1122331", Status.PASS,
////                "test automation", url, userName, password);
//    	createTestCycle("AutomationTime", url, userName, password);
////    	System.out.println(versionId);
//    	
////		Calendar cal = Calendar.getInstance();
////		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 'at' HH-mm");
////		String time = sdf.format(cal.getTime());
////    	
////		String testCycleName = "YuhongAuto" + "_" + time;
////		
////		Object testCycle = createTestCycle(testCycleName, url, userName, password);
////		
////		String[] test1 ={};
////		addExistRestTestToTestCycle(test1, "C://Users//yuhma//Desktop//OMS.csv", -1, 30516, testCycle, url, userName, password);
////		
////		parseHTMLResults results0 = new parseHTMLResults();
////		String resultPath0 = "C://Users//yuhma//Desktop//BasicReport.htm";
//////		String resultPath0 = args[1];
////		HashMap<String, String> testResults0 = results0.parseOpenScriptResults(resultPath0);
////		
////		JSONArray testlists = getTestsInCycle(testCycle, url, userName, password);
////		
////		updateAutomationResultToCycleBySumDes(testlists, testResults0, url, userName, password);
//    }
    
    /**
     * Gets the versionID for the project. 
     * 
     * @param versionName
     * @param projectId
     * @throws IOException
     * @return the ID for the specified Version in the specified Project
     * @throws JSONException 
     * @throws ModuleException 
     * @throws ParseException 
     */
    public static String getVersionID(final String versionName, final String projectId, String url, String userName, String password)
            throws IOException, JSONException, InterruptedException, ModuleException, ParseException {
        // Get list of versions on the specified project
    	JIRAHttpConnection conn = new JIRAHttpConnection(new URI(url), userName, password.toCharArray(), "JIRA");
    	HTTPResponse response = conn.Get(get_version_url + projectId); 
    	int statusCode = response.getStatusCode();
    	String responseData = new String (response.getData());
    	if (statusCode == 200) {
    	  // success - Jira is up
//    		System.out.println("Response from Get call: " + responseData);
	        if (responseData.equals(null)) 
	        {
	        	throw new IllegalStateException("JSONObject is null for projectId=" + projectId);
	        }
	        
	        JSONObject responseObject = new JSONObject(new String(responseData));
	        
	        final JSONArray versionOptions = (JSONArray) responseObject.get("unreleasedVersions");
 
	        // Iterate over versions
	        for (int i = 0; i < versionOptions.length(); i++) {
	            final JSONObject obj2 = versionOptions.getJSONObject(i);
	            // If label matches specified version name
	            if (obj2.getString("label").equals(versionName)) {
	                // Return the ID for this version
	                return obj2.getString("value");
	            }
	        }
	 
	        throw new IllegalStateException("Version ID not found for versionName=" + versionName);
    	}
    	else 
    	{
    		return null;    	 
    	}    	
    }
 
    /**
     * Updates the specified test execution
     * 
     * @param executionId
     *            the ID of the execution
     * @param status
     *            a ZAPI.Status value
     * @param comment
     *            a comment for the test execution
     * @throws IOException
     *             put may throw IOException
     * @throws JSONException 
     * @throws InterruptedException 
     * @throws ModuleException 
     * @throws ParseException 
     */
    public static void updateTestExecution(final String executionId, final Status status,
            final String comment, String url, String userName, String password) throws IOException, JSONException, InterruptedException, ModuleException, ParseException {        
        JIRAHttpConnection conn = new JIRAHttpConnection(new URI(url), userName, password.toCharArray(), "JIRA");
        
        String jsonStr = "{\"status\":\"" + String.valueOf(status.getValue()) + "\",\"comment\":\"execute test\"}";
//        System.out.println(jsonStr);
    	NVPair[] headers = {new NVPair("Content-Type","application/json")};
    	HTTPResponse response =conn.Get("/jira/rest/auth/1/session");
    	int statusCode = response.getStatusCode();
//    	System.out.println(response);
    	response = conn.Put(ZAPI_URL + "execution/" + executionId + "/execute", jsonStr.getBytes(), headers);
//    	System.out.println(ZAPI_URL + "execution/" + executionId + "/execute");
    	statusCode = response.getStatusCode();
    	String responseData = new String (response.getData());
    	System.out.println(responseData);
    	if (statusCode == 200) {
    		System.out.println("Update test execution successfully");	        
    	}
    	else 
    	{
    		System.out.println("Update test execution failed ");	 	 
    	}    	
    }
    
    /**
     * Create Test Cycle
     * @param cycle_name
     * @return 
     * @throws RuntimeException
     * @throws IOException
     * @throws ModuleException 
     * @throws ParseException 
     * @throws JIRAHttpException 
     * @throws java.text.ParseException 
     */
//    public static JSONObject createCycle(final String name, final String description, final String projectId, final String versionId)
    public static Object createTestCycle(String cycle_name, String url, String userName, String password)
            throws JSONException, IOException, ModuleException, ParseException, JIRAHttpException, java.text.ParseException {
    	Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 'at' HH-mm");
		String time = sdf.format(cal.getTime());
		String testCycleName = "UIAuto" + "_" + time;
		
//		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		SimpleDateFormat dateFormat = new SimpleDateFormat("d/MMM/yy");
//		Date date = new Date();
//		String startTime = dateFormat.format(date);
//		System.out.println(dateFormat.format(date)); //2016/11/16	
		
		Locale locale = Locale.US; 
		
		SimpleDateFormat frm = new SimpleDateFormat("d/MMM/yy", locale); 
		String startTime =frm.format(new Date());
//		System.out.println(startTime);
    	
        JIRAHttpConnection conn = new JIRAHttpConnection(new URI(url), userName, password.toCharArray(), "JIRA");
        
        String jsonStr = "{\"clonedCycleId\":\"\", \"name\":\"" + cycle_name + "\", \"build\":\"\",\"environment\":\"\", \"description\":\"Create cycle with sprint\", \"startDate\": \"" + startTime + "\", \"endDate\": \"" + startTime + "\", \"projectId\": \"30516\", \"versionId\": \"136458\"}";       
//        System.out.println(jsonStr);
    	NVPair[] headers = {new NVPair("Content-Type","application/json")};
//    	System.out.println(conn.doPost(get_cycles_url));
    	HTTPResponse response =conn.Get("/jira/rest/auth/1/session");
    	int statusCode = response.getStatusCode();
//    	System.out.println(response);
    	response = conn.Post(get_cycles_url, jsonStr.getBytes(), headers);
//    	System.out.println(get_cycles_url);
    	statusCode = response.getStatusCode();
    	String responseData = new String (response.getData());
//    	System.out.println(responseData);
    	Object cycleId = null;
    	if (statusCode == 200) {
    		System.out.println("Create test cycle successfully");	  
    		JSONObject responseObject = new JSONObject(new String(responseData));
    		cycleId = responseObject.get("id");    
    	}
    	else 
    	{
    		System.out.println("Create test cycle failed ");	 
    	}  
    	// Logout of Jira
    	response = conn.Delete("/jira/rest/auth/1/session", headers);
    	statusCode = response.getStatusCode();
    	if (statusCode != 204) {
    	  responseData = new String(response.getData());
    	  System.out.println("Problem logging out of Jira. Status code " + statusCode + " was returned with response data: " + responseData);
    	}
    	return cycleId;
    }
    
    /**
     * Add exist test cases to test cycle in Jira
     * @param idList
     * @param filePath
     * @return 
     * @return 
     * @throws RuntimeException
     * @throws IOException
     * @throws ModuleException 
     * @throws ParseException 
     */
    
    public static void addExistRestTestToTestCycle(String[] idList, String filePath, int versionId, long projectId, Object cycleId, String url, String userName, String password)
            throws IOException, JSONException, ModuleException, ParseException{
    	String [] testsToAdd = {};
    	String testsToAddContent = "";
    	//If the test cases id provides by file
    	if (filePath != "")
    	{
    		List<String[]> csvList = new ArrayList<String[]>();  
            if (CaseDescription.isCsv(filePath)) {  
                CsvReader reader = new CsvReader(filePath, ',', Charset.forName("GBK"));  
//                reader.readHeaders(); // Skip table title 
                while (reader.readRecord()) { //read lines one by one except table title
                    csvList.add(reader.getValues());  
                }  
                reader.close();  
            } else {  
                System.out.println("This file is not csv file.");  
            }  	
            Iterator<String[]> iter = csvList.iterator();
    		while(iter.hasNext())
    		{  	
    			testsToAdd = iter.next();
    		}
			for(int i=0; i<testsToAdd.length; i++){
				testsToAdd[i] = "\"" + testsToAdd[i] + "\"";
			}
    	}
    	else if(idList.length>0){
    		testsToAdd = idList;
    	}
  	
//    		while(iter.hasNext())
//    		{
////    			testsToAdd = iter.next();
//    			testsToAdd=Arrays.copyOf(testsToAdd, testsToAdd.length+1);
//    			testsToAdd[testsToAdd.length-1]="\"" + iter.next() + "\"";
//    		}
//    	}
//    	else if(idList.length>0){
//    		testsToAdd = idList;
//    	}

        List<String> tests = Arrays.asList(testsToAdd);    
        
        JIRAHttpConnection conn = new JIRAHttpConnection(new URI(url), userName, password.toCharArray(), "JIRA");
        
        String jsonStr = "{\"issues\":" + tests + ", \"versionId\":\"" + versionId + "\", \"cycleId\":\"" + cycleId + "\",\"projectId\":\"" + projectId + "\", \"method\":\"1\"}";       
        System.out.println(jsonStr);
    	NVPair[] headers = {new NVPair("Content-Type","application/json")};
//    	System.out.println(conn.doPost(get_cycles_url));
    	HTTPResponse response =conn.Get("/jira/rest/auth/1/session");
    	int statusCode = response.getStatusCode();
//    	System.out.println(response);
    	response = conn.Post(add_test_to_cycle_url, jsonStr.getBytes(), headers);
    	System.out.println(add_test_to_cycle_url);
    	statusCode = response.getStatusCode();
    	String responseData = new String (response.getData());
    	System.out.println(responseData);
    	if (statusCode == 200) {
    		System.out.println("Add test to test cycle successfully");	  
    	}
    	else 
    	{
    		System.out.println("Add test to test cycle failed ");	 
    	}  
    }
    
    /**
     * get all the tests in cycle,return tests count and list
     * @param cycleId
     * @return 
     * @return 
     * @return 
     * @throws RuntimeException
     * @throws IOException
     */
    
    public static JSONArray getTestsInCycle(Object cycleId, String url, String userName, String password)
            throws IOException, JSONException, ParseException, ModuleException{
//    	https://jira.oraclecorp.com/jira/rest/zapi/latest/execution?action=expand&cycleId=71771
    	
    	JIRAHttpConnection conn = new JIRAHttpConnection(new URI(url), userName, password.toCharArray(), "JIRA");
    	HTTPResponse response = conn.Get(get_executions_url + "?action=expand&cycleId=" + cycleId); 
    	
    	int statusCode = response.getStatusCode();
    	String responseData = new String (response.getData());
    	if (statusCode == 200) {
    	  // success - Jira is up
//    		System.out.println("Response from Get call: " + responseData);
	        if (responseData.equals(null)) 
	        {
	        	throw new IllegalStateException("JSONObject is null");
	        }
	        
	        JSONObject responseObject = new JSONObject(new String(responseData));	 
//	        System.out.println(responseObject);
	        
	        int recordsCount = responseObject.getInt("recordsCount");
	        System.out.println("Test cycle " + cycleId + " has " + recordsCount + " tests in it.");	        
	        
	        JSONArray excutionsList = responseObject.getJSONArray("executions");
	        System.out.println(excutionsList);
	        return excutionsList;  
    	} 
    	else 
    	{
    		return null;    	 
    	}
    }
    
    /**
     * update result to test cycle, test execution list is the test cases list in cycle
     * @param idList
     * @param filePath
     * @return 
     * @return 
     * @return 
     * @throws RuntimeException
     * @throws IOException
     * @throws InterruptedException 
     * @throws ParseException 
     * @throws ModuleException 
     */
    
    public static void updateAutomationResultToCycleBySumDes(JSONArray test_execution_list, HashMap<String, String> result_json, String url, String userName, String password)
            throws IOException, JSONException, InterruptedException, ModuleException, ParseException{
    	
    	for(int i=0; i<test_execution_list.length();i++){
    		String testCaseName = test_execution_list.getJSONObject(i).getString("summary");
    		if(result_json.get(testCaseName)==null){    			
    			continue;	
    		}
    		String status0 = result_json.get(testCaseName);
    		Status status = Status.UNEXECUTED;
    		if(status0.equalsIgnoreCase("Pass")){
    			status = Status.PASS;
    		}
    		else if (status0.equalsIgnoreCase("Fail")){
    			status = Status.FAIL;
    		}
    		
    		int id =test_execution_list.getJSONObject(i).getInt("id");
//    		url = execute_url + "/" + id + "/execute";
//    		
//    		
//            final JSONObject obj = new JSONObject();
//            obj.put("status", String.valueOf(status.getValue()));
//            obj.put("comment", "test");
//     
//            put(url, obj);
//    		
            updateTestExecution(Integer.toString(id), status,"execute test", url, userName, password);
            
//    		updateTestExecution(id, status,"test");
    	}    
    }
}