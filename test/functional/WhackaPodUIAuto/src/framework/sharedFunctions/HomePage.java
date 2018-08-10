package framework.sharedFunctions;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import framework.global.Configuration;
import framework.objects.*;

/**
* Date : 2017-07-26
* Owner : lixia.yuan
* @author lixia.yuan
*/

public class HomePage extends CommonVariables {
	
	public String k8sServiceStatusXPath = "html/body/div[1]/div[1]";
	public String k8sService = "html/body/div[1]/div[1]";
	public String startModalID = "start-modal";
	public String deployStartID = "deploy-start";
	
	public String podIDprefix = "pod-";
	
	public String nextReportMessageID = "report_message";
	
	
	WindowLink wl;
	public HomePage (){
		wl = new WindowLink();
	}
	
	public void openHomePage() throws IOException,InterruptedException {  
		ibrowser.Start_Browser();
	}
	public void openAppPage() throws IOException,InterruptedException {  
		ibrowser.Start_Browser2();
	} 
	public boolean isStartModelDisplayed() throws IOException,InterruptedException {  
		return driver.findElement(By.id(startModalID)).isDisplayed();
	} 
	
//	public boolean isStartModelDisplayed() throws IOException,InterruptedException {  
//		return driver.findElement(By.id(startModalID)).isDisplayed();
//	} 
	
	public String getK8sServiceStatus() throws IOException,InterruptedException {  
		String k8sServiceStatus = driver.findElement(By.xpath(k8sServiceStatusXPath)).getText();
		logger.info("k8sServiceStatus" + k8sServiceStatus); 		
		return k8sServiceStatus;
	} 
	
	public boolean isK8sServiceNotStarted() throws IOException,InterruptedException {  
		return getK8sServiceStatus().contains("Kubernetes service not started yet");
	} 
	
	public boolean isK8sServiceStarting() throws IOException,InterruptedException {  
		return getK8sServiceStatus().contains("Kubernetes service starting up");
	} 
	
	public boolean isK8sServiceUp() throws IOException,InterruptedException {  
		return getK8sServiceStatus().contains("Kubernetes service is UP");
	} 
	
	public boolean isK8sServiceGone() throws IOException,InterruptedException {  
		return getK8sServiceStatus().contains("Kubernetes service went away");
	} 
	
	public boolean isK8sServiceDown() throws IOException,InterruptedException {  
		return getK8sServiceStatus().contains("Kubernetes service is DOWN");
	} 
	
	public boolean isNextK8sServiceDown() throws IOException,InterruptedException { 
		logger.info("color service: " + driver.findElement(By.id(nextReportMessageID)).getText()); 
		return driver.findElement(By.id(nextReportMessageID)).getText().contains("DOWN");
	} 
	
	
	public void clicktoDeploy() throws IOException,InterruptedException {  
		driver.findElement(By.id(deployStartID)).click();
	} 
	
	public String clorAPIRunning() throws IOException,InterruptedException {  
		logger.info("color api: " +  driver.findElement(By.xpath(k8sServiceStatusXPath)).getCssValue("color")); 
		return driver.findElement(By.xpath(k8sServiceStatusXPath)).getCssValue("color");
	} 
	
	public boolean isWhackaPod(int id) throws IOException,InterruptedException {
		boolean whacked = true;
		String podXpath = "//*[@id='pod-" + id +"']/div";
	//	logger.info("check pod is running or not: " + driver.findElement(By.xpath(podXpath)).getAttribute("class"));
		
		int i=0;
		while (!driver.findElement(By.xpath(podXpath)).getAttribute("class").contains("running") && i <= 30){
			logger.info("check pod is running or not: " + driver.findElement(By.xpath(podXpath)).getAttribute("class"));
			Thread.sleep(1000);	
			i++;
			if (i>30){
				logger.info("Pod Start Up Timeout in 30sec"); 	
				whacked = false;
				break;					
			}
		}
		logger.info("whack this pod");
		if (whacked && (Integer.parseInt(driver.findElement(By.className("timer")).getText()) > 4)){
			driver.findElement(By.xpath(podXpath)).click();
			logger.info("podStatus: " + driver.findElement(By.xpath(podXpath)).getAttribute("class"));
		}
		
		return whacked;
						
	}
		
	public void whackaPod(int id) throws IOException,InterruptedException {
		
		String podXpath = "//*[@id='pod-" + id +"']/div";
	//	logger.info("check pod is running or not: " + driver.findElement(By.xpath(podXpath)).getAttribute("class"));
		
		int i=0;
		while (!driver.findElement(By.xpath(podXpath)).getAttribute("class").contains("running") && i <= 4){
			logger.info("check pod is running or not: " + driver.findElement(By.xpath(podXpath)).getAttribute("class"));
			Thread.sleep(1000);	
			i++;
			if (i>4){
				logger.info("Pod Start Up Timeout in 5sec"); 	
				break;					
			}
		}
		
		logger.info("whack this pod");
		if (i <= 4 && Integer.parseInt(driver.findElement(By.className("timer")).getText()) > 4){
			driver.findElement(By.xpath(podXpath)).click();
			logger.info("podStatus: " + driver.findElement(By.xpath(podXpath)).getAttribute("class"));
		}
		
	} 
	
	
	public boolean isPodsDisplayed(int number) throws IOException,InterruptedException {
		String podXpath = "//*[@id='pod-" + number +"']/div";
		return driver.findElement(By.xpath(podXpath)).isDisplayed();
	} 
//	
//	public void goToVersionPanel(){
//		driver.findElement(By.id(versionId)).click();
//	}
//	
//	public void goToAboutUsPanel(){
//		driver.findElement(By.id(aboutUsId)).click();
//	}
////	
//	public String getOMSPversion(){
//		String version = driver.findElement(By.xpath(omspVerionXPath)).getText();
//		return version;
//	}
//	
//	public String getOracleMigversion(){
//		String version = driver.findElement(By.xpath(OracleMigVerionXPath)).getText();
//		return version;
//	}
//	
//	public String getMySQLMigversion(){
//		String version = driver.findElement(By.xpath(MySQLMigVerionXPath)).getText();
//		return version;
//	}
////	
////	public String getAboutUsContent(){
////		String version = driver.findElement(By.id(aboutUsContentId)).getText();
////		return version;
////	}
//	
//	public void closeAbout(){
//		driver.findElement(By.id(aboutDoneId)).click();
//	}
}
