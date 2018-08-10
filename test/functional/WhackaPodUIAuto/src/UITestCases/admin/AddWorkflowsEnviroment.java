package UITestCases.admin;

import static org.junit.Assert.*;


import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import UITestCases.BaseTestCase;
import exception.AssertErrorException;
import exception.UnExpectedException;
import framework.global.Configuration;

import framework.sharedFunctions.HomePage;

import framework.sharedFunctions.Initialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.openqa.selenium.support.ui.Select;
/**
* Date :2018.07.19
* Owner :  Betty.wang
* @author : Betty.wang
*/


public class AddWorkflowsEnviroment extends BaseTestCase{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Configuration.init();
		logger.info("use wercker to create application:"); 			  
		Thread.sleep(3000);
	}
	
	@Before
	public void prepare() throws IOException {

		Configuration.prepare(this.getClass().getName());
		
		 
		setTestName(this.getClass().getName());
		
	}
	
	@Test
	public void test() throws IOException, InterruptedException, AssertErrorException {
		 
		try {
			
			File currentDir = new File("");
			String curDir = currentDir.getAbsolutePath();
			
			String sysSeq = System.getProperty("file.separator");
			
			boolean verify = true;
			logger.info("read file:");
			Properties p = new Properties();
				InputStream ips = new FileInputStream(curDir + sysSeq + "config" + sysSeq + "config.properties");
				p.load(ips);
			logger.info("open wercher:"); 		
			HomePage wap_homepage=new HomePage();
			wap_homepage.openHomePage();

			logger.info("input the wercher username from file:");
			driver.findElement(By.id("username")).clear();
		    driver.findElement(By.id("username")).sendKeys(new String[] { p.getProperty("WERCHER_USERNAME") });
		    logger.info("input the wercher password from file:");
		    driver.findElement(By.id("password")).clear();
		    driver.findElement(By.id("password")).sendKeys(new String[] { p.getProperty("WERCHER_PASSWORD") });
		    logger.info("click lgoin github :");
		    driver.findElement(By.xpath(".//*[@id='js-form-login']/div[5]/div/button")).click();
		    logger.info("input github username from file:");
		   
		    driver.findElement(By.id("login_field")).clear();
		    driver.findElement(By.id("login_field")).sendKeys(new String[] { p.getProperty("GITHUB_USERNAME") });
		    logger.info("input github password from file :");
		    driver.findElement(By.id("password")).clear();
		    driver.findElement(By.id("password")).sendKeys(new String[] { p.getProperty("GITHUB_PASSWORD") });
		    logger.info("click github login button :");
		    driver.findElement(By.name("commit")).click();
		   
		    Thread.sleep(3000);
		    logger.info("if the authorize button display click it:");
	
		    if (driver.findElements( By.id("js-oauth-authorize-btn")).size() != 0) { 
		    	driver.findElement(By.id("js-oauth-authorize-btn")).click();
		    }
		    
		    Thread.sleep(5000);
		    logger.info("click wercher img:");
		    driver.findElement(By.xpath(".//*[@id='root']/div/div[1]/div/div/div/div[1]/a/img")).click();
		    Thread.sleep(5000);
		    logger.info("click the exist application:");
		    driver.findElement(By.xpath("//article/div[2]/div/div/div/div/a")).click();
		    Thread.sleep(5000);
		    
		    logger.info("click the Enviroment tab:");
		    driver.findElement(By.xpath(".//*[@id='page']/div/div[1]/div/div/ol/li[4]/a/div/span[1]")).click();
		    Thread.sleep(5000);
		    
		    logger.info("Add SONAR_URL");
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).sendKeys("SONAR_URL");
		    Thread.sleep(3000);
		   
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).sendKeys(new String[] { p.getProperty("WERCHER_USERNAME") });
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/button")).click();
		    Thread.sleep(5000);
		    
		    logger.info("Add SONAR_PROJECT_KEY");
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).sendKeys("SONAR_PROJECT_KEY");
		    Thread.sleep(3000);
		   
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).sendKeys("com.oracle.microsvctk:demo");
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/button")).click();
		    Thread.sleep(5000);
		    
		    logger.info("Add OCIR_USERNAME");
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).sendKeys("OCIR_USERNAME");
		    Thread.sleep(3000);
		   
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).sendKeys(new String[] { p.getProperty("WERCHER_USERNAME") });
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/button")).click();
		    Thread.sleep(5000);
		    
		    logger.info("Add OCIR_PASSWORD");
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).sendKeys("OCIR_PASSWORD");
		    Thread.sleep(3000);
		   
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).sendKeys(new String[] { p.getProperty("WERCHER_USERNAME") });
		    Thread.sleep(3000);
		  
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/div/label/input")).click();
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/button")).click();
		    Thread.sleep(5000);
		    
		    logger.info("Add OCIR_TAG");
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).sendKeys("OCIR_TAG");
		    Thread.sleep(3000);
		   
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).sendKeys("microsvctk");
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/button")).click();
		    Thread.sleep(3000);
		    
		    logger.info("Add OCIR_REPOSITORY");
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).sendKeys("OCIR_REPOSITORY");
		    Thread.sleep(3000);
		   
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).sendKeys("devops_microservice_toolkit");
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/button")).click();
		    Thread.sleep(3000);
		    
		    logger.info("Add OKE_NAME_PREFIX");
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).sendKeys("OKE_NAME_PREFIX");
		    Thread.sleep(3000);
		   
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).sendKeys("microsvctk");
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/button")).click();
		    Thread.sleep(3000);
		    
		    logger.info("Add OKE_USER_OCID");
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).sendKeys("OKE_USER_OCID");
		    Thread.sleep(3000);
		   
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).sendKeys(new String[] { p.getProperty("WERCHER_USERNAME") });
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/button")).click();
		    Thread.sleep(3000);
		    
		    logger.info("Add OKE_USER_FINGERPRINT");
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).sendKeys("OKE_USER_FINGERPRINT");
		    Thread.sleep(3000);
		   
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).sendKeys(new String[] { p.getProperty("WERCHER_USERNAME") });
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/button")).click();
		    Thread.sleep(3000);
		  
		    logger.info("Add OKE_API_KEY");
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).sendKeys("OKE_API_KEY");
		    Thread.sleep(3000);
		   
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).sendKeys(new String[] { p.getProperty("WERCHER_USERNAME") });
		    Thread.sleep(3000);
		  
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/div/label/input")).click();
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/button")).click();
		    Thread.sleep(3000);
		    
		    logger.info("Add OKE_TENANCY_OCID");
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).sendKeys("OKE_TENANCY_OCID");
		    Thread.sleep(3000);
		   
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).sendKeys(new String[] { p.getProperty("WERCHER_USERNAME") });
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/button")).click();
		    Thread.sleep(3000);
		    
		    logger.info("Add OKE_REGION");
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).sendKeys("OKE_REGION");
		    Thread.sleep(5000);
		   
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).sendKeys(new String[] { p.getProperty("WERCHER_USERNAME") });
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/button")).click();
		    Thread.sleep(5000);
		    
		    logger.info("Add OKE_COMPARTMENT_OCID");
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).sendKeys("OKE_COMPARTMENT_OCID");
		    Thread.sleep(5000);
		   
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).sendKeys(new String[] { p.getProperty("WERCHER_USERNAME") });
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/button")).click();
		    Thread.sleep(5000);
		   
		    
		    logger.info("Add OCIR_REGISTORY");
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).sendKeys("OCIR_REGISTORY");
		    Thread.sleep(5000);
		   
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).sendKeys("https://iad.ocir.io/v2/");
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/button")).click();
		    Thread.sleep(5000);
  
		}catch (Exception e) {
	    	new UnExpectedException(driver, logger, e);
	    	fail();
	    }
		finally{
			driver.quit();

		}
	}

}

	