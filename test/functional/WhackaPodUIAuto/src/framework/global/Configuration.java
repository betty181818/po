package framework.global;

import java.io.File;
import java.util.*;
import java.io.*;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import log.LogHandle;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import util.ScenarioRunning;
import framework.objects.Property;
import framework.objects.SrvConfig;
//import framework.objects.Property;

/**
* Date : 2017-04-27
* Owner : yuhong.ma
* @author yuhong.ma
*/

public class Configuration {
	 
	 public static Property pro= new Property();
	 public static SrvConfig srvconf = new SrvConfig();
	 public static final Logger logger = Logger.getLogger(Configuration.class);
	 
	 	    
		/**
		 * This method is to initialize, loading the log4j.xml, setting runningScenario name and some work
		 * before testing.
		 * 
		 */
		public static void prepare(String runningScenario) {
			ScenarioRunning.runningScenario = runningScenario;
			DOMConfigurator.configure("config//log4j.xml");
		}
		
		public static void prepare1(String runningScenario) {
			ScenarioRunning.runningScenario = runningScenario;
			DOMConfigurator.configure("config//log4jcopy.xml");
		}
	    
		public static void init(){	
			 logger.info("Configuration Initializing...");
			 initProperties();

			 initConfig(pro.getSrvID());
			 
			

			 try {
				  
				  }catch (Exception e) {
			   e.printStackTrace();
			  }
		}
		
		public static void initProperties(){
			
	        Properties prop = new Properties();
	        InputStream fis;
	        //FileInputStream fis;
			
	        try {
	            fis =  Configuration.class.getResourceAsStream("/Auto.properties");
	                       	            
	            prop.load(fis);	
	            fis.close();
	        }
	        catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        
	        pro.setSrvID(prop.getProperty("ServerID"));
	        
	        pro.setWaitPageTimeLOW(Long.parseLong(prop.getProperty("Browser.WaitPageTime.LOW")));
	        pro.setWaitPageTimeMEDIUM(Long.parseLong(prop.getProperty("Browser.WaitPageTime.MEDIUM")));
	        pro.setWaitPageTimeHIGH(Long.parseLong(prop.getProperty("Browser.WaitPageTime.HIGH")));	        
	        pro.setOperationTimeOut(Long.parseLong(prop.getProperty("OPERATION.TIMEOUT")));
	        
	        pro.setShortSleepTime(Long.parseLong(prop.getProperty("shortSleepTime")));
	        pro.setMediumSleepTime(Long.parseLong(prop.getProperty("mediumSleepTime")));
	        pro.setLongSleepTime(Long.parseLong(prop.getProperty("longSleepTime")));	
	        
	        pro.setLocalLogRepository(prop.getProperty("LocalLogRepository"));
	        pro.setRemoteLogRepository(prop.getProperty("RemoteLogRepository"));
	        
	        pro.setLocalLogSrvHost(prop.getProperty("localLogServerHost"));
	        pro.setLocalLogSrvIP(prop.getProperty("localLogServerIP"));
	        pro.setLocalLogUserName(prop.getProperty("localLogUserName"));
	        pro.setLocalLogPassword(prop.getProperty("localLogPassword"));	
	        
	        
	        pro.setNormalFileDir(prop.getProperty("normalFileDir"));
	        pro.setBigFileDir(prop.getProperty("bigFileDir"));
	        pro.setFolderFileDir(prop.getProperty("folderFileDir"));	
	        
	        
	        pro.setRemoteBuiFile(prop.getProperty("remoteBuiFile"));
	        pro.setRemoteCommFile(prop.getProperty("remoteCommFile"));
	        pro.setLocalDir(prop.getProperty("localDir"));
	        
	        pro.setJdbcDriver(prop.getProperty("JDBC.DRIVE"));
	        pro.setJdbcUserName(prop.getProperty("JDBC.USERNAME"));
	        pro.setJdbcPasswd(prop.getProperty("JDBC.PASSWORD"));
	        pro.setJdbcUrl(prop.getProperty("JDBC.URL"));
//	        System.out.println("JDBC.DRIVE=="+prop.getProperty("JDBC.DRIVE"));	       
	        
	        pro.setEmailServerHost(prop.getProperty("emailServerHost"));
	        pro.setEmailServerPort(Long.parseLong(prop.getProperty("emailServerPort")));
	        pro.setEmailAddress(prop.getProperty("emailAddress"));
	        pro.setEmailPassword(prop.getProperty("emailPassword"));
	        
	        pro.setReportLinkPrefix(prop.getProperty("reportLinkPrefix"));
	        pro.setTestCasesListCsvFilePath(prop.getProperty("testCasesListCsvFilePath"));
	
		}
		
		
		public static void initConfig(String SrvID){

	        NodeList serverchildNodes = parseXML(SrvID,"ServerConfig.xml");
		
			getlibrarydata(serverchildNodes);
	
		}
		
		
	
		
		public static void main(String[] args){
			
			//read the messages used for search
			//testit();
			
			//search
			//searchMsg();
			
			//run one case
			
		}
		
		public static void testit(){
			
			//get the messages used in every case
			List<String> tmp = getCaseMsg("OnlineMultiDrive");
			
			for(int i=0; i<tmp.size(); i++){
				System.out.println(tmp.get(i));
			}
			
			
		}
		
		public static void searchMsg(String caseName){
			
			//search the messages in the log
			
			//read the log file from local disk
			//FileOutputStream fileOutputStream = new FileOutputStream("d:/log/case1.log");
			try{
				
				BufferedReader reader = new BufferedReader(new FileReader("d:/log/case1.log"));
				
		        String line = "";
		        List<String> testLines = new ArrayList<String>();
		        while ((line = reader.readLine()) != null) {
		        	testLines.add(line);
		        }
		        reader.close();
		        
//		        System.out.println("===========start==============");
//		        for(int i=0; i<testLines.size(); i++){
//		        	System.out.println(testLines.get(i));
//		        }
//		        System.out.println("===========end==============");

		
				//search, in lines, for the status...
				
		        //get msg name
				List<String> msgNames = getCaseMsg(caseName);
				//get msg status
				List<String> msgStatuses = getCaseMsgStatus(caseName);
				//List<String> msgStatuses = getCaseMsg(caseName);
				
				//initiate the log
				File resultFile = new File("d:/log/result.log");
				//delete older log, create a new empty one
				if(resultFile.exists()){
					resultFile.delete();
					resultFile = new File("d:/log/result.log");	
				}
			
				
				FileWriter out = new FileWriter(resultFile);
			
				boolean flage = false;
				for(int i=0; i<msgNames.size(); i++){
					//search tmp.get(i) in the file
					String msgName = msgNames.get(i);//get msg name
					String msgStatus = msgStatuses.get(i);//get msg status
					
					
						for(int j=0; j<testLines.size(); j++){
							//if(testLines.get(j).
							//if the line contains the string
							if(testLines.get(j).indexOf(msgName)!=-1){//if the line includes this key words,
								if(testLines.get(j).indexOf(msgStatus)!=-1){//the line should also includes the other key words, the status of the key words
									flage = true;
								}
								
							}//if
							
							//output the result to a file
							if(flage == true){
								out.append(msgName + " is in the log, correct!!!!!!!!!!!");
								out.append("\r\n");
								break;//break the j, and continue next message
							}
						}//for j
						
						if(flage == false){
							out.append(msgName + " is not in the log, wrong!!!!!!!!!!!");
							out.append("\r\n");
							out.append(caseName + "failed");
							out.append("\r\n");
							break;//break the j, and continue next message
						}
				
				}//for i
				
				if(flage == true){
					out.append("===========================================================\r\n");
					out.append(caseName + " PASS the test!!!!!!!!!!!");
					out.append("\r\n===========================================================\r\n");
				}
				
				
				out.close();
				
			}catch(IOException e){
				
				e.printStackTrace();
				
			}
				
			
			
			
			
}
		
		
//will be called by online more than one drive at the same time
public static void searchMsg(String caseName, String[] driveSN){
			
			//search the messages in the log
			
			//read the log file from local disk
			//FileOutputStream fileOutputStream = new FileOutputStream("d:/log/case1.log");
			try{
				
				BufferedReader reader = new BufferedReader(new FileReader("d:/log/case1.log"));
				
		        String line = "";
		        List<String> testLines = new ArrayList<String>();
		        while ((line = reader.readLine()) != null) {
		        	testLines.add(line);
		        }
		        reader.close();
		        
//		        System.out.println("===========start==============");
//		        for(int i=0; i<testLines.size(); i++){
//		        	System.out.println(testLines.get(i));
//		        }
//		        System.out.println("===========end==============");

		
				//search, in lines, for the status...
				
		        //get msg name
				List<String> msgNames = getCaseMsg(caseName);
				//get msg status
				List<String> msgStatuses = getCaseMsgStatus(caseName);
				
				String[] driveNames = driveSN;
				
				//initiate the log
				File resultFile = new File("d:/log/result.log");
				//delete older log, create a new empty one
				if(resultFile.exists()){
					resultFile.delete();
					resultFile = new File("d:/log/result.log");	
				}
			
				
				FileWriter out = new FileWriter(resultFile);
			
				boolean flage = false;
				
				//for every drive
				for(int k=0; k<driveNames.length; k++){
					
					String driveName = driveNames[k];
					
					for(int i=0; i<msgNames.size(); i++){
						//search tmp.get(i) in the file
						String msgName = msgNames.get(i);//get msg name
						String msgStatus = msgStatuses.get(i);//get msg status
						
						
							for(int j=0; j<testLines.size(); j++){
								//if(testLines.get(j).
								//if the line contains the string
								if(testLines.get(j).indexOf(msgName)!=-1){//if the line includes this key words,
									if(testLines.get(j).indexOf(msgStatus)!=-1){//the line should also includes the other key words, the status of the key words
										if(testLines.get(j).indexOf(driveName)!=-1){
											flage = true;
										}//if drive name
									}//if msg status
								}//if
								
								//output the result to a file
								if(flage == true){
									out.append(msgName + " is in the log, correct!!!!!!!!!!!");
									out.append("\r\n");
									break;//break the j, and continue next message
								}
							}//for j
							
							if(flage == false){
								out.append(msgName + " is not in the log, wrong!!!!!!!!!!!");
								out.append("\r\n");
								out.append(caseName + "failed");
								out.append("\r\n");
								break;//break the j, and continue next message
							}
					
					}//for i
				
					
				}//k
				
				if(flage == true){
					out.append("===========================================================\r\n");
					out.append(caseName + " PASS the test!!!!!!!!!!!");
					out.append("===========================================================\r\n");
				}
				
				
				out.close();
				
			}catch(IOException e){
				
				e.printStackTrace();
				
			}
				
			
			
			
			
}
		
		
		//
		public static void initWorkflowInfo(){
			//NodeList serverchildNodes = parseXML_WF("WF_of_case.xml");
			//getWFInformation(serverchildNodes);	
		}
		
		public static void initMessagesOfWFInfo(){
			//NodeList serverchildNodes = parseXML_WF("message_of_WF.xml");
			//getMessageInformation(serverchildNodes);	
		}
		
		public static void getMessageInformation(NodeList leroiinfoNodes){
			
			int i=0;
//			OmscDrives[] omscdrives = null;		
			
			
			int tmp = leroiinfoNodes.getLength();
			
		
	            for (int j = 0; j < tmp; j++) {  
	                Node currentinfo = leroiinfoNodes.item(j);  
	               
	                int type = currentinfo.getNodeType();
	                
	                if (currentinfo != null && type == Node.ELEMENT_NODE) {           
	                	
	                  /**Parse the Drives nodes**/ 
	                	String str = currentinfo.getNodeName();
	                	boolean flag = currentinfo.hasChildNodes();
	                    if(currentinfo.getNodeName().equals("MessagesOfWF")  && currentinfo.hasChildNodes() ){                   	
	                    	NodeList driveNodes = currentinfo.getChildNodes();
	                    	
	            			
	                    	
	                    	//store the information into some where
	                    	//
	                    	//omscdrives = new omscDrives [(driveNodes.getLength()-1)/2];
	                    
	                    	//add every workflow of this case
	                    	List<String> WFOfCase = new ArrayList<String>();
	            			WFOfCase.add("CaseName");
	                    	
	                    	
	                    	for (int k = 0; k < driveNodes.getLength(); k++) {
	                    		Node currentDriveNode = driveNodes.item(k); 
	                    		if (currentDriveNode != null && currentDriveNode.getNodeType() == Node.ELEMENT_NODE) {  
	                    			
	                    			WFOfCase.add(currentDriveNode.getAttributes().getNamedItem("msgname").getTextContent());

		                            
		                        }//if
	                    	}//for
	                    	
	                    	for(int m=0; m < WFOfCase.size(); m++){
	                    		logger.info(WFOfCase.get(m));
	                    	}

	                }//parse
	                    
	               }
	                
	                
	            
	       
		}

//	            srvconf.setDrives(omscdrives);
	    	//	logger.info("Set Drives omscDrives[][] successfully.");
			
		}
		
		
		//get the messages for every case:
		//return the message list
		public static List<String> getCaseMsg(String caseName){
			
			
			
			//get the wf of this specific case
			NodeList WFchildNodes = parseXML_WF("WF_of_case.xml", caseName);
			
			
			//WorkFlow
			//serverNodes = root.getChildNodes();
//////////////////////////////////////
			int tmp = WFchildNodes.getLength();
			
			List<String> WFOfCase = new ArrayList<String>();
		
	            for (int j = 0; j < tmp; j++) {  
	                Node currentinfo = WFchildNodes.item(j);  
	               
	                int type = currentinfo.getNodeType();
	                
	                if (currentinfo != null && type == Node.ELEMENT_NODE) {           
	                	
	                  /**Parse the WF nodes**/ 
	                	String str = currentinfo.getNodeName();
	                	boolean flag = currentinfo.hasChildNodes();
	                   
	                	if(currentinfo.getNodeName().equals("WorkFlow") ){
	                		WFOfCase.add(currentinfo.getAttributes().getNamedItem("wfname").getTextContent());
	                    	
	                    }//if
	                    
	                }//if
	               
	       
	            }//for j
	            
			
			//get the msg of the wfs
	            
	            List<String> msgOfCase = new ArrayList<String>();
	            
	            //for each workflow included in this case
	            for(int m=0; m < WFOfCase.size(); m++){
	            	String aaa = WFOfCase.get(m);
	            	NodeList msgchildNodes = parseXML_WF("message_of_WF.xml", aaa);
	            	//NodeList msgchildNodes = parseXML_WF("message_of_WF.xml", WFOfCase.get(m));
	            	
	            	//add each msg included in this workflow
	            	for(int n=0; n<msgchildNodes.getLength(); n++){
	            		Node currentinfo = msgchildNodes.item(n); 
	            		int type = currentinfo.getNodeType();
		                
		                if (currentinfo != null && type == Node.ELEMENT_NODE) {           
		                	
		                	String str = currentinfo.getNodeName();
		                	boolean flag = currentinfo.hasChildNodes();
		                   
		                	if(currentinfo.getNodeName().equals("Messages") ){
		                		msgOfCase.add(currentinfo.getAttributes().getNamedItem("msgname").getTextContent());
		                    	
		                    }//if
		                    
		                }//if
	            	}//for n
	            	
	            	//msgOfCase.add("=========");
            		
            	}//for m
	            
//	            logger.info("This is the messages included in this case===========================");
//	            for(int m=0; m < msgOfCase.size(); m++){
//            		logger.info(msgOfCase.get(m));
//            	}//debug
//	            logger.info("This is the workflow included in this case==========================");
			
			
			
			return msgOfCase;
			
		}
		
public static List<String> getCaseMsgStatus(String caseName){
			
			
			
			//get the wf of this specific case
			NodeList WFchildNodes = parseXML_WF("WF_of_case.xml", caseName);
			
			
			//WorkFlow
			//serverNodes = root.getChildNodes();
//////////////////////////////////////
			int tmp = WFchildNodes.getLength();
			
			List<String> WFOfCase = new ArrayList<String>();
		
	            for (int j = 0; j < tmp; j++) {  
	                Node currentinfo = WFchildNodes.item(j);  
	               
	                int type = currentinfo.getNodeType();
	                
	                if (currentinfo != null && type == Node.ELEMENT_NODE) {           
	                	
	                  /**Parse the Drives nodes**/ 
	                	String str = currentinfo.getNodeName();
	                	boolean flag = currentinfo.hasChildNodes();
	                   
	                	if(currentinfo.getNodeName().equals("WorkFlow") ){
	                		WFOfCase.add(currentinfo.getAttributes().getNamedItem("wfname").getTextContent());
	                    	
	                    }//if
	                    
	                }//if
	               
	       
	            }//for j
	            
//	            logger.info("This is the workflow included in this case===========================");
//	            for(int m=0; m < WFOfCase.size(); m++){
//            		logger.info(WFOfCase.get(m));
//            	}//debug
//	            logger.info("This is the workflow included in this case==========================");
			
			//////////////////////////////////
			
			//get the msg of the wfs
	            
	            List<String> msgStatusOfCase = new ArrayList<String>();
	            
	            //for each workflow included in this case
	            for(int m=0; m < WFOfCase.size(); m++){
	            	NodeList msgchildNodes = parseXML_WF("message_of_WF.xml", WFOfCase.get(m));
	            	
	            	//add each msg included in this workflow
	            	for(int n=0; n<msgchildNodes.getLength(); n++){
	            		Node currentinfo = msgchildNodes.item(n); 
	            		int type = currentinfo.getNodeType();
		                
		                if (currentinfo != null && type == Node.ELEMENT_NODE) {           
		                	
		                	String str = currentinfo.getNodeName();
		                	boolean flag = currentinfo.hasChildNodes();
		                   
		                	if(currentinfo.getNodeName().equals("Messages") ){
		                		msgStatusOfCase.add(currentinfo.getAttributes().getNamedItem("msgstatus").getTextContent());
		                    	
		                    }//if
		                    
		                }//if
	            	}//fgor n
	            	
	            	//msgOfCase.add("=========");
            		
            	}//for m
	            
//	            logger.info("This is the messages included in this case===========================");
//	            for(int m=0; m < msgOfCase.size(); m++){
//            		logger.info(msgOfCase.get(m));
//            	}//debug
//	            logger.info("This is the workflow included in this case==========================");
			
			
			
			return msgStatusOfCase;
			
		}
		
		public static List<String> test(){
			List<String> WFOfCase = new ArrayList<String>();
			
			return WFOfCase;
			
		}
		
		public static void getWFInformation(NodeList leroiinfoNodes){
			
			int i=0;
//			OmscDrives[] omscdrives = null;		
			
			
			int tmp = leroiinfoNodes.getLength();
		
			List<String> WFOfCase = new ArrayList<String>();
		
	            for (int j = 0; j < tmp; j++) {  
	                Node currentinfo = leroiinfoNodes.item(j);  
	               
	                int type = currentinfo.getNodeType();
	                
	                if (currentinfo != null && type == Node.ELEMENT_NODE) {           
	                	
	                  /**Parse the Drives nodes**/ 
	                	String str = currentinfo.getNodeName();
	                	boolean flag = currentinfo.hasChildNodes();
	                    if(currentinfo.getNodeName().equals("WorkFlows")  && currentinfo.hasChildNodes() ){                   	
	                    	NodeList driveNodes = currentinfo.getChildNodes();
	                    	
	            			
	                    	
	                    	//store the information into some where
	                    	//
	                    	//omscdrives = new omscDrives [(driveNodes.getLength()-1)/2];
	                    
	                    	//add every workflow of this case
	                    	
	            			WFOfCase.add("CaseName");
	                    	
	                    	
	                    	for (int k = 0; k < driveNodes.getLength(); k++) {
	                    		Node currentDriveNode = driveNodes.item(k); 
	                    		if (currentDriveNode != null && currentDriveNode.getNodeType() == Node.ELEMENT_NODE) {  
	                    			
	                    			WFOfCase.add(currentDriveNode.getAttributes().getNamedItem("wfname").getTextContent());

		                            
		                        }//if
	                    	}//for
	                    	
	                    	for(int m=0; m < WFOfCase.size(); m++){
	                    		logger.info(WFOfCase.get(m));
	                    	}//debug

	                    }//parse
	                    
	               }//for
	                
	                
	               
	            
	       
		}

//	            srvconf.setDrives(omscdrives);
	    	//	logger.info("Set Drives omscDrives[][] successfully.");
			
		}
		
		/**20130226 
		 * parse ServerConfig.xml
		 * purpose:get the config data
		 * para:String ServerID, String FilePath
		 * return: <server> list **/
		public static NodeList parseXML(String serverName,String filepath){	
			
					NodeList serverNodes = null;
					NodeList leroiinfoNodes = null;
					
				  /* ini a arry to record url/username/adminadmin   */
				//  String[] str = new String[3];
				  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
				  
			            DocumentBuilder builder;
						try {
							builder = dbf.newDocumentBuilder();
				
							
			            InputStream in = Configuration.class.getClassLoader().getResourceAsStream(filepath);
			            Document doc = builder.parse(in);  
			            // root <config>  
			            Element root = doc.getDocumentElement();  
			            if (root == null) return null;  
			            logger.info("Reading server and Hardware info...");
			            //System.err.println(root.getAttribute("name"));  

			            // all <server> nodes  
			            serverNodes = root.getChildNodes(); 
			            //query
			        	for(int i = 0; i < serverNodes.getLength(); i++) {  
			                Node server = serverNodes.item(i);  
			                if (server != null && server.getNodeType() == Node.ELEMENT_NODE) {  
			                	
			                	//judge the server name
			                	if(server.getAttributes().getNamedItem("name").getNodeValue().equals(serverName)){                 	               		
			                	//System.err.println("\t" +"<"+server.getNodeName()+">"+ server.getAttributes().getNamedItem("name").getNodeValue());  		         
			                			                	
			                	// all <server>'s childnodes  
			                    leroiinfoNodes = server.getChildNodes();
			                	}
			                }
			        	}
			            
						} catch (ParserConfigurationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SAXException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						logger.info(filepath+" is parsed successfully.");
						return leroiinfoNodes;
						
			          
		}
		
		public static NodeList parseXML_WF(String filepath, String caseName){	
			
			NodeList serverNodes = null;
			NodeList leroiinfoNodes = null;
			
		  /* ini a arry to record url/username/adminadmin   */
		//  String[] str = new String[3];
		  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
		  
	            DocumentBuilder builder;
				try {
					builder = dbf.newDocumentBuilder();
		
					
	            InputStream in = Configuration.class.getClassLoader().getResourceAsStream(filepath);
	            Document doc = builder.parse(in);  
	            // root <config>  
	            Element root = doc.getDocumentElement();  
	            if (root == null) return null;  
	            logger.info("Reading WF info...");
	            //System.err.println(root.getAttribute("name"));  

	            // all  nodes  
	            serverNodes = root.getChildNodes(); 
	            
	            //query
	        	for(int i = 0; i < serverNodes.getLength(); i++) {  
	                Node server = serverNodes.item(i);  
	                if (server != null && server.getNodeType() == Node.ELEMENT_NODE) {  
	                	
	                	//judge the server name
	                	if(server.getAttributes().getNamedItem("name").getNodeValue().equals(caseName)){                 	               		
	                	//System.err.println("\t" +"<"+server.getNodeName()+">"+ server.getAttributes().getNamedItem("name").getNodeValue());  		         
	                			                	
	                	// all <server>'s childnodes  
	                    leroiinfoNodes = server.getChildNodes();
	                	}
	                }
	        	}
	            
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				logger.info(filepath+" is parsed successfully.");
				return leroiinfoNodes;
				
	          
}
		


		
		/**get library data
		 * @param NodeList
		 * @return String[][]
		 * **/
		public static void getlibrarydata(NodeList leroiinfoNodes){

			for (int j = 0; j < leroiinfoNodes.getLength(); j++) {  
		        Node currentinfo = leroiinfoNodes.item(j);  
		        if (currentinfo != null && currentinfo.getNodeType() == Node.ELEMENT_NODE) {   
		            String nodevalue = currentinfo.getChildNodes().item(0).getNodeValue();
	
		            if(currentinfo.getNodeName().equals("UIUrl"))
		            	srvconf.setUIUrl(nodevalue);
		            if(currentinfo.getNodeName().equals("UIAdvancedUrl"))
		            	srvconf.setUIAdvancedUrl(nodevalue);
		        }
			}
		
			logger.info("Set SrvConfig successfully.");
		}
//		/**get Drive data
//		 * @param NodeList
//		 * @return String[][]
//		 * **/
//		public static void getdrivedata(NodeList leroiinfoNodes){
//			
//			int i=0;
//			
//	            for (int j = 0; j < leroiinfoNodes.getLength(); j++) {  
//	                Node currentinfo = leroiinfoNodes.item(j);  
//	                if (currentinfo != null && currentinfo.getNodeType() == Node.ELEMENT_NODE) {                     
//	                  /**Parse the Drives nodes**/ 
//	                    if(currentinfo.getNodeName().equals("Drives") && currentinfo.hasChildNodes() ){                   	
//	                    	NodeList driveNodes = currentinfo.getChildNodes();
//	                    	
//	                    	for (int k = 0; k < driveNodes.getLength(); k++) {
//	                    		Node currentDriveNode = driveNodes.item(k); 
//	                    		if (currentDriveNode != null && currentDriveNode.getNodeType() == Node.ELEMENT_NODE) {  
//
//		                            String drivesn = currentDriveNode.getAttributes().getNamedItem("drivesn").getTextContent();
//		                            String drivetype = currentDriveNode.getAttributes().getNamedItem("type").getTextContent();
//		                            String driveacsls = currentDriveNode.getAttributes().getNamedItem("acsls").getTextContent();
//                         
//		                            
//		                            i++;
//		                            
//		                        }
//	                    }
//
//	                }
//	                }
//	            
//	       
//		}
////
//////	            srvconf.setDrives(omscdrives);
////	    		logger.info("Set Drives Drives[][] successfully.");
//		}
		
		
		
		
//		/**get Volume data
//		 * @param NodeList
//		 * @return String[][]
//		 * **/
//		public static void getvolumedata(NodeList leroiinfoNodes){
//			
//			int i = 0;
////			OmscVolumes[] omscvolumes = null;
//			
//			
//	            for (int j = 0; j < leroiinfoNodes.getLength(); j++) {  
//	                Node currentinfo = leroiinfoNodes.item(j);  
//	                if (currentinfo != null && currentinfo.getNodeType() == Node.ELEMENT_NODE) {                      
//	                    /**Parse the Volumes nodes **/
//	                    if(currentinfo.getNodeName().equals("Volumes") && currentinfo.hasChildNodes() ){                     	
//	                    	NodeList volumeNodes = currentinfo.getChildNodes(); 
//	                    	
////	                    	for (int k = 0; k < volumeNodes.getLength(); k++) {
////	                    		Node currentDriveNode = volumeNodes.item(k); 
////	                    		if (currentDriveNode != null && currentDriveNode.getNodeType() == Node.ELEMENT_NODE) {   
////		                            String volsn = currentDriveNode.getAttributes().getNamedItem("volsn").getTextContent();
////		                            String voltype = currentDriveNode.getAttributes().getNamedItem("type").getTextContent();
////		                            
////		                            
////		                            i++;
////		                        }
////	                    	}
//	                    }
//	                }
//	            
//	       
//		}
//
//		}	

}
