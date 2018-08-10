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
* Date : 2018-03-28
* Owner : lixia.yuan
* @author : lixia.yuan
*/


public class WhackaPodHomePage extends BaseTestCase{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Initialize wap=new Initialize();
		wap.init();

		logger.info("test case: Open Whack-a-pod home page"); 		
		ibrowser.Start_Browser();
		Thread.sleep(3000);
	}
	
	@Before
	public void prepare() throws IOException {

		Configuration.prepare(this.getClass().getName());
		
		setTestName(this.getClass().getName());
		
//		String testKey = "MIGSERV-1932";
//		writeTestKeyToCsvFile(testKey);
	}
	
	@Test
	public void test() throws IOException, InterruptedException {
									 
		try {
			
			logger.info("Open Whack a Pot Home Page"); 		
			HomePage wap_homepage=new HomePage();
			wap_homepage.openHomePage();
			Thread.sleep(5000);	
			
			boolean verify;

//			//verify welcome shows in start-modal
			logger.info("verify welcome shows in start-modal"); 		
			verify = wap_homepage.isStartModelDisplayed();
			logger.info("IsStartModelDisplayed:" + verify); 	
			
			// assert
			logger.info("verify welcome shows in start-modal" + verify); 	
			assertVerify.verifyTrue(verify);
			success();
			
		}catch (AssertErrorException e) {
	    	fail();
	    }catch (Exception e) {
	    	new UnExpectedException(driver, logger, e);
	    	fail();
	    }
		finally{
		}
	}

}
