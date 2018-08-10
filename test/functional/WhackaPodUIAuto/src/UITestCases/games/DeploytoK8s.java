package UITestCases.games;

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


public class DeploytoK8s extends BaseTestCase{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		logger.info("test case: Click to Deploy"); 		
		ibrowser.Start_Browser();
		Thread.sleep(3000);
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
			logger.info("test case: Click to Deploy"); 		
			HomePage wap_homepage=new HomePage();
			
			logger.info("Click to Deploy"); 		
			wap_homepage.clicktoDeploy();
			Thread.sleep(8000);	
			
			//next
			logger.info("Pods shows up"); 	
			boolean verify = wap_homepage.isPodsDisplayed(0);
		
			//carnival
//			int i=0;
//			boolean verifyServiceUP;
//			verifyServiceUP = wap_homepage.isK8sServiceUp();
//			logger.info("verifyServiceUP: " + verifyServiceUP); 
//			
//			while (!verifyServiceUP){
//				Thread.sleep(1000);	
//				verifyServiceUP = wap_homepage.isK8sServiceUp();
//				i++;
//				if (i>20){
//					logger.info("K8s Service Start Up Timeout 20sec"); 	
//					break;					
//				}
//			}
			

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
