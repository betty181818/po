package UITestCases.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import UITestCases.BaseTestCase;
import exception.AssertErrorException;
import exception.UnExpectedException;
import framework.global.Configuration;

import framework.sharedFunctions.HomePage;

import framework.sharedFunctions.Initialize;
import junit.framework.Assert;

/**
* Date : 2018-08-01
* Owner : betty.wang
* @author : betty.wang
*/


public class Checktext extends BaseTestCase{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Configuration.init();
		logger.info("Checktext:"); 		 
		Thread.sleep(3000);
	}
	
	@Before
	public void prepare() throws IOException {

		Configuration.prepare(this.getClass().getName());
	
		setTestName(this.getClass().getName());

	}
	
	@Test
	public void test() throws IOException, InterruptedException {
									 
		try {
			
			logger.info("Open the Oracle Microservice Development Toolkit application page:"); 		
			HomePage app_page=new HomePage();
			app_page.openAppPage();
	
			logger.info("text: " + driver.findElement(By.xpath("//html")).getText()); 
			
			boolean verify = false;
			Thread.sleep(8000);
			String word = driver.findElement(By.xpath("//html")).getText();
			System.out.print(word);
			if (driver.findElement(By.xpath("//html")).getText().equals("Hello from Oracle Microservice Development Toolkit!"))
			{
				verify = true;
			}
			else{
	        	verify = false;
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
			driver.quit();

		}
	}

}
