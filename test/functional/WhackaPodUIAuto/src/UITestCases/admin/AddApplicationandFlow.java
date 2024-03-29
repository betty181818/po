package UITestCases.admin;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import UITestCases.BaseTestCase;
import exception.AssertErrorException;
import exception.UnExpectedException;
import framework.global.Configuration;

import framework.sharedFunctions.HomePage;

import framework.sharedFunctions.Initialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
* Date :2018.07.19
* Owner :  Betty.wang
* @author : Betty.wang
*/


public class AddApplicationandFlow extends BaseTestCase{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Configuration.init();
		logger.info("use wercker to create application:"); 			  
		Thread.sleep(5000);
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
			
			String fpath = p.getProperty("OCI_PRIVATE_KEY_PATH");
			File file=new File(fpath); 
            Long filelength = file.length();
            byte[] filecontent = new byte[filelength.intValue()];
            try {
                FileInputStream in = new FileInputStream(file);
                in.read(filecontent);
                in.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String api_key = new String(filecontent);
            

			logger.info("input the wercher username from file:");
			driver.findElement(By.id("username")).clear();
		    driver.findElement(By.id("username")).sendKeys(new String[] { p.getProperty("WERCKER_USERNAME") });
		    logger.info("input the wercher password from file:");
		    driver.findElement(By.id("password")).clear();
		    driver.findElement(By.id("password")).sendKeys(new String[] { p.getProperty("WERCKER_PASSWORD") });
		    logger.info("click the login wercher button:");
		    driver.findElement(By.id("login")).click();
//		    logger.info("click lgoin github :");
//		    driver.findElement(By.xpath(".//*[@id='js-form-login']/div[5]/div/button")).click();
//		    logger.info("input github username from file:");
		   
//		    driver.findElement(By.id("login_field")).clear();
//		    driver.findElement(By.id("login_field")).sendKeys(new String[] { p.getProperty("GITHUB_USERNAME") });
//		    logger.info("input github password from file :");
//		    driver.findElement(By.id("password")).clear();
//		    driver.findElement(By.id("password")).sendKeys(new String[] { p.getProperty("GITHUB_PASSWORD") });
//		    logger.info("click github login button :");
//		    driver.findElement(By.name("commit")).click();
		   
		    Thread.sleep(8000);
		    logger.info("if the authorize button display click it:");
	
		    if (driver.findElements( By.id("js-oauth-authorize-btn")).size() != 0) { 
		    	driver.findElement(By.id("js-oauth-authorize-btn")).click();
		    }
		    Thread.sleep(8000);
		    logger.info("click the plus to create application:");
		    driver.findElement(By.xpath(".//*[@id='root']/div/div[1]/div/div/div/div[2]/div/div[2]/div[1]/div[1]/*[name()=\"svg\"]")).click();
		    logger.info("dropdown list choose create application:");
		   Thread.sleep(5000);
		   driver.findElement(By.xpath("//div[3]/div/div/div[2]/div/div/a[contains(text(),'Add application')]")).sendKeys(Keys.ENTER);	 
	
		    Thread.sleep(8000);
		    logger.info("click select scm GitHub :");
		    driver.findElement(By.xpath(".//*[@id='page']/div/div/div/div/div/div[2]/div[2]/div/div[3]/div[2]/div[1]/button")).click(); //select scm GitHub
		    Thread.sleep(3000);
		    if (driver.findElements(By.xpath(".//*[@id='toaster-container']/div[1]/div[1]/div[1]/a")).size() != 0) { 
		    	driver.findElement(By.xpath(".//*[@id='toaster-container']/div[1]/div[1]/div[1]/a")).click();
		    }
		    logger.info("click select scm GitHub page next button:");
		    driver.findElement(By.xpath("//div[@id='page']/div/div/div/div/div/div[2]/div[3]/div[2]/button")).click(); //click the next button
		    Thread.sleep(5000);
		    logger.info("select repository:");
		    driver.findElement(By.xpath(".//*[@id='page']/div/div/div/div/div/div[2]/div[2]/div/div[2]/div[2]/div/div/div[1]/input")).click();
		    driver.findElement(By.xpath(".//*[@id='page']/div/div/div/div/div/div[2]/div[2]/div/div[2]/div[2]/div/div/div[1]/input")).sendKeys(new String[] { p.getProperty("GITHUB_REPOSITORY") });
		    driver.findElement(By.xpath(".//*[@id='page']/div/div/div/div/div/div[2]/div[2]/div/div[2]/div[2]/div/div/div[2]/div[1]/div/div/div/div/div/div[1]/span/strong")).click(); //select repository
		    Thread.sleep(5000);
		    logger.info("in select repository click next button:");
		    driver.findElement(By.xpath("//div[@id='page']/div/div/div/div/div/div[2]/div[3]/div[2]/button")).click(); //click the next button
		    Thread.sleep(5000);
		    logger.info("select setup ssh key");
		    driver.findElement(By.xpath(".//*[@id='page']/div/div/div/div/div/div[2]/div[2]/div/div[3]/div/div")).click(); //setup ssh key
		    Thread.sleep(5000);
		    logger.info("in select setup ssh key page click next button:");
		    driver.findElement(By.xpath("//div[@id='page']/div/div/div/div/div/div[2]/div[3]/div[2]/button")).click(); //click the next button
		    Thread.sleep(5000);
		    logger.info("select public checkbox:");
		    driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		    Thread.sleep(5000);
		    logger.info("click create button:");
		    driver.findElement(By.xpath(".//*[@id='page']/div/div/div/div/div/div[2]/div[3]/div[2]/button")).click();//click the create button
		    Thread.sleep(5000);
		    
		    //Add ENVIROMENT
		    
		    Thread.sleep(5000);
		    logger.info("click wercher img:");
		    driver.findElement(By.xpath(".//*[@id='root']/div/div[1]/div/div/div/div[1]/a/img")).click();
		    Thread.sleep(5000);
		    if (driver.findElements(By.xpath(".//*[@id='toaster-container']/div[1]/div[1]/div[1]/a")).size() != 0) { 
		    	driver.findElement(By.xpath(".//*[@id='toaster-container']/div[1]/div[1]/div[1]/a")).click();
		    }
		    logger.info("click the exist application:");
		    driver.findElement(By.xpath("//article/div[2]/div/div/div/div/a")).click();
		    Thread.sleep(5000);
		    if (driver.findElements(By.xpath(".//*[@id='toaster-container']/div[1]/div[1]/div[1]/a")).size() != 0) { 
		    	driver.findElement(By.xpath(".//*[@id='toaster-container']/div[1]/div[1]/div[1]/a")).click();
		    }
		    logger.info("click the Enviroment tab:");
		    driver.findElement(By.xpath(".//*[@id='page']/div/div[1]/div/div/ol/li[4]/a/div/span[1]")).click();
		    Thread.sleep(10000);
		    
		    logger.info("Add SONAR_URL");
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).sendKeys("SONAR_URL");
		    Thread.sleep(3000);
		   
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).sendKeys(new String[] { p.getProperty("SONAR_URL") });
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/button")).click();
		    Thread.sleep(5000);
		   
		    logger.info("Add SONAR_PROJECT_KEY");
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).sendKeys("SONAR_PROJECT_KEY");
		    Thread.sleep(3000);
		   
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).sendKeys("com.oracle.microsvctk:demo");
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/button")).click();
		    Thread.sleep(3000);
		    
		    logger.info("Add OCIR_USERNAME");
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).sendKeys("OCIR_USERNAME");
		    Thread.sleep(3000);
		   
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).sendKeys(new String[] { p.getProperty("OCIR_USERNAME") });
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/button")).click();
		    Thread.sleep(3000);
		    
		    logger.info("Add OCIR_PASSWORD");
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).sendKeys("OCIR_PASSWORD");
		    Thread.sleep(3000);
		   
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).sendKeys(new String[] { p.getProperty("OCIR_PASSWORD") });
		    Thread.sleep(3000);
		  
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/div/label/input")).click();
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/button")).click();
		    Thread.sleep(3000);
		    
		    logger.info("Add OCIR_TAG");
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).sendKeys("OCIR_TAG");
		    Thread.sleep(3000);
		   
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).sendKeys("microsvctk");
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/button")).click();
		    Thread.sleep(3000);
		    
		    logger.info("Add OCIR_REPOSITORY");
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).sendKeys("OCIR_REPOSITORY");
		    Thread.sleep(3000);
		   
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).sendKeys("devops_microservice_toolkit");
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/button")).click();
		    Thread.sleep(3000);
		    
		    logger.info("Add OKE_NAME_PREFIX");
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).sendKeys("NAME_PREFIX");
		    Thread.sleep(3000);
		   
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).sendKeys("microsvctk");
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/button")).click();
		    Thread.sleep(3000);
		    
		    logger.info("Add OKE_USER_OCID");
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).sendKeys("USER_OCID");
		    Thread.sleep(3000);
		   
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).sendKeys(new String[] { p.getProperty("USER_OCID") });
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/button")).click();
		    Thread.sleep(3000);
		    
		    logger.info("Add OKE_USER_FINGERPRINT");
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).sendKeys("FINGERPRINT");
		    Thread.sleep(3000);
		   
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).sendKeys(new String[] { p.getProperty("FINGER_PRINT") });
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/button")).click();
		    Thread.sleep(3000);
		  
		    logger.info("Add OKE_API_KEY");
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).sendKeys("API_KEY");
		    Thread.sleep(3000);
		   
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).clear();

		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).sendKeys(api_key);
		    Thread.sleep(3000);
		  
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/div/label/input")).click();
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/button")).click();
		    Thread.sleep(3000);
		    
		    logger.info("Add OKE_TENANCY_OCID");
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).sendKeys("TENANCY_OCID");
		    Thread.sleep(3000);
		   
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).sendKeys(new String[] { p.getProperty("TENANCY_OCID") });
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/button")).click();
		    Thread.sleep(3000);
		    
		    logger.info("Add OKE_REGION");
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).sendKeys("REGION");
		    Thread.sleep(5000);
		   
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).sendKeys(new String[] { p.getProperty("REGION_CODE") });
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/button")).click();
		    Thread.sleep(5000);
		    
		    logger.info("Add OKE_COMPARTMENT_OCID");
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).sendKeys("COMPARTMENT_OCID");
		    Thread.sleep(3000);
		   
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).sendKeys(new String[] { p.getProperty("COMPARTMENT_OCID") });
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/button")).click();
		    Thread.sleep(3000);
		   
		    
		    logger.info("Add OCIR_REGISTRY");
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).sendKeys("OCIR_REGISTRY");
		    Thread.sleep(3000);
		   
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).sendKeys(new String[] { p.getProperty("OCIR_REGISTORY") });
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/button")).click();
		    
		    logger.info("Add SONAR_GET_RESULT_LOOP_CNT");
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td/input")).sendKeys("SONAR_GET_RESULT_LOOP_CNT");
		    Thread.sleep(3000);
		   
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).clear();
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/div/textarea")).sendKeys("10");
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//tfoot/tr/td[2]/div/button")).click();
		    Thread.sleep(3000);
		    
		    //Add pipeline and flow
		    
		    Thread.sleep(3000);
		    logger.info("click wercher img:");
		    driver.findElement(By.xpath(".//*[@id='root']/div/div[1]/div/div/div/div[1]/a/img")).click();
		    Thread.sleep(5000);
		    logger.info("click the exist application:");
		    driver.findElement(By.xpath("//article/div[2]/div/div/div/div/a")).click();
		    Thread.sleep(5000);
		  
		    logger.info("click the workflows tab:");
		    driver.findElement(By.xpath("//div[@id='page']/div/div/div/div/ol/li[2]/a/div/span")).click();
		    Thread.sleep(5000);
		    logger.info("click the add new pipeline:");
		    driver.findElement(By.linkText("Add new pipeline")).click();
		    Thread.sleep(5000);
		    logger.info("choose hook type default:");
		    Thread.sleep(5000);
		    driver.findElement(By.id("type-pipeline")).click();
		    Thread.sleep(5000);
		    
		   
		    logger.info("input the pipeline name push-to-registry :");
		    driver.findElement(By.id("pipelineName")).clear();
		    driver.findElement(By.id("pipelineName")).sendKeys("push-to-registry");
		    Thread.sleep(10000);
		    
		    logger.info("input the YML pipeline name push-to-registry:");
		    driver.findElement(By.id("ymlPipeline")).clear();
		    driver.findElement(By.id("ymlPipeline")).sendKeys("push-to-registry");
		    Thread.sleep(10000);
		    logger.info("click create pipeline button:");
		    driver.findElement(By.xpath("//div[@id='page']/div/div[2]/div/div/div/div/form/div[4]/button/span[2]")).click();
		    Thread.sleep(10000);
//		    if (driver.findElements(By.xpath(".//*[@id='toaster-container']/div[1]/div[1]/div[1]/a")).size() != 0) { 
//		    	driver.findElement(By.xpath(".//*[@id='toaster-container']/div[1]/div[1]/div[1]/a")).click();
//		    }
		    

		    Thread.sleep(10000);
		    logger.info("click the workflows tab:");
		    driver.findElement(By.xpath("//div[@id='page']/div/div/div/div/ol/li[2]/a/div")).click();
		    Thread.sleep(10000);
		    logger.info("click the add new pipeline:");
		    driver.findElement(By.linkText("Add new pipeline")).click();
		    Thread.sleep(10000);
		   
//		    logger.info("choose hook type default:");
//		    Thread.sleep(10000);
//		    driver.findElement(By.id("type-pipeline")).click();
//		    Thread.sleep(10000);
		    
		    logger.info("input the pipeline name provision-oke :");
		    driver.findElement(By.id("pipelineName")).clear();
		    driver.findElement(By.id("pipelineName")).sendKeys("provision-oke");
		    Thread.sleep(10000);
		    
		    logger.info("input the YML pipeline name provision-oke:");
		    driver.findElement(By.id("ymlPipeline")).clear();
		    driver.findElement(By.id("ymlPipeline")).sendKeys("provision-oke");
		    Thread.sleep(10000);
		    logger.info("click create pipeline button:");
		    driver.findElement(By.xpath("//div[@id='page']/div/div[2]/div/div/div/div/form/div[4]/button/span[2]")).click();
		    Thread.sleep(10000);
		    
		    logger.info("click the workflows tab:");
		    driver.findElement(By.xpath("//div[@id='page']/div/div/div/div/ol/li[2]/a/div/span")).click();
		    Thread.sleep(10000);
		    logger.info("click the add new pipeline:");
		    driver.findElement(By.linkText("Add new pipeline")).click();
		    Thread.sleep(10000);
//		    logger.info("choose hook type default:");
//		    Thread.sleep(10000);
//		    driver.findElement(By.id("type-pipeline")).click();
//		    Thread.sleep(10000);
		    
		    logger.info("input the pipeline name deploy-oke:");
		    driver.findElement(By.id("pipelineName")).clear();
		    driver.findElement(By.id("pipelineName")).sendKeys("deploy-oke");
		    Thread.sleep(10000);
		    
		    logger.info("input the YML pipeline name deploy-oke:");
		    driver.findElement(By.id("ymlPipeline")).clear();
		    driver.findElement(By.id("ymlPipeline")).sendKeys("deploy-oke");
		    Thread.sleep(10000);
		    logger.info("click create pipeline button:");
		    driver.findElement(By.xpath("//div[@id='page']/div/div[2]/div/div/div/div/form/div[4]/button/span[2]")).click();
		    Thread.sleep(10000);
		    
		    logger.info("click the workflows tab:");
		    driver.findElement(By.xpath("//div[@id='page']/div/div/div/div/ol/li[2]/a/div/span")).click();
		    Thread.sleep(10000);
		    logger.info("click the add new pipeline:");
		    driver.findElement(By.linkText("Add new pipeline")).click();
		    Thread.sleep(10000);
		    logger.info("choose hook type default:");
		    Thread.sleep(10000);
		    driver.findElement(By.id("type-pipeline")).click();
		    Thread.sleep(10000);
		    
		    logger.info("input the pipeline name test:");
		    driver.findElement(By.id("pipelineName")).clear();
		    driver.findElement(By.id("pipelineName")).sendKeys("test");
		    Thread.sleep(10000);
		    
		    logger.info("input the YML pipeline name test:");
		    driver.findElement(By.id("ymlPipeline")).clear();
		    driver.findElement(By.id("ymlPipeline")).sendKeys("test");
		    Thread.sleep(10000);
		    logger.info("click create pipeline button:");
		    driver.findElement(By.xpath("//div[@id='page']/div/div[2]/div/div/div/div/form/div[4]/button/span[2]")).click();
		    Thread.sleep(10000);
		    
		    
 	        logger.info("click the workflows tab:");
		    driver.findElement(By.xpath("//div[@id='page']/div/div/div/div/ol/li[2]/a/div/span")).click();
		    Thread.sleep(10000);
		    logger.info("click the add new pipeline:");
		    driver.findElement(By.linkText("Add new pipeline")).click();
		    Thread.sleep(10000);
		    logger.info("choose hook type default:");
		    Thread.sleep(10000);
		    driver.findElement(By.id("type-pipeline")).click();
		    Thread.sleep(10000);
		    
		    logger.info("input the pipeline name destroy-oke:");
		    driver.findElement(By.id("pipelineName")).clear();
		    driver.findElement(By.id("pipelineName")).sendKeys("destroy-oke");
		    Thread.sleep(10000);
		    
		    logger.info("input the YML pipeline name destroy-oke:");
		    driver.findElement(By.id("ymlPipeline")).clear();
		    driver.findElement(By.id("ymlPipeline")).sendKeys("destroy-oke");
		    Thread.sleep(10000);
		    logger.info("click create pipeline button:");
		    driver.findElement(By.xpath("//div[@id='page']/div/div[2]/div/div/div/div/form/div[4]/button/span[2]")).click();
		    Thread.sleep(10000);
		    
		    
		    
		    
		    logger.info("click the workflows tab:");
		    driver.findElement(By.xpath("//div[@id='page']/div/div/div/div/ol/li[2]/a/div/span")).click();
		    Thread.sleep(10000);
		    logger.info("add workflow push-to-registry:");
		    driver.findElement(By.xpath("//div[@id='page']/div/div[2]/div/div/div/div/div/div/div/div/div[3]/div/div/button")).click();
		    driver.findElement(By.id("pipeline")).click();
		    new Select(driver.findElement(By.id("pipeline"))).selectByVisibleText("push-to-registry");
		    driver.findElement(By.id("pipeline")).click();
		    driver.findElement(By.xpath("//button/span[2]")).click();
		    Thread.sleep(10000);
		    logger.info("add wrokflow provision-oke:");
		    driver.findElement(By.xpath("//div[@id='page']/div/div[2]/div/div/div/div/div/div/div/div/div[5]/div/div/button")).click();
		    driver.findElement(By.id("pipeline")).click();
		    new Select(driver.findElement(By.id("pipeline"))).selectByVisibleText("provision-oke");
		    driver.findElement(By.id("pipeline")).click();
		    driver.findElement(By.xpath("//button/span[2]")).click();
		    Thread.sleep(8000);
		    
		    logger.info("add wrokflow deploy-oke:");
		    driver.findElement(By.xpath("//div[@id='page']/div/div[2]/div/div/div/div/div/div/div/div/div[7]/div/div/button")).click();
		    driver.findElement(By.id("pipeline")).click();
		    new Select(driver.findElement(By.id("pipeline"))).selectByVisibleText("deploy-oke");
		    driver.findElement(By.id("pipeline")).click();
		    driver.findElement(By.xpath("//button/span[2]")).click();
		    Thread.sleep(8000);
		    
		    logger.info("add wrokflow test:");
		    driver.findElement(By.xpath("//div[@id='page']/div/div[2]/div/div/div/div/div/div/div/div/div[9]/div/div/button")).click();
		    driver.findElement(By.id("pipeline")).click();
		    new Select(driver.findElement(By.id("pipeline"))).selectByVisibleText("test");
		    driver.findElement(By.id("pipeline")).click();
		    driver.findElement(By.xpath("//button/span[2]")).click();
		    
		    Thread.sleep(8000);
		    
		    
//		    logger.info("add wrokflow test:");
//		    driver.findElement(By.xpath("//div[@id='page']/div/div[2]/div/div/div/div/div/div/div/div/div[11]/div/div/button")).click();
//		    driver.findElement(By.id("pipeline")).click();
//		    new Select(driver.findElement(By.id("pipeline"))).selectByVisibleText("destroy-oke");
//		    driver.findElement(By.id("pipeline")).click();
//		    driver.findElement(By.xpath("//button/span[2]")).click();
		    
		    
		        
		    Thread.sleep(8000);
		    logger.info("assert: " + verify); 	
			assertVerify.verifyTrue(verify);
			success();
		}catch (Exception e) {
	    	new UnExpectedException(driver, logger, e);
	    	fail();
	    }
		finally{
			driver.quit();

		}
	}

}

	