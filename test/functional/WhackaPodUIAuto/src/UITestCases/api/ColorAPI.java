package UITestCases.api;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import UITestCases.BaseTestCase;
import exception.AssertErrorException;
import exception.UnExpectedException;
import framework.global.Configuration;

import framework.sharedFunctions.HomePage;

import framework.sharedFunctions.Initialize;

/**
* Date : 2017-11-08
* Owner : lixia.yuan
* @author : lixia.yuan
*/


public class ColorAPI extends BaseTestCase{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		logger.info("test case: color api service"); 		
//		ibrowser.Start_Browser();
//		Thread.sleep(3000);
	}
	
	@Before
	public void prepare() throws IOException {
//		init.prepare(this.getClass().getName());
		Configuration.prepare(this.getClass().getName());
		
		 
	//	ls.setCaseName(this.getClass().getSimpleName());
		setTestName(this.getClass().getName());
		
//		String testKey = "MIGSERV-1932";
//		writeTestKeyToCsvFile(testKey);
	}
	
	@Test
	public void test() throws IOException, InterruptedException {
									 
		try {
			logger.info("test case: color api service"); 	
			HomePage wap_homepage=new HomePage();
//			wap_homepage.openHomePage();
//			Thread.sleep(5000);	
			
//			logger.info("Click to Deploy"); 		
//			wap_homepage.clicktoDeploy();
			Thread.sleep(8000);	
			
			boolean verify = true;
			logger.info("check color API is running or not"); 	
			String color = wap_homepage.clorAPIRunning();
			logger.info("color: " + color); 	
			//carnival
//			if ( color.contains("255, 0, 0") &&  !wap_homepage.isK8sServiceUp()){
//				verify = false;
//			}
			
			//next 
			if ( color.contains("51, 51, 51") ){
				verify = false;
				logger.info("check color API is not available, verify: " + verify); 	
			}

			if ( color.contains("255, 0, 0") && wap_homepage.isNextK8sServiceDown()){
				verify = false;
				logger.info("check color API is DOWN, verify: " + verify); 	
			}
			
			// assert
			logger.info("assert: " + verify); 	
			assertVerify.verifyTrue(verify);
			success();
			
		}catch (AssertErrorException e) {
	    	fail();
	    }catch (Exception e) {
	    	new UnExpectedException(driver, logger, e);
	    	fail();
	    }
		finally{
//			Logout logout = new Logout();
//			logout.logout();
		}
	}

}
