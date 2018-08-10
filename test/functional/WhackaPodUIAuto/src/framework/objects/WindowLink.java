package framework.objects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import exception.ElementNotAvailableException;
import exception.UnExpectedException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
* Date : 2017-04-27
* Owner : yuhong.ma
* @author yuhong.ma
*/

public class WindowLink extends CommonVariables {
	
	//Left menus' links ids under Settings
    //hui
    public String AboutId = "ui-id-21";
    public String MigrateDB = "ui-id-19";
    
	public String AboutLTFSLELinkId = "pt1:dc0:np1:0:cni1";
	public String JobStatusLinkId = "pt1:dc0:np1:1:cni1";
	public String SystemMaintenanceScheduleLinkId = "pt1:dc0:np1:2:cni1";
	public String StorageMaintenanceScheduleLinkId = "pt1:dc0:np1:3:cni1";
	public String GlobalHardwareSettingsLinkId = "pt1:dc0:np1:4:cni1";
	public String DeleteUnavailableHardwareLinkId = "pt1:dc0:np1:5:cni1";
	public String RDALogRollUpLinkId = "pt1:dc0:np1:6:cni1";
	public String UserManagementLinkId = "pt1:dc0:np1:7:cni1";
	public String EmailNotificationLinkId = "pt1:dc0:np1:8:cni1";
	public String ConfigureEmailServerLinkId = "pt1:dc0:np1:9:cni1";
	
	

	public WindowLink() {

	}

	// Click Dashboard Tab
	public void clickDashboard() {
		driver.findElement(By.id("pt1:np1:cni1::disclosureAnchor")).click();
		driver.manage().timeouts().implicitlyWait(31, TimeUnit.SECONDS);
	}

	// Click Storage Tab
	public void clickStorage() {
		driver.findElement(By.id("pt1:np1:cni2::disclosureAnchor")).click();
		driver.manage().timeouts().implicitlyWait(31, TimeUnit.SECONDS);
		logger.info("click storage tab");

	}

	// Click Settings Tab
	public void clickSettings() {
		driver.findElement(By.id("pt1:np1:cni11::disclosureAnchor")).click();
		driver.manage().timeouts().implicitlyWait(31, TimeUnit.SECONDS);

	}
	
	// Click About link
		public void clickAbout() {
			driver.findElement(By.id("ui-id-21")).click();
			driver.manage().timeouts().implicitlyWait(31, TimeUnit.SECONDS);

		}
		
	// Click MigrateDB link
		public void clickMigrateDB() {
			driver.findElement(By.id("ui-id-19")).click();
			driver.manage().timeouts().implicitlyWait(31, TimeUnit.SECONDS);

		}
	
	/**
	 * links under Storage tab
	 */
	
	//Expand System Hardware panel under Storage
	public void expandSystemHardwarePanel() {
		if(driver.findElement(By.id("pt1:dc0:sdi1::disAcr")).getAttribute("title").compareTo("Show this panel") == 0 )
		{
			driver.findElement(By.id("pt1:dc0:sdi1::disAcr")).click();
			driver.manage().timeouts().implicitlyWait(31, TimeUnit.SECONDS);
			logger.info("System Hardware panel is expanded.");
		}
		else
		{
			logger.info("System Hardware panel is already expanded.");
		}
	}

	//Expand Tape Drives under Storage
	public void expandTapeDrivePanel() {
		if(driver.findElement(By.id("pt1:dc0:sdi2::disAcr")).getAttribute("aria-expanded").compareTo("false") == 0)
		{
			driver.findElement(By.id("pt1:dc0:sdi2::disAcr")).click();
			driver.manage().timeouts().implicitlyWait(31, TimeUnit.SECONDS);
			logger.info("Tape Drives panel is expanded.");
		}
		else
		{
			logger.info("Tape Drives panel is already expanded.");
		}
	}

	// Expand Tape Volumes panel under Storage
	public void expandTapeVolumePanel() {
		if(driver.findElement(By.id("pt1:dc0:sdi12::disAcr")).getAttribute("title").compareTo("Show this panel") == 0)
		{
			driver.findElement(By.id("pt1:dc0:sdi12::disAcr")).click();
			driver.manage().timeouts().implicitlyWait(31, TimeUnit.SECONDS);
			logger.info("Tape Volumes panel is expanded.");
		}
		else
		{
			logger.info("Tape Volumes panel is already expanded.");
		}
	}
	
	/**
	 * click all type drives tab under Tape Drives
	 * 
	 * @author jacky
	 */
	public void clickAllTypeDrives() {
		// TODO Auto-generated method stub
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("pt1:dc0:np4:0:cni2")));
		driver.findElement(By.id("pt1:dc0:np4:0:cni2")).click();
		logger.info("click all type drives");
		
//		for(int i = 0; i < 3; i++) {
//			if(driver.findElement(By.id("pt1:dc0:np4:0:cni2")).isEnabled()) {
//				driver.findElement(By.id("pt1:dc0:np4:0:cni2")).click();
//				logger.info("click all type drives");
//				break;
//			}else {
//				logger.warn("the all type drives link is disable, try it again");
//				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//			}
//		}		
		
//		logger.error("the all type drives link is disable throw a exception");
//		new ElementNotAvailableException(driver, logger, new Exception());  
	}

	// mary created. Click Default Drive Pool under Tape Drives
	public void clickDefaultDrivePool() throws StaleElementReferenceException, Exception {
//		new WebDriverWait(driver, 32).until(ExpectedConditions.presenceOfElementLocated(By.id("pt1:dc0:np4:1:cni2")));
//		driver.findElement(By.id("pt1:dc0:np4:1:cni2")).click();
//		logger.info("Click Default Drive Pool under Tape Drives.");
		
		for(int i = 0; i < 3; i++) {
			try
			{
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("pt1:dc0:np4:1:cni2")));
				if(driver.findElement(By.id("pt1:dc0:np4:1:cni2")).isEnabled()) 
				{
					driver.findElement(By.id("pt1:dc0:np4:1:cni2")).click();
					logger.info("Click Default Drive Pool under Tape Drives.");
					break;
				}
				else 
				{
					logger.warn("The Default Drive Pool link is disable, try it again");
					Thread.sleep(2000);
				}
			}			
			catch(StaleElementReferenceException e) 
			{ 
				e.toString(); 
				logger.warn("Trying to recover from a stale element :" + e.getMessage()); 
			}
		}
//		driver.findElement(By.id("pt1:dc0:np4:1:cni2")).click();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		logger.info("Click Default Drive Pool under Tape Drives.");
	}

	// mary created. Click Unassigned under Tape Drives
	public void clickUnassignedDrive() throws Exception{
//		new WebDriverWait(driver, 25).until(ExpectedConditions.presenceOfElementLocated(By.id("pt1:dc0:np4:2:cni2")));
//		driver.findElement(By.id("pt1:dc0:np4:2:cni2")).click();
//		logger.info("Click Unassigned under Tape Drives.");
		
		for(int i = 0; i < 3; i++) {
			try{
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("pt1:dc0:np4:2:cni2")));
				if(driver.findElement(By.id("pt1:dc0:np4:2:cni2")).isEnabled()) {
					driver.findElement(By.id("pt1:dc0:np4:2:cni2")).click();
					logger.info("Click Unassigned under Tape Drives.");
					break;
				}else {
					logger.warn("The Unassigned link is disable, try it again");
					Thread.sleep(2000);
				}
			}
			catch(StaleElementReferenceException e) 
			{ 
				e.toString(); 
				logger.warn("Trying to recover from a stale element :" + e.getMessage()); 
			}
			
		}
//		driver.findElement(By.id("pt1:dc0:np4:2:cni2")).click();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		logger.info("Click Unassigned under Tape Drives.");
	}
	
	//Click Tape Library under System Hardware
	public void clickTapeLibrary() throws Exception{
//		new WebDriverWait(driver, 25).until(ExpectedConditions.presenceOfElementLocated(By.id("pt1:dc0:np1:0:cni1")));
//		driver.findElement(By.id("pt1:dc0:np1:0:cni1")).click();
//		logger.info("Click Tape Library under System Hardware.");
		
		for(int i = 0; i < 3; i++) {
			try
			{
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("pt1:dc0:np1:0:cni1")));
				if(driver.findElement(By.id("pt1:dc0:np1:0:cni1")).isEnabled()) {
					driver.findElement(By.id("pt1:dc0:np1:0:cni1")).click();
					logger.info("Click Tape Library under System Hardware.");
					break;
				}else {
					logger.warn("The Tape Library link is disable, try it again");
					Thread.sleep(2000);
				}
			}
			
			catch(StaleElementReferenceException e) 
			{ 
				e.toString(); 
				logger.warn("Trying to recover from a stale element :" + e.getMessage()); 
			}
		}
//		driver.findElement(By.id("pt1:dc0:np1:0:cni1")).click();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
	}
	
	//Click Disk Cache under System Hardware
	public void clickDiskCache() throws Exception{
//		new WebDriverWait(driver, 25).until(ExpectedConditions.presenceOfElementLocated(By.id("pt1:dc0:np1:1:cni1")));
//		driver.findElement(By.id("pt1:dc0:np1:1:cni1")).click();
//		logger.info("Click Disk Cache under System Hardware.");
		
		for(int i = 0; i < 3; i++) {
			try
			{
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("pt1:dc0:np1:1:cni1")));
				if(driver.findElement(By.id("pt1:dc0:np1:1:cni1")).isEnabled()) {
					driver.findElement(By.id("pt1:dc0:np1:1:cni1")).click();
					logger.info("Click Disk Cache under System Hardware.");
					break;
				}else {
					logger.warn("The Disk Cache link is disable, try it again");
					Thread.sleep(2000);
				}
			}
			catch(StaleElementReferenceException e) 
			{ 
				e.toString(); 
				logger.warn("Trying to recover from a stale element :" + e.getMessage()); 
			}			
		}
//		driver.findElement(By.id("pt1:dc0:np1:1:cni1")).click();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}	
	
	//Click All Tape Volumes under Tape Volumes
	public void clickAllTapeVolumes() throws Exception{
//		new WebDriverWait(driver, 25).until(ExpectedConditions.presenceOfElementLocated(By.id("pt1:dc0:np5:0:cni3")));
//		driver.findElement(By.id("pt1:dc0:np5:0:cni3")).click();
//		logger.info("Click All Tape Volumes under Tape Volumes.");
		
		for(int i = 0; i < 3; i++) {
			try
			{
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("pt1:dc0:np5:0:cni3")));
				if(driver.findElement(By.id("pt1:dc0:np5:0:cni3")).isEnabled()) {
					driver.findElement(By.id("pt1:dc0:np5:0:cni3")).click();
					logger.info("Click All Tape Volumes under Tape Volumes.");
					break;
				}else {
					logger.warn("The All Tape Volumes link is disable, try it again");
					Thread.sleep(2000);
				}
			}
			catch(StaleElementReferenceException e) 
			{ 
				e.toString(); 
				logger.warn("Trying to recover from a stale element :" + e.getMessage()); 
			}
		}
//		driver.findElement(By.id("pt1:dc0:np5:0:cni3")).click();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	//Click Data Pool under Tape Volumes
	public void clickDataPool() throws Exception{
//		new WebDriverWait(driver, 25).until(ExpectedConditions.presenceOfElementLocated(By.id("pt1:dc0:np1:1:cni1")));
//		driver.findElement(By.id("pt1:dc0:np1:1:cni1")).click();
//		logger.info("Click Data Pool under Tape Volumes.");
		
		for(int i = 0; i < 3; i++) {
			try
			{
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("pt1:dc0:np5:1:cni3")));
				if(driver.findElement(By.id("pt1:dc0:np5:1:cni3")).isEnabled()) {
					driver.findElement(By.id("pt1:dc0:np5:1:cni3")).click();
					logger.info("Click Data Pool under Tape Volumes.");
					break;
				}else {
					logger.warn("The Data Pool link is disable, try it again");
					Thread.sleep(2000);
				}
			}
			catch(StaleElementReferenceException e) 
			{ 
				e.toString(); 
				logger.warn("Trying to recover from a stale element :" + e.getMessage()); 
			}
		}
//		driver.findElement(By.id("pt1:dc0:np1:1:cni1")).click();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}	
	
	//Click Import Pool under Tape Volume
	public void clickImportPool() throws Exception{
//		new WebDriverWait(driver, 25).until(ExpectedConditions.presenceOfElementLocated(By.id("pt1:dc0:np5:2:cni3")));
//		driver.findElement(By.id("pt1:dc0:np5:2:cni3")).click();
//		logger.info("Click Import Pool under Tape Volume.");
		
		for(int i = 0; i < 3; i++) {
			try
			{
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("pt1:dc0:np5:2:cni3")));
				if(driver.findElement(By.id("pt1:dc0:np5:2:cni3")).isEnabled()) {
					driver.findElement(By.id("pt1:dc0:np5:2:cni3")).click();
					logger.info("Click Import Pool under Tape Volume.");
					break;
				}else {
					logger.warn("The Import Pool link is disable, try it again");
					Thread.sleep(2000);
				}
			}
			catch(StaleElementReferenceException e) 
			{ 
				e.toString(); 
				logger.warn("Trying to recover from a stale element :" + e.getMessage()); 
			}
		}
//		driver.findElement(By.id("pt1:dc0:np5:2:cni3")).click();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}	
	
	//click Export Pool under Tape Volume
	public void clickExportPool() throws Exception{
//		new WebDriverWait(driver, 25).until(ExpectedConditions.presenceOfElementLocated(By.id("pt1:dc0:np5:3:cni3")));
//		driver.findElement(By.id("pt1:dc0:np5:3:cni3")).click();
//		logger.info("Click Export Pool under Tape Volume.");
		
		for(int i = 0; i < 3; i++) {
			try
			{
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("pt1:dc0:np5:3:cni3")));
				if(driver.findElement(By.id("pt1:dc0:np5:3:cni3")).isEnabled()) {
					driver.findElement(By.id("pt1:dc0:np5:3:cni3")).click();
					logger.info("Click Export Pool under Tape Volume.");
					break;
				}else {
					logger.warn("The Export Pool link is disable, try it again");
					Thread.sleep(2000);
				}
			}
			catch(StaleElementReferenceException e) 
			{ 
				e.toString(); 
				logger.warn("Trying to recover from a stale element :" + e.getMessage()); 
			}
		}
//		driver.findElement(By.id("pt1:dc0:np5:3:cni3")).click();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}	
	
	//Click Unassigned under Tape Volume
	public void clickUnassigned() throws Exception{
//		new WebDriverWait(driver, 25).until(ExpectedConditions.presenceOfElementLocated(By.id("pt1:dc0:np5:4:cni3")));
//		driver.findElement(By.id("pt1:dc0:np5:4:cni3")).click();
//		logger.info("Click Unassigned under Tape Volume.");
		
		for(int i = 0; i < 3; i++) {
			try
			{
				new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("pt1:dc0:np5:4:cni3")));
				if(driver.findElement(By.id("pt1:dc0:np5:4:cni3")).isEnabled()) {
					driver.findElement(By.id("pt1:dc0:np5:4:cni3")).click();
					logger.info("Click Unassigned under Tape Volume.");
					break;
				}else {
					logger.warn("The Unassigned link is disable, try it again");
					driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				}
			}
			catch(StaleElementReferenceException e) 
			{ 
				e.toString(); 
				logger.warn("Trying to recover from a stale element :" + e.getMessage()); 
			}
		}
//		driver.findElement(By.id("pt1:dc0:np5:4:cni3")).click();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}	

	/**
	 * links under Settings tab
	 */
	
	//Click About LTFS-LE under Settings
	public void clickAboutLTFSLE(){
		WebElement AboutLTFSLELink = driver.findElement(By.id(AboutLTFSLELinkId));
		AboutLTFSLELink.click();
	}
	
	//Click Job Status under Settings
	public void clickJobStatus(){
		WebElement JobStatusLink =	driver.findElement(By.id(JobStatusLinkId));
		JobStatusLink.click();
	}
	
	//Click System Maintenance Schedule under Settings
	public void clickSystemMaintenanceSchedule(){
		WebElement SystemMaintenanceScheduleLink =	driver.findElement(By.id(SystemMaintenanceScheduleLinkId));
		SystemMaintenanceScheduleLink.click();
	}
	
	//Click Storage Maintenance Schedule under Settings
	public void clickStorageMaintenanceSchedule(){
		WebElement StorageMaintenanceScheduleLink =	driver.findElement(By.id(StorageMaintenanceScheduleLinkId));
		StorageMaintenanceScheduleLink.click();
	}

	//Click Global Hardware Settings under Settings
	public void clickGlobalHardwareSettings(){
		WebElement GlobalHardwareSettingsLink =	driver.findElement(By.id(GlobalHardwareSettingsLinkId));
		GlobalHardwareSettingsLink.click();
	}
	
	//Click Delete Unavailable Hardware under Settings
	public void clickDeleteUnavailableHardware(){
		WebElement DeleteUnavailableHardwareLink =	driver.findElement(By.id(DeleteUnavailableHardwareLinkId));
		DeleteUnavailableHardwareLink.click();
	}
	
	//Click RDA Log RollUp under Settings
	public void clickRDALogRollUp(){
		WebElement RDALogRollUpLink =	driver.findElement(By.id(RDALogRollUpLinkId));
		RDALogRollUpLink.click();
	}
	
	//Click User Management under Settings
	public void clickUserManagement(){
		WebElement UserManagementLink =	driver.findElement(By.id(UserManagementLinkId));
		UserManagementLink.click();
	}
	
	//Click Email Notification under Settings
	public void clickEmailNotification(){
		WebElement EmailNotificationLink =	driver.findElement(By.id(EmailNotificationLinkId));
		EmailNotificationLink.click();
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//Click Configure Email Server under Settings
	public void ClickConfigureEmailServer(){
		WebElement ConfigureEmailServerLink =	driver.findElement(By.id(ConfigureEmailServerLinkId));
		ConfigureEmailServerLink.click();
	}
	
	public void clickDrives() {
		if (driver.findElement(By.id("pt1:np3:cni6")).isEnabled()) {
			driver.findElement(By.id("pt1:np3:cni6")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} else {
			logger.info("The Drives Link is disabled.");
		}

	}

	public void clickVolumes() {
		if (driver.findElement(By.id("pt1:np3:cni7")).isEnabled()) {
			driver.findElement(By.id("pt1:np3:cni7")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} else {

			logger.info("The Volumes Link is disabled.");
		}
	}

	public void clickLogout() {
		if (driver.findElement(By.id("pt1:cl1")).isEnabled()) {
			driver.findElement(By.id("pt1:cl1")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} else {

			logger.info("The Logout Link is disabled.");
		}
	}

	// Click Settings Tab
	public void clickVolumePoolSettings() {
		driver.findElement(By.id("pt1:navigationPane1:cni13")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}
}