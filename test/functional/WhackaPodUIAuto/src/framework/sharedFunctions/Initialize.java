/**
 * 
 */
package framework.sharedFunctions;
import framework.global.Configuration;


/**
* Date : 2018-03-28
* Owner : lixia.yuan
* @author : lixia.yuan
*/

import java.io.File;

import framework.objects.CommonVariables;
import framework.objects.PropertyReader;

public class Initialize extends CommonVariables {

	public void init(){	
		 logger.info("Auto Initializing...");
//		 SrvConfigReader configReader = new SrvConfigReader();	
		 PropertyReader ProReader = new PropertyReader();
		 ProReader.getProperty();
		 
		 try {
			 logHandle.start();
			 File file = new File("logs");
			 File resultFile = new File("logs//Result");
			 File screenFile = new File("logs//Screenshots");
			 String csvFilePath = Configuration.pro.getTestCasesListCsvFilePath();
				
			 File testCaseListsfile = new File(csvFilePath);  
		     if(!testCaseListsfile.exists() )
		     {
		        testCaseListsfile.createNewFile();
		     }

			 if(!file.exists()) {
				 file.mkdir();
			 }
			 if(!resultFile.exists()) {
				 resultFile.mkdir();
			 }
			 if(!screenFile.exists()) {
				 screenFile.mkdir();
			 }
		 }catch (Exception e) {
			 
			 logger.error("fail to creat log files");
			 logger.error(e.getMessage(),e);
		 }
	
	}
}
