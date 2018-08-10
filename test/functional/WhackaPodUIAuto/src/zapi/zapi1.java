package zapi;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;

import com.csvreader.CsvReader;

import framework.objects.CommonVariables;
//import framework.objects.CommonVariables.*;
 
/**
* Date : 2017-04-27
* Owner : yuhong.ma
* @author yuhong.ma
*/

/** Helper class for calling ZAPI */
public class zapi1 {
 
    /** Status IDs enum */
    public enum Status {
        PASS(1), FAIL(2), WIP(3), BLOCKED(4);
        private final int value;
 
        private Status(final int value) {
            this.value = value;
        }
 
        public int getValue() {
            return value;
        }
    }
 
    /** URLS */
    private static final String BASE_URL = "https://jira.oraclecorp.com/jira";
    private static final String ZAPI_URL = BASE_URL + "/rest/zapi/latest";
    
    
    private static final String get_project_url = BASE_URL + "/rest/zapi/latest/util/project-list";
    private static final String get_version_url = BASE_URL + "/rest/zapi/latest/util/versionBoard-list?projectId=";
    private static final String get_cycles_url = BASE_URL + "/rest/zapi/latest/cycle";
    private static final String create_test_issue_url = BASE_URL + "/rest/api/2/issue/";
    private static final String get_project_fields_url = BASE_URL + "/rest/api/2/issue/createmeta";
    private static final String issue_search_url = BASE_URL + "/rest/api/2/search";
    private static final String add_test_to_cycle_url = BASE_URL + "/rest/zapi/latest/execution/addTestsToCycle";
    private static final String get_executions_url = BASE_URL + "/rest/zapi/latest/execution";
    private static String execute_url = BASE_URL + "/rest/zapi/latest/execution";
    private static final String update_attachment_url = BASE_URL + "/rest/zapi/latest/attachment?entityId=";  
    
 
    /** HTTP Proxy details */
    private static final boolean USE_PROXY = false;
    private static final String PROXY_IP = "cn.proxy.jp.oracle.com";
    private static final int PROXY_PORT = 80;
 
    private static final HttpHost HTTP_HOST_PROXY = new HttpHost(PROXY_IP, PROXY_PORT);
    private static final Proxy PROXY = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_IP,
            PROXY_PORT));
 
    /** JIRA credentials: format "username:password" or "" for none. */
    private static final String CREDENTIALS = "yuhong.ma@oracle.com:!QAZ2wsx";
    
    private static String url = "https://jira.oraclecorp.com";
    private static String userName = "oardc-omsp_sg@oracle.com";
    private static String password = "!QAZ2wsx";
    
    private static final Cookie cookie = getCookie();
    
    private static final String UNIQUE_ID = "MS Automation Function";
 
    // ================================================================================
    // ZAPI methods
    // ================================================================================
 
    /**
     * Gets the Project ID for the project. 
     * 
     * @param projectName
     * @throws IOException
     * @return the ID for the specified project
     * @throws JSONException 
     * @throws InterruptedException 
     */
    public static String getProjectId(final String projectName)
            throws IOException, JSONException, InterruptedException {
        // Get list of versions on the specified project
        final JSONObject projectJsonObj =
                httpGetJSONObject(get_project_url);
        if (null == projectJsonObj) {
            throw new IllegalStateException("JSONObject is null");
        }
 
        final JSONArray projectOptions = (JSONArray) projectJsonObj.get("options");
 
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
    
    /**
     * Gets the versionID for the project. 
     * 
     * @param versionName
     * @param projectId
     * @throws IOException
     * @return the ID for the specified Version in the specified Project
     * @throws JSONException 
     */
    public static String getVersionID(final String versionName, final String projectId)
            throws IOException, JSONException, InterruptedException {
        // Get list of versions on the specified project
        final JSONObject projectJsonObj =
                httpGetJSONObject(get_version_url + projectId);
        if (null == projectJsonObj) {
            throw new IllegalStateException("JSONObject is null for projectId=" + projectId);
        }
 
        final JSONArray versionOptions = (JSONArray) projectJsonObj.get("unreleasedVersions");
 
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
     */
    public static JSONObject updateTestExecution(final String executionId, final Status status,
            final String comment) throws IOException, JSONException, InterruptedException {
        // Construct JSON object
        final JSONObject obj = new JSONObject();
        obj.put("status", String.valueOf(status.getValue()));
        obj.put("comment", comment);
 
        return put(ZAPI_URL + "execution/" + executionId + "/execute", obj);
    }
    
    /**
     * Create Test Cycle
     * @param cycle_name
     * @return 
     * @throws RuntimeException
     * @throws IOException
     */
//    public static JSONObject createCycle(final String name, final String description, final String projectId, final String versionId)
    public static Object createTestCycle(String cycle_name)
            throws JSONException, IOException {
 
        final HttpPost httpPost =
                new HttpPost(get_cycles_url);
        httpPost.setHeader("X-Atlassian-Token", "nocheck");
        
        final JSONObject obj = new JSONObject();
        obj.put("clonedCycleId","");
        obj.put("name", cycle_name);
        obj.put("build", "");
        obj.put("environment", "");
        obj.put("description", "Create cycle with sprint");
        obj.put("startDate", "21/April/17");
        obj.put("endDate", "30/April/17");
        obj.put("projectId", "30516");
        obj.put("versionId", "114983");      
        
        JSONObject responseObj = post(get_cycles_url, obj);
        Object cycleId = responseObj.get("id");
        return cycleId;
        
//        return post(ZAPI_URL + "cycle", obj);
    }
    
    /**
     * Check if test cycle exists
     * @param cycle_name
     * @return 
     * @throws RuntimeException
     * @throws IOException
     */
    public static boolean checkIfTestCycleExists(String cycle_name, String projectId, String versionId)
            throws IOException, JSONException{
    	boolean found = false;
        // Get list of test cycle on the specified project
        final JSONObject testCycleJsonObj =
                httpGetJSONObject(get_cycles_url + "?projectId=" + projectId + "&versionId=" + versionId);
//        System.out.println(testCycleJsonObj.toString());
        if (null == testCycleJsonObj) {
            throw new IllegalStateException("JSONObject is null");
        }
        Iterator<String> keys = testCycleJsonObj.keys();
        while(keys.hasNext() ){
	        String key=keys.next(); 
	        if(!key.equalsIgnoreCase("recordsCount")){
//	        	System.out.println(key);
		        
		        JSONObject valueObject = testCycleJsonObj.getJSONObject(key);
//		        System.out.println(valueObject.getString("name").toString());
		        if(valueObject.getString("name").toString().equalsIgnoreCase(cycle_name)){
//		        	System.out.println(valueObject.getString("name").toString());
//		        	System.out.println(key);
		        	found = true;
		        	break;
		        }
	        }
	        
        }
        return found;        
    }

    
    /**
     * Get Project Fields        
     * Getting all the fields of test in project
     * @param projectId
     * @return 
     * @return 
     * @throws RuntimeException
     * @throws IOException
     */
    
    public static String[] getProjectFields(String projectId)
            throws IOException, JSONException{
    	
        // Get create issue meta on the specified project
    	// https://jira.oraclecorp.com/jira/rest/api/2/issue/createmeta?expand=projects.issuetypes.fields&projectIds=30516&issuetypeNames=Test
        final JSONObject issueMetaJsonObj =
                httpGetJSONObject(get_project_fields_url + "?expand=projects.issuetypes.fields" + "&projectIds=" + projectId + "&issuetypeNames=Test");
//        System.out.println(testCycleJsonObj.toString());
        if (null == issueMetaJsonObj) {
            throw new IllegalStateException("JSONObject is null");
        }
        
        JSONObject fieldsJsonObj = issueMetaJsonObj.getJSONArray("projects").getJSONObject(0).getJSONArray("issuetypes").getJSONObject(0).getJSONObject("fields");
        
        JSONObject.getNames(fieldsJsonObj);
        
//        System.out.println(JSONObject.getNames(fieldsJsonObj).length);
        
        //java
        String[] fields={};
        fields = JSONObject.getNames(fieldsJsonObj);
        return fields;
    }
    
    /**
     * Searching test cases in project by test case name in Jira
     * @param testCaseName
     * @param projectId
     * @return 
     * @return 
     * @throws RuntimeException
     * @throws IOException
     */
    
    public static String searchTestsInProjectByName(String projectId, String testCaseName)
            throws IOException, JSONException{
    	
        // Searching test cases in project by test case name in Jira
    	// https://jira.oraclecorp.com/jira/rest/api/2/search
    	// "jql": "project = 30516 AND issuetype = Test  AND summary ~ 'LoginUI' "
    	
        final HttpPost httpPost =
                new HttpPost(issue_search_url);
        httpPost.setHeader("X-Atlassian-Token", "nocheck");
        
        final JSONObject obj = new JSONObject();
        String jqlcontent = "project = " + projectId + " AND issuetype = Test  AND summary ~ '" + testCaseName +"'";
//        System.out.println(jqlcontent);
//        "project = 30516 AND issuetype = Test  AND summary ~ 'LoginUI' "
        obj.put("jql",jqlcontent);  
        
        JSONObject responseObj = post(issue_search_url, obj);
        
        if(responseObj.getInt("total") == 1) {
        	if(responseObj.getJSONArray("issues").getJSONObject(0).getJSONObject("fields").getString("summary").equalsIgnoreCase(testCaseName)){
        		System.out.println("The test case exists.");
        		return responseObj.getJSONArray("issues").getJSONObject(0).getString("key");
        	}
        	else{
        		System.out.println("The test case doesn't exist.");
        	}
        }
        
        else if (responseObj.getInt("total") > 1) {
        	int count = 0;
        	String key = "";
        	for(int i=0; i<responseObj.getInt("total") ; i++){
        		if(responseObj.getJSONArray("issues").getJSONObject(i).getJSONObject("fields").getString("summary").equalsIgnoreCase(testCaseName)){
        		count ++;
        		key = responseObj.getJSONArray("issues").getJSONObject(i).getString("key");
        		}
        		if (count ==0){
        			System.out.println("The test case doesn't exist.");
        		}
        		else if (count ==1){
        			System.out.println("The test case exists.");
        			return key;
        		}
        		else{
        			System.out.println("Duplicated: #{count} test cases for same test case name: "+testCaseName);
        			return key;
        		}
        	}        	
        }
        else{
        	System.out.println("The test case doesn't exist.");
        }
		return null;
    }
    
    /**
     * Searching test cases in project by query
     * @param projectId
     * @return all the matched tests
     * @return 
     * @throws RuntimeException
     * @throws IOException
     */
    
    public static ArrayList<String> searchTestsInProjectByQuery(String projectId)
            throws IOException, JSONException{
    	
        // Search test cases in project by lables: 'MS-UI-Automation'
    	// https://jira.oraclecorp.com/jira/rest/api/2/search
    	// "jql": "project = 30516 AND issuetype = Test  AND summary ~ 'LoginUI' "
    	
        final HttpPost httpPost =
                new HttpPost(issue_search_url);
        httpPost.setHeader("X-Atlassian-Token", "nocheck");
        
        final JSONObject obj = new JSONObject();
        
//        query = 'project= ' + @projectId
//        query = query + ' AND issuetype = Test AND labels = "SRMQA-UI-Automation" AND reporter in ("Xueling Yang")&maxResults=1000'
        String jqlcontent = "project = " + projectId + " AND issuetype = Test  AND labels = " + "MS-UI-Automation";
        obj.put("jql",jqlcontent);  
        
        JSONObject responseObj = post(issue_search_url, obj);
//        System.out.println(responseObj);
        ArrayList<String> keys = new ArrayList<String>();
        
        int total = responseObj.getInt("total");
        System.out.println(total);
        if (total > 0) {
        	String key = "";
        	for(int i=0; i<responseObj.getInt("total") ; i++){
        		key = responseObj.getJSONArray("issues").getJSONObject(i).getString("key");
//        		System.out.println(key);
        		keys.add(key);
        	}        	
        }
        System.out.println("The project has " + total + " matched test in it.");
//        System.out.println(keys);
        return keys;
    }
    
    /**
     * Searching test cases in project by query
     * @param projectId
     * @return all the matched tests
     * @return 
     * @throws RuntimeException
     * @throws IOException
     */
    
    public static String getFunctionOfTestsByKey(String key)
            throws IOException, JSONException{
    	
        // Search test cases Summary and Id in Jira for key
    	// https://jira.oraclecorp.com/jira/rest/api/2/search
    	// "jql": "key=keyValue"
    	
        final HttpPost httpPost =
                new HttpPost(issue_search_url);
        httpPost.setHeader("X-Atlassian-Token", "nocheck");
        
        final JSONObject obj = new JSONObject();
        
//        query = 'project= ' + @projectId
//        query = query + ' AND issuetype = Test AND labels = "SRMQA-UI-Automation" AND reporter in ("Xueling Yang")&maxResults=1000'
        String jqlcontent = "key = " + key;
        obj.put("jql",jqlcontent);  
        
        JSONObject responseObj = post(issue_search_url, obj);
//        System.out.println(responseObj);
        
        int total = responseObj.getInt("total");
//        System.out.println(total);
        String function = "";
        if (total == 1) {
        	function = responseObj.getJSONArray("issues").getJSONObject(0).getJSONObject("fields").getString("summary");
        	return function;
        }
        else {
        	return null;
        }
    }
    
    
//    ###############################################################################################
//    # add test in project                                                                         #
//    #                                                                                             #
//    #  newTestValues = "{                                                                         #
//    #     \"fields\": {
//    #       \"project\": {
//    #        \"id\": \"#{@projectId}\"
//    #        },
//    #       \"summary\": \"#{test_case[:summary]}\",
//    #        \"description\": \"#{test_case[:description]}\",
//    #       \"issuetype\": {
//    #        \"name\": \"Test\"
//    #        }
//    #     }
//    #    }"
//    ################################################################################################
    
    public static void requiredFilds(String projectId, String user)
    {
    	Map<String, Object> fields = new HashMap<String, Object>();
//    	Map<String, HashMap<String, String>> fields = new HashMap<String, HashMap<String, String>>();
    	//project
    	HashMap<String, String> project = new HashMap<String, String>();
    	project.put("id", projectId);
    	fields.put("project", project);
    	
    	//version
    	
    	//issue type
    	HashMap<String, String> issuetype = new HashMap<String, String>();
    	issuetype.put("name", "Test");
    	fields.put("issuetype", issuetype);
    	
    	//assignee
    	HashMap<String, String> assignee = new HashMap<String, String>();
    	assignee.put("assignee", user);
    	fields.put("assignee", assignee);
    	
    	//labels
        String label = "MS-UI-Automation";
    	fields.put("labels", label);
    }
    
    /**
     * Add test issues in Jira from case list: "+case_list.join(',')
     * @param projectId
     * @param id_list
     * @return 
     * @return 
     * @throws RuntimeException
     * @throws IOException
     */
    
    public static void addTestsInJira(String productId)
            throws IOException, JSONException{
    	
    	List<String[]> casesList = CaseDescription.translateFileToList("");
    	String[] fieldsList = getProjectFields(productId);

    	Iterator<String[]> iter = casesList.iterator();
    	
    	String[] testCase = {};
    	String[] fields = {};
    	
//    	String id = searchTestsInProjectByName("30516", "");

		while(iter.hasNext())
		{
			testCase = iter.next();
			for(int i=0; i<testCase.length; i++){
				System.out.println(testCase[i]);
			}
		}
       
    }
    
    /**
     * Add exist test cases to test cycle in Jira
     * @param idList
     * @param filePath
     * @return 
     * @return 
     * @throws RuntimeException
     * @throws IOException
     */
    
    public static void addExistTestToTestCycle(String[] idList, String filePath, int versionId, long projectId, Object cycleId)
            throws IOException, JSONException{
    	String [] testsToAdd = {};
    	String testsToAddContent = "";
    	//If the test cases id provides by file
    	if (filePath != "")
    	{
    		List<String[]> csvList = new ArrayList<String[]>();  
            if (CaseDescription.isCsv(filePath)) {  
                CsvReader reader = new CsvReader(filePath, ',', Charset.forName("UTF-8"));  
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
//    			for(int i=0; i<testsToAdd.length; i++){
//    				System.out.println(testsToAdd[i]);
//    			}
    		}
    	}
    	else if(idList.length>0){
    		testsToAdd = idList;
    	}
    	
        final HttpPost httpPost =
                new HttpPost(add_test_to_cycle_url);
        httpPost.setHeader("X-Atlassian-Token", "nocheck");
        
        final JSONObject obj = new JSONObject();
        
        obj.put("issues", testsToAdd);
        obj.put("versionId",versionId);
        obj.put("cycleId", cycleId);
        obj.put("projectId", projectId);
        obj.put("method", "1");   
        System.out.println(obj);
        
        JSONObject responseObj = post(add_test_to_cycle_url, obj);       
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
    
    public static JSONArray getTestsInCycle(Object cycleId)
            throws IOException, JSONException{
//    	https://jira.oraclecorp.com/jira/rest/zapi/latest/execution?action=expand&cycleId=71771
        final JSONObject testsMetaJsonObj =
                httpGetJSONObject(get_executions_url + "?action=expand&cycleId=" + cycleId);
//        System.out.println(testCycleJsonObj.toString());
        if (null == testsMetaJsonObj) {
            throw new IllegalStateException("JSONObject is null");
        }
        
        int recordsCount = testsMetaJsonObj.getInt("recordsCount");
        System.out.println("Test cycle " + cycleId + " has " + recordsCount + " tests in it.");
        
        
        JSONArray excutionsList = testsMetaJsonObj.getJSONArray("executions");
        return excutionsList;
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
     */
    
    public static void updateAutomationResultToCycleBySumDes(JSONArray test_execution_list, HashMap<String, String> result_json)
            throws IOException, JSONException, InterruptedException{
    	
    	for(int i=0; i<test_execution_list.length();i++){
    		String testCaseName = test_execution_list.getJSONObject(i).getString("summary");
    		if(result_json.get(testCaseName)==null){    			
    			continue;	
    		}
    		String status0 = result_json.get(testCaseName);
    		Status status = Status.PASS;
    		if(status0.equalsIgnoreCase("Pass")){
    			status = Status.PASS;
    		}
    		else if (status0.equalsIgnoreCase("Fail")){
    			status = Status.FAIL;
    		}
    		
    		int id =test_execution_list.getJSONObject(i).getInt("id");
    		url = execute_url + "/" + id + "/execute";
    		
    		
            final JSONObject obj = new JSONObject();
            obj.put("status", String.valueOf(status.getValue()));
            obj.put("comment", "test");
     
            put(url, obj);
    		
//    		updateTestExecution(id, status,"test");
    	}  
    }
 
    /**
     * Adds attachment to an execution.
     * 
     * @param fileToUpload
     *            - the file to attach
     * @param executionId
     * @throws RuntimeException
     * @throws IOException
     */
    public static void addAttachment(final File fileToUpload, final String executionId)
            throws RuntimeException, IOException {
        // set up proxy for http client
        final HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        clientBuilder.useSystemProperties();
        if (USE_PROXY) {
            clientBuilder.setProxy(HTTP_HOST_PROXY);
        }
        final CloseableHttpClient httpClient = clientBuilder.build();
 
        final HttpPost httpPost =
                new HttpPost(update_attachment_url + executionId
                        + "&entityType=EXECUTION");
        httpPost.setHeader("X-Atlassian-Token", "nocheck");
 
        if (!CREDENTIALS.isEmpty()) {
            final String encoding = new Base64().encodeToString(CREDENTIALS.getBytes());
            httpPost.setHeader("Authorization", "Basic " + encoding);
        }
 
        final MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addBinaryBody("file", fileToUpload, ContentType.APPLICATION_OCTET_STREAM,
                fileToUpload.getName());
        final HttpEntity multipart = builder.build();
        httpPost.setEntity(multipart);
 
        final CloseableHttpResponse response = httpClient.execute(httpPost);
        final HttpEntity responseEntity = response.getEntity();
        if (null != responseEntity) {
            EntityUtils.consume(responseEntity);
        }
 
        // ensure file was uploaded correctly
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Error uploading file");
        }
    }
 
    /**
     * Deletes all of the attachments on the specified execution
     * 
     * @param executionId
     *            the id of the execution
     * @throws IOException
     *             delete may throw IOException
     * @throws JSONException 
     * @throws InterruptedException 
     */
    public static List<JSONObject> deleteAttachments(final String executionId) throws IOException, JSONException {
        final ArrayList<String> fileIds = new ArrayList<String>();
        // Note the IDs for the files currently attached to the execution
        final JSONObject obj =
                httpGetJSONObject(update_attachment_url + executionId + "&entityType=EXECUTION");
        if (null == obj) {
            throw new IllegalStateException("Response is null");
        }
 
        final JSONArray data = (JSONArray) obj.get("data");
        for (int i = 0; i < data.length(); i++) {
            final JSONObject fileData = data.getJSONObject(i);
            fileIds.add(fileData.getString("fileId"));
        }
 
        // Iterate over attachments
        final ArrayList<JSONObject> responses = new ArrayList<JSONObject>(data.length());
        for (final String fileId : fileIds) {
            responses.add(delete(ZAPI_URL + "attachment/" + fileId));
        }
        return responses;
    }
 
    // ================================================================================
    // HTTP request methods
    // ================================================================================
 
    /**
     * Send GET request to the specified URL
     * 
     * @param url
     * @throws IOException
     * @throws JSONException 
     * @throws InterruptedException 
     */
    private static JSONObject httpGetJSONObject(final String url) throws IOException, JSONException {
        return new JSONObject(httpGetJSONString(url));
    }
 
    /**
     * Send GET request to the specified URL
     * 
     * @param url
     * @throws IOException
     * @throws JSONException 
     * @throws InterruptedException 
     */
    private static JSONArray httpGetJSONArray(final String url) throws IOException, JSONException {
        return new JSONArray(httpGetJSONString(url));
    }
 
    /**
     * Get a string from a url.
     * 
     * @param url
     *            the URL to perform the GET method on
     * @return a String representing the body of the http response
     * @throws IOException
     * @throws InterruptedException 
     */
    private static String httpGetJSONString(final String url) throws IOException {
        final HttpURLConnection httpCon = createHttpCon(url, "GET");
        final BufferedReader br =
                new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
 
        final StringBuffer httpResponse = new StringBuffer();
        String line = "";
        while (null != (line = br.readLine())) {
            httpResponse.append(line);
        }
 
        return httpResponse.toString();
    }
 
    /**
     * Send a request with JSON content with the specified method
     * 
     * @param url
     *            - the URL to send the request to
     * @param obj
     *            - the JSON content to send
     * @param method
     *            - e.g. PUT
     * @throws IOException
     * @throws JSONException 
     * @throws InterruptedException 
     */
    private static JSONObject sendRequest(final String url, final JSONObject obj,
            final String method) throws IOException, JSONException{
        final HttpURLConnection httpCon = createHttpCon(url, method);
 
        if (null != obj) {
            final OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
            out.write(obj.toString());
            out.close();
        }
 
        final BufferedReader rd =
                new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
        final StringBuffer result = new StringBuffer();
        String line = "";
        while (null != (line = rd.readLine())) {
            result.append(line);
        }
        return new JSONObject(result.toString());
    }
 
    /**
     * Send PUT request to the specified URL
     * 
     * @param url
     *            - the URL to send the request to
     * @param obj
     *            - the JSON content to send
     * @throws IOException
     * @throws JSONException 
     * @throws InterruptedException 
     */
    private static JSONObject put(final String url, final JSONObject obj) throws IOException, JSONException {
        return sendRequest(url, obj, "PUT");
    }
 
    /**
     * Send POST request to the specified URL
     * 
     * @param url
     *            - the URL to send the request to
     * @param obj
     *            - the JSON content to send
     * @throws IOException
     * @throws JSONException 
     * @throws InterruptedException 
     */
    private static JSONObject post(final String url, final JSONObject obj) throws IOException, JSONException {
        return sendRequest(url, obj, "POST");
    }
 
    /**
     * Send DELETE request to the specified URL
     * 
     * @param url
     *            - the URL to send the request to
     * @throws IOException
     * @throws JSONException 
     * @throws InterruptedException 
     */
    private static JSONObject delete(final String url) throws IOException, JSONException{
        return sendRequest(url, null, "DELETE");
    }
 
    /**
     * Return a HttpURLConnection object for the specified URL and request method
     * 
     * @param url
     *            the URL to connect to
     * @param method
     *            - e.g. GET
     * @throws InterruptedException 
     */
    private static HttpURLConnection createHttpCon(final String url, final String method)
            throws IOException {
        final HttpURLConnection httpCon;
        if (USE_PROXY) {
            httpCon = (HttpURLConnection) new URL(url).openConnection(PROXY);
        } else {
            httpCon = (HttpURLConnection) new URL(url).openConnection();
        }
 
        httpCon.setDoOutput(true);
        httpCon.setRequestMethod(method);
 
//        if (!CREDENTIALS.isEmpty()) {
//            final String encoding = new Base64().encodeToString(CREDENTIALS.getBytes());
//            httpCon.setRequestProperty("Authorization", "Basic " + encoding);
//        }
 
        httpCon.setRequestProperty("Content-type", "application/json");
//        httpCon.setRequestProperty("Cookie", "OHS-jira.oraclecorp.com-80=A62E01B31C922FBF853984C80DA0A3C512E73B486F9E7A2BC3E537BB1C87DEFD70609D198C78D3BB62312214F29DD489E60468C1E8D1ECC6E50A7FD7FEA68ED7B1DD0C80CA26288BB8782CDD152D864751F9373AE3E0264D538DA926E55909E37AC7864208E7170960014B06D78FA8F07FF58B2B0E157AEC2B129625FE108D4A2BD1D7C57623015A1A7192D483D3EEB05302DA8FE050BE870B131938F5ED6CC2F3EA15C5668A3421E048B2D3ECC5765D17920306B8E2AEECEB6DB82CD0458015F973C9E239FF771609EBDAAF9BDBBFC73836965FF1D379CAE65677461D6F4BFB5EE66B940D0D0532~"); 
        httpCon.setRequestProperty("Cookie", cookie.toString());
        return httpCon;
    }
    
	    private static Cookie getCookie() {      
	        // Instantiate CookieManager;
	        // make sure to set CookiePolicy
	    	CookieManager manager = new CookieManager();
			manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
			CookieHandler.setDefault(manager);
	        
			//logger.info("Login to Jira UI:"); 
			CommonVariables commVar = new CommonVariables();
			commVar.driver.get(url);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			commVar.driver.findElement(By.id("sso_username")).sendKeys(userName);
			commVar.driver.findElement(By.id("ssopassword")).sendKeys(password);
			commVar.driver.findElement(By.xpath(".//*[@class='submit_btn']")).click();		
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        // get cookies from browser
			Cookie cookie1 = commVar.driver.manage().getCookieNamed("OHS-jira.oraclecorp.com-80");
			System.out.println(cookie1);
			commVar.driver.quit();
			return cookie1;
	    }
}