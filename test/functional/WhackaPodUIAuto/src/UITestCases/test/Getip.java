package UITestCases.test;


import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
import java.io.PrintWriter;
import java.io.Writer;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

 

/**
* Date :2018.08.02
* Owner :  Betty.wang
* @author : Betty.wang
*/


public class Getip extends BaseTestCase{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Initialize oms=new Initialize();
        oms.init();
		Configuration.init();
		logger.info("try to get the ip:"); 			  
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
		
		
			logger.info("input the wercher username from file:");
			driver.findElement(By.id("username")).clear();
		    driver.findElement(By.id("username")).sendKeys(new String[] { p.getProperty("WERCKER_USERNAME") });
		    logger.info("input the wercher password from file:");
		    driver.findElement(By.id("password")).clear();
		    driver.findElement(By.id("password")).sendKeys(new String[] { p.getProperty("WERCKER_PASSWORD") });
		    logger.info("click the login wercher button:");
		    driver.findElement(By.id("login")).click();
		    
		    Thread.sleep(8000);
		    if (driver.findElements(By.xpath(".//*[@id='toaster-container']/div[1]/div[1]/div[1]/a")).size() != 0) { 
		    	driver.findElement(By.xpath(".//*[@id='toaster-container']/div[1]/div[1]/div[1]/a")).click();
		    }
		    Thread.sleep(5000);
		    logger.info("click the oke link:");
	
		    driver.findElement(By.xpath("(//span[contains(text(),'deploy-oke')]/parent::div/preceding-sibling::div/i[@class='icon-passed icon-color-success'])[1]")).click();
		   
		   Thread.sleep(5000);
		   driver.findElement(By.xpath("//div[@id='page']/div/div[2]/div/div/div/div/div[4]/div/div[4]/div/div/div")).click();
		   
		   logger.info("check the span:");
		    Thread.sleep(8000);
		    driver.findElement(By.xpath("//div[@id='page']/div/div[2]/div/div/div/div/div[4]/div/div[4]/div/div[2]/div")).click();
		   
		   
		   String path = ".//div[@id='page']/div/div[2]/div/div/div/div/div[4]/div/div[4]/div/div[2]/div/span";
		   
		   String span = driver.findElement(By.xpath(path)).getText();
		   String[] strs = span.split("Obtained External IP:");
		   String ipline =  strs[1];
		   System.out.println(ipline);
		   logger.info("get the IP");
		   String[]ip=ipline.split("\n");
		   String ip1 = ip[0].trim();
		   System.out.println(ip1);
		   String Url="http://"+ip1+":8080/hello";
		   System.out.println(Url);
		   logger.info("get the Url:");
		   Thread.sleep(5000);
		   
		   
		   logger.info("update the server config file:");
	   try {
		   String filepath2 = curDir + sysSeq + "config" + sysSeq + "ServerConfig.xml";
		   DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		   DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		   Document doc = docBuilder.parse(filepath2);

			// Get the staff element by tag name directly
		   Node server = doc.getElementsByTagName("server").item(0);


			// loop the server child node
			NodeList list = server.getChildNodes();

			for (int i = 0; i < list.getLength(); i++) {
					
		                 Node node = list.item(i);

			// get the UIAdvancedUrl element
			if ("UIAdvancedUrl".equals(node.getNodeName())) {
				node.setTextContent(Url);
				   }


				}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath2));
			transformer.transform(source, result);

			System.out.println("Done");

			 } catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			 } catch (TransformerException tfe) {
				tfe.printStackTrace();
			 } catch (IOException ioe) {
				ioe.printStackTrace();
			 } catch (SAXException sae) {
				sae.printStackTrace();
			 }
				
		    Thread.sleep(8000);
		    logger.info("assert: " + verify); 	
			assertVerify.verifyTrue(verify);
			success();
		}catch (Exception e) {
	    	new UnExpectedException(driver, logger, e);
	    	fail();
	    }
		finally{
//			driver.quit();

		}
	}

}

	